package com.sg.kata.mowitnow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sg.kata.mowitnow.commun.entities.CardinalDirection;
import com.sg.kata.mowitnow.commun.entities.Coordinate;
import com.sg.kata.mowitnow.commun.entities.MowerAction;
import com.sg.kata.mowitnow.file.entities.FileEntity;
import com.sg.kata.mowitnow.file.services.FileService;
import com.sg.kata.mowitnow.lawn.entities.LawnEntity;
import com.sg.kata.mowitnow.lawn.services.LawnService;
import com.sg.kata.mowitnow.mower.entities.MowerEntity;
import com.sg.kata.mowitnow.mower.services.MowerService;
import com.sg.kata.mowitnow.mowingSimulation.services.MowingSimulationEngine;

public class MowItNowTestService {

	private FileService mowItNowFileService;
	private FileEntity mowItNowFileEntity;

	@Before
	public void initialize() throws IOException {
		mowItNowFileEntity = new FileEntity(MowitNowTestUtils.FILE_URL);
		mowItNowFileService = new FileService(mowItNowFileEntity);
		mowItNowFileService.loadFileLines();
	}

	@Test
	public void checkIfMowerFileNotEmpty() throws IOException {
		assertTrue(mowItNowFileService.checkIfFileItExists());
		assertTrue(mowItNowFileService.checkIfFileIsNotEmpty());
	}
	
	@Test
	public void checkLawnStringCoordinates() throws IOException {
		LawnService lawnService =  new LawnService(mowItNowFileEntity);
		String expected = "5 5";
		String result = lawnService.getLawnData();
		assertEquals(expected, result);
	}
	
	@Test
	public void checkLawnArrayCoordinates() throws IOException {
		LawnService lawnService =  new LawnService(mowItNowFileEntity);
		String[] expected =  {"5","5"};
		String[] result = lawnService.transformLawnDataToStringArray();
		Assert.assertArrayEquals(expected,result);
	}
	@Test
	public void checkBuildingLawnEntityWithCoordinate() throws IOException {
		LawnService lawnService =  new LawnService(mowItNowFileEntity);
		LawnEntity expected = new LawnEntity(new Coordinate(5, 5));
		LawnEntity result = lawnService.buildLawnEntity();
		assertEquals(expected,result);
	}
	
	@Test
	public void checkMower1StringData() throws IOException {
		MowerService mowerService = new MowerService(mowItNowFileEntity);
		String expected ="1 2 N-GAGAGAGAA" ;
		String result = mowerService.getMowersData().get(0);
		assertEquals(expected,result );
	}
	
	@Test
	public void checkMower2StringData() throws IOException {
		MowerService mowerService = new MowerService(mowItNowFileEntity);
		String expected ="3 3 E-AADAADADDA";
		String result = mowerService.getMowersData().get(1);
		assertEquals(expected,result );
	}

	
	@Test
	public void checkMower1ArrayData() throws IOException {
		MowerService mowerService = new MowerService(mowItNowFileEntity);
		String expected ="1 2 N-GAGAGAGAA";
		String result =mowerService.getMowersData().get(0);
		assertEquals(expected,result );
	}
	
	@Test
	public void checkBuildingMower1EntityWithCoordinate() throws IOException {
		MowerService mowerService = new MowerService(mowItNowFileEntity);
		MowerAction []  expectedActionArray =  {MowerAction.G,MowerAction.A,MowerAction.G,MowerAction.A,MowerAction.G,MowerAction.A,MowerAction.G,MowerAction.A,MowerAction.A};
		MowerEntity expected =  new MowerEntity(new Coordinate(1, 2), CardinalDirection.N, new ArrayList<>(Arrays.asList(expectedActionArray)));
		MowerEntity result =  mowerService.buildMowersEntities().get(0);
		assertEquals(expected,result);
	}
	
	@Test
	public void checkBuildingMower2EntityWithCoordinate() throws IOException {
		MowerService mowerService = new MowerService(mowItNowFileEntity);
		MowerAction []  expectedActionArray =  {MowerAction.A,MowerAction.A,MowerAction.D,MowerAction.A,MowerAction.A,MowerAction.D,MowerAction.A,MowerAction.D,MowerAction.D,MowerAction.A};
		MowerEntity expected =  new MowerEntity(new Coordinate(3, 3), CardinalDirection.E,new ArrayList<>(Arrays.asList(expectedActionArray)));
		MowerEntity result =  mowerService.buildMowersEntities().get(1);
		assertEquals(expected,result);
	}
	
	@Test
	public void checkBuildingmowingSimulationEngineCreation() {
		MowingSimulationEngine expected = new MowingSimulationEngine();
		assertEquals(expected,expected);
	}
	
	@Test
	public void checkBuildingMowingSimulationEngineWithParameters() throws IOException {
		
		MowerService mowerService = new MowerService(mowItNowFileEntity);
		LawnService lawnService =  new LawnService(mowItNowFileEntity);
		
		LawnEntity expectedLawnEntity = new LawnEntity(new Coordinate(5, 5));
		List<MowerEntity> expectedMowerList = MowitNowTestUtils.buildExpected2MowersEntity(new Coordinate(1, 2),CardinalDirection.N, new Coordinate(3, 3), CardinalDirection.E);

		MowingSimulationEngine expected = new MowingSimulationEngine(expectedLawnEntity,expectedMowerList);
		
		List<MowerEntity> mowerListResult= 	mowerService.buildMowersEntities();
		LawnEntity lawnEntityResult = lawnService.buildLawnEntity();
		MowingSimulationEngine result = new MowingSimulationEngine(lawnEntityResult,mowerListResult);
		
		assertEquals(expected,result);
	}
	
	@Test
	public void checkIfSimulationActionDFromNorthIsE() throws Exception {
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine();
		CardinalDirection expected = CardinalDirection.E;
		CardinalDirection result= mowingSimulationEngine.calculateNextCardinalDirection(CardinalDirection.N, MowerAction.D);
		assertEquals(expected, result);
	}
	
	@Test
	public void checkIfSimulationActionDFromEstIsS() throws Exception {
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine();
		CardinalDirection expected = CardinalDirection.S;
		CardinalDirection result= mowingSimulationEngine.calculateNextCardinalDirection(CardinalDirection.E, MowerAction.D);
		assertEquals(expected, result);
	}
	
	@Test
	public void checkIfSimulationActionDFromWestIsN() throws Exception {
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine();
		CardinalDirection expected = CardinalDirection.N;
		CardinalDirection result= mowingSimulationEngine.calculateNextCardinalDirection(CardinalDirection.W, MowerAction.D);
		assertEquals(expected, result);
	}
	
	@Test
	public void checkIfSimulationActionGFromNorthIsW() throws Exception {
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine();
		CardinalDirection expected = CardinalDirection.W;
		CardinalDirection result= mowingSimulationEngine.calculateNextCardinalDirection(CardinalDirection.N, MowerAction.G);
		assertEquals(expected, result);
	}
	
	@Test
	public void checkIfSimulationActionGFromEstIsN() throws Exception {
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine();
		CardinalDirection expected = CardinalDirection.N;
		CardinalDirection result= mowingSimulationEngine.calculateNextCardinalDirection(CardinalDirection.E, MowerAction.G);
		assertEquals(expected, result);
	}
	
	@Test(expected= Exception.class)
	public void checkThrowExceptionWhenNullValue() throws Exception {
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine();
		CardinalDirection expected = CardinalDirection.N;
		CardinalDirection result= mowingSimulationEngine.calculateNextCardinalDirection(null,null);
		assertEquals(expected, result);
	}
	
	@Test
	public void checkSimulationActionDFromNorthIsEWithingEntity() throws Exception {
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine();
		
		MowerAction []  actionArray =  {MowerAction.D};
		MowerEntity givenMowerEntity =  new MowerEntity(new Coordinate(1, 1), CardinalDirection.N, new ArrayList<>(Arrays.asList(actionArray)));
		
		MowerEntity expected = new MowerEntity(new Coordinate(1, 1), CardinalDirection.E, new ArrayList<>(Arrays.asList(actionArray)));
		
	    mowingSimulationEngine.handleMowerActions(givenMowerEntity);
	   
	     assertEquals(expected, givenMowerEntity);

	}
	
	@Test
	public void checkSimulationActionDFromEstIsSWithingEntity() throws Exception {
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine();
		
		MowerAction []  actionArray =  {MowerAction.D};
		MowerEntity givenMowerEntity =  new MowerEntity(new Coordinate(1, 1), CardinalDirection.E, new ArrayList<>(Arrays.asList(actionArray)));
		
		MowerEntity expected = new MowerEntity(new Coordinate(1, 1), CardinalDirection.S, new ArrayList<>(Arrays.asList(actionArray)));
		
	    mowingSimulationEngine.handleMowerActions(givenMowerEntity);
	   
	     assertEquals(expected, givenMowerEntity);

	}
	
	@Test
	public void checkSimulationActionsGGFromSouthIsNWithingEntity() throws Exception {
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine();
		
		MowerAction []  actionArray =  {MowerAction.G,MowerAction.G};
		MowerEntity givenMowerEntity =  new MowerEntity(new Coordinate(1, 1), CardinalDirection.S, new ArrayList<>(Arrays.asList(actionArray)));
		
		MowerEntity expected = new MowerEntity(new Coordinate(1, 1), CardinalDirection.N, new ArrayList<>(Arrays.asList(actionArray)));
		
	    mowingSimulationEngine.handleMowerActions(givenMowerEntity);
	   
	     assertEquals(expected, givenMowerEntity);

	}
	
	
	@Test
	public void checkSimulationActionsAToNorthDirection() throws Exception {
		LawnService lawnService =  new LawnService(mowItNowFileEntity);
		LawnEntity lawnEntityResult = lawnService.buildLawnEntity();
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine(lawnEntityResult,null);
		
		MowerAction []  actionArray =  {MowerAction.A};
		MowerEntity givenMowerEntity =  new MowerEntity(new Coordinate(1, 1), CardinalDirection.N, new ArrayList<>(Arrays.asList(actionArray)));
		
		MowerEntity expected = new MowerEntity(new Coordinate(1, 2), CardinalDirection.N, new ArrayList<>(Arrays.asList(actionArray)));
		
	     mowingSimulationEngine.handleMowerActions(givenMowerEntity);
	     assertEquals(expected, givenMowerEntity);

	}
	
	@Test
	public void checkSimulationActionsAToNorthWhenMaxCoordinate() throws Exception {
		
		LawnService lawnService =  new LawnService(mowItNowFileEntity);
		LawnEntity lawnEntityResult = lawnService.buildLawnEntity();
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine(lawnEntityResult,null);
		
		MowerAction []  actionArray =  {MowerAction.A};
		MowerEntity givenMowerEntity =  new MowerEntity(new Coordinate(5, 5), CardinalDirection.N, new ArrayList<>(Arrays.asList(actionArray)));
		
		MowerEntity expected = new MowerEntity(new Coordinate(5, 5), CardinalDirection.N, new ArrayList<>(Arrays.asList(actionArray)));
		
	    mowingSimulationEngine.handleMowerActions(givenMowerEntity);
	   
		assertEquals(expected, givenMowerEntity);

	}
	
	
	@Test
	public void checkSimulateService() throws IOException {
		
		LawnService lawnService =  new LawnService(mowItNowFileEntity);
		LawnEntity lawnEntityResult = lawnService.buildLawnEntity();
		
		MowerService mowerService = new MowerService(mowItNowFileEntity);
		List<MowerEntity> mowerListResult= 	mowerService.buildMowersEntities();
		
		MowingSimulationEngine mowingSimulationEngine = new MowingSimulationEngine(lawnEntityResult,mowerListResult);
		
		List<MowerEntity> expectedMowerList = MowitNowTestUtils.buildExpected2MowersEntity(new Coordinate(1, 3),CardinalDirection.N, new Coordinate(5, 1), CardinalDirection.E);
		
		List<MowerEntity> mowerEntitiesResult=  mowingSimulationEngine.simulate();
		
		assertEquals(expectedMowerList, mowerEntitiesResult);
		
	}
	
	
	
	
	

}
