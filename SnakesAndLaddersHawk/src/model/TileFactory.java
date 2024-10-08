package model;

import Interfaces.TileMessage;
import Utils.PlayerColor;

public class TileFactory {
	
	public TileFactory() {
		
	}
	
	public TileMessage GetTileType(String type,int  tileNum, PlayerColor color) {
		switch(type) {
		case "default":
			return new Tile(tileNum, color);
		case "SurpriseTile":
			return new SurpriseTile(tileNum, color);
		case "QuestionTile":
			return new QuestionTile(tileNum, color);
		}
		return null;
	}

}
