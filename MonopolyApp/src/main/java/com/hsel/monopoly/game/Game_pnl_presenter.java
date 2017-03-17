package com.hsel.monopoly.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hsel.defaults.presenter.Presenter_with_Backend;
import com.hsel.monopoly.app.AppConfig;
import com.hsel.monopoly.app.Game;
import com.hsel.monopoly.app.Wuerfel;
import com.hsel.monopoly.entities.Feld;
import com.hsel.monopoly.entities.Feld.Feldtyp;
import com.hsel.monopoly.entities.Spieler;
import com.hsel.monopoly.entities.Zufallskarte;
import com.hsel.monopoly.entities.Zufallskarte.Empfaenger;
import com.hsel.monopoly.entities.Zufallskarte.Richtung;
import com.hsel.monopoly.game.ui.Game_pnl;
import com.hsel.monopoly.game.ui.Kartendetails_pnl;
import com.hsel.monopoly.game.ui.Spieler_pnl;
import com.hsel.monopoly.game.ui.Zufallskartendetails_pnl;
import com.hsel.monopoly.service.MonopolyServices;

public class Game_pnl_presenter extends Presenter_with_Backend {

	private Spielfeld_pnl_presenter preSpielfeld;
	private Map<Spieler, Spieler_pnl_presenter> mapSpielerPresenter;
	private Zufallskarte gezogenekarte;

	public Game_pnl_presenter(final AppConfig appContext, final Game_pnl view,
			final MonopolyServices fassade) {
		super(appContext, view, fassade);
	}

	@Override
	public AppConfig getAppContext() {
		return (AppConfig) getObjAppContext();
	}

	@Override
	public Game_pnl getView() {
		return (Game_pnl) getObjViewContainer();
	}
	
	@Override
	public MonopolyServices getFassade() {
		return (MonopolyServices) getFassadeContainer();
	}
	
	@Override
	public void initialize() {
		super.initialize();
		getPreSpielfeld().initialize();
		ausblendenAlleButtons();
	}

	@Override
	public void registerActions() {
		getPreSpielfeld().getView().getBtnOk().addActionListener(e -> ausfuehrenKartenaktion());
		getPreSpielfeld().getView().getBtnWuerfeln().addActionListener(e -> ausfuehrenSpielzug());
		getPreSpielfeld().getView().getBtnKaufen().addActionListener(e -> kaufen());
		getPreSpielfeld().getView().getBtnNichtKaufen().addActionListener(e -> naechsterSpielzug());
		getPreSpielfeld().getView().getBtnMieteZahlen().addActionListener(e -> zahlenMiete());
	}
	
	public void startenSpiel(final List<Spieler> p_spieler) {
		getPreSpielfeld().reset();
		getMapSpielerPresenter().clear();
		getView().getPnlSpieler().removeAll();
		Game.getSpieler().clear();
		Game.setAktuellerSpieler(null);
		for (Feld_pnl_presenter f : getPreSpielfeld().getFelder()) {
			f.resetView();
		}
		
		for (Spieler s : p_spieler) {
			// Da auch ein Neustart möglich ist, werden die Spieler Objekte hier
			// neu erstellt und nur der Name und das Icon übernommen.
			Spieler spieler = new Spieler(s.getName(), getAppContext().getStartgeld());
			spieler.setIcon(s.getIcon());
			Game.getSpieler().add(spieler);
			getPreSpielfeld().setztenSpielerAufFeld(spieler, 0);
			Spieler_pnl_presenter pre = new Spieler_pnl_presenter(getAppContext(),
					new Spieler_pnl(), getFassade());
			pre.fuellenView(spieler);
			getMapSpielerPresenter().put(spieler, pre);
			getView().getPnlSpieler().add(pre.getView());
		}
		naechsterSpieler();
	}
	
	private void naechsterSpielzug() {
		getPreSpielfeld().clearenPnlCenter();
		if (Game.getAktuellerSpieler().getGeld() < 0) {
			getPreSpielfeld().anzeigenText(Game.getAktuellerSpieler().getName() + " ist pleite");
			Game.getSpieler().remove(Game.getAktuellerSpieler());
			getView().getPnlSpieler()
					.remove(getMapSpielerPresenter().get(Game.getAktuellerSpieler()).getView());
			if (Game.getSpieler().size() == 1) {
				getPreSpielfeld()
						.anzeigenText(Game.getSpieler().get(0).getName() + " hat gewonnen");
				Game.sleep(5000);
			}
		}
		ausblendenAlleButtons();
		
		if (Wuerfel.getInstance().hattePasch() && !Game.getAktuellerSpieler().isImGefaengnis()) {
			if (Wuerfel.getInstance().mussInsGefaengnis()) {
				getPreSpielfeld().schickenInsGefaengnis(Game.getAktuellerSpieler());
				naechsterSpieler();
			}
			else {
				getPreSpielfeld().anzeigenText("Du hast einen Pasch gefürfelt und darfst nochmal.");
				getPreSpielfeld().zuruecksetzenWuerfel();
			}
		}
		else {
			naechsterSpieler();
		}
	}
	
	private void ausblendenAlleButtons() {
		getPreSpielfeld().getView().getBtnOk().setVisible(false);
		getPreSpielfeld().getView().getBtnWuerfeln().setVisible(false);
		getPreSpielfeld().getView().getBtnMieteZahlen().setVisible(false);
		getPreSpielfeld().getView().getBtnKaufen().setVisible(false);
		getPreSpielfeld().getView().getBtnNichtKaufen().setVisible(false);
	}

	private void naechsterSpieler() {
		if (Game.getAktuellerSpieler() != null) {
			getMapSpielerPresenter().get(Game.getAktuellerSpieler()).hervorheben(false);
		}
		Game.naechsterSpieler();
		// Das Panel des Aktuellen Spielers hervorheben
		getMapSpielerPresenter().get(Game.getAktuellerSpieler()).hervorheben(true);
		getPreSpielfeld().anzeigenText(Game.getAktuellerSpieler().getName() + " ist dran");
		if (Game.getAktuellerSpieler().isImGefaengnis()) {
			Game.sleep(200);
			if (Game.getAktuellerSpieler().getRundenImGefaengnis() > 3) {
				// Nach der 3. Runde im Knast kommt man automatisch frei
				getPreSpielfeld().entlassenAusGefaengnis(Game.getAktuellerSpieler());
			}
			else {
				// weitere Runde im Knast
				getPreSpielfeld()
						.anzeigenText("Würfel einen Pasch, um aus dem Gefängnis frei zu kommen!");
				Game.getAktuellerSpieler().setRundenImGefaengnis(
						Game.getAktuellerSpieler().getRundenImGefaengnis() + 1);
			}
		}
		Wuerfel.getInstance().naechsterSpieler();
		getPreSpielfeld().zuruecksetzenWuerfel();
	}

	private void ausfuehrenSpielzug() {
		ExecutorService exe = Executors.newSingleThreadExecutor();
		exe.execute(new Runnable() {
			
			@Override
			public void run() {
				int[] w = Wuerfel.getInstance().wuerfeln();
				getPreSpielfeld().getView().getBtnWuerfeln().setVisible(false);
				getPreSpielfeld().anzeigenWuerfelergebnis(w[0], w[1]);
				
				if (Game.getAktuellerSpieler().isImGefaengnis()) {
					if (w[0] == w[1]) {
						getPreSpielfeld().entlassenAusGefaengnis(Game.getAktuellerSpieler());
					}
					else {
						Game.getAktuellerSpieler().setRundenImGefaengnis(
								Game.getAktuellerSpieler().getRundenImGefaengnis() + 1);
					}
					Game.sleep(1000);
					naechsterSpielzug();
				}
				else {
					Feld feld = getPreSpielfeld().bewegenSpieler(Game.getAktuellerSpieler(),
							w[0] + w[1]);
					anzeigenOptionenFuerFeld(feld);
				}
			}
		});
		exe.shutdown();
	}

	private void anzeigenOptionenFuerFeld(final Feld feld) {
		getPreSpielfeld().getView().getPnlCenter().removeAll();
		if (feld.getTyp().equals(Feldtyp.EREIGNISFELD)) {
			anzeigenOptionenZufallskarten(getFassade().ziehenEreigniskarte());
		}
		else if (feld.getTyp().equals(Feldtyp.GEMEINSCHAFTSFELD)) {
			anzeigenOptionenZufallskarten(getFassade().ziehenGemeinschaftskarte());
		}
		else if (feld.getTyp().equals(Feldtyp.STRASSE) || feld.getTyp().equals(Feldtyp.BAHNHOF)
				|| feld.getTyp().equals(Feldtyp.VERSORGER)) {
			anzeigenOptionenStrasse(feld);
		}
		else if (feld.getTyp().equals(Feldtyp.START)) {
			Game.getAktuellerSpieler().hinzufuegenGeld(getAppContext().getRundengeld());
			naechsterSpielzug();
		}
		else if (feld.getTyp().equals(Feldtyp.GEFAENGNIS)
				|| feld.getTyp().equals(Feldtyp.FREIPARKEN)) {
			// Hier passiert nichts
			naechsterSpielzug();
		}
		else if (feld.getTyp().equals(Feldtyp.GEHEINSGEFAENGNIS)) {
			getPreSpielfeld().schickenInsGefaengnis(Game.getAktuellerSpieler());
			naechsterSpieler();
		}
		else if (feld.getTyp().equals(Feldtyp.STEUER)) {
			Game.getAktuellerSpieler().entfernenGeld(feld.getMiete0());
			naechsterSpielzug();
		}
	}
	
	private void anzeigenOptionenStrasse(final Feld feld) {
		Kartendetails_pnl_presenter preKarte = new Kartendetails_pnl_presenter(getAppContext(),
				new Kartendetails_pnl());
		preKarte.fuellenView(feld);
		getPreSpielfeld().getView().getPnlCenter().add(preKarte.getView());
		
		if (feld.getBesitzer().equals(Game.getBank())) {
			getPreSpielfeld().anzeigenText("Möchtest du " + feld.getName() + " kaufen?");
			getPreSpielfeld().getView().getBtnKaufen().setVisible(true);
			getPreSpielfeld().getView().getBtnNichtKaufen().setVisible(true);
			getPreSpielfeld().getView().getBtnKaufen()
					.setEnabled(Game.getAktuellerSpieler().getGeld() >= feld.getKaufpreis());
		}
		else if (!feld.getBesitzer().equals(Game.getAktuellerSpieler())) {
			getPreSpielfeld().anzeigenText("Du musst Miete zahlen!");
			getPreSpielfeld().getView().getBtnMieteZahlen().setVisible(true);
			getPreSpielfeld().getView().getBtnMieteZahlen().setEnabled(Game.getAktuellerSpieler()
					.getGeld() >= feld.berechnenMiete(Wuerfel.getInstance().getAugen()));
		}
		else {
			naechsterSpielzug();
		}
	}

	private void anzeigenOptionenZufallskarten(final Zufallskarte karte) {
		gezogenekarte = karte;
		getPreSpielfeld().getView().getPnlCenter().add(new Zufallskartendetails_pnl(karte));
		getPreSpielfeld().getView().repaint();
		getPreSpielfeld().getView().revalidate();
		getPreSpielfeld().getView().getBtnOk().setVisible(true);
	}
	
	private void ausfuehrenKartenaktion() {
		ExecutorService exe = Executors.newSingleThreadExecutor();
		exe.execute(new Runnable() {
			
			@Override
			public void run() {
				getPreSpielfeld().getView().getBtnOk().setVisible(false);
				Feld neuesfeld = null;
				if (!gezogenekarte.getEmpfaenger().equals(Empfaenger.KEINER)) {
					ausfuehrenKarteneffektGeldTransaktion(gezogenekarte);
				}
				if (!gezogenekarte.getRichtung().equals(Richtung.KEINE)) {
					neuesfeld = ausfuehrenKarteneffektBewegung(gezogenekarte);
				}
				if (neuesfeld != null) {
					anzeigenOptionenFuerFeld(neuesfeld);
				}
				else {
					naechsterSpielzug();
				}
			}
		});
		exe.shutdown();
	}
	
	private void ausfuehrenKarteneffektGeldTransaktion(final Zufallskarte gezogenekarte) {
		if (gezogenekarte.getEmpfaenger().equals(Empfaenger.SPIELER)) {
			// Der Spieler bekommt Geld von der Bank
			Game.getAktuellerSpieler().hinzufuegenGeld(gezogenekarte.getGeld());
		}
		else if (gezogenekarte.getEmpfaenger().equals(Empfaenger.BANK)) {
			// Der Spieler muss der bank Geld zahlen
			Game.getAktuellerSpieler().entfernenGeld(gezogenekarte.getGeld());
		}
		else if (gezogenekarte.getEmpfaenger().equals(Empfaenger.ALLESPIELER)) {
			// Der Spieler muss allen anderen Geld zahlen
			Game.getAktuellerSpieler()
					.entfernenGeld(gezogenekarte.getGeld() * Game.getSpieler().size());
			for (Spieler s : Game.getSpieler()) {
				if (!s.equals(Game.getAktuellerSpieler())) {
					s.hinzufuegenGeld(gezogenekarte.getGeld());
				}
			}
		}
	}

	private Feld ausfuehrenKarteneffektBewegung(final Zufallskarte gezogenekarte) {
		if (gezogenekarte.getRichtung().equals(Richtung.DIREKT)) {
			getPreSpielfeld().setztenSpielerAufFeld(Game.getAktuellerSpieler(),
					gezogenekarte.getZielfeld() - 1);
			return null;
		}
		else if (gezogenekarte.getRichtung().equals(Richtung.VORWAERTS)) {
			if (gezogenekarte.getZielfeld() > Game.getAktuellerSpieler().getAktuellesFeld()) {
				int felder = gezogenekarte.getZielfeld()
						- Game.getAktuellerSpieler().getAktuellesFeld() - 1;
				return getPreSpielfeld().bewegenSpieler(Game.getAktuellerSpieler(), felder);
			}
			else {
				int felder = gezogenekarte.getZielfeld() + getPreSpielfeld().getFelder().size()
						- Game.getAktuellerSpieler().getAktuellesFeld() - 1;
				return getPreSpielfeld().bewegenSpieler(Game.getAktuellerSpieler(), felder);
			}
		}
		else if (gezogenekarte.getRichtung().equals(Richtung.RUECKWAERTS)) {
			int felder = Game.getAktuellerSpieler().getAktuellesFeld() - gezogenekarte.getZielfeld()
					- 1;
			return getPreSpielfeld().bewegenSpieler(Game.getAktuellerSpieler(), felder, true);
		}
		return null;
	}

	private void kaufen() {
		Feld feld = getPreSpielfeld().getSpielerfeld(Game.getAktuellerSpieler());
		Game.getAktuellerSpieler().entfernenGeld(feld.getKaufpreis());
		feld.setBesitzer(Game.getAktuellerSpieler());
		Game.getAktuellerSpieler().getLisGrundstueke().add(feld);
		Game.getAktuellerSpieler().notifyObservers(feld);
		getPreSpielfeld().aktualisierenTooltip(feld);
		naechsterSpielzug();
	}
	
	private void zahlenMiete() {
		Feld feld = getPreSpielfeld().getSpielerfeld(Game.getAktuellerSpieler());
		int miete = feld.berechnenMiete(Wuerfel.getInstance().getAugen());
		Game.getAktuellerSpieler().entfernenGeld(miete);
		feld.getBesitzer().hinzufuegenGeld(miete);
		naechsterSpielzug();
	}

	private Spielfeld_pnl_presenter getPreSpielfeld() {
		if (preSpielfeld == null) {
			preSpielfeld = new Spielfeld_pnl_presenter(getAppContext(), getView().getPnlSpielfeld(),
					getFassade());
		}
		return preSpielfeld;
	}
	
	private Map<Spieler, Spieler_pnl_presenter> getMapSpielerPresenter() {
		if (mapSpielerPresenter == null) {
			mapSpielerPresenter = new HashMap<Spieler, Spieler_pnl_presenter>();
		}
		return mapSpielerPresenter;
	}

}