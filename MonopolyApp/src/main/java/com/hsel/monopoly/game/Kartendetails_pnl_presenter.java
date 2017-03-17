package com.hsel.monopoly.game;

import java.awt.Container;

import com.hsel.defaults.application.ApplicationContext;
import com.hsel.defaults.presenter.TypedPresenter;
import com.hsel.monopoly.app.AppConfig;
import com.hsel.monopoly.entities.Feld;
import com.hsel.monopoly.game.ui.Kartendetails_pnl;

public class Kartendetails_pnl_presenter extends TypedPresenter<Feld> {
	
	public Kartendetails_pnl_presenter(final ApplicationContext appContext,
			final Container objViewContainer) {
		super(appContext, objViewContainer);
	}
	
	@Override
	public AppConfig getAppContext() {
		return (AppConfig) getObjAppContext();
	}
	
	@Override
	public Kartendetails_pnl getView() {
		return (Kartendetails_pnl) getObjViewContainer();
	}
	
	@Override
	public void registerActions() {
	}
	
	@Override
	public void fuellenView(final Feld f) {
		getView().getPnlTop().setBackground(f.getFarbe());
		getView().getLblStrassenname().setText(f.getName_einzeilig());
		getView().getLblKaufpreis().setText(f.getKaufpreis() + " €");
		getView().getLblHypothekenwert().setText(f.getHypothekenwert() + " €");
		getView().getLblMiete0().setText(f.getMiete0() + " €");
		getView().getLblMiete1().setText(f.getMiete1() + " €");
		getView().getLblMiete2().setText(f.getMiete2() + " €");
		getView().getLblMiete3().setText(f.getMiete3() + " €");
		getView().getLblMiete4().setText(f.getMiete4() + " €");
		getView().getLblMiete5().setText(f.getMiete5() + " €");
		getView().getLblHauspreis().setText(f.getHauspreis() + " €");
		getView().getLblHotelpreis().setText(f.getHotelpreis() + " €");
	}
	
	@Override
	public Feld fuellenObjekt() {
		return null;
	}
}