package controller;

import Utils.PlayerColor;
import model.Question;
import model.QuestionTile;

public class QuestionTileController {
	
	private static QuestionTileController instance;
	private QuestionTileController() {}

	public static QuestionTileController getInstance() {
		if(instance == null)
			instance = new QuestionTileController();
		return instance; 
	}
	
	/*public QuestionTile NewTile(int tileNumber,Color tileColor,int questionDifficulty) {
		// taking the question from the question controller then added
		return new QuestionTile(tileNumber,tileColor,tileQuestion,questionDifficulty); 
	}*/


}
