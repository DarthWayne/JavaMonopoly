package com.hsel.monopoly.menu.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import org.jdesktop.swingx.JXButton;

import com.hsel.defaults.ui.Schriftgroesse;
import com.hsel.monopoly.ui.components.MainMenuButtonUI;
import com.hsel.monopoly.ui.components.OverlayDialog_pnl;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Monopoly_frm extends JFrame {
	
	private JPanel pnlContent;
	private OverlayDialog_pnl pnlOverlay;
	private MainMenu_pnl pnlMainMenu;
	private JXButton btnNeustart;
	private JXButton btnHauptmenu;
	private JXButton btnSpielBeenden;
	private JXButton btnFortsetzen;
	private SpielerErstellen_pnl pnlSpielerErstellen;

	public Monopoly_frm() {
		initialize();
	}
	
	private void initialize() {
		setTitle("Monopoly");
		setUndecorated(true);
		setSize(getToolkit().getScreenSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/icons/Icon.png")).getImage());
		getContentPane().setLayout(new OverlayLayout(getContentPane()));
		getContentPane().add(getPnlSpielerErstellen());
		getContentPane().add(getPnlOverlay());
		getContentPane().add(getPnlContent());
	}
	
	public JPanel getPnlContent() {
		if (pnlContent == null) {
			pnlContent = new JPanel();
			pnlContent.setLayout(new BorderLayout());
			pnlContent.add(getPnlMainMenu(), BorderLayout.CENTER);
		}
		return pnlContent;
	}
	
	public SpielerErstellen_pnl getPnlSpielerErstellen() {
		if (pnlSpielerErstellen == null) {
			pnlSpielerErstellen = new SpielerErstellen_pnl();
			pnlSpielerErstellen.setVisible(false);
		}
		return pnlSpielerErstellen;
	}
	
	public OverlayDialog_pnl getPnlOverlay() {
		if (pnlOverlay == null) {
			pnlOverlay = new OverlayDialog_pnl();
			pnlOverlay.getPnlDialog()
					.setLayout(new FormLayout(
							new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC,
									ColumnSpec.decode("200px"), FormSpecs.RELATED_GAP_COLSPEC, },
							new RowSpec[] { RowSpec.decode("30dlu"), RowSpec.decode("30dlu"),
									FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("30dlu"),
									FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("30dlu"),
									FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("30dlu"),
									FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("30dlu"), }));
			pnlOverlay.getPnlDialog().add(getBtnFortsetzen(), "2, 2");
			pnlOverlay.getPnlDialog().add(getBtnNeustart(), "2, 4");
			pnlOverlay.getPnlDialog().add(getBtnHauptmenu(), "2, 6");
			pnlOverlay.getPnlDialog().add(getBtnSpielBeenden(), "2, 8");
			pnlOverlay.setVisible(false);
		}
		return pnlOverlay;
	}

	public MainMenu_pnl getPnlMainMenu() {
		if (this.pnlMainMenu == null) {
			this.pnlMainMenu = new MainMenu_pnl();
		}
		return this.pnlMainMenu;
	}
	
	public JXButton getBtnNeustart() {
		if (btnNeustart == null) {
			btnNeustart = new JXButton();
			btnNeustart.setForeground(Color.WHITE);
			btnNeustart.setText("Neustart");
			btnNeustart.setFont(Schriftgroesse.SEHR_GROSS.alsUeberschrift());
			btnNeustart.setUI(new MainMenuButtonUI());
		}
		return btnNeustart;
	}
	
	public JXButton getBtnHauptmenu() {
		if (btnHauptmenu == null) {
			btnHauptmenu = new JXButton();
			btnHauptmenu.setForeground(Color.WHITE);
			btnHauptmenu.setText("Haupmenu");
			btnHauptmenu.setFont(Schriftgroesse.SEHR_GROSS.alsUeberschrift());
			btnHauptmenu.setUI(new MainMenuButtonUI());
		}
		return btnHauptmenu;
	}
	
	public JXButton getBtnSpielBeenden() {
		if (btnSpielBeenden == null) {
			btnSpielBeenden = new JXButton();
			btnSpielBeenden.setForeground(Color.WHITE);
			btnSpielBeenden.setText("Spiel beenden");
			btnSpielBeenden.setFont(Schriftgroesse.SEHR_GROSS.alsUeberschrift());
			btnSpielBeenden.setUI(new MainMenuButtonUI());
		}
		return btnSpielBeenden;
	}

	public JXButton getBtnFortsetzen() {
		if (btnFortsetzen == null) {
			btnFortsetzen = new JXButton();
			btnFortsetzen.setText("Fortsetzen");
			btnFortsetzen.setForeground(Color.WHITE);
			btnFortsetzen.setFont(Schriftgroesse.SEHR_GROSS.alsUeberschrift());
			btnFortsetzen.setUI(new MainMenuButtonUI());
		}
		return btnFortsetzen;
	}
}
