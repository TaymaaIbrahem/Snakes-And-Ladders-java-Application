package model;


import java.util.Arrays;

import Utils.SnakeColor;

public class Snake {

	private SnakeColor color;
	private int headTileNumber;
	private int[] headBoardCoordinates;
	private int taileTileNumber;
	private int[] taileBoardCoordinates;
	
	
	public Snake(SnakeColor color, int headTileNumber,int[]headBoardCoordinates,int taileTileNumber, int[] taileBoardCoordinates) {
		this.color = color;
		this.headTileNumber = headTileNumber;
		this.taileTileNumber = taileTileNumber;
		this.headBoardCoordinates = headBoardCoordinates;
		this.taileBoardCoordinates = taileBoardCoordinates;
	}

	
	

	public int[] getHeadBoardCoordinates() {
		return headBoardCoordinates;
	}




	public void setHeadBoardCoordinates(int[] headBoardCoordinates) {
		this.headBoardCoordinates = headBoardCoordinates;
	}




	public int[] getTaileBoardCoordinates() {
		return taileBoardCoordinates;
	}




	public void setTaileBoardCoordinates(int[] taileBoardCoordinates) {
		this.taileBoardCoordinates = taileBoardCoordinates;
	}




	public SnakeColor getColor() {
		return color;
	}


	public void setColor(SnakeColor color) {
		this.color = color;
	}


	public int getHeadTileNumber() {
		return headTileNumber;
	}


	public void setHeadTileNumber(int headTileNumber) {
		this.headTileNumber = headTileNumber;
	}


	public int getTaileTileNumber() {
		return taileTileNumber;
	}


	public void setTaileTileNumber(int endTileNumber) {
		this.taileTileNumber = endTileNumber;
	}




	@Override
	public String toString() {
		return "Snake [color=" + color + ", headTileNumber=" + headTileNumber + ", headBoardCoordinates="
				+ Arrays.toString(headBoardCoordinates) + ", taileTileNumber=" + taileTileNumber
				+ ", taileBoardCoordinates=" + Arrays.toString(taileBoardCoordinates) + "]";
	}
	
	
	
	
}
