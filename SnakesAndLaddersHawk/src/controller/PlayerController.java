package controller;

import java.util.ArrayList;

import Utils.PlayerObject;
import model.Ladder;
import model.Main;
import model.Player;
import model.Question;
import model.Snake;

public class PlayerController {
	private static PlayerController instance;

	 public static PlayerController getInstance() {
	     if (instance == null)
	           instance = new PlayerController();
	     return instance;
	 }
	 
	 
	 //function deals with player being on head or tail of snake
	 //moving player from head of snake to the tail position
	public void MovePlayerSnake(Player P, Snake s) {
		if(P.getCurrentTileNumber() == s.getHeadTileNumber()) {
			if(!"Red".equals(s.getColor().toString())) {
			  movePlayer(s.getTaileTileNumber(),s.getTaileBoardCoordinates()[0],s.getTaileBoardCoordinates()[1], P );
			}else {
				int difficulty = Main.gameController.getGame().getGameDifficulty();
				switch(difficulty) {
				case 1:
					movePlayer(43,6,0, P );
					break;
				case 2:
					movePlayer(91,9,0, P );
					break;
				case 3:
					movePlayer(157,12,0, P );
					break;
					
				}
			}
		}
		//if game end
		if(P.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
			Main.gameController.getGame().setWinnerNickName(P.getNickname());
			Main.gameController.endGame();
		}
	}
	
	public void movePlayer(int target,int row, int col,  Player player) {
		player.setPlayerCurrentTile(target);
		int[] pos = {row,col};
		player.setCurrentCoordinates(pos);
	}
	
	
	//function to move player when he steps on the beginning of ladder
	//Moving the player from the tail of ladder to the head.
	public void MovePlayerLadder(Player p, Ladder l) {
		if(p.getCurrentTileNumber() == l.getStartTileNumber()) {
			movePlayer(l.getEndTileNumber(), l.getEndCoordinates()[0], l.getEndCoordinates()[1], p );
			/*p.setPlayerCurrentTile(l.getEndTileNumber());
			p.setCurrentCoordinates(l.getEndCoordinates());*/
		}
		//if game end
		if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
			Main.gameController.getGame().setWinnerNickName(p.getNickname());
			Main.gameController.endGame();
		}
		
		
	}
	
	
	//function to move player by steps
	//steps are numbers generated by the dice roll.
	//if the player steps on winning tile the method return true otherwise false
	public Boolean MovePlayerStepsForward(Player p, int steps, int board_size) {
	
		 int playerCurTile = p.getCurrentTileNumber();
		 int[] playerCoordinates = p.getCurrentCoordinates();
		 while(steps!=0) {
			  
			  if(playerCoordinates[1]== board_size-1 && playerCoordinates[0]%2 == 0 &&(board_size != 10)) {
				  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
					    System.out.println(" you arrived to winning tile ");
						Main.gameController.getGame().setWinnerNickName(p.getNickname());
						Main.gameController.endGame();
						return true;
					}
				   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   playerCoordinates[0]= playerCoordinates[0] -1;
				   p.setCurrentTileNumber(p.getCurrentTileNumber()- board_size);
				   System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   steps--;
				 
			  }
			  if(playerCoordinates[1]== board_size-1 && playerCoordinates[0]%2 == 0 &&(board_size == 10)) {
				  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
					    System.out.println(" you arrived to winning tile ");
						Main.gameController.getGame().setWinnerNickName(p.getNickname());
						Main.gameController.endGame();
						return true;
					}
				   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   playerCoordinates[0]= playerCoordinates[0] +1;
				   p.setCurrentTileNumber(p.getCurrentTileNumber()+ board_size);
				   System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   steps--;
				 
			  }
	
			  
			  if(playerCoordinates[1]== 0 && playerCoordinates[0]%2 != 0 && board_size!=10) {
				  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
					  System.out.println(" you arrived to winning tile ");
						Main.gameController.getGame().setWinnerNickName(p.getNickname());
						Main.gameController.endGame();
						return true;
					}
				   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   playerCoordinates[0]= playerCoordinates[0] -1;
				   p.setCurrentTileNumber(p.getCurrentTileNumber()- board_size);
				   System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   steps--;
			  }
			  if(playerCoordinates[1]== 0 && playerCoordinates[0]%2 != 0 && board_size==10) {
				  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
					  System.out.println(" you arrived to winning tile ");
						Main.gameController.getGame().setWinnerNickName(p.getNickname());
						Main.gameController.endGame();
						return true;
					}
				   if(playerCoordinates[0] + 1 != board_size) {
				   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   playerCoordinates[0]= playerCoordinates[0] +1;
				   p.setCurrentTileNumber(p.getCurrentTileNumber()+ board_size);
				   System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   steps--;
				   }else {
					   if(steps !=0 && playerCoordinates[0] + 1 == board_size) {
						   steps =0;
					   }
				   }
			  }
		      if(((playerCoordinates[0]%2 == 0)) && steps!=0) {
		           if( playerCoordinates[1]!= board_size-1) {
		        	   if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
		        		   System.out.println(" you arrived to winning tile ");
							Main.gameController.getGame().setWinnerNickName(p.getNickname());
							System.out.println(" the winner is "+ Main.gameController.getGame().getWinnerNickName());
							Main.gameController.endGame();
							return true;
						}
		        	   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				       playerCoordinates[1]= playerCoordinates[1]+1;
				       p.setCurrentTileNumber(p.getCurrentTileNumber()+1);
				       System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				       steps--;
				       
		           }
		      }
		      if(playerCoordinates[0]%2 != 0 && steps!=0) {
		          if( playerCoordinates[1]!= 0) {
		        	  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
		        		  System.out.println(" you arrived to winning tile ");
							Main.gameController.getGame().setWinnerNickName(p.getNickname());
							Main.gameController.endGame();
							return true;
						}
		        	   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				      playerCoordinates[1]= playerCoordinates[1]-1;
				      p.setCurrentTileNumber(p.getCurrentTileNumber()-1);
				      System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				      steps--;
		
		          }
		      } 
			  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
				  System.out.println(" you arrived to winning tile ");
					Main.gameController.getGame().setWinnerNickName(p.getNickname());
					Main.gameController.endGame();
					return true;
				}

		  }
		 p.setCurrentCoordinates(playerCoordinates);
		 Snake snake = Main.snakeController.checkSnake(p.getCurrentTileNumber());
		 if(snake != null) {
			 MovePlayerSnake(p,snake);
		 }
		 Ladder ladder = Main.ladderController.checkLadder(p.getCurrentTileNumber());
		 if(ladder!=null) {
			 MovePlayerLadder(p, ladder);
		 }
		  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
			  System.out.println(" you arrived to winning tile after ladder");
				Main.gameController.getGame().setWinnerNickName(p.getNickname());
				Main.gameController.endGame();
				return true;
			}
         return false;
		
	}

	
	public Boolean MovePlayerStepsBack(Player p, int steps, int board_size) {
		
		 int playerCurTile = p.getCurrentTileNumber();
		 int[] playerCoordinates = p.getCurrentCoordinates();
		 System.out.println(" i got here");
		 while(steps!=0) {
			  System.out.println("i stepped in the while in player controller");
			  if(playerCoordinates[1]== 0 && playerCoordinates[0]%2 == 0 && board_size!=10) {
				  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
					    System.out.println(" you arrived to winning tile ");
						Main.gameController.getGame().setWinnerNickName(p.getNickname());
						Main.gameController.endGame();
						return true;
					}
				   if(playerCoordinates[0]+1 != board_size) {
				   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   playerCoordinates[0]= playerCoordinates[0] +1;
				   p.setCurrentTileNumber(p.getCurrentTileNumber()+ board_size);
				   System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   steps--;
				   }else {
					   System.out.println("i got to biginning but cant get back more");
					   if(steps!=0 && playerCoordinates[0]+1 == board_size) {
						   steps =0;
					   }
				   }
			  }
			  if(playerCoordinates[1]== 0 && playerCoordinates[0]%2 == 0 && board_size==10) {
				  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
					    System.out.println(" you arrived to winning tile ");
						Main.gameController.getGame().setWinnerNickName(p.getNickname());
						Main.gameController.endGame();
						return true;
					}
				   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   playerCoordinates[0]= playerCoordinates[0] -1;
				   p.setCurrentTileNumber(p.getCurrentTileNumber()- board_size);
				   System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   steps--;
				 
			  }
			  if(playerCoordinates[1]== board_size -1  && playerCoordinates[0]%2 != 0 && board_size!=10) {
				  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
					  System.out.println(" you arrived to winning tile ");
						Main.gameController.getGame().setWinnerNickName(p.getNickname());
						Main.gameController.endGame();
						return true;
					}
				   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   playerCoordinates[0]= playerCoordinates[0] +1;
				   p.setCurrentTileNumber(p.getCurrentTileNumber()+ board_size);
				   System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   steps--;
			  }
			  if(playerCoordinates[1]== board_size -1  && playerCoordinates[0]%2 != 0 && board_size==10) {
				  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
					  System.out.println(" you arrived to winning tile ");
						Main.gameController.getGame().setWinnerNickName(p.getNickname());
						Main.gameController.endGame();
						return true;
					}
				   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   playerCoordinates[0]= playerCoordinates[0] -1;
				   p.setCurrentTileNumber(p.getCurrentTileNumber()- board_size);
				   System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				   steps--;
			  }

		      if(playerCoordinates[0]%2 == 0 && steps!=0) {
		           if( playerCoordinates[1]!= 0) {
		        	   if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
		        		   System.out.println(" you arrived to winning tile ");
							Main.gameController.getGame().setWinnerNickName(p.getNickname());
							System.out.println(" the winner is "+ Main.gameController.getGame().getWinnerNickName());
							Main.gameController.endGame();
							return true;
						}
		        	   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				       playerCoordinates[1]= playerCoordinates[1]-1;
				       p.setCurrentTileNumber(p.getCurrentTileNumber()-1);
				       System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				       steps--;
				       
		           }
		      }
		      if(playerCoordinates[0]%2 != 0 && steps!=0) {
		          if( playerCoordinates[1]!= board_size -1) {
		        	  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
		        		  System.out.println(" you arrived to winning tile ");
							Main.gameController.getGame().setWinnerNickName(p.getNickname());
							Main.gameController.endGame();
							return true;
						}
		        	   System.out.println(p.getNickname()+" i moved from "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				      playerCoordinates[1]= playerCoordinates[1]+1;
				      p.setCurrentTileNumber(p.getCurrentTileNumber()+1);
				      System.out.println(p.getNickname()+" to "+playerCoordinates[0]+" "+playerCoordinates[1]+" tile "+ p.getCurrentTileNumber());
				      steps--;
		
		          }
		      } 
			  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
				  System.out.println(" you arrived to winning tile ");
					Main.gameController.getGame().setWinnerNickName(p.getNickname());
					Main.gameController.endGame();
					return true;
				}

		  }
		 p.setCurrentCoordinates(playerCoordinates);
		 Snake snake = Main.snakeController.checkSnake(p.getCurrentTileNumber());
		 if(snake != null) {
			 MovePlayerSnake(p,snake);
		 }
		 Ladder ladder = Main.ladderController.checkLadder(p.getCurrentTileNumber());
		 if(ladder!=null) {
			 MovePlayerLadder(p, ladder);
		 }
		  if(p.getCurrentTileNumber() == Main.gameController.getWinningTile()) {
			  System.out.println(" you arrived to winning tile after ladder");
				Main.gameController.getGame().setWinnerNickName(p.getNickname());
				Main.gameController.endGame();
				return true;
			}
        return false;
		
	}
	public void resetPlayersPostoStartTile(int board_size) {
		switch(board_size) {
		case 7:
			resetPlayersPostoStartTileByBoardSiz(7);
			break;
		case 10:
			resetPlayersPostoStartTileByBoardSiz(10);
			break;
		case 13:
			resetPlayersPostoStartTileByBoardSiz(13);	
			break;
		}
	}
	
	public void resetPlayersPostoStartTileByBoardSiz(int size) {
		ArrayList<Player> players = Main.gameController.getPlayers();
		for(Player p : players) {
			p.setCurrentTileNumber( ((size * size) - size) + 1);
			int[] pos = {size -1, 0};
			p.setCurrentCoordinates(pos);
		}
	}
	public String getPlayerObjImagePath(Player p ) {
		switch(p.getObject()) {
		case BlondeWoman:
			return "/hawk_images/woman1.png";
		case WomanWithBrownHair:
			return"/hawk_images/woman2.png";
		case ManWithOrangeShirt:
			return "/hawk_images/man2.png";
		case girlWithGreenShirt:
			return "/hawk_images/girlWithGreenShirt.png";
		case oldMan:
			return "/hawk_images/oldMan.png";
		default :
			return "/hawk_images/manWithYellowShirt.png";
		}
	}
	
	public PlayerObject getPlayerObject(String path) {
		switch(path) {
		case "/hawk_images/woman1.png":
			return PlayerObject.BlondeWoman;
		case "/hawk_images/woman2.png":
			return PlayerObject.WomanWithBrownHair;
		case "/hawk_images/man2.png":
			return PlayerObject.ManWithOrangeShirt;
		case "/hawk_images/girlWithGreenShirt.png":
			return PlayerObject.girlWithGreenShirt;
		case "/hawk_images/oldMan.png":
			return PlayerObject.oldMan;
		default :
			return PlayerObject.ManWithRedShirt;
		}
	}
	
	public boolean CorrectAnswer(Player p, Question q) {
		return false;
		
	}
}
