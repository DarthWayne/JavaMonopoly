package com.hsel.monopoly.game;

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.hsel.defaults.application.ApplicationContext;
import com.hsel.defaults.presenter.Presenter_with_Backend;
import com.hsel.monopoly.app.AppConfig;
import com.hsel.monopoly.app.Game;
import com.hsel.monopoly.entities.Feld;
import com.hsel.monopoly.entities.Feld.Feldtyp;
import com.hsel.monopoly.entities.Spieler;
import com.hsel.monopoly.game.ui.Feld_pnl;
import com.hsel.monopoly.game.ui.Spielfeld_pnl;
import com.hsel.monopoly.service.MonopolyServices;

public class Spielfeld_pnl_presenter extends Presenter_with_Backend {

	private Feld_pnl_presenter gefaengnis;
	private List<Feld_pnl_presenter> felder;

	public Spielfeld_pnl_presenter(final ApplicationContext appContext,
			final Spielfeld_pnl objViewContainer, final MonopolyServices fassade) {
		super(appContext, objViewContainer, fassade);
	}

	@Override
	public AppConfig getAppContext() {
		return (AppConfig) getObjAppContext();
	}

	@Override
	public Spielfeld_pnl getView() {
		return (Spielfeld_pnl) getObjViewContainer();
	}
	
	@Override
	public MonopolyServices getFassade() {
		return (MonopolyServices) getFassadeContainer();
	}

	@Override
	public void initialize() {
		super.initialize();
		getFelder().clear();
		int index = 1;
		Feld_pnl feld = getFeld(index);
		while (feld != null) {
			Feld f = getFassade().auslesenFelddaten(index);
			// Besitzer setzten
			f.setBesitzer(Game.getBank());
			Game.getBank().getLisGrundstueke().add(f);

			Feld_pnl_presenter presenter = new Feld_pnl_presenter(getAppContext(), feld,
					getFassade());
			presenter.initialize();
			presenter.fuellenView(f);
			getFelder().add(presenter);
			index++;
			feld = getFeld(index);
		}
	}
	
	public void reset() {
		for (Feld_pnl_presenter presenter : getFelder()) {
			presenter.resetView();
			Feld f = presenter.fuellenObjekt();
			f.setBesitzer(Game.getBank());
			f.setAnzahlHaeuser(0);
			f.setAnzahlHotels(0);
			Game.getBank().getLisGrundstueke().add(f);
			presenter.fuellenView(f);
		}
		clearenPnlCenter();
		zuruecksetzenWuerfel();
	}

	@Override
	public void registerActions() {
	
	}

	public List<Feld_pnl_presenter> getFelder() {
		if (felder == null) {
			felder = new ArrayList<Feld_pnl_presenter>();
		}
		return felder;
	}

	private Feld_pnl_presenter getGefaengnis() {
		if (gefaengnis == null) {
			for (Feld_pnl_presenter pre : getFelder()) {
				if (pre.fuellenObjekt().getTyp().equals(Feldtyp.GEFAENGNIS)) {
					gefaengnis = pre;
					break;
				}
			}
		}
		return gefaengnis;
	}

	public Feld_pnl getFeld(final int index) {
		try {
			DecimalFormat dc00 = new DecimalFormat("00");
			Object feld = Spielfeld_pnl.class.getMethod("getFeld" + dc00.format(index))
					.invoke(getView());
			return (Feld_pnl) feld;
		}
		catch (NoSuchMethodException e) {
			// Feld existiert nicht. Schleife wird bei null Rückgabe beendet.
		}
		catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException
				| SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Feld getSpielerfeld(final Spieler spieler) {
		return getFelder().get(spieler.getAktuellesFeld()).fuellenObjekt();
	}
	
	/**
	 * Setzt den Spieler initial auf das Start Feld.
	 *
	 * @param spieler
	 */
	public void setztenSpielerAufFeld(final Spieler spieler, final int feld) {
		getFelder().get(spieler.getAktuellesFeld()).entfernenSpieler(spieler);
		getFelder().get(feld).hinzufuegenSpieler(spieler);
		spieler.setAktuellesFeld(feld);
		if (getFelder().get(feld).fuellenObjekt().equals(Feldtyp.GEFAENGNIS)) {
			schickenInsGefaengnis(spieler);
		}
	}

	public void schickenInsGefaengnis(final Spieler spieler) {
		getFelder().get(spieler.getAktuellesFeld()).entfernenSpieler(spieler);
		// getGefaengnis().hinzufuegenSpieler(spieler);
		spieler.setRundenImGefaengnis(0);
		spieler.setImGefaengnis(true);
		spieler.setAktuellesFeld(-1);
		// getFelder().indexOf(getGefaengnis()));
		anzeigenText(spieler.getName() + " ist im Gefängnis!");
	}
	
	public void entlassenAusGefaengnis(final Spieler spieler) {
		// Der Spieler wird nirgends angezeigt
		getGefaengnis().hinzufuegenSpieler(spieler);
		spieler.setRundenImGefaengnis(-1);
		spieler.setImGefaengnis(false);
		spieler.setAktuellesFeld(getFelder().indexOf(getGefaengnis()));
		anzeigenText(spieler.getName() + " ist wieder frei!");
	}
	
	public Feld bewegenSpieler(final Spieler spieler, final int felder, final boolean rueckwaerts) {
		if (rueckwaerts) {
			int zielfeld = spieler.getAktuellesFeld() - felder;
			// Bischen billige Animation
			Game.sleep(1000);
			for (int i = spieler.getAktuellesFeld(); i > zielfeld; i--) {
				Game.sleep(300);
				getFelder().get(i).entfernenSpieler(spieler);
				if (i - 1 >= 0) {
					getFelder().get(i - 1).hinzufuegenSpieler(spieler);
				}
				else {
					getFelder().get(getFelder().size()).hinzufuegenSpieler(spieler);
				}
				getView().repaint();
				getView().revalidate();
			}
			spieler.setAktuellesFeld(zielfeld);
			return getFelder().get(zielfeld).fuellenObjekt();
		}
		return bewegenSpieler(spieler, felder);
	}
	
	/**
	 * Bewegt die Figur des Spielers um die übergebene Anzahl Felder. Das neue
	 * Feld wird zurückgegeben. Diese Methode führt eine Animation aus, die den
	 * Spieler auf jedes Feld nacheinander setzt. Damit die Animation richtig
	 * ausgeführt wird, muss die Methode innerhalb eines neuen Threads gecallt
	 * werden.
	 *
	 * @param spieler
	 *            Spieler
	 * @param felder
	 *            Anzahl Felder
	 * @return Neues Feld
	 */
	public Feld bewegenSpieler(final Spieler spieler, final int felder) {
		int zielfeld = spieler.getAktuellesFeld() + felder;
		// Bischen billige Animation
		Game.sleep(1000);
		for (int i = spieler.getAktuellesFeld(); i < zielfeld; i++) {
			Game.sleep(300);
			getFelder().get(i).entfernenSpieler(spieler);
			if (i >= getFelder().size() - 1) {
				// Neue Runde anfangen
				getFelder().get(i).entfernenSpieler(spieler);
				zielfeld = zielfeld - i - 1;
				i = -1;
				if (zielfeld != 0) {
					// Nur Geld übertragen, wenn das Zielfeld NICHT direkt Start
					// ist, das das Rundengeld in diesem Fall von einer anderen
					// Methode vergeben wird.
					spieler.hinzufuegenGeld(getAppContext().getRundengeld());
				}
			}

			if (i + 1 < getFelder().size()) {
				getFelder().get(i + 1).hinzufuegenSpieler(spieler);
			}
			else {
				getFelder().get(0).hinzufuegenSpieler(spieler);
			}
			getView().repaint();
			getView().revalidate();
		}
		spieler.setAktuellesFeld(zielfeld);
		return getFelder().get(zielfeld).fuellenObjekt();
	}

	/**
	 * Gibt eine kurze einzeilige Nachricht in der Mitte des Spielfeldes aus.
	 *
	 * @param text
	 */
	public void anzeigenText(final String text) {
		getView().getLblNachricht().setText(text);
		Game.sleep(500);
	}
	
	/**
	 * Zeigt das Würfelergebnis grafisch an. Bei 0 wird ein Würfel mit
	 * Fragezeichen verwendet.
	 *
	 * @param w1
	 *            0 - 6
	 * @param w2
	 *            0 - 6
	 */
	public void anzeigenWuerfelergebnis(final int w1, final int w2) {
		int s = 48;
		ImageIcon image1 = new ImageIcon(getClass().getResource("/Icons/wuerfel0" + w1 + ".png"));
		ImageIcon image2 = new ImageIcon(getClass().getResource("/Icons/wuerfel0" + w2 + ".png"));
		// passend skalieren
		image1 = new ImageIcon(image1.getImage().getScaledInstance(s, s, Image.SCALE_SMOOTH));
		image2 = new ImageIcon(image2.getImage().getScaledInstance(s, s, Image.SCALE_SMOOTH));
		getView().getLblWuerfel01().setIcon(image1);
		getView().getLblWuerfel02().setIcon(image2);
	}

	public void clearenPnlCenter() {
		getView().getPnlCenter().removeAll();
		getView().getPnlCenter().repaint();
		getView().getPnlCenter().revalidate();
	}

	public void zuruecksetzenWuerfel() {
		getView().getBtnWuerfeln().setVisible(true);
		anzeigenWuerfelergebnis(0, 0);
	}

	public void aktualisierenTooltip(final Feld feld) {
		for (Feld_pnl_presenter pre : felder) {
			if (pre.fuellenObjekt().equals(feld)) {
				pre.fuellenView(feld);
			}
		}
	}
}