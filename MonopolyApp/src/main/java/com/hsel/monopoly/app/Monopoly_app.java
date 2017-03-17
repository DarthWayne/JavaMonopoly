package com.hsel.monopoly.app;

import javax.swing.ToolTipManager;

import com.hsel.defaults.application.Application;
import com.hsel.monopoly.connector.FassadeFactory;
import com.hsel.monopoly.menu.Monopoly_frm_presenter;
import com.hsel.monopoly.menu.ui.Monopoly_frm;

public class Monopoly_app extends Application {

	private Monopoly_frm_presenter preMonopoly;

	public static void main(final String[] args) {
		Monopoly_app monopoly = new Monopoly_app();
		monopoly.initialize(args);
	}

	@Override
	public void startup(final String[] args) {
		ToolTipManager.sharedInstance().setDismissDelay(100000);

		AppConfig appContext = new AppConfig();
		appContext.setRundengeld(4000);
		appContext.setStartgeld(30000);
		// appContext.getSpielfiguren().add(getSpielfigur("/Spielfiguren/spielfigur_turm.png"));
		// appContext.getSpielfiguren().add(getSpielfigur("/Spielfiguren/spielfigur_schuh.png"));
		// appContext.getSpielfiguren().add(getSpielfigur("/Spielfiguren/spielfigur_auto.png"));
		// appContext.getSpielfiguren().add(getSpielfigur("/Spielfiguren/spielfigur_schiff.png"));
		// appContext.getSpielfiguren().add(getSpielfigur("/Spielfiguren/spielfigur_hund.png"));

		preMonopoly = new Monopoly_frm_presenter(appContext, new Monopoly_frm(),
				FassadeFactory.getInstance().getMonopolyService());
		preMonopoly.initialize();
		preMonopoly.setVisible(true);
	}

	// private Image getSpielfigur(final String res) {
	// ImageIcon img = new ImageIcon(getClass().getResource(res));
	// return img.getImage();
	// }
}