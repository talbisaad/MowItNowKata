package com.sg.kata.mowitnow.mower.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sg.kata.mowitnow.commun.entities.CardinalDirection;
import com.sg.kata.mowitnow.commun.entities.Coordinate;
import com.sg.kata.mowitnow.commun.entities.MowerAction;
import com.sg.kata.mowitnow.commun.entities.SEPARATOR;
import com.sg.kata.mowitnow.file.entities.FileEntity;
import com.sg.kata.mowitnow.mower.entities.MowerEntity;

public class MowerService {

	private  FileEntity mowItNowFileEntity;	

	public MowerService(FileEntity mowItNowFileEntity) {
		this.mowItNowFileEntity = mowItNowFileEntity;
	}

	/** 
	 *  build list of mowers entity objects using string data 
	 */
	public List<MowerEntity> buildMowersEntities() throws IOException {
		return getMowersData().stream().map(this::buildMowerEntity).collect(Collectors.toList());
	}
	
	/** 
	 *  build mower entity objects using string data 
	 */
	private MowerEntity buildMowerEntity(String mowerDataLine) {
		
		String[] mowerSplitedData=  mowerDataLine.split(SEPARATOR.DASH_SEPARATOR);
		String[] mowerPositionData= mowerSplitedData[0].substring(0,mowerSplitedData[0].length()-2).split(SEPARATOR.SPACE_SEPARATOR);
		String mowerCardinalDirectionData= String.valueOf(mowerSplitedData[0].charAt(mowerSplitedData[0].length()-1));
		String[] mowerActionArray = mowerSplitedData[1].split(SEPARATOR.CHAR_SEPRATOR);
		
		List<MowerAction> mowerActionList= transformToMowerActionList(mowerActionArray);
		Coordinate mowerCoordinateEntity = Coordinate.transformToCoordinateEntity(mowerPositionData);
		CardinalDirection mowerCardinalDirection = CardinalDirection.valueOf(mowerCardinalDirectionData);
		
		return new MowerEntity(mowerCoordinateEntity, mowerCardinalDirection, mowerActionList);
		
	}

	/** 
	 * transform the action part string data (list) to List of enum value 
	 */
	private List<MowerAction> transformToMowerActionList(
			String[] mowerActionArray) {
		return Arrays.asList(mowerActionArray).stream().map(MowerAction :: valueOf).collect(Collectors.toList());
	}
	
		public List<String> getMowersData() {
			List<String> mawersCoordinates = new ArrayList<>();
			for (int i = 1; i < this.mowItNowFileEntity.getFileLines()
					.size(); i++) {
				mawersCoordinates.add(this.mowItNowFileEntity.getFileLineAt(i).concat(SEPARATOR.DASH_SEPARATOR)
						.concat(this.mowItNowFileEntity.getFileLineAt(i + 1)));
				i++;
			}
			return mawersCoordinates;
		}
		
	
}
