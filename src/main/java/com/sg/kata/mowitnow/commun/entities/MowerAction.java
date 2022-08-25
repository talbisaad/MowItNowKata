package com.sg.kata.mowitnow.commun.entities;

public enum MowerAction {
	D(90),G(-90),A(0);
	
	private int actionValue;
	
	private MowerAction(int actionValue) {
		this.actionValue=actionValue;
	}

	public int getActionValue() {
		return actionValue;
	}
	
}
