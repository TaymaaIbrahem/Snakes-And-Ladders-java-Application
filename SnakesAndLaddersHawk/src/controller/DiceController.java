package controller;

import java.util.Random;

import model.Player;

public class DiceController {
	
	
	private DiceController() { }
	private static DiceController instance;

	 public static DiceController getInstance() {
	     if (instance == null)
	           instance = new DiceController();
	     return instance;
	 }
	  	
	 
	 
	
	//Function for rollDice, Generates random numbers depends on game difficulty and roll numbers
	 public int rollDice( int gameDifficulty) {
	        Random r = new Random();
	        int randomNum;
	        int excludeVal1 = 6;
	        int excludeVal2 = 7;
	        
	        

	        switch (gameDifficulty) {
	            case 1: //Game Difficulty - Easy - Medium - Hard
	                do {                   
	                	randomNum = r.nextInt(10);
	                } while (randomNum == excludeVal1 || randomNum == excludeVal2);
	                break;
	            case 2: 
	                if (Player.getRollNumber()!= 2)
	                    randomNum = r.nextInt(10);
	                else
	                    randomNum = r.nextInt(3) + 8;
	                break;
	            case 3:
	                if (Player.getRollNumber() == 4)
	                    randomNum = 10;
	                else if(Player.getRollNumber() == 2)
	                    randomNum = r.nextInt(2)+8;
	                else
	                	randomNum = r.nextInt(10);
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid game difficulty");
	        }
	        return randomNum;
	    }
	
	

}


