package com.hsel.monopoly.connector;

import com.hsel.monopoly.backend.service.impl.MonopolyServices_impl;
import com.hsel.monopoly.service.MonopolyServices;

public class FassadeFactory {

	private static FassadeFactory instance;
	
	private FassadeFactory() {
	}
	
	public static FassadeFactory getInstance() {
		if (instance == null) {
			instance = new FassadeFactory();
		}
		return instance;
	}

	private MonopolyServices monopolyService;
	
	public MonopolyServices getMonopolyService() {
		if (monopolyService == null) {
			monopolyService = new MonopolyServices_impl();
		}
		return monopolyService;
	}
}