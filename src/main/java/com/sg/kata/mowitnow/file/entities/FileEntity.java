package com.sg.kata.mowitnow.file.entities;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileEntity {

	private String url;
	private Path path;
	private List<String> fileLines;

	public FileEntity(String url) {
		this.url = url;
		this.path = Paths.get(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public List<String> getFileLines() {
		return fileLines;
	}

	public void setFileLines(List<String> fileLines) {
		this.fileLines = fileLines;
	}
	
	public String getFileLineAt(int index) {
		return this.fileLines.get(index);
	}
	
	 

}
