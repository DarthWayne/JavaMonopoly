package com.hsel.monopoly.game.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;

import com.hsel.defaults.ui.Schriftgroesse;
import com.hsel.monopoly.app.AppConfig;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Feld_pnl extends JPanel {
	
	private JPanel pnlHaeuser;
	private JTextPane tpnName;
	private JPanel pnlSpieler;
	private JPanel pnlContent;
	private JPopupMenu popupMenu;
	private JMenuItem mniVerkaufen;
	private JMenuItem mniHausBauen;
	private JMenuItem mniHotelBauen;
	private JMenuItem mniHausVerkaufen;
	private JMenuItem mniHotelVerkaufen;
	
	public Feld_pnl(final Position pos) {
		initialize(pos);
	}

	public enum Position {
		SUED, WEST, NORD, OST, ECKE
	}
	
	private void initialize(final Position pos) {
		setLayout(new BorderLayout(0, 0));
		if (pos.equals(Position.NORD)) {
			add(getPnlHaeuser(), BorderLayout.SOUTH);
			setBorder(new MatteBorder(1, 0, 1, 1, AppConfig.farbeFeldgrenzen));
		}
		else if (pos.equals(Position.SUED)) {
			add(getPnlHaeuser(), BorderLayout.NORTH);
			setBorder(new MatteBorder(1, 1, 1, 0, AppConfig.farbeFeldgrenzen));
		}
		else if (pos.equals(Position.OST)) {
			add(getPnlHaeuser(), BorderLayout.WEST);
			setBorder(new MatteBorder(0, 1, 1, 1, AppConfig.farbeFeldgrenzen));
		}
		else if (pos.equals(Position.WEST)) {
			add(getPnlHaeuser(), BorderLayout.EAST);
			setBorder(new MatteBorder(1, 1, 0, 1, AppConfig.farbeFeldgrenzen));
		}
		else if (pos.equals(Position.ECKE)) {
		}
		add(getPnlContent(), BorderLayout.CENTER);
	}
	
	public JPanel getPnlHaeuser() {
		if (pnlHaeuser == null) {
			pnlHaeuser = new JPanel();
			pnlHaeuser.setPreferredSize(new Dimension(24, 24));
			pnlHaeuser.setBackground(Color.GREEN);
		}
		return pnlHaeuser;
	}
	
	public JTextPane getTpnName() {
		if (tpnName == null) {
			tpnName = new JTextPane();
			tpnName.setOpaque(false);
			tpnName.setEditable(false);
			tpnName.setContentType("text/html");
		}
		return tpnName;
	}
	
	public JPanel getPnlSpieler() {
		if (pnlSpieler == null) {
			pnlSpieler = new JPanel();
			pnlSpieler.setPreferredSize(new Dimension(40, 40));
			pnlSpieler.setOpaque(false);
			pnlSpieler.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		}
		return pnlSpieler;
	}
	
	private JPanel getPnlContent() {
		if (pnlContent == null) {
			pnlContent = new JPanel();
			pnlContent.setBackground(Color.WHITE);
			pnlContent
					.setLayout(
							new FormLayout(new ColumnSpec[] { ColumnSpec.decode("20dlu:grow"), },
									new RowSpec[] { RowSpec.decode("16px"),
											RowSpec.decode("default:grow"),
											RowSpec.decode("16px"), }));
			pnlContent.add(getTpnName(), "1, 2, fill, fill");
			pnlContent.add(getPnlSpieler(), "1, 1, fill, top");
		}
		return pnlContent;
	}

	public JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.add(getMniHausBauen());
			popupMenu.add(getMniHausVerkaufen());
			popupMenu.add(getMniHotelBauen());
			popupMenu.add(getMniHotelVerkaufen());
			popupMenu.add(getMniVerkaufen());
		}
		return popupMenu;
	}

	public JMenuItem getMniVerkaufen() {
		if (mniVerkaufen == null) {
			mniVerkaufen = new JMenuItem("Verkaufen");
			mniVerkaufen.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return mniVerkaufen;
	}

	public JMenuItem getMniHausBauen() {
		if (mniHausBauen == null) {
			mniHausBauen = new JMenuItem("Haus bauen");
			mniHausBauen.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return mniHausBauen;
	}

	public JMenuItem getMniHausVerkaufen() {
		if (mniHausVerkaufen == null) {
			mniHausVerkaufen = new JMenuItem("Haus verkaufen");
			mniHausVerkaufen.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return mniHausVerkaufen;
	}

	public JMenuItem getMniHotelBauen() {
		if (mniHotelBauen == null) {
			mniHotelBauen = new JMenuItem("Hotel bauen");
			mniHotelBauen.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return mniHotelBauen;
	}

	public JMenuItem getMniHotelVerkaufen() {
		if (mniHotelVerkaufen == null) {
			mniHotelVerkaufen = new JMenuItem("Hotel verkaufen");
			mniHotelVerkaufen.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return mniHotelVerkaufen;
	}
}
