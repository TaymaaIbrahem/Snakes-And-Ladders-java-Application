package model;

public class Dice {
	
	private int numberOfTheRoll;
	private int gameDifficulty;

	public Dice(int gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
		this.numberOfTheRoll = -1;
	}

	public int getNumberOfTheRoll() {
		return numberOfTheRoll;
	}

	public void setNumberOfTheRoll(int numberOfTheRoll) {
		this.numberOfTheRoll = numberOfTheRoll;
	}

	public int getGameDifficulty() {
		return gameDifficulty;
	}

	public void setGameDifficulty(int gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}

}
