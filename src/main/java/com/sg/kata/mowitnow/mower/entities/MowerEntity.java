package com.sg.kata.mowitnow.mower.entities;

import java.util.List;
import java.util.Objects;

import com.sg.kata.mowitnow.commun.entities.CardinalDirection;
import com.sg.kata.mowitnow.commun.entities.Coordinate;
import com.sg.kata.mowitnow.commun.entities.MowerAction;

public class MowerEntity {

	private Coordinate coordinate;
	private CardinalDirection cardinalDirection;
	private List<MowerAction> actionList;

	public MowerEntity() {

	}

	public MowerEntity(Coordinate coordinate,
			CardinalDirection cardinalDirection, List<MowerAction> actionList) {
		this.coordinate = coordinate;
		this.cardinalDirection = cardinalDirection;
		this.actionList=actionList;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate initialCoordinate) {
		this.coordinate = initialCoordinate;
	}
	
	public CardinalDirection getCardinalDirection() {
		return cardinalDirection;
	}
	public void setCardinalDirection(
			CardinalDirection cardinalDirection) {
		this.cardinalDirection = cardinalDirection;
	}
	public List<MowerAction> getActionList() {
		return actionList;
	}

	public void setActionList(List<MowerAction> actionList) {
		this.actionList = actionList;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MowerEntity other = (MowerEntity) obj;
		return Objects.equals(actionList, other.actionList)
				&& cardinalDirection == other.cardinalDirection
				&& Objects.equals(coordinate, other.coordinate);
	}
	
	
	 

	 
	

}
