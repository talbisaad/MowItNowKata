package com.sg.kata.mowitnow.commun.entities;


public enum CardinalDirection {
	N(0), E(90), S(180), W(270);

	private int degreValue;

	private CardinalDirection(int degreValue) {
		this.degreValue = degreValue;
	}

	public int getDegreValue() {
		return degreValue;
	}

}
