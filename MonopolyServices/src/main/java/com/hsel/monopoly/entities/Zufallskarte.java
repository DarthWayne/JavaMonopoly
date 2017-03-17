package com.hsel.monopoly.entities;

public class Zufallskarte  {

	private String text;
	private int geld;
	private Empfaenger empfaenger;
	private int zielfeld;
	private Richtung richtung;

	public int getZielfeld() {
		return zielfeld;
	}
	
	public void setZielfeld(final int zielfeld) {
		this.zielfeld = zielfeld;
	}
	
	public Richtung getRichtung() {
		return richtung;
	}
	
	public void setRichtung(final Richtung richtung) {
		this.richtung = richtung;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(final String text) {
		this.text = text;
	}
	
	public int getGeld() {
		return geld;
	}
	
	public void setGeld(final int geld) {
		this.geld = geld;
	}
	
	public Empfaenger getEmpfaenger() {
		return empfaenger;
	}
	
	public void setEmpfaenger(final Empfaenger empfaenger) {
		this.empfaenger = empfaenger;
	}
	
	public enum Richtung {
		VORWAERTS, RUECKWAERTS, DIREKT, KEINE;
	}

	public enum Empfaenger {
		SPIELER, BANK, ALLESPIELER, KEINER;
	}
}