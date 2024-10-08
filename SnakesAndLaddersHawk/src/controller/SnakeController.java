package controller;

import java.util.HashMap;

import model.Main;
import model.Snake;

public class SnakeController {
	private static SnakeController instance;
	//private HashMap<Integer,Integer> snakesPos; 

	private SnakeController() {
		
	}
	 public static SnakeController getInstance() {
	     if (instance == null)
	           instance = new SnakeController();
	     return instance;
	 }
	 //Adds snakes position to the hashmap
	/* public void AddSnake(int tailPos, int headPos) {
		 snakesPos.put(tailPos, headPos);
	 }*/
	 
	 //check if current position is snake's head position return the snake
	 public Snake checkSnake(int tileNum) {
		 HashMap<Integer, Snake> snakes = Main.gameController.getGame().getSnakes();
		 if(snakes.containsKey(tileNum)) {
			 return snakes.get(tileNum);
		 }
		 return null;
	 }
	 
	 //method gets snake and return bath for its image by the color
	 public String getSnakeImagePath(Snake s) {
		
		 switch(s.getColor()) {
		 
		 case Red:
			 return "/hawk_images/rSnake.png";
		 case Yellow:
			 return "/hawk_images/ySnake.png";
		 case Blue:
			 return "/hawk_images/bSnake.png";
		default:
			return "/hawk_images/gSnake.png";
		 }
	}
	 
}
