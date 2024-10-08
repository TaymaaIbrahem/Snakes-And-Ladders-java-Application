package model;


import Interfaces.TileMessage;
import Utils.PlayerColor;

public class QuestionTile extends Tile implements TileMessage{

	private Question tileQuestion;
	private int playerAnswerNumber;
	private int questionDifficulty; 
	
	

	public QuestionTile(int tileNumber, PlayerColor tileColor) {
		super(tileNumber, tileColor);
		// TODO Auto-generated constructor stub
	}




	public QuestionTile(int tileNumber, PlayerColor tileColor, Question tileQuestion, int questionDifficulty) {
		super(tileNumber, tileColor);
		// TODO Auto-generated constructor stub
		this.tileQuestion = tileQuestion;
		this.questionDifficulty = questionDifficulty;
	}
	
	
	

	public Question getTileQuestion() {
		return tileQuestion;
	}

	public void setTileQuestion(Question tileQuestion) {
		this.tileQuestion = tileQuestion;
	}

	public int getPlayerAnswerNumber() {
		return playerAnswerNumber;
	}

	public void setPlayerAnswerNumber(int playerAnswerNumber) {
		this.playerAnswerNumber = playerAnswerNumber;
	}

	public int getQuestionDifficulty() {
		return questionDifficulty;
	}

	




	@Override
	public String toString() {
		return super.toString()+"QuestionTile [tileQuestion=" + tileQuestion +  ", questionDifficulty=" + questionDifficulty + "]";
	}



	


	@Override
	public String setTileMessage(String message) {
		return "You have to answer the question to continue the game "+message+" !";
		
	}

	@Override
	public Boolean checkTileType(Tile tile) {
		// TODO Auto-generated method stub
		return tile instanceof QuestionTile;
	}
	

	
}
