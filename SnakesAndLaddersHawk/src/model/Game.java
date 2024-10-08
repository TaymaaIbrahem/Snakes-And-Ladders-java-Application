package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
	
	private static int counter = 0;
	private int gameID;
	private String gameDate;
	private String winnerNickName;
	private int gameDifficulty;
	private String gameDuration;
	private ArrayList<Player> players;
	private ArrayList<Tile> tiles;
	private HashMap<Integer,Snake> snakes;
	private HashMap<Integer,Ladder> ladders;

	
	
	public Game(String gameDate, int gameDifficulty, ArrayList<Player> players, ArrayList<Tile> tiles,
			HashMap<Integer,Snake> snakes, HashMap<Integer,Ladder> ladders) {
		
		setCounter(getCounter()+1);
		this.gameID = getCounter();
		this.winnerNickName = null;
		this.gameDuration = null;
		this.gameDate = gameDate;
		this.gameDifficulty = gameDifficulty;
		this.players = players;
		this.tiles = tiles;
		this.snakes = snakes;
		this.ladders = ladders;
	}

//the constructor of game history - we don't need to store the other variables
	
	public Game(String gameDate, String winnerNickName, int gameDifficulty, String gameDuration,
			ArrayList<Player> players) {
		super();
		setCounter(getCounter()+1);
		this.gameID = getCounter();
		this.gameDate = gameDate;
		this.winnerNickName = winnerNickName;
		this.gameDifficulty = gameDifficulty;
		this.gameDuration = gameDuration;
		this.players = players;
	}
	
	public Game(String gameDate,  int gameDifficulty, ArrayList<Player> players) {
		super();
		setCounter(getCounter()+1);
		this.gameID = getCounter();
		this.gameDifficulty = gameDifficulty;
		this.players = players;
		this.gameDate = gameDate;
		

	}


	public static int getCounter() {
		return counter;
	}


	public static void setCounter(int counter) {
		Game.counter = counter;
	}


	public int getGameID() {
		return gameID;
	}


	public void setGameID(int gameID) {
		this.gameID = gameID;
	}


	public String getGameDate() {
		return gameDate;
	}


	public void setGameDate(String gameDate) {
		this.gameDate = gameDate;
	}


	public String getWinnerNickName() {
		return winnerNickName;
	}


	public void setWinnerNickName(String winnerNickName) {
		this.winnerNickName = winnerNickName;
	}


	public int getGameDifficulty() {
		return gameDifficulty;
	}


	public void setGameDifficulty(int gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}


	public String getGameDuration() {
		return gameDuration;
	}


	public void setGameDuration(String gameDuration) {
		this.gameDuration = gameDuration;
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}


	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}


	public ArrayList<Tile> getTiles() {
		return tiles;
	}


	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}


	public HashMap<Integer,Snake> getSnakes() {
		return snakes;
	}


	public void setSnakes(HashMap<Integer,Snake> snakes) {
		this.snakes = snakes;
	}


	public HashMap<Integer,Ladder> getLadders() {
		return ladders;
	}


	public void setLadders( HashMap<Integer,Ladder> ladders) {
		this.ladders = ladders;
	}

	@Override
	public String toString() {
	    String playerInfo = "";
	    
	    for (Player player : players) {
	        playerInfo += "\n\t" + player.toString();
	    }

	    return "Game [gameID=" + gameID +
	           ", gameDate=" + gameDate +
	           ", winnerNickName=" + winnerNickName +
	           ", gameDifficulty=" + gameDifficulty +
	           ", gameDuration=" + gameDuration +
	           ", players=[" + playerInfo + "\n]]";
	}
	
	
	
	
	

}
