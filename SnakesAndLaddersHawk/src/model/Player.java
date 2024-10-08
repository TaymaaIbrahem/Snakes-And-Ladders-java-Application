package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Utils.PlayerColor;
import Utils.PlayerObject;

public class Player implements PlayerObserver {
	
	private static int counter=0;
	private int playerID;
	private String Nickname;
	private PlayerColor color;
	private PlayerObject object;
	private int playerNumInOrder;
	private int currentTileNumber;
	private int[] currentCoordinates;
	private static int rollNumber = 1;
	private List<PlayerObserver> observers = new ArrayList<>();
	
	

	public Player(String nickname, PlayerColor color, PlayerObject object, int cuttentTileNumber, int[] currentCoordinates) {

		setCounter(getCounter()+1);
		playerID = getCounter();
		Nickname = nickname;
		this.color = color;
		this.object = object;
		this.playerNumInOrder = 0;
		this.currentTileNumber = cuttentTileNumber;
		this.currentCoordinates=currentCoordinates;
		setRollNumber(getRollNumber()+1);
		
		
	}
	
	
	


	public Player(String nickname, PlayerColor color, PlayerObject object) {
		super();
		setCounter(getCounter()+1);
		playerID = getCounter();
		Nickname = nickname;
		this.color = color;
		this.object = object;
	}





	public int[] getCurrentCoordinates() {
		return currentCoordinates;
	}


	public void setCurrentCoordinates(int[] currentCoordinates) {
		this.currentCoordinates = currentCoordinates;
	}


	public static int getRollNumber() {
		return rollNumber;
	}


	public void setRollNumber(int rollNumber) {
		if(rollNumber > 4)
			Player.rollNumber = 1;
		else
			Player.rollNumber = rollNumber;
	}


	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		Player.counter = counter;
	}


	public int getPlayerID() {
		return playerID;
	}


	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}


	public String getNickname() {
		return Nickname;
	}


	public void setNickname(String nickname) {
		Nickname = nickname;
	}


	public PlayerColor getColor() {
		return color;
	}


	public void setColor(PlayerColor color) {
		this.color = color;
	}


	public PlayerObject getObject() {
		return object;
	}


	public void setObject(PlayerObject object) {
		this.object = object;
	}

	public void setPlayerCurrentTile(int tileNum)
	{
		this.currentTileNumber = tileNum;
	}

	public int getPlayerNumInOrder() {
		return playerNumInOrder;
	}


	public void setPlayerNumInOrder(int playerNumInOrder) {
		this.playerNumInOrder = playerNumInOrder;
	}


	public int getCurrentTileNumber() {
		return currentTileNumber;
	}


	public void setCurrentTileNumber(int cuttentTileNumber) {
		this.currentTileNumber = cuttentTileNumber;
	}





	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", Nickname=" + Nickname + ", color=" + color + ", object=" + object
				+ "]";
	}





	public List<PlayerObserver> getObservers() {
		return observers;
	}





	public void setObservers(List<PlayerObserver> observers) {
		this.observers = observers;
	}
	
	public void addObserver(PlayerObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(int newPosition) {
        for (PlayerObserver observer : observers) {
            observer.update(this, this.currentCoordinates[0], this.currentCoordinates[1]);
        }
    }

	@Override
	public void update(Player player, int raw, int column) {
		System.out.println(player.getNickname() + " moved to position " + raw + column);
		
	}





	@Override
	public int hashCode() {
		return Objects.hash(object, playerID);
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return object == other.object && playerID == other.playerID;
	}
	
	


	

}
