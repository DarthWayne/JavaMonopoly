package com.hsel.monopoly.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.border.DropShadowBorder;

import com.hsel.ui.components.panel.TexturedPanel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class OverlayDialog_pnl extends TexturedPanel {

	private JPanel pnlSchatten;
	private JPanel pnlDialog;

	public OverlayDialog_pnl() {
		initialize();
	}

	private void initialize() {
		setOpaque(false);
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("50dlu:grow"), FormSpecs.DEFAULT_COLSPEC,
						ColumnSpec.decode("50dlu:grow"), },
				new RowSpec[] { RowSpec.decode("50dlu:grow"), FormSpecs.DEFAULT_ROWSPEC,
						RowSpec.decode("50dlu:grow"), }));
		add(getPnlSchatten(), "2, 2, fill, fill");
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseMoved(final MouseEvent e) {
				// Sorgt dafür, dass das darunterliegende Panel keine Mouse
				// Events bekommt, was zu ungewollten Rollovereffeckten führen
				// kann.
				e.consume();
			}
		});

		ImageIcon img = new ImageIcon(getClass().getResource("/Bilder/Abdunkelung.png"));
		setTextureImage(img.getImage());
	}

	private JPanel getPnlSchatten() {
		if (pnlSchatten == null) {
			pnlSchatten = new JPanel();
			DropShadowBorder shadow = new DropShadowBorder();
			shadow.setShowBottomShadow(true);
			shadow.setShowLeftShadow(true);
			shadow.setShowRightShadow(true);
			shadow.setShowTopShadow(true);
			pnlSchatten.setBorder(shadow);
			pnlSchatten.setOpaque(false);
			pnlSchatten.setLayout(new BorderLayout(0, 0));
			pnlSchatten.add(getPnlDialog(), BorderLayout.CENTER);
		}
		return pnlSchatten;
	}

	public JPanel getPnlDialog() {
		if (pnlDialog == null) {
			pnlDialog = new JPanel();
			pnlDialog.setBackground(Color.GRAY);
			pnlDialog.setBorder(new LineBorder(Color.BLACK));
		}
		return pnlDialog;
	}
}
