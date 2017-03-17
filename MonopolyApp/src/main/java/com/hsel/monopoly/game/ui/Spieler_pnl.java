package com.hsel.monopoly.game.ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.border.DropShadowBorder;

import com.hsel.defaults.ui.Schriftgroesse;
import com.hsel.ui.components.panel.TexturedPanel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Spieler_pnl extends TexturedPanel {
	
	private JPanel pnlContent;
	private JLabel lblSpielername;
	private JPanel pnlKarten;
	private JLabel lblGeld;
	private JLabel lblImGefaengnis;
	
	public Spieler_pnl() {
		initialize();
	}
	
	private void initialize() {
		ImageIcon img = new ImageIcon(getClass().getResource("/Bilder/Abdunkelung.png"));
		setTextureImage(img.getImage());
		setOpaque(false);
		
		DropShadowBorder shadow = new DropShadowBorder();
		shadow.setShowBottomShadow(true);
		shadow.setShowLeftShadow(true);
		shadow.setShowRightShadow(true);
		shadow.setShowTopShadow(true);
		LineBorder lineborder = new LineBorder(Color.DARK_GRAY);
		setBorder(new CompoundBorder(shadow, lineborder));

		setBackground(Color.WHITE);
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						ColumnSpec.decode("20dlu"), ColumnSpec.decode("21dlu:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.UNRELATED_GAP_COLSPEC, },
				new RowSpec[] { FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.PARAGRAPH_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, }));
		add(getLblSpielername(), "2, 2");
		add(getLblImGefaengnis(), "4, 2, default, fill");
		add(getLblGeld(), "6, 2");
		add(getPnlKarten(), "2, 4, 5, 1, fill, fill");
	}
	
	public JPanel getPnlContent() {
		if (pnlContent == null) {
			pnlContent = new JPanel();
		}
		return pnlContent;
	}
	
	public JLabel getLblSpielername() {
		if (lblSpielername == null) {
			lblSpielername = new JLabel("Spielername");
			lblSpielername.setForeground(Color.WHITE);
			lblSpielername.setFont(Schriftgroesse.SEHR_GROSS.alsUeberschrift());
		}
		return lblSpielername;
	}
	
	public JPanel getPnlKarten() {
		if (pnlKarten == null) {
			pnlKarten = new JPanel();
			pnlKarten.setOpaque(false);
			pnlKarten.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		}
		return pnlKarten;
	}
	
	public JLabel getLblGeld() {
		if (lblGeld == null) {
			lblGeld = new JLabel("Geld");
			lblGeld.setForeground(Color.WHITE);
			lblGeld.setFont(Schriftgroesse.SEHR_GROSS.alsUeberschrift());
		}
		return lblGeld;
	}

	public JLabel getLblImGefaengnis() {
		if (lblImGefaengnis == null) {
			lblImGefaengnis = new JLabel("(im Gef\u00E4ngnis)");
			lblImGefaengnis.setForeground(Color.WHITE);
			lblImGefaengnis.setFont(Schriftgroesse.GROSS.alsUeberschrift());
		}
		return lblImGefaengnis;
	}
}
