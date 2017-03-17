package com.hsel.monopoly.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

public class Spieler extends Observable {

	private static Map<Spieler, Observer> observers = new HashMap<Spieler, Observer>();
	private static int spielernummer = 0;
	private int id;
	private String name;
	private ImageIcon icon;
	private int geld;
	private List<Feld> lisGrundstueke;
	private int aktuellesFeld;
	private boolean imGefaengnis;
	private int rundenImGefaengnis = 0;

	public Spieler(final int startgeld) {
		spielernummer++;
		id = spielernummer;
		setName("Spieler " + spielernummer);
		setGeld(startgeld);
	}

	public Spieler(final String name) {
		spielernummer++;
		id = spielernummer;
		setName(name);
	}

	public Spieler(final String name, final int startgeld) {
		spielernummer++;
		id = spielernummer;
		setName(name);
		setGeld(startgeld);
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<Feld> getLisGrundstueke() {
		if (lisGrundstueke == null) {
			lisGrundstueke = new ArrayList<Feld>();
		}
		return lisGrundstueke;
	}
	
	/**
	 * Von extern die Methoden {@link #hinzufuegenGeld(int)} und
	 * {@link #entfernenGeld(int)} verwenden.
	 *
	 * @param geld
	 */
	private void setGeld(final int geld) {
		this.geld = geld;
	}

	public int getGeld() {
		return geld;
	}
	
	public boolean isImGefaengnis() {
		return imGefaengnis;
	}
	
	public void setImGefaengnis(final boolean imGefaengnis) {
		this.imGefaengnis = imGefaengnis;
		notifyObservers(this, imGefaengnis);
	}

	public void setRundenImGefaengnis(final int imGefaengnisSeit) {
		this.rundenImGefaengnis = imGefaengnisSeit;
		notifyObservers(this, imGefaengnisSeit);
	}

	public int getRundenImGefaengnis() {
		return rundenImGefaengnis;
	}

	public ImageIcon getIcon() {
		if (icon == null) {
			icon = new ImageIcon(Spieler.class.getResource("/Spielfiguren/spielfigur.png"));
		}
		return icon;
	}

	public void setIcon(final ImageIcon icon) {
		this.icon = icon;
		notifyObservers(this, icon);
	}

	public int getAktuellesFeld() {
		return aktuellesFeld;
	}

	public void setAktuellesFeld(final int aktuellesfeld) {
		this.aktuellesFeld = aktuellesfeld;
		notifyObservers(this, aktuellesfeld);
	}

	public void hinzufuegenGeld(final int geld) {
		setGeld(getGeld() + geld);
		notifyObservers(this, getGeld());
	}

	public void entfernenGeld(final int geld) {
		setGeld(getGeld() - geld);
		notifyObservers(this, getGeld());
	}

	@Override
	public void notifyObservers(final Object obj) {
		// auf das statische Observer Framework umleiten
		notifyObservers(this, obj);
	}

	public static void addObserver(final Spieler spieler, final Observer o) {
		observers.put(spieler, o);
	}

	public static void notifyObservers(final Spieler spieler, final Object value) {
		if (observers.containsKey(spieler)) {
			observers.get(spieler).update(spieler, value);
		}
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Spieler other = (Spieler) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}