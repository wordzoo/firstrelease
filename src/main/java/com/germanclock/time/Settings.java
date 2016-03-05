package com.germanclock.time;

public class Settings {
	private Integer umgangssprachlich;
	
	public void setUmgangssprachlich(Integer umgangssprachlich) {
		this.umgangssprachlich = umgangssprachlich;
		
	}
	
	public Integer getUmgangssprachlich() {
		return this.umgangssprachlich;
		
	}
	
	public Boolean isFormal() {
		if(getUmgangssprachlich() < 5)
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}
	
}
