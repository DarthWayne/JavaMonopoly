package com.hsel.monopoly.menu;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.hsel.defaults.presenter.Presenter;
import com.hsel.monopoly.app.AppConfig;
import com.hsel.monopoly.entities.Spieler;
import com.hsel.monopoly.menu.ui.SpielerErstellen_pnl;
import com.hsel.monopoly.menu.ui.SpielerItem_pnl;

public class SpielerErstellen_pnl_presenter extends Presenter {
	
	private List<Spieler> spieler;
	
	public SpielerErstellen_pnl_presenter(final AppConfig appContext,
			final SpielerErstellen_pnl view) {
		super(appContext, view);
	}
	
	@Override
	public AppConfig getAppContext() {
		return (AppConfig) getObjAppContext();
	}
	
	@Override
	public SpielerErstellen_pnl getView() {
		return (SpielerErstellen_pnl) getObjViewContainer();
	}

	@Override
	public void initialize() {
		super.initialize();
		getView().getCmbSpielfigur().addItem("Auto");
		getView().getCmbSpielfigur().addItem("Hund");
		getView().getCmbSpielfigur().addItem("Schiff");
		getView().getCmbSpielfigur().addItem("Schuh");
		getView().getCmbSpielfigur().addItem("Turm");
	}

	@Override
	public void registerActions() {
		getView().getTxfNeuerSpieler().addKeyListener(new ActionEnter());
		getView().getBtnAdd().addActionListener(e -> hinzufuegenSpieler());
	}
	
	private void hinzufuegenSpieler() {
		String name = getView().getTxfNeuerSpieler().getText();
		if (!name.isEmpty() && !name.equalsIgnoreCase("bank")) {
			Spieler s = new Spieler(name, getAppContext().getStartgeld());
			// TODO Geht sauberer
			String selection = getView().getCmbSpielfigur().getSelectedItem().toString();
			s.setIcon(new ImageIcon(getClass()
					.getResource("/Spielfiguren/spielfigur_" + selection.toLowerCase() + ".png")));
			getView().getCmbSpielfigur().removeItem(selection);

			SpielerItem_pnl item = new SpielerItem_pnl();
			item.getLblSpielername().setText(s.getName());
			Image img = s.getIcon().getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
			item.getLblSpielfigur().setIcon(new ImageIcon(img));
			item.getBtnX().addActionListener(e -> enfernenSpieler(item, s));
			getView().getPnlSpieler().add(item);
			getView().getPnlSpieler().repaint();
			getView().getPnlSpieler().revalidate();
			getSpieler().add(s);
			getView().getTxfNeuerSpieler().setText("");
		}
		getView().getTxfNeuerSpieler().requestFocus();
	}
	
	private void enfernenSpieler(final SpielerItem_pnl item, final Spieler s) {
		getView().getPnlSpieler().remove(item);
		getView().getPnlSpieler().repaint();
		getView().getPnlSpieler().revalidate();
		getSpieler().remove(s);
	}

	public List<Spieler> getSpieler() {
		if (spieler == null) {
			spieler = new ArrayList<Spieler>();
		}
		return spieler;
	}
	
	private class ActionEnter extends KeyAdapter {

		@Override
		public void keyReleased(final KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				hinzufuegenSpieler();
			}
		}
	}
}