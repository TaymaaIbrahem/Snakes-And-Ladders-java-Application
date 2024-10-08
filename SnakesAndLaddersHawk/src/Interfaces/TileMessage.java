package Interfaces;

import Utils.Direction;
import model.Question;
import model.Tile;

public interface TileMessage {
	
	public String setTileMessage(String message);
	public Boolean checkTileType(Tile tile);
}