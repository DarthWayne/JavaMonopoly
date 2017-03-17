package com.hsel.monopoly.game.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.jdesktop.swingx.JXButton;

import com.hsel.defaults.ui.Schriftgroesse;
import com.hsel.monopoly.app.AppConfig;
import com.hsel.monopoly.game.ui.Feld_pnl.Position;
import com.hsel.monopoly.ui.components.MainMenuButtonUI;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Spielfeld_pnl extends JPanel {

	private Feld_pnl feld10;
	private Feld_pnl feld09;
	private Feld_pnl feld02;
	private Feld_pnl feld08;
	private Feld_pnl feld05;
	private Feld_pnl feld04;
	private Feld_pnl feld03;
	private Feld_pnl feld06;
	private Feld_pnl feld07;
	private Feld_pnl feld01;
	private Feld_pnl feld11;
	private Feld_pnl feld12;
	private Feld_pnl feld18;
	private Feld_pnl feld19;
	private Feld_pnl feld20;
	private Feld_pnl feld14;
	private Feld_pnl feld17;
	private Feld_pnl feld13;
	private Feld_pnl feld16;
	private Feld_pnl feld15;
	private Feld_pnl feld21;
	private JPanel pnlCenter;
	private Feld_pnl feld23;
	private Feld_pnl feld22;
	private Feld_pnl feld24;
	private Feld_pnl feld25;
	private Feld_pnl feld26;
	private Feld_pnl feld27;
	private Feld_pnl feld28;
	private Feld_pnl feld29;
	private Feld_pnl feld30;
	private Feld_pnl feld31;
	private Feld_pnl feld32;
	private Feld_pnl feld33;
	private Feld_pnl feld34;
	private Feld_pnl feld35;
	private Feld_pnl feld36;
	private Feld_pnl feld37;
	private Feld_pnl feld38;
	private Feld_pnl feld39;
	private Feld_pnl feld40;
	private JPanel panel;
	private JPanel pnlButtons;
	private JXButton btnOk;
	private JXButton btnKaufen;
	private JXButton btnNichtKaufen;
	private JXButton btnMieteZahlen;
	private JXButton btnWuerfeln;
	private JLabel lblNachricht;
	private JPanel pnlWuerfel;
	private JLabel lblWuerfel01;
	private JLabel lblWuerfel02;

	public Spielfeld_pnl() {
		initialize();
	}

	public void initialize() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(AppConfig.farbeSpielfeld);
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("50dlu:grow"), ColumnSpec.decode("20dlu:grow"),
						ColumnSpec.decode("20dlu:grow"), ColumnSpec.decode("20dlu:grow"),
						ColumnSpec.decode("20dlu:grow"), ColumnSpec.decode("20dlu:grow"),
						ColumnSpec.decode("20dlu:grow"), ColumnSpec.decode("20dlu:grow"),
						ColumnSpec.decode("20dlu:grow"), ColumnSpec.decode("20dlu:grow"),
						ColumnSpec.decode("50dlu:grow"), },
				new RowSpec[] { RowSpec.decode("50dlu:grow"), RowSpec.decode("20dlu:grow"),
						RowSpec.decode("20dlu:grow"), RowSpec.decode("20dlu:grow"),
						RowSpec.decode("20dlu:grow"), RowSpec.decode("20dlu:grow"),
						RowSpec.decode("20dlu:grow"), RowSpec.decode("20dlu:grow"),
						RowSpec.decode("20dlu:grow"), RowSpec.decode("20dlu:grow"),
						RowSpec.decode("50dlu:grow"), }));
		add(getFeld01(), "1, 1, fill, fill");
		add(getFeld02(), "2, 1, fill, fill");
		add(getFeld03(), "3, 1, fill, fill");
		add(getFeld04(), "4, 1, fill, fill");
		add(getFeld05(), "5, 1, fill, fill");
		add(getFeld06(), "6, 1, fill, fill");
		add(getFeld07(), "7, 1, fill, fill");
		add(getFeld08(), "8, 1, fill, fill");
		add(getFeld09(), "9, 1, fill, fill");
		add(getFeld10(), "10, 1, fill, fill");
		add(getFeld11(), "11, 1, fill, fill");
		add(getFeld40(), "1, 2, fill, fill");
		add(getPanel(), "2, 2, 9, 9, fill, fill");
		add(getFeld12(), "11, 2, fill, fill");
		add(getFeld39(), "1, 3, fill, fill");
		add(getFeld13(), "11, 3, fill, fill");
		add(getFeld38(), "1, 4, fill, fill");
		add(getFeld14(), "11, 4, fill, fill");
		add(getFeld37(), "1, 5, fill, fill");
		add(getFeld15(), "11, 5, fill, fill");
		add(getFeld36(), "1, 6, fill, fill");
		add(getFeld16(), "11, 6, fill, fill");
		add(getFeld35(), "1, 7, fill, fill");
		add(getFeld17(), "11, 7, fill, fill");
		add(getFeld34(), "1, 8, fill, fill");
		add(getFeld18(), "11, 8, fill, fill");
		add(getFeld33(), "1, 9, fill, fill");
		add(getFeld19(), "11, 9, fill, fill");
		add(getFeld32(), "1, 10, fill, fill");
		add(getFeld20(), "11, 10, fill, fill");
		add(getFeld31(), "1, 11, fill, fill");
		add(getFeld30(), "2, 11, fill, fill");
		add(getFeld29(), "3, 11, fill, fill");
		add(getFeld28(), "4, 11, fill, fill");
		add(getFeld27(), "5, 11, fill, fill");
		add(getFeld26(), "6, 11, fill, fill");
		add(getFeld25(), "7, 11, fill, fill");
		add(getFeld24(), "8, 11, fill, fill");
		add(getFeld23(), "9, 11, fill, fill");
		add(getFeld22(), "10, 11, fill, fill");
		add(getFeld21(), "11, 11, fill, fill");
	}

	public Feld_pnl getFeld02() {
		if (feld02 == null) {
			feld02 = new Feld_pnl(Position.NORD);
		}
		return feld02;
	}

	public Feld_pnl getFeld10() {
		if (feld10 == null) {
			feld10 = new Feld_pnl(Position.NORD);
		}
		return feld10;
	}
	
	public Feld_pnl getFeld09() {
		if (feld09 == null) {
			feld09 = new Feld_pnl(Position.NORD);
		}
		return feld09;
	}

	public Feld_pnl getFeld08() {
		if (feld08 == null) {
			feld08 = new Feld_pnl(Position.NORD);
		}
		return feld08;
	}
	
	public Feld_pnl getFeld05() {
		if (feld05 == null) {
			feld05 = new Feld_pnl(Position.NORD);
		}
		return feld05;
	}
	
	public Feld_pnl getFeld04() {
		if (feld04 == null) {
			feld04 = new Feld_pnl(Position.NORD);
		}
		return feld04;
	}
	
	public Feld_pnl getFeld03() {
		if (feld03 == null) {
			feld03 = new Feld_pnl(Position.NORD);
		}
		return feld03;
	}
	
	public Feld_pnl getFeld06() {
		if (feld06 == null) {
			feld06 = new Feld_pnl(Position.NORD);
		}
		return feld06;
	}
	
	public Feld_pnl getFeld07() {
		if (feld07 == null) {
			feld07 = new Feld_pnl(Position.NORD);
		}
		return feld07;
	}
	
	public Feld_pnl getFeld01() {
		if (feld01 == null) {
			feld01 = new Feld_pnl(Position.ECKE);
			feld01.setBorder(new MatteBorder(1, 1, 0, 1, AppConfig.farbeFeldgrenzen));
		}
		return feld01;
	}
	
	public Feld_pnl getFeld11() {
		if (feld11 == null) {
			feld11 = new Feld_pnl(Position.ECKE);
			feld11.setBorder(new MatteBorder(1, 0, 1, 1, AppConfig.farbeFeldgrenzen));
		}
		return feld11;
	}

	public Feld_pnl getFeld12() {
		if (feld12 == null) {
			feld12 = new Feld_pnl(Position.OST);
		}
		return feld12;
	}
	
	public Feld_pnl getFeld18() {
		if (feld18 == null) {
			feld18 = new Feld_pnl(Position.OST);
		}
		return feld18;
	}
	
	public Feld_pnl getFeld19() {
		if (feld19 == null) {
			feld19 = new Feld_pnl(Position.OST);
		}
		return feld19;
	}
	
	public Feld_pnl getFeld20() {
		if (feld20 == null) {
			feld20 = new Feld_pnl(Position.OST);
		}
		return feld20;
	}
	
	public Feld_pnl getFeld14() {
		if (feld14 == null) {
			feld14 = new Feld_pnl(Position.OST);
		}
		return feld14;
	}
	
	public Feld_pnl getFeld17() {
		if (feld17 == null) {
			feld17 = new Feld_pnl(Position.OST);
		}
		return feld17;
	}
	
	public Feld_pnl getFeld13() {
		if (feld13 == null) {
			feld13 = new Feld_pnl(Position.OST);
		}
		return feld13;
	}
	
	public Feld_pnl getFeld16() {
		if (feld16 == null) {
			feld16 = new Feld_pnl(Position.OST);
		}
		return feld16;
	}
	
	public Feld_pnl getFeld15() {
		if (feld15 == null) {
			feld15 = new Feld_pnl(Position.OST);
		}
		return feld15;
	}

	public Feld_pnl getFeld21() {
		if (feld21 == null) {
			feld21 = new Feld_pnl(Position.ECKE);
			feld21.setBorder(new MatteBorder(0, 1, 1, 1, AppConfig.farbeFeldgrenzen));
		}
		return feld21;
	}
	
	public JPanel getPnlCenter() {
		if (pnlCenter == null) {
			pnlCenter = new JPanel();
			pnlCenter.setBackground(Color.WHITE);
			pnlCenter.setLayout(new BorderLayout());
		}
		return pnlCenter;
	}
	
	public Feld_pnl getFeld23() {
		if (feld23 == null) {
			feld23 = new Feld_pnl(Position.SUED);
		}
		return feld23;
	}
	
	public Feld_pnl getFeld22() {
		if (feld22 == null) {
			feld22 = new Feld_pnl(Position.SUED);
		}
		return feld22;
	}
	
	public Feld_pnl getFeld24() {
		if (feld24 == null) {
			feld24 = new Feld_pnl(Position.SUED);
		}
		return feld24;
	}
	
	public Feld_pnl getFeld25() {
		if (feld25 == null) {
			feld25 = new Feld_pnl(Position.SUED);
		}
		return feld25;
	}

	public Feld_pnl getFeld26() {
		if (feld26 == null) {
			feld26 = new Feld_pnl(Position.SUED);
		}
		return feld26;
	}

	public Feld_pnl getFeld27() {
		if (feld27 == null) {
			feld27 = new Feld_pnl(Position.SUED);
		}
		return feld27;
	}

	public Feld_pnl getFeld28() {
		if (feld28 == null) {
			feld28 = new Feld_pnl(Position.SUED);
		}
		return feld28;
	}

	public Feld_pnl getFeld29() {
		if (feld29 == null) {
			feld29 = new Feld_pnl(Position.SUED);
		}
		return feld29;
	}

	public Feld_pnl getFeld30() {
		if (feld30 == null) {
			feld30 = new Feld_pnl(Position.SUED);
		}
		return feld30;
	}

	public Feld_pnl getFeld31() {
		if (feld31 == null) {
			feld31 = new Feld_pnl(Position.ECKE);
			feld31.setBorder(new MatteBorder(1, 1, 1, 0, AppConfig.farbeFeldgrenzen));
		}
		return feld31;
	}
	
	public Feld_pnl getFeld32() {
		if (feld32 == null) {
			feld32 = new Feld_pnl(Position.WEST);
		}
		return feld32;
	}

	public Feld_pnl getFeld33() {
		if (feld33 == null) {
			feld33 = new Feld_pnl(Position.WEST);
		}
		return feld33;
	}

	public Feld_pnl getFeld34() {
		if (feld34 == null) {
			feld34 = new Feld_pnl(Position.WEST);
		}
		return feld34;
	}

	public Feld_pnl getFeld35() {
		if (feld35 == null) {
			feld35 = new Feld_pnl(Position.WEST);
		}
		return feld35;
	}

	public Feld_pnl getFeld36() {
		if (feld36 == null) {
			feld36 = new Feld_pnl(Position.WEST);
		}
		return feld36;
	}

	public Feld_pnl getFeld37() {
		if (feld37 == null) {
			feld37 = new Feld_pnl(Position.WEST);
		}
		return feld37;
	}

	public Feld_pnl getFeld38() {
		if (feld38 == null) {
			feld38 = new Feld_pnl(Position.WEST);
		}
		return feld38;
	}

	public Feld_pnl getFeld39() {
		if (feld39 == null) {
			feld39 = new Feld_pnl(Position.WEST);
		}
		return feld39;
	}

	public Feld_pnl getFeld40() {
		if (feld40 == null) {
			feld40 = new Feld_pnl(Position.WEST);
		}
		return feld40;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(AppConfig.farbeSpielfeld);
			panel.setLayout(new FormLayout(
					new ColumnSpec[] { ColumnSpec.decode("4dlu:grow"), FormSpecs.DEFAULT_COLSPEC,
							ColumnSpec.decode("4dlu:grow"), },
					new RowSpec[] { RowSpec.decode("50dlu"), RowSpec.decode("4dlu:grow"),
							FormSpecs.DEFAULT_ROWSPEC, RowSpec.decode("4dlu:grow"),
							RowSpec.decode("top:40dlu"), }));
			panel.add(getLblNachricht(), "1, 1, 3, 1");
			panel.add(getPnlWuerfel(), "3, 2, 1, 3, fill, fill");
			panel.add(getPnlCenter(), "2, 3");
			panel.add(getPnlButtons(), "1, 5, 3, 1, fill, fill");
		}
		return panel;
	}

	private JPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlButtons.getLayout();
			flowLayout.setHgap(20);
			flowLayout.setVgap(10);
			flowLayout.setAlignOnBaseline(true);
			pnlButtons.setOpaque(false);
			pnlButtons.add(getBtnWuerfeln());
			pnlButtons.add(getBtnOk());
			pnlButtons.add(getBtnKaufen());
			pnlButtons.add(getBtnNichtKaufen());
			pnlButtons.add(getBtnMieteZahlen());
		}
		return pnlButtons;
	}
	
	public JXButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JXButton();
			btnOk.setFont(Schriftgroesse.GROESSER.alsUeberschrift());
			btnOk.setUI(new MainMenuButtonUI());
			btnOk.setText("OK");
		}
		return btnOk;
	}
	
	public JXButton getBtnKaufen() {
		if (btnKaufen == null) {
			btnKaufen = new JXButton();
			btnKaufen.setFont(Schriftgroesse.GROESSER.alsUeberschrift());
			btnKaufen.setUI(new MainMenuButtonUI());
			btnKaufen.setText("Kaufen");
		}
		return btnKaufen;
	}
	
	public JXButton getBtnNichtKaufen() {
		if (btnNichtKaufen == null) {
			btnNichtKaufen = new JXButton();
			btnNichtKaufen.setFont(Schriftgroesse.GROESSER.alsUeberschrift());
			btnNichtKaufen.setUI(new MainMenuButtonUI());
			btnNichtKaufen.setText("Nicht kaufen");
		}
		return btnNichtKaufen;
	}
	
	public JXButton getBtnMieteZahlen() {
		if (btnMieteZahlen == null) {
			btnMieteZahlen = new JXButton();
			btnMieteZahlen.setFont(Schriftgroesse.GROESSER.alsUeberschrift());
			btnMieteZahlen.setUI(new MainMenuButtonUI());
			btnMieteZahlen.setText("Miete zahlen");
		}
		return btnMieteZahlen;
	}
	
	public JXButton getBtnWuerfeln() {
		if (btnWuerfeln == null) {
			btnWuerfeln = new JXButton();
			btnWuerfeln.setFont(Schriftgroesse.GROESSER.alsUeberschrift());
			btnWuerfeln.setUI(new MainMenuButtonUI());
			btnWuerfeln.setText("W\u00FCrfeln");
		}
		return btnWuerfeln;
	}
	
	public JLabel getLblNachricht() {
		if (lblNachricht == null) {
			lblNachricht = new JLabel("Spieler 1 ist dran!");
			lblNachricht.setHorizontalAlignment(SwingConstants.CENTER);
			lblNachricht.setFont(Schriftgroesse.GROESSER.alsUeberschrift());
		}
		return lblNachricht;
	}
	
	private JPanel getPnlWuerfel() {
		if (pnlWuerfel == null) {
			pnlWuerfel = new JPanel();
			pnlWuerfel.setOpaque(false);
			pnlWuerfel.setLayout(new FormLayout(
					new ColumnSpec[] { ColumnSpec.decode("9dlu:grow"), FormSpecs.DEFAULT_COLSPEC,
							ColumnSpec.decode("50dlu"), },
					new RowSpec[] { RowSpec.decode("9dlu:grow"), FormSpecs.DEFAULT_ROWSPEC,
							FormSpecs.PARAGRAPH_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
							RowSpec.decode("9dlu:grow"), }));
			pnlWuerfel.add(getLblWuerfel01(), "2, 2");
			pnlWuerfel.add(getLblWuerfel02(), "2, 4");
		}
		return pnlWuerfel;
	}
	
	public JLabel getLblWuerfel01() {
		if (lblWuerfel01 == null) {
			lblWuerfel01 = new JLabel();
		}
		return lblWuerfel01;
	}
	
	public JLabel getLblWuerfel02() {
		if (lblWuerfel02 == null) {
			lblWuerfel02 = new JLabel();
		}
		return lblWuerfel02;
	}
}
