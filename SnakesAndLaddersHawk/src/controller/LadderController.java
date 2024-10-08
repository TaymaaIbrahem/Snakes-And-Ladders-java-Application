package controller;

import java.util.HashMap;

import model.Ladder;
import model.Main;
import model.Snake;

public class LadderController {
	private static LadderController instance;
	//private HashMap<Integer,Integer> ladders;

	 public static LadderController getInstance() {
	     if (instance == null)
	           instance = new LadderController();
	     return instance;
	 }
/*	 public void AddLadder(int startPos, int endPos) {
		 ladders.put(startPos,endPos);
	}*/
	 //check if current position has ladder 
	 //we check the start of the ladder
	public Ladder checkLadder(Integer tileNum) {
		 HashMap<Integer, Ladder> ladders = Main.gameController.getGame().getLadders();
		 if(ladders.containsKey(tileNum)) {
			 
			 return ladders.get(tileNum);
		 }
		 return null;
	}
	
	public String getLadderImagePath(Ladder l) {
		
		switch(l.getNumberOfTiles()) {
		case 1:
			return "/hawk_images/ladder1.png";
		case 2:
			return "/hawk_images/ladder2.png";
		case 3:
			return "/hawk_images/ladder3.png";
		case 4:
			return "/hawk_images/ladder4.png";
		case 5:
			return "/hawk_images/ladder5.png";
		case 6:
			return "/hawk_images/ladder6.png";
		case 7:
			return "/hawk_images/ladder7.png";
		default:
			return "/hawk_images/ladder8.png";
			
			
		}
	}
	 
	 
}
