package com.hsel.monopoly.service;

import java.awt.Color;
import java.util.List;

import com.hsel.defaults.backend.Backendservice;
import com.hsel.monopoly.entities.Feld;
import com.hsel.monopoly.entities.Zufallskarte;

public interface MonopolyServices extends Backendservice {
	
	/**
	 * Alle lokal gebufferten Daten werden gel�scht.
	 */
	public void clearBuffer();

	/**
	 * gibt das Feld mit dem �bergebenen Index zur�ck.
	 *
	 * @param id
	 * @return
	 */
	public Feld auslesenFelddaten(final int id);

	/**
	 * Gibt das erste Feld mit dem �bergebenen Namen zur�ck. Nicht f�r
	 * Ereignisfelder oder Gemeinschaftsfelder geeignet.
	 *
	 * @param name
	 * @return
	 */
	public Feld auslesenFelddaten(final String name);

	/**
	 * Gibt alle Felder mit der �bergebenen Farbe zur�ck. Bei Wei� werden
	 * Bahnh�fe UND Versorger zur�ckgegeben!
	 *
	 * @param farbe
	 * @return
	 */
	public List<Feld> getFelderGruppe(Color farbe);

	/**
	 * Gibt alle Felder der Gruppe zur�ck, zu der das �bergebene Feld geh�rt.
	 *
	 * @param feld
	 * @return
	 */
	public List<Feld> getFelderGruppe(Feld feld);

	/**
	 * Gibt eine zuf�llige Ereigniskarte zur�ck.
	 *
	 * @return
	 */
	public Zufallskarte ziehenEreigniskarte();
	
	/**
	 * Gibt eine zuf�llige Gemeinschaftskarte zur�ck.
	 *
	 * @return
	 */
	public Zufallskarte ziehenGemeinschaftskarte();

}