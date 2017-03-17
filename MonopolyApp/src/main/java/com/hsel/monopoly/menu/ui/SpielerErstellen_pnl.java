package com.hsel.monopoly.menu.ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXTextField;
import org.jdesktop.swingx.VerticalLayout;

import com.hsel.defaults.ui.Schriftgroesse;
import com.hsel.monopoly.ui.components.OverlayDialog_pnl;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class SpielerErstellen_pnl extends OverlayDialog_pnl {

	private JXTextField txfNeuerSpieler;
	private JXButton btnSpielStarten;
	private JPanel pnlSpieler;
	private JTextPane tpnTitel;
	private JXButton btnAdd;
	private JPanel pnlButtons;
	private JXButton btnAbbrechen;
	private JComboBox<String> cmbSpielfigur;
	
	public SpielerErstellen_pnl() {
		initialize();
	}

	private void initialize() {
		getPnlDialog().setBackground(Color.WHITE);
		getPnlDialog().setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("30dlu"), ColumnSpec.decode("50dlu:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						ColumnSpec.decode("30dlu"), },
				new RowSpec[] { RowSpec.decode("15dlu"), FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.PARAGRAPH_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("100dlu"),
						FormSpecs.PARAGRAPH_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						RowSpec.decode("20dlu"), }));
		getPnlDialog().add(getTpnTitel(), "2, 2, 3, 1, fill, fill");
		getPnlDialog().add(getTxfNeuerSpieler(), "2, 4, 3, 1, fill, fill");
		getPnlDialog().add(getCmbSpielfigur(), "2, 6, 3, 1, fill, default");
		getPnlDialog().add(getBtnAdd(), "4, 8, right, fill");
		getPnlDialog().add(getPnlSpieler(), "2, 10, 3, 1, fill, fill");
		getPnlDialog().add(getPnlButtons(), "2, 12, 3, 1, fill, fill");
	}

	public JXTextField getTxfNeuerSpieler() {
		if (txfNeuerSpieler == null) {
			txfNeuerSpieler = new JXTextField();
			txfNeuerSpieler.setPrompt("Spielername");
			txfNeuerSpieler.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return txfNeuerSpieler;
	}
	
	public JXButton getBtnSpielStarten() {
		if (btnSpielStarten == null) {
			btnSpielStarten = new JXButton();
			btnSpielStarten.setText("Spiel starten");
		}
		return btnSpielStarten;
	}
	
	public JPanel getPnlSpieler() {
		if (pnlSpieler == null) {
			pnlSpieler = new JPanel();
			pnlSpieler.setBackground(Color.WHITE);
			pnlSpieler.setLayout(new VerticalLayout());
		}
		return pnlSpieler;
	}

	private JTextPane getTpnTitel() {
		if (tpnTitel == null) {
			tpnTitel = new JTextPane();
			tpnTitel.setEditable(false);
			tpnTitel.setText("F\u00FCge weitere Spieler hinzu, oder starte das Spiel");
			tpnTitel.setFont(Schriftgroesse.GROSS.alsUeberschrift());
		}
		return tpnTitel;
	}

	public JXButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JXButton();
			btnAdd.setText("Hinzuf\u00FCgen");
		}
		return btnAdd;
	}
	
	@Override
	public void setVisible(final boolean aFlag) {
		super.setVisible(aFlag);
		getTxfNeuerSpieler().requestFocus();
	}
	
	private JPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			pnlButtons.setBackground(Color.WHITE);
			pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnlButtons.add(getBtnSpielStarten());
			pnlButtons.add(getBtnAbbrechen());
		}
		return pnlButtons;
	}
	
	public JXButton getBtnAbbrechen() {
		if (btnAbbrechen == null) {
			btnAbbrechen = new JXButton();
			btnAbbrechen.setText("Abbrechen");
		}
		return btnAbbrechen;
	}

	public JComboBox<String> getCmbSpielfigur() {
		if (cmbSpielfigur == null) {
			cmbSpielfigur = new JComboBox<String>(new DefaultComboBoxModel<String>());
			cmbSpielfigur.setRenderer(new DefaultListCellRenderer());
		}
		return cmbSpielfigur;
	}
}
