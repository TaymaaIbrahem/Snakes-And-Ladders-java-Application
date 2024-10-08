package controller;

import Utils.PlayerColor;
import model.Tile;

public class TileController {
	
	private static TileController instance;
	private TileController() {}

	public static TileController getInstance() {
		if(instance == null)
			instance = new TileController();
		return instance; 
	}
	//declare new tile
	public Tile NewTile(int tileNumber,PlayerColor tileColor) {
			return new Tile(tileNumber,tileColor); 

	}


}
