package com.hsel.monopoly.app;

import java.util.Random;

public class Wuerfel {
	
	private static Wuerfel instance;
	private static final int GEFAENGNIS = 3;
	private int augen;
	private int paschnr;
	
	private Wuerfel() {
	}

	public static Wuerfel getInstance() {
		if (instance == null) {
			instance = new Wuerfel();
		}
		return instance;
	}
	
	public int[] wuerfeln() {
		Random r = new Random();
		int wuerfel1 = r.nextInt(6) + 1;
		int wuerfel2 = r.nextInt(6) + 1;
		if (wuerfel1 == wuerfel2) {
			paschnr++;
		}
		else {
			paschnr = 0;
		}
		augen = wuerfel1 + wuerfel2;
		return new int[] { wuerfel1, wuerfel2 };
	}

	public int getAugen() {
		return augen;
	}
	
	public boolean hattePasch() {
		return paschnr > 0;
	}

	public boolean mussInsGefaengnis() {
		return paschnr >= GEFAENGNIS;
	}

	public void naechsterSpieler() {
		paschnr = 0;
	}
}