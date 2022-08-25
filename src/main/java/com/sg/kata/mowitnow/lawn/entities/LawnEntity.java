package com.sg.kata.mowitnow.lawn.entities;

import com.sg.kata.mowitnow.commun.entities.Coordinate;

public class LawnEntity {

	private Coordinate lawnMaxCoordinate;


	public LawnEntity(Coordinate lawnMaxCoordinate) {
		this.lawnMaxCoordinate = lawnMaxCoordinate;
	}

	public Coordinate getLawnMaxCoordinate() {
		return lawnMaxCoordinate;
	}

	public void setLawnMaxCoordinate(Coordinate lawnMaxCoordinate) {
		this.lawnMaxCoordinate = lawnMaxCoordinate;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LawnEntity other = (LawnEntity) obj;
		if (lawnMaxCoordinate == null) {
			if (other.lawnMaxCoordinate != null)
				return false;
		} else if (!lawnMaxCoordinate.equals(other.lawnMaxCoordinate))
			return false;
		return true;
	}

	
	
	
}
