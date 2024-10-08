package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Utils.PlayerColor;
import Utils.Direction;
import Utils.SnakeColor;

import model.Game;
import model.Ladder;
import model.Main;
import model.Player;
import model.Question;
import model.QuestionTile;
import model.Snake;
import model.SurpriseTile;
import model.Tile;

public class GameController {

	private static GameController instance;
	private Tile[][] board=null;
	private HashSet<Integer> occupiedPositions;
	SecureRandom secureRandom = new SecureRandom();
	private ArrayList<Player> players;
	private ArrayList<Tile> tiles;
	private ArrayList<Snake> snakes;
	private ArrayList<Ladder> ladders;
	public HashMap<Integer,Game> allGames;
	private int winningTile;
	private Game game ;
	private Player currentPlayer;


	private GameController() { 
		occupiedPositions = new HashSet<Integer>();
		allGames = Main.sysData.getAllHistory();

	}

	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	public void buildBoard( int gameDifficulty){

		int maxlen =0, boardSize=0;
		switch(gameDifficulty) {
		case 1:
			maxlen=4;  boardSize=7; this.winningTile=7;
			break;
		case 2:
			maxlen=6;  boardSize=10; this.winningTile=1;
			break;
		case 3:
			maxlen=8; boardSize=13; this.winningTile=13;
			break;
		}

		this.board = new Tile[boardSize][boardSize]; 
		this.tiles = setTiles(gameDifficulty);
		this.snakes = createGameSnakes(gameDifficulty);
		this.ladders = setLadders(maxlen, boardSize);

	}


	public Game getGame() {
		return game;
	}


	public int getWinningTile() {
		return winningTile;
	}
	


	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
    public void nextPlayerTurn() {
    	ArrayList<Player> players = this.game.getPlayers();
    	int playersNum=players.size();
    	for(Player p : players) {
    		if(p.equals(currentPlayer)) {
    			if(players.indexOf(p)!= playersNum -1) {
    				currentPlayer = players.get(players.indexOf(p)+1);
    			}else {
    				currentPlayer = players.get(0);
    			}
    		 return;
    		}
    	}
    }
    //method to call to start a new game 
	public void startGame(int difficulty, ArrayList<Player> players) {
		LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		game = new Game(formattedDate, difficulty, players);

		//allGames.put(game.getGameID(), game);

		buildBoard(difficulty);
        setCurrentPlayer(players.get(0));
		return;
	}

	//set all the tiles of the board 
	public ArrayList<Tile> setTiles(int difficulty) {
		ArrayList<QuestionTile> questionTiles = new ArrayList<QuestionTile>();
		ArrayList<SurpriseTile> surpriseTiles = new ArrayList<SurpriseTile>();
		ArrayList<Tile> normalTiles = new  ArrayList<Tile>();
		switch(difficulty) {
		case 1:
			questionTiles = setQuestionTilesByBoardSize(7);
			normalTiles = setNormalTiles(7);
			break;
		case 2:
			questionTiles = setQuestionTilesByBoardSize(10);
			surpriseTiles = setSurpriseTilesByDifficulty(10,1);
			normalTiles = setNormalTiles(10);
			break;
		case 3:
			questionTiles = setQuestionTilesByBoardSize(13);
			surpriseTiles = setSurpriseTilesByDifficulty(13,2);
			normalTiles = setNormalTiles(13);
			break;
		}
		normalTiles.addAll(questionTiles);
		normalTiles.addAll(surpriseTiles);
		game.setTiles(normalTiles);
		return normalTiles;

	}
	//method creates all the snakes of specific game by difficulty
	public ArrayList<Snake> createGameSnakes(int difficulty){

		ArrayList<Snake> snakes = new ArrayList<Snake>(); 
		switch(difficulty) {
		case 1:
			snakes =  createRedSnakes(7, 1);
			snakes.addAll(createYellowSnakes(7, 1)) ;
			snakes.addAll(createGreenSnakes(7, 1)) ;
			snakes.addAll(createBlueSnakes(7, 1)) ;
			break;

		case 2:
			snakes =  createRedSnakes(10, 2);
			snakes.addAll(createYellowSnakes(10, 1)) ;
			snakes.addAll(createGreenSnakes(10, 2)) ;
			snakes.addAll(createBlueSnakes(10, 1)) ;
			break;
		case 3:
			snakes =  createRedSnakes(13, 2);
			snakes.addAll(createYellowSnakes(13, 2)) ;
			snakes.addAll(createGreenSnakes(13, 2)) ;
			snakes.addAll(createBlueSnakes(13, 2)) ;
			break;
		}
		HashMap<Integer,Snake> snakeMap = new HashMap<Integer,Snake>();
		for(Snake s : snakes) {
			snakeMap.put(s.getHeadTileNumber(), s);
		}
		game.setSnakes(snakeMap);
		return snakes;
	}
	//method create all the ladders of specific game by board size
	public ArrayList<Ladder> setLadders(int maxLen , int boardSize){

		ArrayList<Ladder> ladders = new ArrayList<Ladder>();
		int row = -1;
		int col = -1;
		
		Ladder l = null;
		for (int i = 1; i <= maxLen ; i++) {

			boolean flag = true;
			int start = 0;

			while (flag) {

				start = secureRandom.nextInt((boardSize*boardSize) - boardSize) +1 + boardSize;
				if (!occupiedPositions.contains(start) && !occupiedPositions.contains(start-(i*boardSize))
						&& start-(i*boardSize)>=1 && start!= (boardSize*boardSize)-boardSize+1 ) {

					occupiedPositions.add(start);
					occupiedPositions.add(start-(i*boardSize));
					row = (start - 1) / boardSize; // Integer division gives the row
					col = (start - 1) % boardSize; // Modulo gives the column
					
					int [] startCoordinates = new int[2];
					int [] endCoordinates = new int[2];
					startCoordinates[0]=row; startCoordinates[1]=col;
					endCoordinates[0]=row-i; endCoordinates[1]=col;
					l = new Ladder(i,start,startCoordinates, start-(i*boardSize),endCoordinates);
					
					ladders.add(l);
					break;
				}
			}
		}
		HashMap<Integer,Ladder> mapladders = new HashMap<Integer,Ladder>();
		for(Ladder ladder : ladders) {
			mapladders.put(ladder.getStartTileNumber(), ladder);
		}
		game.setLadders(mapladders);
		return ladders;
	}

	//method sets 3 question Tiles one for each difficulty level randomly and return this tiles
	public  ArrayList<QuestionTile> setQuestionTilesByBoardSize(int boardSize) {
		ArrayList<QuestionTile> qTiles = new ArrayList<QuestionTile>();
		int row = -1;
		int col = -1;
		QuestionTile qt = null;
		for(int i=0 ; i < 3 ; i++) {
			boolean flag = true;
			int qTileNum=-1;
			while(flag) {
				qTileNum = secureRandom.nextInt(boardSize*boardSize)+1;
				if(!occupiedPositions.contains(qTileNum)
					  && 
						((qTileNum!=(boardSize*boardSize)-boardSize+1 && qTileNum!=boardSize	&& (boardSize == 7 || boardSize == 13))
						  || 
						 ( qTileNum!=1 && qTileNum!=((boardSize*boardSize)-boardSize+1)&& boardSize==10 )
						)
				   ){
					occupiedPositions.add(qTileNum);
					PlayerColor c = qTileNum%2 == 0 ? PlayerColor.Green : PlayerColor.Yellow;
					 Question q = QuestionController.getInstance().getQuestionByDifficultyLevel(i+1);
	                 qt = new QuestionTile(qTileNum, c, q, i+1);
	                 
	   				 qTiles.add(i, qt);
					row = (qTileNum - 1) / boardSize; // Integer division gives the row
					col = (qTileNum - 1) % boardSize; // Modulo gives the column
					board[row][col]= qt;
					break;
				}
				
			}

		}
		return qTiles;

	}
	//method sets surprise tiles 
	public  ArrayList<SurpriseTile> setSurpriseTilesByDifficulty(int boardSize, int quantity) {
		ArrayList<SurpriseTile> surpriseTiles = new ArrayList<SurpriseTile>();
		int row = -1;
		int col = -1;
		SurpriseTile st = null;
		Direction d = Direction.Back;
		for(int i=0 ; i<quantity ; i++) {
			boolean flag = true;
			int sTileNum=-1;
			while (flag) {
				sTileNum = secureRandom.nextInt(boardSize*boardSize)+1;
				if (!occupiedPositions.contains(sTileNum)
						&& (sTileNum!=boardSize && (sTileNum+10)<=(boardSize*boardSize) && (sTileNum-10)>=1)
						&& ((sTileNum!=(boardSize*boardSize)-boardSize+1 && (boardSize == 7 || boardSize == 13))
							 ||
							( sTileNum!=1 && sTileNum!=((boardSize*boardSize)-boardSize+1)&& boardSize==10))
					) {

					occupiedPositions.add(sTileNum);
					PlayerColor c = sTileNum%2 == 0 ? PlayerColor.Green : PlayerColor.Yellow;
					st = new SurpriseTile(sTileNum, c,d);
					d = d.equals(Direction.Back)? Direction.Forward : Direction.Back;
					surpriseTiles.add(i, st);
					row = (sTileNum - 1) / boardSize; // Integer division gives the row
					col = (sTileNum - 1) % boardSize; // Modulo gives the column
					board[row][col]= st;
					break;
				}
			}
		}
		return surpriseTiles;
	}
	//sets normal tiles
	public  ArrayList<Tile> setNormalTiles(int boardSize){
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int row = -1;
		int col = -1;
		Tile t = null;
		PlayerColor c = null;
		for(int i=1 ; i<=boardSize*boardSize ; i++) {
			if(!occupiedPositions.contains(i)) {
				c = i%2 == 0 ? PlayerColor.Green : PlayerColor.Yellow;
				t = new Tile(i,c);
				row = (i - 1) / boardSize; // Integer division gives the row
				col = (i - 1) % boardSize; // Modulo gives the column
				board[row][col]= t;
				tiles.add(t);
			}
		}
		return tiles;
	}


	// methods for creating snakes by quantity and board size  
	public ArrayList<Snake> createRedSnakes(int boardSize, int quantity){
		ArrayList<Snake> snakes = new ArrayList<Snake>();
		int row = 0;
		int col = 0;
		
		Snake s = null;
		for(int i=0 ; i<quantity ; i++) {
			boolean flag = true;
			int sTileNum=-1;
			while (flag) {
				sTileNum = secureRandom.nextInt(boardSize*boardSize)+1;
				if (!occupiedPositions.contains(sTileNum) && sTileNum!=(boardSize*boardSize)-boardSize+1 
						&&
						 ((sTileNum!= boardSize && (boardSize == 7 || boardSize == 13)) ||( sTileNum!=1 && boardSize==10)) ) {
					occupiedPositions.add(sTileNum);
					row = (sTileNum - 1) / boardSize; // Integer division gives the row
					col = (sTileNum - 1) % boardSize; // Modulo gives the column
					int [] headCoordinates = new int[2];
					int [] taileCoordinates = new int[2];
					headCoordinates[0]=row; headCoordinates[1]=col;
					taileCoordinates[0]=row; taileCoordinates[1]=col;
					s = new Snake(SnakeColor.Red, sTileNum,headCoordinates, sTileNum,taileCoordinates);
					snakes.add(s);
					
					break;
				}
			}
		}
		return snakes;
	}

	public ArrayList<Snake> createYellowSnakes(int boardSize, int quantity){
		ArrayList<Snake> snakes = new ArrayList<Snake>();
		int row = -1;
		int col = -1;
		Snake s = null;
		for(int i=0 ; i<quantity ; i++) {
			boolean flag = true;
			int sTileNum=-1;
			while (flag) {
				sTileNum = secureRandom.nextInt(boardSize*(boardSize-1))+1;
				if (!occupiedPositions.contains(sTileNum) && !occupiedPositions.contains(sTileNum+boardSize)
						&&  ((sTileNum!= boardSize && (boardSize == 7 || boardSize == 13)) ||( sTileNum!=1 && boardSize==10)) ) {
					occupiedPositions.add(sTileNum);
					occupiedPositions.add(sTileNum+boardSize);
					row = (sTileNum - 1) / boardSize; // Integer division gives the row
					col = (sTileNum - 1) % boardSize; // Modulo gives the column
					int [] headCoordinates = new int[2];
					int [] taileCoordinates = new int[2];

					headCoordinates[0]=row; headCoordinates[1]=col;
					taileCoordinates[0]=row+1; taileCoordinates[1]=col;
					s = new Snake(SnakeColor.Yellow, sTileNum,headCoordinates, sTileNum+boardSize,taileCoordinates);
					snakes.add(s);
					
					break;
				}
			}
		}
		return snakes;
	}

	public ArrayList<Snake> createGreenSnakes(int boardSize, int quantity){
		ArrayList<Snake> snakes = new ArrayList<Snake>();
		int row = -1;
		int col = -1;
		Snake s = null;
		for(int i=0 ; i<quantity ; i++) {
			boolean flag = true;
			int sTileNum=-1;
			while (flag) {
				sTileNum = secureRandom.nextInt(boardSize*(boardSize-2))+1;
				if (!occupiedPositions.contains(sTileNum) && !occupiedPositions.contains(sTileNum+(2*boardSize)) 
						&&
						 ((sTileNum!= boardSize && (boardSize == 7 || boardSize == 13)) ||( sTileNum!=1 && boardSize==10)) ) {
					occupiedPositions.add(sTileNum);
					occupiedPositions.add(sTileNum+(2*boardSize));
					row = (sTileNum - 1) / boardSize; // Integer division gives the row
					col = (sTileNum - 1) % boardSize; // Modulo gives the column
					int [] headCoordinates = new int[2];
					int [] taileCoordinates = new int[2];

					headCoordinates[0]=row; headCoordinates[1]=col;
					taileCoordinates[0]=row+2; taileCoordinates[1]=col;
					s = new Snake(SnakeColor.Green, sTileNum,headCoordinates, sTileNum+ (2*boardSize),taileCoordinates);
					snakes.add(s);
					
					break;
				}
			}
		}
		return snakes; 
	}

	public ArrayList<Snake> createBlueSnakes(int boardSize, int quantity){
		ArrayList<Snake> snakes = new ArrayList<Snake>();
		int row = -1;
		int col = -1;
		Snake s = null;
		for(int i=0 ; i<quantity ; i++) {
			boolean flag = true;
			int sTileNum=-1;
			while (flag) {
				sTileNum = secureRandom.nextInt(boardSize*(boardSize-3))+1;
				if (!occupiedPositions.contains(sTileNum) && !occupiedPositions.contains(sTileNum+(3*boardSize)) 
						&&  ((sTileNum!= boardSize && (boardSize == 7 || boardSize == 13)) ||( sTileNum!=1 && boardSize==10)) ) {
					occupiedPositions.add(sTileNum);
					occupiedPositions.add(sTileNum+(3*boardSize));
					row = (sTileNum - 1) / boardSize; // Integer division gives the row
					col = (sTileNum - 1) % boardSize; // Modulo gives the column
					int [] headCoordinates = new int[2];
					int [] taileCoordinates = new int[2];

					headCoordinates[0]=row; headCoordinates[1]=col;
					taileCoordinates[0]=row+3; taileCoordinates[1]=col;
					s = new Snake(SnakeColor.Blue, sTileNum,headCoordinates, sTileNum+ (3*boardSize),taileCoordinates);
					snakes.add(s);
					
					break;
				}
			}
		}
		return snakes; 
	}
	
	
	public ArrayList<Player> getPlayers() {
		return game.getPlayers();
	}
    
	
	public Tile[][] getBoard() {
		return board;
	}



	public ArrayList<Tile> getTiles() {
		return game.getTiles();
	}
	public ArrayList<Snake> getSnakes() {
		return this.snakes;
	}


	public ArrayList<Ladder> getLadders() {
		return this.ladders;
	}

	public void setLadders(ArrayList<Ladder> ladders) {
		this.ladders = ladders;
	}


	public void endGame() {

		addGametoHistory(game);
	}

	//after the game end we should story the history
	private boolean addGametoHistory(Game g) {
		if(Main.sysData.getAllHistory().containsKey(g.getGameID())) {
			for(Game gm : Main.sysData.getAllHistory().values()) {
				System.out.println(gm.toString());
			}
			System.out.println("i cant add the game the game id is "+ g.getGameID());
			return false;
		}
		else {
			Main.sysData.getAllHistory().put(g.getGameID(), g);
			System.out.println(" the game end and the winner is "+ g.getWinnerNickName());
			Main.exitGame();
			return true;
		}
	}

}
