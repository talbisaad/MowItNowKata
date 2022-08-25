package com.sg.kata.mowitnow.lawn.services;

import java.io.IOException;

import com.sg.kata.mowitnow.commun.entities.Coordinate;
import com.sg.kata.mowitnow.commun.entities.SEPARATOR;
import com.sg.kata.mowitnow.file.entities.FileEntity;
import com.sg.kata.mowitnow.lawn.entities.LawnEntity;

public class LawnService {

	private FileEntity mowItNowFileEntity;
	
	public LawnService(FileEntity mowItNowFileEntity ) {
		this.mowItNowFileEntity=mowItNowFileEntity;
	}
	/** 
	 *  retrieve the string content of lawn from file 
	 */
	public String getLawnData() throws IOException {
		return mowItNowFileEntity.getFileLineAt(0);
	}
	
	/** 
	 *  build Lawn entity objects 
	 */
	public LawnEntity buildLawnEntity() throws IOException {

		String[] lawnCoordinates = transformLawnDataToStringArray();
		return new LawnEntity(Coordinate.transformToCoordinateEntity(lawnCoordinates));
	}
	/** 
	 *  transform the lawn line content to array of String 
	 */	
	public String[] transformLawnDataToStringArray() throws IOException {
		return getLawnData().split(SEPARATOR.SPACE_SEPARATOR);
	}

	

}
