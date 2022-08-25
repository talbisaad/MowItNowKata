package com.sg.kata.mowitnow.mowingSimulation.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.sg.kata.mowitnow.commun.entities.CardinalDirection;
import com.sg.kata.mowitnow.commun.entities.Coordinate;
import com.sg.kata.mowitnow.commun.entities.MowerAction;
import com.sg.kata.mowitnow.lawn.entities.LawnEntity;
import com.sg.kata.mowitnow.mower.entities.MowerEntity;

public class MowingSimulationEngine {

	private LawnEntity lawnEntity;
	private List<MowerEntity> mowerEntityList;
	
	public MowingSimulationEngine() {
	}

	public MowingSimulationEngine(LawnEntity lawnEntity,
			List<MowerEntity> mowerEntityList) {
		this.lawnEntity = lawnEntity;
		this.mowerEntityList = mowerEntityList;
	}
	
	/** 
	 *  service to handle mowing simulation, it set mowers final coordinates and cardinal direction
	 */
	public List<MowerEntity> simulate() {
		return mowerEntityList.stream().map(this::handleMowerActions).collect(Collectors.toList());
	}
	
	/** 
	 *  service to manage a given list of mower actions D/G/A
	 */
	public MowerEntity handleMowerActions(MowerEntity givenMowerEntity)   {
		
		givenMowerEntity.getActionList().stream().forEach(action -> {
			
			if(MowerAction.A.equals(action)) {
				
				givenMowerEntity.setCoordinate(calculateNextCoordinate(givenMowerEntity.getCardinalDirection(), givenMowerEntity.getCoordinate()));
				
			}else {
				
				try {
					givenMowerEntity.setCardinalDirection(calculateNextCardinalDirection(givenMowerEntity.getCardinalDirection(), action));
				} 
				catch (Exception e) {
				  System.out.println(e.getMessage());
				}
				
			}
	   });
		
		
		return givenMowerEntity;
	 
	}
	
	/** 
	 *  given a current cardinal direction E/N/S/W and a action G/D, this service compute next cardinalDirection
	 */
	public CardinalDirection calculateNextCardinalDirection(CardinalDirection currentCardinalDirection, MowerAction mowerAction) throws Exception {
		
		
		if(currentCardinalDirection!=null && mowerAction!=null) {
			
		int nextDirectionDegre = calculateNextDirectionDegre(currentCardinalDirection.getDegreValue(),mowerAction.getActionValue());
		
		for(CardinalDirection direction : CardinalDirection.values()) {
			if( nextDirectionDegre == direction.getDegreValue()) {
				return direction;
			}
		}
		
		}
		
		throw new Exception("Cardinal direction calculation error !");
		
	}
	
	/** 
	 *  given a current cardinal direction E/N/S/W and a current coordinate (x,y) , this service compute next coordinate
	 */
	private Coordinate calculateNextCoordinate(CardinalDirection cardinalDirection, Coordinate currentCoordinate) {
		
		switch (cardinalDirection) {
			case N :
					if(currentCoordinate.getY() < lawnEntity.getLawnMaxCoordinate().getY())
						currentCoordinate.setY(currentCoordinate.getY()+1);
				break;
			case E :
				if(currentCoordinate.getX() < lawnEntity.getLawnMaxCoordinate().getX())
					currentCoordinate.setX(currentCoordinate.getX()+1);
				break;
			case S :
				if(currentCoordinate.getY() >0)
					currentCoordinate.setY(currentCoordinate.getY()-1);
				break;
			case W :
				if(currentCoordinate.getX() >0 )
					currentCoordinate.setX(currentCoordinate.getX()-1);
				break;

			default : 
				break;
		}
		return currentCoordinate;
		
	}
	
	/** 
	 *   "sub-service" to calculate the next cardinal direction degre
	 */
	private int calculateNextDirectionDegre(int currentCardinalDirectionDegreValue, int actionDegreValue) {
		
		int nextDirectionDegre = (currentCardinalDirectionDegreValue + actionDegreValue) % 360;
		return nextDirectionDegre < 0 ? nextDirectionDegre+=360 :nextDirectionDegre ;
	}
	 
	
	 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MowingSimulationEngine other = (MowingSimulationEngine) obj;
		return Objects.equals(lawnEntity, other.lawnEntity)
				&& Objects.equals(mowerEntityList, other.mowerEntityList);
	}

	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

