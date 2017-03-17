package com.hsel.monopoly.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

import com.hsel.defaults.ui.Schriftgroesse;

public class MainMenuButtonUI extends BasicButtonUI implements Serializable, MouseListener {

	private Font font = Schriftgroesse.RIESIG.alsUeberschrift();
	private boolean hovereffekt;

	public MainMenuButtonUI() {
		hovereffekt = true;
	}

	public MainMenuButtonUI(final boolean hovereffekt) {
		this.hovereffekt = hovereffekt;
	}
	
	@Override
	public void installUI(final JComponent c) {
		super.installUI(c);
		c.addMouseListener(this);
		font = c.getFont();
		installDefaults(c);
	}
	
	private void installDefaults(final JComponent c) {
		c.setBorder(null);
		c.setOpaque(false);
	}

	@Override
	public void uninstallUI(final JComponent c) {
		super.uninstallUI(c);
		c.removeMouseListener(this);
	}

	@Override
	public void paint(final Graphics g, final JComponent c) {
		AbstractButton b = (AbstractButton) c;
		Dimension d = b.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				
		g2d.setFont(c.getFont());
		FontMetrics fm = g2d.getFontMetrics();

		if (c.isEnabled()) {
			g2d.setColor(b.getForeground());
		}
		else {
			g2d.setColor(Color.GRAY);
		}
		String caption = b.getText();
		int x = (d.width - fm.stringWidth(caption)) / 2;
		int y = (d.height + fm.getAscent()) / 2;
		g2d.drawString(caption, x, y);
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
	}

	@Override
	public void mousePressed(final MouseEvent e) {
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		if (c.isEnabled() && hovereffekt) {
			c.setFont(font.deriveFont(font.getSize() + 2F));
			c.repaint();
		}
	}

	@Override
	public void mouseExited(final MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		c.setFont(font);
		c.repaint();
	}
}