package model;

import java.util.Arrays;

public class Ladder {
	
	private int numberOfTiles;
	private int startTileNumber;
	private int[] startCoordinates;
	private int endTileNumber;
	private int[] endCoordinates;
	
	
	public Ladder(int numberOfTiles, int startTileNumber,int[] startCoordinates, int endTileNumber,int[] endCoordinates) {
		this.numberOfTiles = numberOfTiles;
		this.startTileNumber = startTileNumber;
		this.startCoordinates = startCoordinates;
		this.endTileNumber = endTileNumber;
		this.endCoordinates = endCoordinates;
	}

	
	

	public int[] getStartCoordinates() {
		return startCoordinates;
	}




	public void setStartCoordinates(int[] startCoordinates) {
		this.startCoordinates = startCoordinates;
	}




	public int[] getEndCoordinates() {
		return endCoordinates;
	}




	public void setEndCoordinates(int[] endCoordinates) {
		this.endCoordinates = endCoordinates;
	}




	public int getNumberOfTiles() {
		return numberOfTiles;
	}


	public void setNumberOfTiles(int numberOfTiles) {
		this.numberOfTiles = numberOfTiles;
	}


	public int getStartTileNumber() {
		return startTileNumber;
	}


	public void setStartTileNumber(int startTileNumber) {
		this.startTileNumber = startTileNumber;
	}


	public int getEndTileNumber() {
		return endTileNumber;
	}


	public void setEndTileNumber(int endTileNumber) {
		this.endTileNumber = endTileNumber;
	}




	@Override
	public String toString() {
		return "Ladder [numberOfTiles=" + numberOfTiles + ", startTileNumber=" + startTileNumber + ", startCoordinates="
				+ Arrays.toString(startCoordinates) + ", endTileNumber=" + endTileNumber + ", endCoordinates="
				+ Arrays.toString(endCoordinates) + "]";
	}

	
}
