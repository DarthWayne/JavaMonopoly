package com.hsel.monopoly.app;

import java.util.ArrayList;
import java.util.List;

import com.hsel.monopoly.entities.Spieler;

public class Game {

	private static Spieler bank;
	private static Spieler aktuellerSpieler;
	private static List<Spieler> spieler;

	public static Spieler getBank() {
		if (bank == null) {
			bank = new Spieler("Bank");
		}
		return bank;
	}
	
	public static void setBank(final Spieler bank) {
		Game.bank = bank;
	}
	
	public static Spieler getAktuellerSpieler() {
		return aktuellerSpieler;
	}
	
	public static void setAktuellerSpieler(final Spieler aktuellerSpieler) {
		Game.aktuellerSpieler = aktuellerSpieler;
	}
	
	public static List<Spieler> getSpieler() {
		if (spieler == null) {
			spieler = new ArrayList<Spieler>();
		}
		return spieler;
	}
	
	public static void setSpieler(final List<Spieler> spieler) {
		Game.spieler = spieler;
	}

	public static void naechsterSpieler() {
		if (getAktuellerSpieler() == null) {
			setAktuellerSpieler(getSpieler().get(0));
		}
		else {
			int index = getSpieler().indexOf(getAktuellerSpieler());
			if (index < getSpieler().size() - 1) {
				setAktuellerSpieler(getSpieler().get(index + 1));
			}
			else {
				setAktuellerSpieler(getSpieler().get(0));
			}
		}
	}

	public static void sleep(final int millis) {
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}