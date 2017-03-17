package com.hsel.monopoly.app;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.hsel.defaults.application.ApplicationContext;

public class AppConfig extends ApplicationContext {

	public static final String feldnameStart = "Start";
	public static final String feldnameFreiParken = "Frei parken";
	public static final String feldnameGefaengnis = "Gefängnis";
	public static final String feldnameGeheInsGefaengnis = "Gehe ins Gefängnis";
	public static final Color farbeFeldgrenzen = Color.DARK_GRAY;
	public static final Color farbeHintergrund = Color.WHITE;
	public static final Color farbeSpielfeld = new Color(182, 211, 189);
	private List<Image> spielfiguren;
	private int startgeld;
	private int rundengeld;
	
	public AppConfig() {
	}

	public int getStartgeld() {
		return startgeld;
	}

	public void setStartgeld(final int startgeld) {
		this.startgeld = startgeld;
	}

	public int getRundengeld() {
		return rundengeld;
	}

	public void setRundengeld(final int rundengeld) {
		this.rundengeld = rundengeld;
	}
	
	public List<Image> getSpielfiguren() {
		if (spielfiguren == null) {
			spielfiguren = new ArrayList<Image>();
		}
		return spielfiguren;
	}
}
