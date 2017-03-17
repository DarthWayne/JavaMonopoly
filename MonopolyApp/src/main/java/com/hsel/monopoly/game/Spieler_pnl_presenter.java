package com.hsel.monopoly.game;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.border.DropShadowBorder;

import com.hsel.defaults.presenter.TypedPresenter_with_Backend;
import com.hsel.monopoly.app.AppConfig;
import com.hsel.monopoly.entities.Feld;
import com.hsel.monopoly.entities.Feld.Feldtyp;
import com.hsel.monopoly.entities.Spieler;
import com.hsel.monopoly.game.ui.Spieler_pnl;
import com.hsel.monopoly.game.ui.Strassengruppe;
import com.hsel.monopoly.service.MonopolyServices;

public class Spieler_pnl_presenter extends TypedPresenter_with_Backend<Spieler> {
	
	private Map<Color, Strassengruppe> strassen;
	private Strassengruppe bahnhoefe;
	private Strassengruppe versorger;
	private Spieler spieler;

	public Spieler_pnl_presenter(final AppConfig appContext, final Spieler_pnl view,
			final MonopolyServices service) {
		super(appContext, view, service);
	}
	
	@Override
	public AppConfig getAppContext() {
		return (AppConfig) getObjAppContext();
	}
	
	@Override
	public Spieler_pnl getView() {
		return (Spieler_pnl) getObjViewContainer();
	}

	@Override
	public MonopolyServices getFassade() {
		return (MonopolyServices) getObjFassadeContainer();
	}
	
	@Override
	public void registerActions() {
	
	}
	
	@Override
	public void fuellenView(final Spieler s) {
		spieler = s;
		Spieler.addObserver(spieler, new Observer() {
			
			@Override
			public void update(final Observable o, final Object arg) {
				aktualisierenView();
			}
		});
		aktualisierenView();
	}

	public void aktualisierenView() {
		getView().getLblSpielername().setText(spieler.getName());
		getView().getLblImGefaengnis().setVisible(spieler.isImGefaengnis());
		getView().getLblGeld().setText(spieler.getGeld() + " €");
		getView().getPnlKarten().removeAll();
		getView().getPnlKarten().removeAll();
		getStrassen().clear();
		bahnhoefe = null;
		versorger = null;

		for (Feld feld : spieler.getLisGrundstueke()) {
			if (feld.getTyp().equals(Feldtyp.BAHNHOF)) {
				if (bahnhoefe == null) {
					bahnhoefe = erstellenStrasse(feld);
				}
				else {
					bahnhoefe.getFelder().add(feld);
				}
			}
			else if (feld.getTyp().equals(Feldtyp.BAHNHOF)) {
				if (versorger == null) {
					versorger = erstellenStrasse(feld);
				}
				else {
					versorger.getFelder().add(feld);
				}
			}
			else if (getStrassen().containsKey(feld.getFarbe())) {
				Strassengruppe strasse = getStrassen().get(feld.getFarbe());
				strasse.getFelder().add(feld);
				strasse.repaint();
			}
			else {
				getStrassen().put(feld.getFarbe(), erstellenStrasse(feld));
			}
		}
		getView().repaint();
		getView().revalidate();
	}
	
	private Strassengruppe erstellenStrasse(final Feld feld) {
		Strassengruppe strasse = new Strassengruppe();
		strasse.setFarbe(feld.getFarbe());
		strasse.getFelder().add(feld);
		strasse.setAnzahlGesamt(getFassade().getFelderGruppe(feld).size());
		getView().getPnlKarten().add(strasse);
		return strasse;
		
	}
	
	public void hervorheben(final boolean flag) {
		if (flag) {
			DropShadowBorder shadow = new DropShadowBorder();
			shadow.setShowBottomShadow(true);
			shadow.setShowLeftShadow(true);
			shadow.setShowRightShadow(true);
			shadow.setShowTopShadow(true);
			LineBorder lineborder = new LineBorder(Color.GREEN);
			getView().setBorder(new CompoundBorder(shadow, lineborder));
		}
		else {
			DropShadowBorder shadow = new DropShadowBorder();
			shadow.setShowBottomShadow(true);
			shadow.setShowLeftShadow(true);
			shadow.setShowRightShadow(true);
			shadow.setShowTopShadow(true);
			LineBorder lineborder = new LineBorder(Color.DARK_GRAY);
			getView().setBorder(new CompoundBorder(shadow, lineborder));
		}
	}
	
	private Map<Color, Strassengruppe> getStrassen() {
		if (strassen == null) {
			strassen = new HashMap<Color, Strassengruppe>();
		}
		return strassen;
	}

	@Override
	public Spieler fuellenObjekt() {
		return null;
	}
}