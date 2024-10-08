package model;


import Interfaces.TileMessage;
import Utils.Direction;
import Utils.PlayerColor;

public class Tile implements TileMessage{

	private int tileNumber;
	private PlayerColor tileColor;
	protected String message;
	
	public Tile(int tileNumber, PlayerColor tileColor) {
		this.tileNumber = tileNumber;
		this.tileColor = tileColor;
	}

	public int getTileNumber() {
		return tileNumber;
	}

	public void setTileNumber(int tileNumber) {
		this.tileNumber = tileNumber;
	}

	public PlayerColor getTileColor() {
		return tileColor;
	}

	public void setTileColor(PlayerColor tileColor) {
		this.tileColor = tileColor;
	}

	@Override
	public String toString() {
		return "Tile [tileNumber=" + tileNumber + ", tileColor=" + tileColor + "]";
	}

	public String getMessage() {
		return message;
	}


	@Override
	public String setTileMessage(String message) {
		return "It`s a simple tile "+message;
		
	}

	@Override
	public Boolean checkTileType(Tile tile) {
		// TODO Auto-generated method stub
		return tile instanceof Tile;
	}
	
}
