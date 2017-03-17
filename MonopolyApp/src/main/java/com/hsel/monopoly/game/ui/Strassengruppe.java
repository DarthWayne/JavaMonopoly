package com.hsel.monopoly.game.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.hsel.defaults.ui.Schriftgroesse;
import com.hsel.monopoly.entities.Feld;

public class Strassengruppe extends JComponent {

	private List<Feld> felder;
	private Color farbe = Color.WHITE;
	private int anzahlGesamt = 0;
	
	public Strassengruppe() {
		setPreferredSize(new Dimension(75, 100));
	}
	
	@Override
	public void paint(final Graphics g) {
		int versatzX = getWidth() / (4 * anzahlGesamt);
		int versatzY = getHeight() / (4 * anzahlGesamt);
		int x = 0;
		int y = 0;
		int w = getWidth() - versatzX * (anzahlGesamt);
		int h = getHeight() - versatzY * (anzahlGesamt - 1);
		
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 1; i <= anzahlGesamt; i++) {
			if (i <= anzahlGesamt - getFelder().size()) {
				g2d.setPaint(Color.LIGHT_GRAY);
			}
			else {
				g2d.setPaint(farbe);
			}
			g2d.fillRect(x, y, w, h);
			
			g2d.setPaint(Color.BLACK);
			// obere Linie
			g2d.drawLine(x, y, x + w, y);
			// linke Linie
			g2d.drawLine(x, y, x, y + h);
			// rechte Linie
			g2d.drawLine(x + w, y, x + w, y + h);
			// untere Linie
			g2d.drawLine(x, y + h, x + w, y + h);

			x = x + versatzX;
			y = y + versatzY;
		}
		x = x + w / 2 - 30;
		y = y + h / 2 - 20;
		g2d.setFont(Schriftgroesse.GROSS.alsText());
		g2d.drawString(getFelder().size() + " / " + anzahlGesamt, x, y);

		g2d.dispose();
		super.paint(g2d);
	}

	public List<Feld> getFelder() {
		if (felder == null) {
			felder = new ArrayList<Feld>();
		}
		return felder;
	}
	
	public Color getFarbe() {
		return farbe;
	}
	
	public void setFarbe(final Color farbe) {
		this.farbe = farbe;
	}
	
	public int getAnzahlGesamt() {
		return anzahlGesamt;
	}
	
	public void setAnzahlGesamt(final int anzahlGesamt) {
		this.anzahlGesamt = anzahlGesamt;
	}
}