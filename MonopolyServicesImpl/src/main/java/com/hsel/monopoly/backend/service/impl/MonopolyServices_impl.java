package com.hsel.monopoly.backend.service.impl;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.hsel.monopoly.entities.Feld;
import com.hsel.monopoly.entities.Feld.Feldfarbe;
import com.hsel.monopoly.entities.Feld.Feldtyp;
import com.hsel.monopoly.entities.Zufallskarte;
import com.hsel.monopoly.entities.Zufallskarte.Empfaenger;
import com.hsel.monopoly.entities.Zufallskarte.Richtung;
import com.hsel.monopoly.service.MonopolyServices;

public class MonopolyServices_impl implements MonopolyServices {

	private List<Feld> felder;
	private List<Zufallskarte> gemeinschaftskarten;
	private List<Zufallskarte> ereigniskarten;

	private static final DecimalFormat dc00 = new DecimalFormat("00");

	public MonopolyServices_impl() {
		clearBuffer();
	}
	
	@Override
	public void clearBuffer() {
		felder = auslesenAlleFelder();
		gemeinschaftskarten = auslesenZufallskarten("/Gemeinschaftskarten/Gemeinschaftskarte");
		ereigniskarten = auslesenZufallskarten("/Ereigniskarten/Ereigniskarte");
		
	}
	
	@Override
	public Feld auslesenFelddaten(final int id) {
		for (Feld f : felder) {
			if (id == f.getId()) {
				return f;
			}
		}
		System.out.println("Feld mit der ID " + id + " nicht gefunden");
		return null;
	}
	
	@Override
	public Feld auslesenFelddaten(final String name) {
		for (Feld f : felder) {
			if (f.getName().equalsIgnoreCase(name)) {
				return f;
			}
		}
		System.out.println("Feld mit Namen " + name + " nicht gefunden");
		return null;
	}
	
	@Override
	public Zufallskarte ziehenEreigniskarte() {
		return (ereigniskarten.get(new Random().nextInt(ereigniskarten.size())));
	}
	
	@Override
	public Zufallskarte ziehenGemeinschaftskarte() {
		return (gemeinschaftskarten.get(new Random().nextInt(gemeinschaftskarten.size())));
	}
	
	@Override
	public List<Feld> getFelderGruppe(final Color farbe) {
		List<Feld> treffer = new ArrayList<Feld>();
		for (Feld f : felder) {
			if (f.getFarbe().equals(farbe)) {
				treffer.add(f);
			}
		}
		return treffer;
	}
	
	@Override
	public List<Feld> getFelderGruppe(final Feld suchfeld) {
		List<Feld> treffer = new ArrayList<Feld>();
		for (Feld feld : felder) {
			if (feld.getFarbe().equals(suchfeld.getFarbe())
					&& feld.getTyp().equals(suchfeld.getTyp())) {
				treffer.add(feld);
			}
		}
		return treffer;
	}

	private List<Feld> auslesenAlleFelder() {
		List<Feld> felder = new ArrayList<Feld>();
		SAXBuilder builder = new SAXBuilder();
		int i = 1;
		InputStream stream = getClass().getResourceAsStream("/Felder/Feld01.xml");
		while (stream != null) {
			try {
				Element e = builder.build(stream).getRootElement();
				Feld f = new Feld();
				f.setId(i);
				f.setName(e.getChild("name").getText());
				f.setKaufpreis(Integer.valueOf(e.getChildText("kaufpreis")));
				f.setHypothekenwert(Integer.valueOf(e.getChildText("hypothekenwert")));
				f.setHauspreis(Integer.valueOf(e.getChildText("hauspreis")));
				f.setHotelpreis(Integer.valueOf(e.getChildText("hotelpreis")));
				f.setMiete0(Integer.valueOf(e.getChildText("miete0")));
				f.setMiete1(Integer.valueOf(e.getChildText("miete1")));
				f.setMiete2(Integer.valueOf(e.getChildText("miete2")));
				f.setMiete3(Integer.valueOf(e.getChildText("miete3")));
				f.setMiete4(Integer.valueOf(e.getChildText("miete4")));
				f.setMiete5(Integer.valueOf(e.getChildText("miete5")));
				f.setTyp(Feldtyp.valueOf(e.getChildText("typ").toUpperCase()));
				f.setFarbe(Feldfarbe.valueOf(e.getChildText("farbe").toUpperCase()).getColor());

				felder.add(f);
				stream.close();
				i++;
				stream.close();
				stream = getClass().getResourceAsStream("/Felder/Feld" + dc00.format(i) + ".xml");
			}
			catch (IOException | JDOMException e) {
				e.printStackTrace();
				break;
			}
		}
		return felder;
	}
	
	private List<Zufallskarte> auslesenZufallskarten(final String pfad) {
		List<Zufallskarte> karten = new ArrayList<Zufallskarte>();
		SAXBuilder builder = new SAXBuilder();
		int i = 1;
		InputStream stream = getClass().getResourceAsStream(pfad + "01.xml");
		while (stream != null) {
			try {
				Element e = builder.build(stream).getRootElement();

				Zufallskarte k = new Zufallskarte();
				k.setText(e.getChildText("text"));
				k.setZielfeld(Integer.valueOf(e.getChildText("zielfeld")));
				k.setRichtung(Richtung.valueOf(e.getChildText("richtung").toUpperCase()));
				k.setGeld(Integer.valueOf(e.getChildText("geld")));
				k.setEmpfaenger(Empfaenger.valueOf(e.getChildText("empfaenger").toUpperCase()));
				
				karten.add(k);
				stream.close();
				i++;
				stream = getClass().getResourceAsStream(pfad + dc00.format(i) + ".xml");
			}
			catch (IOException | JDOMException e) {
				// Schleife abbrechen
				break;
			}
		}
		return karten;
	}
}