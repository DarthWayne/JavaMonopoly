package com.hsel.monopoly.game;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.hsel.defaults.presenter.TypedPresenter_with_Backend;
import com.hsel.monopoly.app.AppConfig;
import com.hsel.monopoly.app.Game;
import com.hsel.monopoly.entities.Feld;
import com.hsel.monopoly.entities.Feld.Feldtyp;
import com.hsel.monopoly.entities.Spieler;
import com.hsel.monopoly.game.ui.Feld_pnl;
import com.hsel.monopoly.service.MonopolyServices;

public class Feld_pnl_presenter extends TypedPresenter_with_Backend<Feld> {
	
	private static final String htmlheader = "<html> <center> <font face = Calibri> ";
	private static final String htmlfooter = " </font> </center> </html>";
	private Map<Spieler, JLabel> mapSpieler;
	private Feld feld;
	
	public Feld_pnl_presenter(final AppConfig appContext, final Feld_pnl view,
			final MonopolyServices fassade) {
		super(appContext, view, fassade);
	}
	
	@Override
	public AppConfig getAppContext() {
		return (AppConfig) getObjAppContext();
	}
	
	@Override
	public Feld_pnl getView() {
		return (Feld_pnl) getObjViewContainer();
	}

	@Override
	public MonopolyServices getFassade() {
		return (MonopolyServices) getObjFassadeContainer();
	}
	
	@Override
	public void registerActions() {
		getView().getTpnName().addMouseListener(new ActionPopup());
		getView().getPnlHaeuser().addMouseListener(new ActionPopup());
		getView().getPnlSpieler().addMouseListener(new ActionPopup());
		getView().getMniVerkaufen().addActionListener(e -> verkaufen());
		getView().getMniHausBauen().addActionListener(e -> bauenHaus());
		getView().getMniHotelBauen().addActionListener(e -> bauenHotel());
	}

	@Override
	public void fuellenView(final Feld feld) {
		this.feld = feld;
		getView().getPnlHaeuser().setBackground(feld.getFarbe());
		getView().getTpnName().setText(htmlheader + feld.getName() + htmlfooter);
		getView().getTpnName().setToolTipText(feld.erstellenTooltiptext());
		getView().getPnlHaeuser().setToolTipText(feld.erstellenTooltiptext());
		getView().getPnlSpieler().setToolTipText(feld.erstellenTooltiptext());
		if (feld.getAnzahlHotels() == 1) {
			JLabel label = new JLabel(new ImageIcon(getClass().getResource("/Icons/hotel.png")));
			getView().getPnlHaeuser().add(label);
			getView().getPnlHaeuser().repaint();
			getView().getPnlHaeuser().revalidate();
		}
		else {
			for (int i = 0; i < feld.getAnzahlHaeuser(); i++) {
				JLabel label = new JLabel(new ImageIcon(getClass().getResource("/Icons/haus.png")));
				getView().getPnlHaeuser().add(label);
			}
			getView().getPnlHaeuser().repaint();
			getView().getPnlHaeuser().revalidate();
		}
		boolean strasse = feld.getTyp().equals(Feldtyp.STRASSE);
		getView().getMniHausBauen().setVisible(strasse);
		getView().getMniHausVerkaufen().setVisible(strasse);
		getView().getMniHotelBauen().setVisible(strasse);
		getView().getMniHotelVerkaufen().setVisible(strasse);
		getView().getMniHausBauen().setText("Haus bauen (" + feld.getHauspreis() + " €)");
		getView().getMniHotelBauen().setText("Hotel bauen (" + feld.getHotelpreis() + " €)");
		getView().getMniHausVerkaufen().setText("Haus verkaufen (" + feld.getHauspreis() + " €)");
		getView().getMniHotelVerkaufen()
				.setText("Hotel verkaufen (" + feld.getHotelpreis() + " €)");
	}

	@Override
	public Feld fuellenObjekt() {
		return feld;
	}

	public void resetView() {
		getView().getPnlHaeuser().removeAll();
		getView().getPnlSpieler().removeAll();
		getMapSpieler().clear();
		getView().repaint();
		getView().revalidate();
	}

	private Map<Spieler, JLabel> getMapSpieler() {
		if (mapSpieler == null) {
			mapSpieler = new HashMap<Spieler, JLabel>();
		}
		return mapSpieler;
	}
	
	private void bauenHaus() {
		feld.getBesitzer().entfernenGeld(feld.getHauspreis());
		feld.setAnzahlHaeuser(feld.getAnzahlHaeuser() + 1);
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("/Icons/haus.png")));
		getView().getPnlHaeuser().add(label);
		getView().getPnlHaeuser().repaint();
		getView().getPnlHaeuser().revalidate();
	}
	
	private void bauenHotel() {
		getView().getPnlHaeuser().removeAll();
		feld.getBesitzer().entfernenGeld(feld.getHotelpreis());
		feld.setAnzahlHotels(feld.getAnzahlHotels() + 1);
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("/Icons/hotel.png")));
		getView().getPnlHaeuser().add(label);
		getView().getPnlHaeuser().repaint();
		getView().getPnlHaeuser().revalidate();
	}

	private void verkaufen() {
		feld.getBesitzer().getLisGrundstueke().remove(feld);
		feld.getBesitzer().hinzufuegenGeld(feld.getKaufpreis());
		feld.setBesitzer(Game.getBank());
		Game.getBank().getLisGrundstueke().add(feld);
		fuellenView(feld);
	}

	public void hinzufuegenSpieler(final Spieler spieler) {
		JLabel label = new JLabel();
		Image img = spieler.getIcon().getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(img));
		getView().getPnlSpieler().add(label);
		getView().getPnlSpieler().repaint();
		getView().getPnlSpieler().revalidate();
		getMapSpieler().put(spieler, label);
	}
	
	public void entfernenSpieler(final Spieler spieler) {
		if (getMapSpieler().containsKey(spieler)) {
			getView().getPnlSpieler().remove(getMapSpieler().get(spieler));
			getMapSpieler().remove(spieler);
			getView().getPnlSpieler().repaint();
			getView().getPnlSpieler().revalidate();
		}
	}
	
	private void anzeigenPopup(final int x, final int y) {
		if (feld.getTyp().equals(Feldtyp.STRASSE)) {
			boolean alleStrassen = pruefenSpielerHatAlleStrassen(feld);
			int anzahlHaeuser = feld.getAnzahlHaeuser();
			int anzahlHotels = feld.getAnzahlHotels();
			
			getView().getMniHausBauen().setEnabled(alleStrassen && anzahlHaeuser < 4);
			getView().getMniHausVerkaufen().setEnabled(anzahlHaeuser > 0 && anzahlHotels == 0);
			getView().getMniHotelBauen().setEnabled(anzahlHaeuser == 4);
			getView().getMniHotelVerkaufen().setEnabled(anzahlHotels == 1);
		}
		getView().getPopupMenu().show(getView(), x, y);
	}
	
	private boolean pruefenSpielerHatAlleStrassen(final Feld feld) {
		for (Feld f : getFassade().getFelderGruppe(feld)) {
			if (!f.getBesitzer().equals(feld.getBesitzer())) {
				return false;
			}
		}
		return true;
	}

	private class ActionPopup extends MouseAdapter {
		
		@Override
		public void mouseReleased(final MouseEvent e) {
			// Nur der Spieler, der aktuell an der Reihe ist, kann Aktionen
			// durchführen
			if (e.getButton() == MouseEvent.BUTTON3) {
				if (feld.getBesitzer().equals(Game.getAktuellerSpieler())) {
					anzeigenPopup(e.getX(), e.getY());
				}
			}
		}
	}
}