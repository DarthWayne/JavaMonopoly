package com.hsel.monopoly.menu.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXPanel;

import com.hsel.defaults.ui.Schriftgroesse;
import com.hsel.monopoly.ui.components.MainMenuButtonUI;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class MainMenu_pnl extends JPanel {
	
	private JXPanel pnlBackground;
	private JLabel lblBackground;
	private JXPanel pnlButtons;
	private JButton btnNeuesSpiel;
	private JButton btnSpielBeenden;
	private JButton btnEinstellungen;
	private JLabel lblSchriftzug;

	public MainMenu_pnl() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new OverlayLayout(this));
		add(getPnlButtons());
		add(getPnlBackground());
	}
	
	private JLabel getLblBackground() {
		if (lblBackground == null) {
			lblBackground = new JLabel();
			ImageIcon bg = new ImageIcon(getClass().getResource("/Bilder/MainMenuHintergrund.png"));
			int w = (int) getToolkit().getScreenSize().getWidth();
			int h = (int) getToolkit().getScreenSize().getHeight();
			bg = new ImageIcon(bg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
			lblBackground.setIcon(bg);
		}
		return lblBackground;
	}

	private JXPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JXPanel();
			pnlButtons.setOpaque(false);
			pnlButtons
					.setLayout(
							new FormLayout(
									new ColumnSpec[] { ColumnSpec.decode("4dlu:grow(2)"),
											ColumnSpec.decode("150dlu"),
											ColumnSpec.decode("4dlu:grow"),
											ColumnSpec.decode("150dlu"),
											ColumnSpec.decode("4dlu:grow"), ColumnSpec.decode(
													"150dlu"),
											ColumnSpec.decode("4dlu:grow(2)"), },
									new RowSpec[] { RowSpec.decode("20dlu"),
											FormSpecs.DEFAULT_ROWSPEC,
											RowSpec.decode("50dlu:grow(10)"),
											RowSpec.decode("bottom:default"),
											RowSpec.decode("9dlu:grow"), }));
			pnlButtons.add(getLblSchriftzug(), "2, 2, 5, 1");
			pnlButtons.add(getBtnNeuesSpiel(), "2, 4");
			pnlButtons.add(getBtnEinstellungen(), "4, 4");
			pnlButtons.add(getBtnSpielBeenden(), "6, 4");
		}
		return pnlButtons;
	}

	private JXPanel getPnlBackground() {
		if (pnlBackground == null) {
			pnlBackground = new JXPanel();
			pnlBackground.setLayout(new BorderLayout());
			pnlBackground.add(getLblBackground(), BorderLayout.CENTER);
		}
		return pnlBackground;
	}
	
	public JButton getBtnNeuesSpiel() {
		if (btnNeuesSpiel == null) {
			btnNeuesSpiel = new JButton();
			btnNeuesSpiel.setFont(Schriftgroesse.RIESIG.alsUeberschrift());
			btnNeuesSpiel.setForeground(Color.WHITE);
			btnNeuesSpiel.setUI(new MainMenuButtonUI());
			btnNeuesSpiel.setText("Neues Spiel");
		}
		return btnNeuesSpiel;
	}

	public JButton getBtnSpielBeenden() {
		if (btnSpielBeenden == null) {
			btnSpielBeenden = new JButton();
			btnSpielBeenden.setFont(Schriftgroesse.RIESIG.alsUeberschrift());
			btnSpielBeenden.setForeground(Color.WHITE);
			btnSpielBeenden.setUI(new MainMenuButtonUI());
			btnSpielBeenden.setText("Spiel beenden");
		}
		return btnSpielBeenden;
	}

	public JButton getBtnEinstellungen() {
		if (btnEinstellungen == null) {
			btnEinstellungen = new JButton();
			btnEinstellungen.setFont(Schriftgroesse.RIESIG.alsUeberschrift());
			btnEinstellungen.setForeground(Color.WHITE);
			btnEinstellungen.setUI(new MainMenuButtonUI());
			btnEinstellungen.setText("Einstellungen");
		}
		return btnEinstellungen;
	}
	
	private JLabel getLblSchriftzug() {
		if (lblSchriftzug == null) {
			lblSchriftzug = new JLabel("");
			lblSchriftzug.setHorizontalAlignment(SwingConstants.CENTER);
			lblSchriftzug.setIcon(
					new ImageIcon(getClass().getResource("/Bilder/MonopolySchriftzug.png")));
		}
		return lblSchriftzug;
	}
}
