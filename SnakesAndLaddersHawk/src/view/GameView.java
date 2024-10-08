package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Interfaces.TileMessage;
import Utils.Direction;
import Utils.PlayerColor;
import Utils.PlayerObject;
import Utils.SnakeColor;

import controller.DiceController;
import controller.GameController;
import controller.LadderController;
import controller.PlayerController;
import controller.QuestionController;
import controller.SnakeController;
import model.Ladder;
import model.Main;
import model.Player;
import model.QuestionTile;
import model.Snake;
import model.SurpriseTile;
import model.Tile;
import model.TileFactory;
import view.HomeView.openFrameListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class GameView extends JFrame {

	private JPanel contentPane;
	private JLabel lblResultNumb;
	private JLabel lblResultImage;
	private Dimension screenSize;
	
	final String ESAY_BOARD_BACKGROUND = "/hawk_images/7x7board.png";
	final String MEDIUM_BOARD_BACKGROUND = "/hawk_images/10x10board.png";
	final String HARD_BOARD_BACKGROUND = "/hawk_images/13x13_board.png";
	
	final String Desert_ESAY_BOARD_BACKGROUND = "/hawk_images/7x7Desert.png";
	final String Desert_MEDIUM_BOARD_BACKGROUND = "/hawk_images/10x10Desert.png";
	final String Desert_HARD_BOARD_BACKGROUND = "/hawk_images/13x13Desert.png";
	
	final String Snow_ESAY_BOARD_BACKGROUND = "/hawk_images/7x7Snow.png";
	final String Snow_MEDIUM_BOARD_BACKGROUND = "/hawk_images/10x10Snow.png";
	final String Snow_HARD_BOARD_BACKGROUND = "/hawk_images/13x13Snow.png";
	
	final String Forest_ESAY_BOARD_BACKGROUND = "/hawk_images/7x7Forest.png";
	final String Forest_MEDIUM_BOARD_BACKGROUND = "/hawk_images/10x10Forest.png";
	final String Forest_HARD_BOARD_BACKGROUND = "/hawk_images/13x13Forest.png";
	final int BOARD_SIZE ;
	final int X_PLAYER = 50;
	final int y_PLAYER = 240;
	/*+72*/
	final int WIDTH_PLAYER = 280;
	final int HIGHT_PLAYER = 32;
	
	 int TILE_START_X ;
	 int TILE_START_Y;
	
	 int TILE_PLAYER_START_X;
	
	
	final int TILE_7X7_WIDTH = 117;
	final int TILE_7X7_HIGHT = 98;
	 int TILE_7X7_PLAYER_START_Y  ;
	final int TILE_7X7_WIDTH_PLAYER_START = 59;
	final int TILE_7X7_HIGHT_PLAYER_START = 49;
	
	final int TILE_10X10_WIDTH = 81;
	final int TILE_10X10_HIGHT = 69;
	 int TILE_10X10_PLAYER_START_Y  ;
	final int TILE_10X10_WIDTH_PLAYER_START = 41;
	final int TILE_10X10_HIGHT_PLAYER_START = 34;
	
	final int TILE_13X13_WIDTH = 62;
	final int TILE_13X13_HIGHT = 53;
	 int TILE_13X13_PLAYER_START_Y = 720 ;
	final int TILE_13X13_WIDTH_PLAYER_START = 31;
	final int TILE_13X13_HIGHT_PLAYER_START = 26;
	GameController gc;
	private JLabel boardBackground;
	private JLabel timeLabel;
	HashMap<Player,JLabel> playersLabels;
	ArrayList< JLabel> playerShading;
	int indexCurrentShading;
	JButton diceBtn;
	
	private Timer timer;
	private int secondsElapsed;

	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView frame = new GameView(0,null,0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameView(int difficulty,HomeView h,int selected) {
		
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    this.setUndecorated(true);
	    
	    // Get the screen dimensions
	     screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    // Set the preferred size of the frame
	    this.setPreferredSize(screenSize);
	    TILE_START_X = (screenSize.width/2) - (screenSize.width/4)+ 30;
		TILE_START_Y = (screenSize.height/2) - (screenSize.height/4) - 10;
		TILE_PLAYER_START_X = (screenSize.width/2) - (screenSize.width/4)+ 30;
		
		TILE_7X7_PLAYER_START_Y = 6*(693/7) + TILE_START_Y;
		TILE_10X10_PLAYER_START_Y = 9*(693/10) + TILE_START_Y;
		TILE_13X13_PLAYER_START_Y = 12*(693/13) + TILE_START_Y;
		
		gc = Main.gameController.getInstance();
		ArrayList<Player> players = gc.getPlayers();
		ArrayList<Snake> snakes = gc.getSnakes();
		ArrayList<Ladder> ladders = gc.getLadders();
		Tile[][] tiles = gc.getBoard();
		
		String boardImage ="";
		switch(difficulty) {
		case 1:
			 if(selected == 0) {
				 boardImage = ESAY_BOARD_BACKGROUND;
			 }
			 if(selected == 1) {
				 boardImage = Desert_ESAY_BOARD_BACKGROUND;
			 }
			 if(selected == 2) {
				 boardImage = Snow_ESAY_BOARD_BACKGROUND;
			 }
			 if(selected == 3) {
				 boardImage = Forest_ESAY_BOARD_BACKGROUND;
			 }
			   BOARD_SIZE = 7;
			   break;
		case 2:
			if(selected == 0) {
				 boardImage = MEDIUM_BOARD_BACKGROUND;
			 }
			 if(selected == 1) {
				 boardImage = Desert_MEDIUM_BOARD_BACKGROUND;
			 }
			 if(selected == 2) {
				 boardImage = Snow_MEDIUM_BOARD_BACKGROUND;
			 }
			 if(selected == 3) {
				 boardImage = Forest_MEDIUM_BOARD_BACKGROUND;
			 }
			   BOARD_SIZE = 10;
			   break;
		case 3:
			if(selected == 0) {
				 boardImage = HARD_BOARD_BACKGROUND;
			 }
			 if(selected == 1) {
				 boardImage = Desert_HARD_BOARD_BACKGROUND;
			 }
			 if(selected == 2) {
				 boardImage = Snow_HARD_BOARD_BACKGROUND;
			 }
			 if(selected == 3) {
				 boardImage = Forest_HARD_BOARD_BACKGROUND;
			 }
			   BOARD_SIZE = 13;
			   break;
		default : BOARD_SIZE = 0;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1210, 870);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			// Load the Barriecito font using InputStream
	        InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
	        Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(64f);
	        // Create a new font with size 24
	        Font newFont = barriecitoFont.deriveFont(24f);
	        Font titleFont = barriecitoFont.deriveFont(35f);
	        JLabel playersTirle = new JLabel("Players:");
	        playersTirle.setBounds(50, 200, 160, 40);
	        playersTirle.setFont(titleFont);
	        playersTirle.setForeground(new Color(255, 190, 64));
            contentPane.add(playersTirle);
            JLabel lblDiceResult = new JLabel("Dice Result:");
            lblDiceResult.setFont(newFont);
            lblDiceResult.setForeground(new Color(255, 190, 64));
            lblDiceResult.setBounds(screenSize.width - (screenSize.width/7) + 60,(screenSize.height/4) +170 , 160, 45);
            contentPane.add(lblDiceResult);
            lblResultNumb = new JLabel("");
            lblResultNumb.setFont(newFont);
            lblResultNumb.setForeground(new Color(255, 190, 64));
            lblResultNumb.setBounds(screenSize.width - (screenSize.width/7)+190, (screenSize.height/4) +177 , 74, 32);
            contentPane.add(lblResultNumb);

            
	        playerShading = new ArrayList<JLabel>();
	        for(int i=0 ; i<players.size() ; i++) {
	        	
	        	//display the nicknames of the players
	        	
	        	JLabel nickname = new JLabel((i+1)+". "+players.get(i).getNickname());
	        	nickname.setBounds(X_PLAYER, y_PLAYER+ ((i+1)*72), WIDTH_PLAYER, HIGHT_PLAYER);
	        	nickname.setFont(newFont);
	        	nickname.setForeground(new Color(255, 190, 64));
	            contentPane.add(nickname);
	            
	            PlayerController pc = PlayerController.getInstance();
	            JLabel playerIcone = new JLabel("");
	            playerIcone.setBounds(X_PLAYER +WIDTH_PLAYER, nickname.getY(), 45, 32);
	            ImageIcon originalImageIconb = new ImageIcon(GameView.class.getResource(pc.getPlayerObjImagePath(players.get(i))));
	            Image originalImageb = originalImageIconb.getImage();
	            Image scaledImageb = originalImageb.getScaledInstance( playerIcone.getWidth(), playerIcone.getHeight(), Image.SCALE_SMOOTH);
	            ImageIcon scaledImageIconb = new ImageIcon(scaledImageb);
	            playerIcone.setIcon(scaledImageIconb);
	            contentPane.add(playerIcone);
               
	            JLabel shade =  new JLabel("");
	            shade = new JLabel("");
	            shade.setBounds(X_PLAYER - 5 , y_PLAYER + ((i+1)*72)-5 , WIDTH_PLAYER+45+10, HIGHT_PLAYER+32);
		        ImageIcon originalImageIconp = new ImageIcon(GameView.class.getResource("/hawk_images/playerShade.png"));
		        Image originalImagep = originalImageIconp.getImage();
		        Image scaledImagep = originalImagep.getScaledInstance( shade.getWidth(), shade.getHeight(), Image.SCALE_SMOOTH);
		        ImageIcon scaledImageIconp = new ImageIcon(scaledImagep);
		        shade.setIcon(scaledImageIconp);
		        contentPane.add(shade);
		        playerShading.add(shade);
		        shade.setVisible(false);

	            if(i==0) {//shading the current player which is the first player in the start of the game
	            	playerShading.get(i).setVisible(true);
	            	indexCurrentShading = i;
			    }
	            
	            //put on every player on the start tile 
	            
	        }
	        
	        timeLabel = new JLabel("00:00:00");
	        timeLabel.setBounds(50, 50, 160, 40);
	        Font newFont1 = barriecitoFont.deriveFont(30f);
	        timeLabel.setFont(newFont1);
	        timeLabel.setForeground(new Color(255, 190, 64));
	        resumeTimer();
	        contentPane.add(timeLabel);
	        
		}catch(FontFormatException | IOException e) {
            e.printStackTrace();
        }
		
		set_Snakes_ladders_players_on_board(difficulty, snakes, ladders, players, tiles);
		
		diceBtn = new JButton("");
        //diceBtn.setBounds(1040, 161, 120, 113);
        diceBtn.setBounds(screenSize.width - (screenSize.width/7), screenSize.height/4, (screenSize.width/8), (screenSize.height/9));
        diceBtn.setOpaque(false);
        diceBtn.setContentAreaFilled(false);
        diceBtn.setBorderPainted(false);
        diceBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceBtn.png")));
        diceBtn.setBackground(Color.BLACK);
        diceBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        diceBtn.setVisible(true);
        diceBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int resultNum;
				resultNum = DiceController.getInstance().rollDice(difficulty);
				lblResultNumb.setText(Integer.toString(resultNum));
				Player p = gc.getCurrentPlayer();
				int playerCurTile = p.getCurrentTileNumber();
				//if the result is normal steps
				PlayerController pc = PlayerController.getInstance();
				Boolean is_winning = false;
				updateDiceResultImage(resultNum);
				if(resultNum <= 6 ) {
				  
				  gc.getGame().setGameDuration(timeLabel.getText());
				  
				  switch(BOARD_SIZE) {
				  case 7:
					  is_winning = pc.MovePlayerStepsForward(p, resultNum, BOARD_SIZE);
				      break;
				  case 10:
					  is_winning = pc.MovePlayerStepsBack(p, resultNum, BOARD_SIZE);
					  break;
				   default :
					  is_winning = pc.MovePlayerStepsForward(p, resultNum, BOARD_SIZE);
					  
				  }
				  
				  movePlayerOnBoard( p, BOARD_SIZE);
				  stepedOnSpecialTile(p);
				  if(is_winning) {
					  stopTimer();
					  new WinningView(p,Main.gameController.getGame(),GameView.this,h).setVisible(true);
					 // JOptionPane.showMessageDialog(rootPane, "the winner is " + p.getNickname());
				  }
				  
				}else {
					//if the result is question
					Boolean[] answer = new Boolean[1];
					  switch(resultNum) {
					  case 7:
						  is_winning = questionRollFrame(1,answer,p,BOARD_SIZE);
						 break;
					  case 8:
						  is_winning =questionRollFrame(2,answer,p,BOARD_SIZE);
						  break;
					  case 9:
						  is_winning =questionRollFrame(3,answer,p,BOARD_SIZE);
						  break;
					  }
					  System.out.println("im come back");
					  movePlayerOnBoard( p, BOARD_SIZE);
	                  is_winning = stepedOnSpecialTile(p);
					  if(is_winning) {
						  stopTimer();
						  new WinningView(p,Main.gameController.getGame(),GameView.this,h).setVisible(true);
						 // JOptionPane.showMessageDialog(rootPane, "the winner is " + p.getNickname());
					  }

					  
	
				}
				indexCurrentShading = gc.getGame().getPlayers().indexOf(p);
				gc.nextPlayerTurn();
				shadeTheCurentPlayer();
				
				

				
			}
        	
        });
        getContentPane().add(diceBtn);
        MusicButton musicBtn = new MusicButton("/hawk_images/volumeBtn1.png","/hawk_images/mute_btn.png");


      //  JButton musicBtn = new JButton("");
        musicBtn.setBounds(screenSize.width - 300, 50, 76, 69);
        musicBtn.setOpaque(false);
        musicBtn.setContentAreaFilled(false);
        musicBtn.setBorderPainted(false);
        musicBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/volumeBtn1.png")));
        musicBtn.setBackground(Color.BLACK);
        musicBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        musicBtn.setVisible(true);
       
        contentPane.add(musicBtn);
        
        JButton pauseBtn = new JButton("");
        //pauseBtn.setBounds(1102, 23, 35, 35);
        pauseBtn.setBounds(screenSize.width - 200, 50, 67, 65);

        pauseBtn.setOpaque(false);
        pauseBtn.setContentAreaFilled(false);
        pauseBtn.setBorderPainted(false);
        pauseBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/pausebutton.png")));
        pauseBtn.setBackground(Color.BLACK);
        pauseBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        pauseBtn.setVisible(true);
        pauseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stopTimer();
				new PauseView(GameView.this).setVisible(true);
				resumeTimer();
				
			}
        	
        });
        contentPane.add(pauseBtn);
        
        JButton homeBtn = new JButton("");
        //homeBtn.setBounds(1141, 23, 45, 35);
        homeBtn.setBounds(screenSize.width - 100 , 50, 65, 65);

        homeBtn.setOpaque(false);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setBorderPainted(false);
        homeBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/home.png")));
        homeBtn.setBackground(Color.BLACK);
        homeBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        homeBtn.setVisible(true);
        homeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 GameView.super.dispose();
				 
				 new HomeView().setVisible(true);

			}
        	
        });
		

        contentPane.add(homeBtn);
        
        lblResultImage =  new JLabel("");
        lblResultImage.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblResultImage.setForeground(new Color(255, 190, 64));
        lblResultImage.setBounds(screenSize.width - (screenSize.width/7)+80, (screenSize.height/4) +230 , 75, 75);
        
        contentPane.add(lblResultImage);

        
        
        
        
        
        
		boardBackground = new JLabel("");
        //boardBackground.setBounds(204, 81, 816, 693);
		boardBackground.setBounds((screenSize.width/2) - (screenSize.width/4)+ 30, (screenSize.height/2) - (screenSize.height/4) - 10, 816, 693);
        ImageIcon originalImageIconb = new ImageIcon(GameView.class.getResource(boardImage));
        Image originalImageb = originalImageIconb.getImage();
        Image scaledImageb = originalImageb.getScaledInstance( boardBackground.getWidth(), boardBackground.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIconb = new ImageIcon(scaledImageb);
        boardBackground.setIcon(scaledImageIconb);
        contentPane.add(boardBackground);
        
        
		JLabel background = new JLabel("");
		//background.setBounds(0, 0, 1186, 823);
		background.setBounds(0, 0, screenSize.width, screenSize.height);
        ImageIcon originalImageIcon = new ImageIcon(GameView.class.getResource("/hawk_images/greenBackground.jpeg"));
        Image originalImage = originalImageIcon.getImage();
        int labelWidth = background.getWidth();
        int labelHeight = background.getHeight();
        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        background.setIcon(scaledImageIcon);
        getContentPane().add(background);
        
        JLabel playersLabel = new JLabel("");
        playersLabel.setBounds(0, 218, 139, 32);
        contentPane.add(playersLabel);        
        
        JLabel label = new JLabel("New label");
        label.setBounds(1047, 169, 45, 13);
        contentPane.add(label);
        
        JLabel label_1 = new JLabel("New label");
        label_1.setBounds(149, 269, 45, 13);
        contentPane.add(label_1);
           

        
	}
	
	//method that starts the timer of the game
	private void resumeTimer() {
        // Resume the timer with the elapsed time
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsElapsed++;
                updateTimeLabel();
            }
        });

        timer.start();
    }
	
	//method to update the timer
	private void updateTimeLabel() {
		 int hours = secondsElapsed / 3600;
	     int minutes = (secondsElapsed % 3600) / 60;
	     int seconds = secondsElapsed % 60;

	     // Format the time as hh/mm/ss
	     String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
	     timeLabel.setText(formattedTime);
    }
	//method to stop the timer 
	private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
	
	
	//method to set snakes on board
	private void set_Snakes_ladders_players_on_board(int difficulty , ArrayList<Snake> snakes, ArrayList<Ladder> ladders, ArrayList<Player> players,Tile[][]tiles) {
		
		
		switch(difficulty) {
		case 1:
               //set snakes on the board 
			setTilesByDifficulty(TILE_START_X,TILE_START_Y,TILE_7X7_HIGHT, TILE_7X7_WIDTH ,tiles,7);
			setSnakesByDifficulty(TILE_START_X,TILE_START_Y,TILE_7X7_HIGHT,TILE_7X7_WIDTH,snakes);
			setLaddersByDifficulty(TILE_START_X, TILE_START_Y, TILE_7X7_HIGHT, TILE_7X7_WIDTH, ladders);
			setPlayersByDifficulty(TILE_PLAYER_START_X, TILE_7X7_PLAYER_START_Y, TILE_7X7_HIGHT_PLAYER_START, TILE_7X7_WIDTH_PLAYER_START, players);
			break;
		case 2:
			setTilesByDifficulty(TILE_START_X,TILE_START_Y,TILE_10X10_HIGHT, TILE_10X10_WIDTH ,tiles,10);
			setSnakesByDifficulty(TILE_START_X,TILE_START_Y,TILE_10X10_HIGHT,TILE_10X10_WIDTH,snakes);
			setLaddersByDifficulty(TILE_START_X, TILE_START_Y, TILE_10X10_HIGHT, TILE_10X10_WIDTH, ladders);
			setPlayersByDifficulty(TILE_PLAYER_START_X, TILE_10X10_PLAYER_START_Y, TILE_10X10_HIGHT_PLAYER_START, TILE_10X10_WIDTH_PLAYER_START, players);
			break;
		case 3:
			setTilesByDifficulty(TILE_START_X,TILE_START_Y,TILE_13X13_HIGHT, TILE_13X13_WIDTH ,tiles,13);
			setSnakesByDifficulty(TILE_START_X,TILE_START_Y,TILE_13X13_HIGHT,TILE_13X13_WIDTH,snakes);
			setLaddersByDifficulty(TILE_START_X, TILE_START_Y, TILE_13X13_HIGHT, TILE_13X13_WIDTH, ladders);
			setPlayersByDifficulty(TILE_PLAYER_START_X, TILE_13X13_PLAYER_START_Y, TILE_13X13_HIGHT_PLAYER_START, TILE_13X13_WIDTH_PLAYER_START, players);
			break;
		}
	}
	//method to use when we want to set snakes by difficulty or board size
	private void setSnakesByDifficulty(int tile_start_x, int tile_start_y , int tile_hight, int tile_width,ArrayList<Snake> snakes) {
		
		for(Snake s : snakes) {
			   int [] head = s.getHeadBoardCoordinates();
			   int [] taile = s.getTaileBoardCoordinates();
			   int x = tile_start_x + (tile_width * head[1])+5;
			   int y = tile_start_y + (tile_hight * head[0]) +5;
			   int hight =  tile_hight * ((taile[0] - head[0])+1)-10 ;
			   JLabel snake = new JLabel("");
			   snake.setBounds(x, y, tile_width  , hight);
			   ImageIcon originalImageIcon = new ImageIcon(GameView.class.getResource(SnakeController.getInstance().getSnakeImagePath(s)));
		       Image originalImage = originalImageIcon.getImage();
		       Image scaledImage = originalImage.getScaledInstance(snake.getWidth(), snake.getHeight(), Image.SCALE_SMOOTH);
		       ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		       snake.setIcon(scaledImageIcon);

	           contentPane.add(snake);
		   }
	}
	//method to use when we want to set ladders by difficulty or board size
	private void setLaddersByDifficulty(int tile_start_x, int tile_start_y , int tile_hight, int tile_width,ArrayList<Ladder> ladders) {
		
		for(Ladder l : ladders) {
			   /*int [] start = l.getStartCoordinates();
			   int [] end = l.getEndCoordinates();
			   int x = tile_start_x + (tile_width * start[1])+25;
			   int y = tile_start_y + (tile_hight * start[0]) + 20;
			   int hight =  tile_hight * ((end[0] - start[0])+1) - 50;
			   JLabel ladder = new JLabel("");
			   ladder.setBounds(x, y, tile_width -25 , hight);
			   ImageIcon originalImageIcon = new ImageIcon(GameView.class.getResource(LadderController.getInstance().getLadderImagePath(l)));
		       Image originalImage = originalImageIcon.getImage();
		       Image scaledImage = originalImage.getScaledInstance(ladder.getWidth(), ladder.getHeight(), Image.SCALE_SMOOTH);
		       ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		       ladder.setIcon(scaledImageIcon);

	           contentPane.add(ladder);*/
			int [] start = l.getStartCoordinates();
			   int [] end = l.getEndCoordinates();
			   int x = tile_start_x + (tile_width * end[1])+5;
			   int y = tile_start_y + (tile_hight * end[0])+5;
			   int hight =  tile_hight * (l.getNumberOfTiles() +1)-20;
			   JLabel ladder = new JLabel("");
			   ladder.setBounds(x, y, tile_width-10, hight);
			   ImageIcon originalImageIcon = new ImageIcon(GameView.class.getResource(LadderController.getInstance().getLadderImagePath(l)));
		       Image originalImage = originalImageIcon.getImage();
		       Image scaledImage = originalImage.getScaledInstance(ladder.getWidth(), ladder.getHeight(), Image.SCALE_SMOOTH);
		       ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		       ladder.setIcon(scaledImageIcon);
	           contentPane.add(ladder);
		   }
	}
	//59 width 
	//49 hight
	private void setPlayersByDifficulty(int tile_start_x, int tile_start_y , int tile_hight, int tile_width,ArrayList<Player> players) {
		
		playersLabels = new HashMap<Player,JLabel>();
		int i=0;
		int j=0;
		for(Player l : players) {
			   int x = tile_start_x + (tile_width*i);
			   int y = tile_start_y  +(tile_hight*j);
			   JLabel player = new JLabel("");
			   player.setBounds(x, y, tile_width, tile_hight);
			   ImageIcon originalImageIcon = new ImageIcon(GameView.class.getResource(PlayerController.getInstance().getPlayerObjImagePath(l)));
		       Image originalImage = originalImageIcon.getImage();
		       Image scaledImage = originalImage.getScaledInstance(player.getWidth(), player.getHeight(), Image.SCALE_SMOOTH);
		       ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		       player.setIcon(scaledImageIcon);
		       /*int []pos = {BOARD_SIZE-1,0};
		       l.setCurrentCoordinates(pos);*/
		       Main.playerController.resetPlayersPostoStartTile(BOARD_SIZE);
		       playersLabels.put(l,player);
	           contentPane.add(player);
	          
	           if(i==0) {
	        	   i++;
	           }else {
	        	   i = 0;
	        	   j++;
	           }
		   }
		
	}
	private void movePlayerOnBoard(Player player, int board_size) {
		int x,y,tile_width,tile_hight;
		int[] pos = player.getCurrentCoordinates();
		switch(board_size) {
		case 7: 
		    x = (TILE_START_X + (TILE_7X7_WIDTH*pos[1]) ) + (TILE_7X7_WIDTH/2);
			y =( TILE_START_Y  +(TILE_7X7_HIGHT*pos[0])) + (TILE_7X7_HIGHT/2);
			tile_width = TILE_7X7_WIDTH/2;
			tile_hight = TILE_7X7_HIGHT/2;
			break;
		case 10:
		    x = (TILE_START_X + (TILE_10X10_WIDTH*pos[1]) ) + (TILE_10X10_WIDTH/2);
			y =( TILE_START_Y  +(TILE_10X10_HIGHT*pos[0])) + (TILE_10X10_HIGHT/2);
			tile_width = TILE_10X10_WIDTH/2;
			tile_hight = TILE_10X10_HIGHT/2;
			break;
			
		default: 
		    x = (TILE_START_X + (TILE_13X13_WIDTH*pos[1]) ) + (TILE_13X13_WIDTH/2);
			y =( TILE_START_Y  +(TILE_13X13_HIGHT*pos[0])) + (TILE_13X13_HIGHT/2);
			tile_width = TILE_13X13_WIDTH/2;
			tile_hight = TILE_13X13_HIGHT/2;
			
		}
		//playersLabels.get(player).setAlignmentX(x);
		//playersLabels.get(player).setAlignmentY(y);
		playersLabels.get(player).setBounds(x, y, playersLabels.get(player).getWidth(), playersLabels.get(player).getHeight());
	}
	private void setTilesByDifficulty(int tile_start_x, int tile_start_y , int tile_hight, int tile_width,Tile[][] tiles, int difficulty) {
	
		for(int i =0 ; i<difficulty ; i++) {
			for(int j=0 ; j<difficulty ; j++) {
				
				if((tiles[i][j] instanceof QuestionTile) || (tiles[i][j] instanceof SurpriseTile) ) {
					
				 int x = (tile_start_x + (tile_width*j) ) + (tile_width/2);
				 int y =( tile_start_y  +(tile_hight*i)) + (tile_hight/2);
				 JLabel tile = new JLabel("");
				 tile.setBounds(x, y, tile_width/2, tile_hight/2);
				 ImageIcon originalImageIcon = null ;
				 if(tiles[i][j] instanceof QuestionTile) {
					 originalImageIcon = new ImageIcon(GameView.class.getResource("/hawk_images/questionMark.png"));
					 System.out.println(tiles[i][j].getTileNumber()+" tiles "+i+" "+j);
				 }else if(tiles[i][j] instanceof SurpriseTile) {
					 originalImageIcon = new ImageIcon(GameView.class.getResource("/hawk_images/surprise.png"));
				 }
			     Image originalImage = originalImageIcon.getImage();
			     Image scaledImage = originalImage.getScaledInstance(tile.getWidth(), tile.getHeight(), Image.SCALE_SMOOTH);
			     ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
			     tile.setIcon(scaledImageIcon);
		         contentPane.add(tile);
				}
			}
		}
		
	}
	
	public void shadeTheCurentPlayer() {
         int playerIndex = gc.getGame().getPlayers().indexOf(gc.getCurrentPlayer());
         playerShading.get(indexCurrentShading).setVisible(false);
         playerShading.get(playerIndex).setVisible(true);
		// playerShadeLabel.setBounds(X_PLAYER - 5 , y_PLAYER + ((playerIndex+1)*72)-5 , WIDTH_PLAYER+45+10, HIGHT_PLAYER+32);
	}
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

    public Boolean stepedOnSpecialTile(Player p) {
    	//here`s the use of factory method design pattern
        PlayerController pc = Main.playerController;
		TileFactory tf = new TileFactory();
		TileMessage tm1 = tf.GetTileType("QuestionTile", p.getCurrentTileNumber(), null);
		TileMessage tm2 = tf.GetTileType("SurpriseTile", p.getCurrentTileNumber(), null);
		Tile t = gc.getBoard()[p.getCurrentCoordinates()[0]][p.getCurrentCoordinates()[1]];
		Boolean is_question_tile = tm1.checkTileType(t);
		Boolean is_surprise_tile = tm2.checkTileType(t);
		if(is_question_tile) {
			Boolean []answer = new Boolean[1];
			Boolean is_winning;
			is_winning = questionRollFrame(((QuestionTile)Main.gameController.getBoard()[p.getCurrentCoordinates()[0]][p.getCurrentCoordinates()[1]]).getQuestionDifficulty(),answer,p,BOARD_SIZE);
			  movePlayerOnBoard( p, BOARD_SIZE);
			  /*if(is_winning) {
				  JOptionPane.showMessageDialog(rootPane, "the winner is " + p.getNickname());
			  }*/
			  return is_winning;
			//JOptionPane.showMessageDialog(rootPane, "you have to answer question ");
			// here we call the method that opens the JFrame of the question 
		}else {
			if(is_surprise_tile) {
				Direction d = ((SurpriseTile)t).getJumpingSide();
				String message = tm2.setTileMessage(d.toString());
				new SurpriseView(message,GameView.this).setVisible(true);
				if(d.toString().equals("Forward")) {
					 Boolean is_winning = false;
					 gc.getGame().setGameDuration(timeLabel.getText());
					 if(BOARD_SIZE == 7 || BOARD_SIZE == 13) {
					    is_winning = pc.MovePlayerStepsForward(p, 10, BOARD_SIZE);
					 }else {
						 is_winning = pc.MovePlayerStepsBack(p, 10, BOARD_SIZE);
					 }
					  movePlayerOnBoard( p, BOARD_SIZE);
					  return is_winning;
					  /*if(is_winning) {
						  JOptionPane.showMessageDialog(rootPane, "the winner is " + p.getNickname());
					  }*/
				}else {
					  Boolean is_winning = false;
					  gc.getGame().setGameDuration(timeLabel.getText());
					  if(BOARD_SIZE == 7 || BOARD_SIZE == 13) {
					     is_winning = pc.MovePlayerStepsBack(p, 10, BOARD_SIZE);
					  }else {
						  is_winning = pc.MovePlayerStepsForward(p, 10, BOARD_SIZE);
					  }
					  movePlayerOnBoard( p, BOARD_SIZE);

					  /*if(is_winning) {
						  JOptionPane.showMessageDialog(rootPane, "the winner is " + p.getNickname());
					  }*/
					  return is_winning;

				}
			}
		}
		return false;
    }
    
    //updating the dice image result based on dice result number
    public void updateDiceResultImage(int resultNum)
    {
    	switch(resultNum) {
    	case 0:
    	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_0.png")));
    	       lblResultImage.revalidate();
    	       break;
    	case 1:
 	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_1.png")));
 	       lblResultImage.revalidate();
 	       break;
    	case 2:
  	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_2.png")));
  	       lblResultImage.revalidate();
  	       break;
    	case 3:
  	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_3.png")));
  	       lblResultImage.revalidate();
  	       break;
    	case 4:
  	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_4.png")));
  	       lblResultImage.revalidate();
  	       break;
    	case 5:
  	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_5.png")));
  	       lblResultImage.revalidate();
  	       break;
    	case 6:
  	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_6.png")));
  	       lblResultImage.revalidate();
  	       break;
    	case 7:
  	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_7.png")));
  	       lblResultImage.revalidate();
  	       break;
    	case 8:
  	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_8.png")));
  	       lblResultImage.revalidate();
  	       break;
    	case 9:
  	       lblResultImage.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/diceResult_9.png")));
  	       lblResultImage.revalidate();
  	       break;
    	       
    	}
    }
    
	 //method that open JFrame with question of the difficulty that the player got from the roll dice
    public  Boolean questionRollFrame(int difficulty,Boolean [] answer,Player p,int board_size) {
    	// waiting for the frame from abdalla ...................
    	Boolean win = false;
		 //new QuestionMessageView(difficulty,GameView.this,answer).setVisible(true);
    	new QuestionDialog(difficulty,GameView.this,answer).setVisible(true);
		 if(answer[0]) {
			 System.out.println("answerd correct");
			 if(difficulty == 3) {
				 switch(board_size) {
				 case 7:
					 win = Main.playerController.MovePlayerStepsForward(p, 1, board_size);
					 break;
				 case 10:
					 win = Main.playerController.MovePlayerStepsBack(p, 1, board_size);
				     break;
				 case 13:
					 win = Main.playerController.MovePlayerStepsForward(p, 1, board_size);
					 break;
				 
				 }
			 }
			 
		 }else {
			 System.out.println("rong answer");
			 switch(difficulty) {
			 case 1:
				     System.out.println("the q level was 1");
					 switch(board_size) {
					 	case 7:
					 		System.out.println("im going to move in 7 size");
					 		win = Main.playerController.MovePlayerStepsBack(p, 1, board_size);
					 		System.out.println("im back from moving");
					 		break;
					 	case 10:
					 		System.out.println("im going to move in 10 size");
					 		win = Main.playerController.MovePlayerStepsForward(p, 1, board_size);
					 		System.out.println("im back from moving");
					 		break;
					 	case 13:
					 		System.out.println("im going to move in 10 size");
					 		win = Main.playerController.MovePlayerStepsBack(p, 1, board_size);
					 		System.out.println("im back from moving");
					 		break;
					 }
				 
				 break;
				 
			 case 2:
				     System.out.println("the q level was 2");
					 switch(board_size) {
					 	case 7:
					 		System.out.println("im going to move in 7 size");
					 		win = Main.playerController.MovePlayerStepsBack(p, 2, board_size);
					 		System.out.println("im back from moving");
					 		break;
					 	case 10:
					 		System.out.println("im going to move in 10 size");
					 		win = Main.playerController.MovePlayerStepsForward(p, 2, board_size);
					 		System.out.println("im back from moving");
					 		break;
					 	case 13:
					 		System.out.println("im going to move in 13 size");
					 		win = Main.playerController.MovePlayerStepsBack(p, 2, board_size);
					 		System.out.println("im back from moving");
					 		break;
					 }
				 
				 break;
				 
			 case 3:
				     System.out.println("the q level was 3");
					 switch(board_size) {
					 	case 7:
					 		System.out.println("im going to move in 7 size");
					 		win = Main.playerController.MovePlayerStepsBack(p, 3, board_size);
					 		System.out.println("im back from moving");
					 		break;
					 	case 10:
					 		System.out.println("im going to move in 10 size");
					 		win = Main.playerController.MovePlayerStepsForward(p, 3, board_size);
					 		System.out.println("im back from moving");
					 		break;
					 	case 13:
					 		System.out.println("im going to move in 13 size");
					 		win = Main.playerController.MovePlayerStepsBack(p, 3, board_size);
					 		System.out.println("im back from moving");
					 		break;
					 }
				 
				 break;
			 }
			 
		 }
		 return  win ;
		 

    }

	 
}
