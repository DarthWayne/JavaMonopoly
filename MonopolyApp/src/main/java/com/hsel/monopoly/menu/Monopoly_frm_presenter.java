package com.hsel.monopoly.menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import com.hsel.defaults.application.ApplicationContext;
import com.hsel.defaults.presenter.Presenter_with_Backend;
import com.hsel.monopoly.app.AppConfig;
import com.hsel.monopoly.game.Game_pnl_presenter;
import com.hsel.monopoly.game.ui.Game_pnl;
import com.hsel.monopoly.menu.ui.Monopoly_frm;
import com.hsel.monopoly.service.MonopolyServices;

public class Monopoly_frm_presenter extends Presenter_with_Backend {
	
	private SpielerErstellen_pnl_presenter preSpielerHinzufuegen;
	private Game_pnl_presenter preGame;

	public Monopoly_frm_presenter(final ApplicationContext appContext, final Container view,
			final MonopolyServices fassade) {
		super(appContext, view, fassade);
	}
	
	@Override
	public AppConfig getAppContext() {
		return (AppConfig) getObjAppContext();
	}
	
	@Override
	public Monopoly_frm getView() {
		return (Monopoly_frm) getObjViewContainer();
	}

	@Override
	public MonopolyServices getFassade() {
		return (MonopolyServices) getFassadeContainer();
	}
	
	@Override
	public void initialize() {
		getPreSpielerHinzufuegen().initialize();
		super.initialize();
	}
	
	@Override
	public void registerActions() {
		// Globaler Key Listener
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new GlobalDispatcher());

		// Main Menu Acitons
		getView().getPnlMainMenu().getBtnSpielBeenden().addActionListener(e -> getView().dispose());
		getView().getPnlMainMenu().getBtnNeuesSpiel().addActionListener(e -> erstellenSpieler());
		// getView().getPnlMainMenu().getBtnEinstellungen().addActionListener(e
		// -> einstellungen());

		// Spielererstellung
		getView().getPnlSpielerErstellen().getBtnSpielStarten()
				.addActionListener(e -> spielStarten());
		getView().getPnlSpielerErstellen().getBtnAbbrechen().addActionListener(e -> zumHauptmenu());

		// ESC Menu Actions
		getView().getBtnFortsetzen().addActionListener(e -> fortsetzen());
		getView().getBtnNeustart().addActionListener(e -> spielStarten());
		getView().getBtnHauptmenu().addActionListener(e -> zumHauptmenu());
		getView().getBtnSpielBeenden().addActionListener(e -> getView().dispose());

	}
	
	private void fortsetzen() {
		getView().getPnlOverlay().setVisible(false);
	}

	private void zumHauptmenu() {
		getView().getPnlOverlay().setVisible(false);
		getView().getPnlSpielerErstellen().setVisible(false);
		getView().getPnlContent().removeAll();
		getView().getPnlContent().add(getView().getPnlMainMenu());
		getView().getPnlContent().repaint();
		getView().getPnlContent().revalidate();
	}
	
	private void erstellenSpieler() {
		preGame = new Game_pnl_presenter(getAppContext(), new Game_pnl(), getFassade());
		preGame.initialize();
		getView().getPnlContent().removeAll();
		getView().getPnlContent().add(preGame.getView(), BorderLayout.CENTER);
		getView().getPnlContent().repaint();
		getView().getPnlContent().revalidate();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				getView().getPnlSpielerErstellen().setVisible(true);
			}
		});
	}

	private void spielStarten() {
		if (getPreSpielerHinzufuegen().getSpieler().size() > 1) {
			getView().getPnlOverlay().setVisible(false);
			getView().getPnlSpielerErstellen().setVisible(false);
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					preGame.startenSpiel(getPreSpielerHinzufuegen().getSpieler());
				}
			});
		}
	}
	
	private SpielerErstellen_pnl_presenter getPreSpielerHinzufuegen() {
		if (preSpielerHinzufuegen == null) {
			preSpielerHinzufuegen = new SpielerErstellen_pnl_presenter(getAppContext(),
					getView().getPnlSpielerErstellen());
		}
		return preSpielerHinzufuegen;
	}

	private class GlobalDispatcher implements KeyEventDispatcher {

		@Override
		public boolean dispatchKeyEvent(final KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_ESCAPE
					&& !getView().getPnlMainMenu().isShowing()
					&& !getView().getPnlSpielerErstellen().isShowing()) {
				getView().getPnlOverlay().setVisible(!getView().getPnlOverlay().isVisible());
				return true;
			}
			return false;
		}
	}
}