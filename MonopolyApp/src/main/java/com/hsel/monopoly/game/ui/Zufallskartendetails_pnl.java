package com.hsel.monopoly.game.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.jdesktop.swingx.border.DropShadowBorder;

import com.hsel.monopoly.app.AppConfig;
import com.hsel.monopoly.entities.Zufallskarte;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Zufallskartendetails_pnl extends JPanel {
	
	private JPanel pnlContent;
	private JTextPane tpnText;

	public Zufallskartendetails_pnl(final Zufallskarte karte) {
		setPreferredSize(new Dimension(300, 200));
		initialize();
		StringBuilder stbText = new StringBuilder();
		stbText.append("<html> <center> <font face=\"Calibri Light\" size = 4>");
		stbText.append(karte.getText());
		stbText.append("</font> </center> </html>");
		getTpnText().setText(stbText.toString());
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

	public JPanel getPnlContent() {
		if (pnlContent == null) {
			pnlContent = new JPanel();
			pnlContent.setBackground(Color.WHITE);
			pnlContent
					.setLayout(
							new FormLayout(new ColumnSpec[] { ColumnSpec.decode("50dlu:grow"), },
									new RowSpec[] { RowSpec.decode("9dlu:grow"),
											FormSpecs.DEFAULT_ROWSPEC,
											RowSpec.decode("9dlu:grow"), }));
			pnlContent.add(getTpnText(), "1, 2, fill, fill");
		}
		return pnlContent;
	}
	
	private JTextPane getTpnText() {
		if (tpnText == null) {
			tpnText = new JTextPane();
			tpnText.setContentType("text/html");
		}
		return tpnText;
	}
}
