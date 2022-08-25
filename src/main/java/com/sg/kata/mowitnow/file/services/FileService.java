package com.sg.kata.mowitnow.file.services;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.sg.kata.mowitnow.file.entities.FileEntity;
/** 
 *  Service to handle initialization,extracting and loading the file content.
 */
public class FileService {

	private FileEntity fileEntity;
	
	public FileService(FileEntity fileEntity)
			throws IOException {

		this.fileEntity = fileEntity;

	}

	public Boolean checkIfFileItExists() throws IOException {
		return Files.exists(fileEntity.getPath());
	}

	public boolean checkIfFileIsNotEmpty() throws IOException {
		return Files.size(fileEntity.getPath()) > 0;
	}
	/** 
	 *  Read and load file lines into fileEntity
	 */
	public void loadFileLines() throws IOException {
		List<String> fileLines = Files
				.readAllLines(this.fileEntity.getPath());
		this.fileEntity.setFileLines(fileLines);
	}
	
	public FileEntity getMowItNowFileEntity() {
		return fileEntity;
	}

	public void setMowItNowFileEntity(FileEntity mowItNowFileEntity) {
		this.fileEntity = mowItNowFileEntity;
	}

	

}
