package com.hsel.monopoly.service;

import java.awt.Color;
import java.util.List;

import com.hsel.defaults.backend.Backendservice;
import com.hsel.monopoly.entities.Feld;
import com.hsel.monopoly.entities.Zufallskarte;

public interface MonopolyServices extends Backendservice {
	
	/**
	 * Alle lokal gebufferten Daten werden gelöscht.
	 */
	public void clearBuffer();

	/**
	 * gibt das Feld mit dem übergebenen Index zurück.
	 *
	 * @param id
	 * @return
	 */
	public Feld auslesenFelddaten(final int id);

	/**
	 * Gibt das erste Feld mit dem übergebenen Namen zurück. Nicht für
	 * Ereignisfelder oder Gemeinschaftsfelder geeignet.
	 *
	 * @param name
	 * @return
	 */
	public Feld auslesenFelddaten(final String name);

	/**
	 * Gibt alle Felder mit der übergebenen Farbe zurück. Bei Weiß werden
	 * Bahnhöfe UND Versorger zurückgegeben!
	 *
	 * @param farbe
	 * @return
	 */
	public List<Feld> getFelderGruppe(Color farbe);

	/**
	 * Gibt alle Felder der Gruppe zurück, zu der das übergebene Feld gehört.
	 *
	 * @param feld
	 * @return
	 */
	public List<Feld> getFelderGruppe(Feld feld);

	/**
	 * Gibt eine zufällige Ereigniskarte zurück.
	 *
	 * @return
	 */
	public Zufallskarte ziehenEreigniskarte();
	
	/**
	 * Gibt eine zufällige Gemeinschaftskarte zurück.
	 *
	 * @return
	 */
	public Zufallskarte ziehenGemeinschaftskarte();

}