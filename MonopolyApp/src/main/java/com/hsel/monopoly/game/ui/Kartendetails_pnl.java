package com.hsel.monopoly.game.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.border.DropShadowBorder;

import com.hsel.defaults.ui.Schriftgroesse;
import com.hsel.monopoly.app.AppConfig;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Kartendetails_pnl extends JPanel {

	private JPanel pnlContent;
	private JLabel lblStrassenname;
	private JLabel lblKaufpreisTag;
	private JLabel lblMiete0Tag;
	private JLabel lblHausKostet;
	private JLabel lblMiete1Tag;
	private JLabel lblMiete2Tag;
	private JLabel lblMiete3Tag;
	private JLabel lblMiete4Tag;
	private JLabel lblMiete5Tag;
	private JLabel lblMiete0;
	private JLabel lblMiete1;
	private JLabel lblMiete2;
	private JLabel lblMiete3;
	private JLabel lblMiete4;
	private JLabel lblMiete5;
	private JLabel lblHotelKostet;
	private JLabel lblBesitzrechtkarte;
	private JPanel pnlTop;
	private JLabel lblHauspreis;
	private JLabel lblHotelpreis;
	private JLabel lblKaufpreis;
	private JLabel lblHypothekenwertTag;
	private JLabel lblHypothekenwert;

	public Kartendetails_pnl() {
		initialize();
	}
	
	private void initialize() {
		DropShadowBorder border = new DropShadowBorder();
		border.setShowBottomShadow(true);
		border.setShowLeftShadow(true);
		border.setShowRightShadow(true);
		border.setShowTopShadow(true);
		setBorder(border);
		setBackground(AppConfig.farbeSpielfeld);
		setLayout(new BorderLayout());
		add(getPnlContent(), BorderLayout.CENTER);
	}
	
	private JPanel getPnlContent() {
		if (pnlContent == null) {
			pnlContent = new JPanel();
			pnlContent.setBackground(Color.WHITE);
			pnlContent.setLayout(new FormLayout(
					new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("80dlu"),
							FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("60dlu"),
							FormSpecs.UNRELATED_GAP_COLSPEC, },
					new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.PARAGRAPH_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.PARAGRAPH_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, }));
			pnlContent.add(getPnlTop(), "1, 1, 5, 1, fill, fill");
			pnlContent.add(getLblKaufpreisTag(), "2, 3");
			pnlContent.add(getLblKaufpreis(), "4, 3");
			pnlContent.add(getLblHypothekenwertTag(), "2, 5");
			pnlContent.add(getLblHypothekenwert(), "4, 5");
			pnlContent.add(getLblMiete0Tag(), "2, 7");
			pnlContent.add(getLblMiete0(), "4, 7");
			pnlContent.add(getLblMiete1Tag(), "2, 9");
			pnlContent.add(getLblMiete1(), "4, 9");
			pnlContent.add(getLblMiete2Tag(), "2, 11");
			pnlContent.add(getLblMiete2(), "4, 11");
			pnlContent.add(getLblMiete3Tag(), "2, 13");
			pnlContent.add(getLblMiete3(), "4, 13");
			pnlContent.add(getLblMiete4Tag(), "2, 15");
			pnlContent.add(getLblMiete4(), "4, 15");
			pnlContent.add(getLblMiete5Tag(), "2, 17");
			pnlContent.add(getLblMiete5(), "4, 17");
			pnlContent.add(getLblHausKostet(), "2, 19");
			pnlContent.add(getLblHauspreis(), "4, 19");
			pnlContent.add(getLblHotelKostet(), "2, 21");
			pnlContent.add(getLblHotelpreis(), "4, 21");
		}
		return pnlContent;
	}

	public JLabel getLblStrassenname() {
		if (lblStrassenname == null) {
			lblStrassenname = new JLabel("M\u00FCnchener Strasse");
			lblStrassenname.setFont(Schriftgroesse.GROESSER.alsUeberschrift());
			lblStrassenname.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblStrassenname;
	}

	private JLabel getLblKaufpreisTag() {
		if (lblKaufpreisTag == null) {
			lblKaufpreisTag = new JLabel("Kaufpreis");
			lblKaufpreisTag.setForeground(Color.DARK_GRAY);
			lblKaufpreisTag.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return lblKaufpreisTag;
	}

	private JLabel getLblMiete0Tag() {
		if (lblMiete0Tag == null) {
			lblMiete0Tag = new JLabel("Miete ohne Haus");
			lblMiete0Tag.setForeground(Color.DARK_GRAY);
			lblMiete0Tag.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return lblMiete0Tag;
	}

	private JLabel getLblHausKostet() {
		if (lblHausKostet == null) {
			lblHausKostet = new JLabel("1 Haus kostet:");
			lblHausKostet.setForeground(Color.DARK_GRAY);
			lblHausKostet.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return lblHausKostet;
	}

	private JLabel getLblMiete1Tag() {
		if (lblMiete1Tag == null) {
			lblMiete1Tag = new JLabel("Miete mit 1 Haus");
			lblMiete1Tag.setForeground(Color.DARK_GRAY);
			lblMiete1Tag.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return lblMiete1Tag;
	}

	private JLabel getLblMiete2Tag() {
		if (lblMiete2Tag == null) {
			lblMiete2Tag = new JLabel("Miete mit 2 H\u00E4usern");
			lblMiete2Tag.setForeground(Color.DARK_GRAY);
			lblMiete2Tag.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return lblMiete2Tag;
	}

	private JLabel getLblMiete3Tag() {
		if (lblMiete3Tag == null) {
			lblMiete3Tag = new JLabel("Miete mit 3 H\u00E4usern");
			lblMiete3Tag.setForeground(Color.DARK_GRAY);
			lblMiete3Tag.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return lblMiete3Tag;
	}

	private JLabel getLblMiete4Tag() {
		if (lblMiete4Tag == null) {
			lblMiete4Tag = new JLabel("Miete mit 4 H\u00E4usern");
			lblMiete4Tag.setForeground(Color.DARK_GRAY);
			lblMiete4Tag.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return lblMiete4Tag;
	}

	private JLabel getLblMiete5Tag() {
		if (lblMiete5Tag == null) {
			lblMiete5Tag = new JLabel("Miete mit 1 Hotel");
			lblMiete5Tag.setForeground(Color.DARK_GRAY);
			lblMiete5Tag.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return lblMiete5Tag;
	}
	
	public JLabel getLblMiete0() {
		if (lblMiete0 == null) {
			lblMiete0 = new JLabel("0 \u20AC");
			lblMiete0.setForeground(Color.DARK_GRAY);
			lblMiete0.setFont(Schriftgroesse.NORMAL.alsText());
			lblMiete0.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblMiete0;
	}
	
	public JLabel getLblMiete1() {
		if (lblMiete1 == null) {
			lblMiete1 = new JLabel("0 \u20AC");
			lblMiete1.setForeground(Color.DARK_GRAY);
			lblMiete1.setFont(Schriftgroesse.NORMAL.alsText());
			lblMiete1.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblMiete1;
	}
	
	public JLabel getLblMiete2() {
		if (lblMiete2 == null) {
			lblMiete2 = new JLabel("0 \u20AC");
			lblMiete2.setForeground(Color.DARK_GRAY);
			lblMiete2.setFont(Schriftgroesse.NORMAL.alsText());
			lblMiete2.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblMiete2;
	}
	
	public JLabel getLblMiete3() {
		if (lblMiete3 == null) {
			lblMiete3 = new JLabel("0 \u20AC");
			lblMiete3.setForeground(Color.DARK_GRAY);
			lblMiete3.setFont(Schriftgroesse.NORMAL.alsText());
			lblMiete3.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblMiete3;
	}
	
	public JLabel getLblMiete4() {
		if (lblMiete4 == null) {
			lblMiete4 = new JLabel("0 \u20AC");
			lblMiete4.setForeground(Color.DARK_GRAY);
			lblMiete4.setFont(Schriftgroesse.NORMAL.alsText());
			lblMiete4.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblMiete4;
	}
	
	public JLabel getLblMiete5() {
		if (lblMiete5 == null) {
			lblMiete5 = new JLabel("0 \u20AC");
			lblMiete5.setForeground(Color.DARK_GRAY);
			lblMiete5.setFont(Schriftgroesse.NORMAL.alsText());
			lblMiete5.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblMiete5;
	}
	
	private JLabel getLblHotelKostet() {
		if (lblHotelKostet == null) {
			lblHotelKostet = new JLabel("1 Hotel kostet:");
			lblHotelKostet.setForeground(Color.DARK_GRAY);
			lblHotelKostet.setFont(Schriftgroesse.NORMAL.alsText());
		}
		return lblHotelKostet;
	}
	
	private JLabel getLblBesitzrechtkarte() {
		if (lblBesitzrechtkarte == null) {
			lblBesitzrechtkarte = new JLabel("Besitzrechtkarte");
			lblBesitzrechtkarte.setFont(Schriftgroesse.GROSS.alsUeberschrift());
			lblBesitzrechtkarte.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblBesitzrechtkarte;
	}
	
	public JPanel getPnlTop() {
		if (pnlTop == null) {
			pnlTop = new JPanel();
			pnlTop.setBackground(Color.WHITE);
			pnlTop.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("default:grow"), },
					new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
							FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
							FormSpecs.RELATED_GAP_ROWSPEC, }));
			pnlTop.add(getLblBesitzrechtkarte(), "1, 2");
			pnlTop.add(getLblStrassenname(), "1, 4");
		}
		return pnlTop;
	}

	public JLabel getLblHauspreis() {
		if (lblHauspreis == null) {
			lblHauspreis = new JLabel("0 \u20AC");
			lblHauspreis.setForeground(Color.DARK_GRAY);
			lblHauspreis.setFont(Schriftgroesse.NORMAL.alsText());
			lblHauspreis.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblHauspreis;
	}

	public JLabel getLblHotelpreis() {
		if (lblHotelpreis == null) {
			lblHotelpreis = new JLabel("0 \u20AC");
			lblHotelpreis.setForeground(Color.DARK_GRAY);
			lblHotelpreis.setFont(Schriftgroesse.NORMAL.alsText());
			lblHotelpreis.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblHotelpreis;
	}

	public JLabel getLblKaufpreis() {
		if (lblKaufpreis == null) {
			lblKaufpreis = new JLabel("0 \u20AC");
			lblKaufpreis.setForeground(Color.DARK_GRAY);
			lblKaufpreis.setFont(Schriftgroesse.NORMAL.alsText());
			lblKaufpreis.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblKaufpreis;
	}
	
	private JLabel getLblHypothekenwertTag() {
		if (lblHypothekenwertTag == null) {
			lblHypothekenwertTag = new JLabel("Hypothekenwert");
			lblHypothekenwertTag.setForeground(Color.DARK_GRAY);
			lblHypothekenwertTag.setFont(new Font("Calibri", Font.PLAIN, 12));
		}
		return lblHypothekenwertTag;
	}
	
	public JLabel getLblHypothekenwert() {
		if (lblHypothekenwert == null) {
			lblHypothekenwert = new JLabel("0 \u20AC");
			lblHypothekenwert.setHorizontalAlignment(SwingConstants.RIGHT);
			lblHypothekenwert.setForeground(Color.DARK_GRAY);
			lblHypothekenwert.setFont(new Font("Calibri", Font.PLAIN, 12));
		}
		return lblHypothekenwert;
	}
}
