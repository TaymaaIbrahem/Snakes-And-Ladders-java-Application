
package controller;

import java.util.Random;

import Utils.PlayerColor;
import Utils.Direction;
import model.SurpriseTile;

public class SurpriseTileController {
	
	private static SurpriseTileController instance;
	private SurpriseTileController() {}
	Random r = new Random();

	public static SurpriseTileController getInstance() {
		if(instance == null)
			instance = new SurpriseTileController();
		return instance; 
	}
	
	public SurpriseTile NewTile(int tileNumber, PlayerColor tileColor,Direction jumpingSide) {
		if(jumpingSide == null)
			return new SurpriseTile(tileNumber,tileColor,RandomjumpingSide());
		return new SurpriseTile(tileNumber,tileColor,jumpingSide); 
	}
	public Direction RandomjumpingSide() {
		int i = r.nextInt(1);
		if(i==0) return Direction.Back;
		return Direction.Forward;
	}


}
