package com.hsel.monopoly.menu.ui;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXButton;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class SpielerItem_pnl extends JPanel {
	
	private JLabel lblSpielfigur;
	private JLabel lblSpielername;
	private JXButton btnX;
	
	public SpielerItem_pnl() {
		initialize();
	}
	
	private void initialize() {
		setBackground(Color.WHITE);
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, }));
		add(getLblSpielfigur(), "2, 2");
		add(getLblSpielername(), "4, 2");
		add(getBtnX(), "6, 2");
	}
	
	public JLabel getLblSpielfigur() {
		if (lblSpielfigur == null) {
			lblSpielfigur = new JLabel("");
		}
		return lblSpielfigur;
	}
	
	public JLabel getLblSpielername() {
		if (lblSpielername == null) {
			lblSpielername = new JLabel("Spielername");
		}
		return lblSpielername;
	}
	
	public JXButton getBtnX() {
		if (btnX == null) {
			btnX = new JXButton();
			btnX.setMargin(new Insets(2, 6, 2, 6));
			btnX.setText("x");
		}
		return btnX;
	}
}
