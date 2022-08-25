package com.sg.kata.mowitnow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sg.kata.mowitnow.commun.entities.CardinalDirection;
import com.sg.kata.mowitnow.commun.entities.Coordinate;
import com.sg.kata.mowitnow.commun.entities.MowerAction;
import com.sg.kata.mowitnow.mower.entities.MowerEntity;

public class MowitNowTestUtils {
	
	public static final String FILE_URL = "src/test/resources/MowerInstructions.txt";

	public static List<MowerEntity> buildExpected2MowersEntity(Coordinate mower1Coordinate, CardinalDirection mower1Direction, Coordinate mower2Coordinate, CardinalDirection mower2Direction) {
		MowerAction []  expectedMower1ActionArray =  {MowerAction.G,MowerAction.A,MowerAction.G,MowerAction.A,MowerAction.G,MowerAction.A,MowerAction.G,MowerAction.A,MowerAction.A};
		MowerEntity expectedMowerEntity1 =  new MowerEntity(mower1Coordinate, mower1Direction, new ArrayList<>(Arrays.asList(expectedMower1ActionArray)));

		MowerAction []  expectedMower2ActionArray =  {MowerAction.A,MowerAction.A,MowerAction.D,MowerAction.A,MowerAction.A,MowerAction.D,MowerAction.A,MowerAction.D,MowerAction.D,MowerAction.A};
		MowerEntity expectedMowerEntity2 =  new MowerEntity(mower2Coordinate, mower2Direction,new ArrayList<>(Arrays.asList(expectedMower2ActionArray)));
		
		List<MowerEntity> mowerList = new ArrayList<>();
		mowerList.add(expectedMowerEntity1);
		mowerList.add(expectedMowerEntity2);
		return mowerList;
	}	
	
	
}
