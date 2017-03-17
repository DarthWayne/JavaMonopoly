package com.hsel.monopoly.entities;

import java.awt.Color;

public class Feld {

	private int id;
	private String name;
	private String name_einzeilig;
	private Feldtyp typ;
	private Color farbe;
	private int kaufpreis;
	private int hypothekenwert;
	private int hauspreis;
	private int hotelpreis;
	private int miete0;
	private int miete1;
	private int miete2;
	private int miete3;
	private int miete4;
	private int miete5;

	private int anzahlHaeuser;
	private int anzahlHotels;
	private Spieler besitzer;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName_einzeilig() {
		if (name_einzeilig == null) {
			name_einzeilig = name;
			if (name.contains("-") && name.charAt(name.indexOf("-") + 1) == ' ') {
				StringBuilder stb = new StringBuilder();
				stb.append(name.substring(0, name.indexOf("-")));
				stb.append(name.substring(name.indexOf("-") + 2).toLowerCase());
				name_einzeilig = stb.toString();
			}
		}
		return name_einzeilig;
	}

	public Feldtyp getTyp() {
		return typ;
	}

	public void setTyp(final Feldtyp typ) {
		this.typ = typ;
	}

	public Color getFarbe() {
		return farbe;
	}

	public void setFarbe(final Color farbe) {
		this.farbe = farbe;
	}

	public int getKaufpreis() {
		return kaufpreis;
	}

	public void setKaufpreis(final int kaufpreis) {
		this.kaufpreis = kaufpreis;
	}

	public int getHypothekenwert() {
		return hypothekenwert;
	}

	public void setHypothekenwert(final int hypothekenwert) {
		this.hypothekenwert = hypothekenwert;
	}

	public int getHauspreis() {
		return hauspreis;
	}

	public void setHauspreis(final int hauspreis) {
		this.hauspreis = hauspreis;
	}

	public int getHotelpreis() {
		return hotelpreis;
	}

	public void setHotelpreis(final int hotelpreis) {
		this.hotelpreis = hotelpreis;
	}

	public int getMiete0() {
		return miete0;
	}

	public void setMiete0(final int miete0) {
		this.miete0 = miete0;
	}

	public int getMiete1() {
		return miete1;
	}

	public void setMiete1(final int miete1) {
		this.miete1 = miete1;
	}

	public int getMiete2() {
		return miete2;
	}

	public void setMiete2(final int miete2) {
		this.miete2 = miete2;
	}

	public int getMiete3() {
		return miete3;
	}

	public void setMiete3(final int miete3) {
		this.miete3 = miete3;
	}

	public int getMiete4() {
		return miete4;
	}

	public void setMiete4(final int miete4) {
		this.miete4 = miete4;
	}

	public int getMiete5() {
		return miete5;
	}

	public void setMiete5(final int miete5) {
		this.miete5 = miete5;
	}

	public int getAnzahlHaeuser() {
		return anzahlHaeuser;
	}

	public void setAnzahlHaeuser(final int anzahlHaeuser) {
		this.anzahlHaeuser = anzahlHaeuser;
	}

	public int getAnzahlHotels() {
		return anzahlHotels;
	}

	public void setAnzahlHotels(final int anzahlHotels) {
		this.anzahlHotels = anzahlHotels;
	}

	public Spieler getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(final Spieler besitzer) {
		this.besitzer = besitzer;
	}
	
	@Override
	public String toString() {
		return getName() + " mit Index " + getId();
	}
	
	/**
	 * Berechnet die zu zahlende Miete.
	 *
	 * @return miete
	 */
	public int berechnenMiete(final int augen) {
		if (getTyp().equals(Feldtyp.BAHNHOF)) {
			int anzahlbahnhoefe = 0;
			for (Feld f : getBesitzer().getLisGrundstueke()) {
				if (f.getTyp().equals(Feldtyp.BAHNHOF)) {
					anzahlbahnhoefe++;
				}
			}
			if (anzahlbahnhoefe == 1) {
				return getMiete0();
			}
			else if (anzahlbahnhoefe == 2) {
				return getMiete1();
			}
			else if (anzahlbahnhoefe == 3) {
				return getMiete2();
			}
			else if (anzahlbahnhoefe == 4) {
				return getMiete3();
			}
		}
		else if (getTyp().equals(Feldtyp.VERSORGER)) {
			int anzahlversorger = 0;
			for (Feld f : getBesitzer().getLisGrundstueke()) {
				if (f.getTyp().equals(Feldtyp.BAHNHOF)) {
					anzahlversorger++;
				}
			}
			return anzahlversorger == 2 ? (augen * 200) : (augen * 80);
		}
		else if (getTyp().equals(Feldtyp.STRASSE)) {
			if (getAnzahlHotels() == 1) {
				return getMiete5();
			}
			else if (getAnzahlHaeuser() == 4) {
				return getMiete4();
			}
			else if (getAnzahlHaeuser() == 3) {
				return getMiete3();
			}
			else if (getAnzahlHaeuser() == 2) {
				return getMiete2();
			}
			else if (getAnzahlHaeuser() == 1) {
				return getMiete1();
			}
			return getMiete0();
		}
		return 0;
	}
	
	public String erstellenTooltiptext() {
		StringBuilder stbTooltip = new StringBuilder();
		stbTooltip.append("<html>");
		stbTooltip.append("<font face=\"Calibri Light\" size=6>");
		stbTooltip.append("&#160" + getName_einzeilig() + "</font><br><br>");
		stbTooltip.append("<font face=\"Calibri\">");
		
		if (getTyp().equals(Feldtyp.STRASSE)) {
			stbTooltip.append("<table style=\"width:150px\">");
			stbTooltip.append(erstellenZeile("Besitzer", getBesitzer().getName()));
			stbTooltip.append(erstellenZeile("Grundstückspreis", getKaufpreis()));
			stbTooltip.append(erstellenZeile("Hypothekenwert", getHypothekenwert()));
			stbTooltip.append(erstellenZeile("Häuserpreis", getHauspreis()));
			stbTooltip.append(erstellenZeile("Hotelpreis", getHotelpreis()));
			stbTooltip.append(erstellenLeerzeile());
			stbTooltip.append(erstellenZeile("Miete ohne Häuser", getMiete0()));
			stbTooltip.append(erstellenZeile("Miete mit 1 Haus", getMiete1()));
			stbTooltip.append(erstellenZeile("Miete mit 2 Haus", getMiete2()));
			stbTooltip.append(erstellenZeile("Miete mit 3 Haus", getMiete3()));
			stbTooltip.append(erstellenZeile("Miete mit 4 Haus", getMiete4()));
			stbTooltip.append(erstellenZeile("Miete mit 1 Hotel", getMiete5()));
			stbTooltip.append("</table>");
		}
		else if (getTyp().equals(Feldtyp.BAHNHOF)) {
			stbTooltip.append("<table style=\"width:200px\">");
			stbTooltip.append(erstellenZeile("Besitzer", getBesitzer().getName()));
			stbTooltip.append(erstellenZeile("Grundstückspreis", getKaufpreis()));
			stbTooltip.append(erstellenZeile("Hypothekenwert", getHypothekenwert()));
			stbTooltip.append(erstellenLeerzeile());
			stbTooltip.append(erstellenZeile("Miete, wenn man 1 Bahnhof besitzt", getMiete0()));
			stbTooltip.append(erstellenZeile("Miete, wenn man 2 Bahnhöfe besitzt", getMiete1()));
			stbTooltip.append(erstellenZeile("Miete, wenn man 3 Bahnhöfe besitzt", getMiete2()));
			stbTooltip.append(erstellenZeile("Miete, wenn man 4 Bahnhöfe besitzt", getMiete3()));
			stbTooltip.append("</table>");
		}
		else if (getTyp().equals(Feldtyp.VERSORGER)) {
			stbTooltip.append("<table style=\"width:150px\">");
			stbTooltip.append(erstellenZeile("Besitzer", getBesitzer().getName()));
			stbTooltip.append(erstellenZeile("Grundstückspreis", getKaufpreis()));
			stbTooltip.append(erstellenZeile("Hypothekenwert", getHypothekenwert()));
			stbTooltip.append(erstellenLeerzeile());
			stbTooltip.append("</table>");
			stbTooltip.append("<table style=\"width:150px\"><tr><td>");
			stbTooltip.append("Wenn man Besitzer des Elektrizitätswerks ");
			stbTooltip.append("ist, so ist die Miete 40-mal so hoch, ");
			stbTooltip.append("wie Augen auf den zwei Würfeln sind.");
			stbTooltip.append("<br><br>");
			stbTooltip.append("Wenn man Besitzer beider Versorgungswerke ");
			stbTooltip.append("ist, so ist die Miete 200-mal so hoch, ");
			stbTooltip.append("wie Augen auf den zwei Würfeln sind. ");
			stbTooltip.append("</td></tr></table>");
		}
		stbTooltip.append("</font> </html>");
		return stbTooltip.toString();
	}
	
	private String erstellenZeile(final String text, final int euro) {
		return erstellenZeile(text, euro + " €");
	}
	
	private String erstellenZeile(final String text, final String euro) {
		return "<tr><td>" + text + "</td><td align=\"right\">" + euro + "</td></tr>";
	}

	private String erstellenLeerzeile() {
		return "<tr height=5px><td></td></tr>";
	}

	public enum Feldtyp {

		STRASSE, GEMEINSCHAFTSFELD, EREIGNISFELD, BAHNHOF, VERSORGER, START, GEFAENGNIS, FREIPARKEN, GEHEINSGEFAENGNIS, STEUER
	}
	
	public enum Feldfarbe {
		
		// TODO schönere Farben
		DUNKELLILA(128, 51, 153), //
		HELLBLAU(128, 204, 255), //
		LILA(204, 68, 204), //
		ORANGE(255, 128, 0), //
		ROT(255, 0, 0), //
		GELB(Color.YELLOW), //
		GRUEN(Color.GREEN), //
		DUNKELBLAU(Color.BLUE), //
		WEISS(Color.WHITE);
		
		private final Color c;
		
		private Feldfarbe(final int r, final int g, final int b) {
			this.c = new Color(r, g, b);
		}
		
		private Feldfarbe(final Color c) {
			this.c = c;
		}
		
		public Color getColor() {
			return c;
		}
	}
}