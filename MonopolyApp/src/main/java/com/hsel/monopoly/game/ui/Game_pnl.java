package com.hsel.monopoly.game.ui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jdesktop.swingx.VerticalLayout;
import org.jdesktop.swingx.border.DropShadowBorder;

import com.hsel.ui.components.panel.TexturedPanel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Game_pnl extends TexturedPanel {
	
	private ImageIcon background = new ImageIcon(getClass().getResource("/Bilder/Background.png"));
	private Spielfeld_pnl pnlSpielfeld;
	private JPanel pnlSpieler;
	private JPanel pnlSchatten;

	public Game_pnl() {
		initialize();
	}
	
	private void initialize() {
		setTextureImage(background.getImage());
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("20dlu"), ColumnSpec.decode("200dlu:grow"),
						ColumnSpec.decode("30dlu"), ColumnSpec.decode("50dlu:grow"),
						ColumnSpec.decode("20dlu"), },
				new RowSpec[] { RowSpec.decode("15dlu"), RowSpec.decode("50dlu:grow"),
						RowSpec.decode("15dlu"), }));
		add(getPnlSchatten(), "2, 2, fill, fill");
		add(getPnlSpieler(), "4, 2, fill, fill");
	}
	
	@Override
	public void repaint() {
		super.repaint();

	}

	public Spielfeld_pnl getPnlSpielfeld() {
		if (pnlSpielfeld == null) {
			pnlSpielfeld = new Spielfeld_pnl();
		}
		return pnlSpielfeld;
	}

	public JPanel getPnlSpieler() {
		if (pnlSpieler == null) {
			pnlSpieler = new JPanel();
			pnlSpieler.setOpaque(false);
			pnlSpieler.setLayout(new VerticalLayout(10));
		}
		return pnlSpieler;
	}
	
	private JPanel getPnlSchatten() {
		if (pnlSchatten == null) {
			pnlSchatten = new JPanel();
			DropShadowBorder border = new DropShadowBorder();
			border.setShowBottomShadow(true);
			border.setShowLeftShadow(true);
			border.setShowRightShadow(true);
			border.setShowTopShadow(true);
			pnlSchatten.setBorder(border);
			pnlSchatten.setOpaque(false);
			pnlSchatten.setLayout(new BorderLayout(0, 0));
			pnlSchatten.add(getPnlSpielfeld(), BorderLayout.CENTER);
		}
		return pnlSchatten;
	}
}
