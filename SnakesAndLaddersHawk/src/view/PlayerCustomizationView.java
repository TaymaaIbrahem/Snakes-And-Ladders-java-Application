package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument.Content;

import Utils.PlayerObject;
import controller.GameController;
import controller.PlayerController;
import Utils.PlayerColor;
import model.Game;
import model.Player;

import javax.swing.JButton;

public class PlayerCustomizationView extends JInternalFrame {
	private JTextField txtPlayerName1;
	private JTextField txtPlayerName2;
	private JTextField txtPlayerName3;
	private JTextField txtPlayerName4;
	private static Container contentPane;
	private Font barriecitoFont;
	private JComboBox<Integer> numPlayersComboBox;
	private JComboBox<Object> Player4ComboBox;
	private JComboBox<Object> Player3ComboBox;
	private JComboBox<String> backgroundComboBox;
	private JLabel lblPlayer3;
	private JLabel lblPlayer4;
	private ArrayList<Player> playerList;
	private JLabel lblPreviewImage1;
	private JLabel lblPreviewImage2;
	private JLabel lblPreviewImage3;
	private JLabel lblPreviewImage4;
	private JLabel lblChooseBackground;
	private JLabel lblPreview;
	private JLabel lblArrow;
	private Dimension screenSize;
	private JButton playBtn;
    private int yValue = 136; //y coordinates 
    private int yValue2 = 76; // y2 coordinates
	
	
	
	private String path1 = "/hawk_images/manWithYellowShirt.png";
    private String path2 = "/hawk_images/man2.png";
    private String path3 = "/hawk_images/woman1.png";
    private String path4 = "/hawk_images/woman2.png";
    private String path5 = "/hawk_images/girlWithGreenShirt.png";
    private String path6 = "/hawk_images/oldMan.png";

	private MusicButton musicBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PlayerCustomizationView	frame = new PlayerCustomizationView(null);
					frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayerCustomizationView(HomeView h) {

		  try {
	            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		playerList = new ArrayList<Player>();
		setBounds(100, 100, 1194, 823);
		SwingUtilities.invokeLater(() -> {
	        try {
	            this.setMaximum(true);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    });
		getContentPane().setLayout(null);
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    
        // Load the Barriecito font using InputStream
        InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
        Font barriecitoFont;
		try {
			barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(64f);

        // Create a new font with size 24
        Font newFont64 = barriecitoFont.deriveFont(64f);
        Font newFont28 = barriecitoFont.deriveFont(28f);

        
        JLabel PlayerCustomizationLabel = new JLabel("Player Customization");
        PlayerCustomizationLabel.setForeground(new Color(255, 204, 102));
        PlayerCustomizationLabel.setFont(newFont64);
        PlayerCustomizationLabel.setBounds(433 , 11, 575, 128);
        PlayerCustomizationLabel.setBounds(((screenSize.width/2)- (screenSize.width/10))-90, ((screenSize.height)/3)- 300, 575, 128);
        getContentPane().add(PlayerCustomizationLabel);
        
        
        
        JLabel DifficultyLevelLabel = new JLabel("Difficulty Level");
        DifficultyLevelLabel.setForeground(Color.WHITE);
        DifficultyLevelLabel.setFont(newFont28);
        DifficultyLevelLabel.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)- 150, 201, 30);
        getContentPane().add(DifficultyLevelLabel);
        
        JLabel NumberOfPlayersView = new JLabel("Number Of Players");
        NumberOfPlayersView.setForeground(Color.WHITE);
        NumberOfPlayersView.setFont(newFont28);
        NumberOfPlayersView.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)-100, 251, 43);
        getContentPane().add(NumberOfPlayersView);
        
        JComboBox<String> difficultyComboBox = new JComboBox<String>();
        difficultyComboBox.setToolTipText("Choose difficulty");
        difficultyComboBox.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)-150, 195, 30);
        difficultyComboBox.addItem("Easy");
        difficultyComboBox.addItem("Medium");
        difficultyComboBox.addItem("Hard");
        
        
        getContentPane().add(difficultyComboBox);
        
        numPlayersComboBox = new JComboBox<Integer>();
        numPlayersComboBox.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250,  ((screenSize.height)/3)-100, 195, 30);
        numPlayersComboBox.addItem(2);
        numPlayersComboBox.addItem(3);
        numPlayersComboBox.addItem(4);
        numPlayersComboBox.setSelectedItem(2);
        numPlayersComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				UpdateView();
				UpdateBackgroundSelection();
				
			}
		});
        
        getContentPane().add(numPlayersComboBox);
        
        
		ImageIcon man1 =  new ImageIcon(PlayerCustomizationView.class.getResource(path1));
		ImageIcon man2 =  new ImageIcon(PlayerCustomizationView.class.getResource(path2));
		ImageIcon woman1 =  new ImageIcon(PlayerCustomizationView.class.getResource(path3));
		ImageIcon woman2 =  new ImageIcon(PlayerCustomizationView.class.getResource(path4));
		ImageIcon girlWithGreenShirt = new ImageIcon(PlayerCustomizationView.class.getResource(path5));
		ImageIcon oldMan = new ImageIcon(PlayerCustomizationView.class.getResource(path6));
        

		
        Object[] items =
        {
        		man1,
        		man2,
        		woman1,
        		woman2,
        		girlWithGreenShirt,
        		oldMan
    		 
        };

        
        JLabel lblPlayer1 = new JLabel("Player 1");
        lblPlayer1.setForeground(Color.WHITE);
        lblPlayer1.setFont(newFont28);
        lblPlayer1.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)-50, 116, 32);
        getContentPane().add(lblPlayer1);
        
        JLabel lblPlayer2 = new JLabel("Player 2");
        lblPlayer2.setForeground(Color.WHITE);
        lblPlayer2.setFont(newFont28);
        lblPlayer2.setBounds(((screenSize.width/2)- (screenSize.width/10))-50,((screenSize.height)/3), 116, 32);
        getContentPane().add(lblPlayer2);
        
        lblPlayer3 = new JLabel("Player 3");
        lblPlayer3.setForeground(Color.WHITE);
        lblPlayer3.setFont(newFont28);
        lblPlayer3.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+50, 116, 32);
        getContentPane().add(lblPlayer3);
        
        lblPlayer4 = new JLabel("Player 4");
        lblPlayer4.setForeground(Color.WHITE);
        lblPlayer4.setFont(newFont28);
        lblPlayer4.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+100, 116, 32);
        getContentPane().add(lblPlayer4);
        
         lblPreview = new JLabel("Preview");
        lblPreview.setForeground(Color.WHITE);
        lblPreview.setFont(newFont28);
        lblPreview.setBounds(((screenSize.width/2)- (screenSize.width/10))+250, ((screenSize.height)/3)+yValue2, 116, 32);
        getContentPane().add(lblPreview);
        
         lblChooseBackground = new JLabel("Background:");
        lblChooseBackground.setForeground(Color.WHITE);
        lblChooseBackground.setFont(newFont28);

        lblChooseBackground.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+yValue2, 171, 32);
        getContentPane().add(lblChooseBackground);
        
        JComboBox<Object> player1comboBox;

        player1comboBox = new JComboBox<Object>( items );
        player1comboBox.setBounds((((screenSize.width/2)- (screenSize.width/10))-50)+130, ((screenSize.height)/3)-50, 60, 43);
        getContentPane().add(player1comboBox);
        
        JComboBox<Object> player2Combobox;

        player2Combobox = new JComboBox<Object>( items );
        player2Combobox.setBounds((((screenSize.width/2)- (screenSize.width/10))-50)+130, ((screenSize.height)/3), 60, 43);
        getContentPane().add(player2Combobox);
        
        Player3ComboBox = new JComboBox<Object>( items );
        Player3ComboBox.setBounds((((screenSize.width/2)- (screenSize.width/10))-50)+130, ((screenSize.height)/3)+50, 60, 43);
        getContentPane().add(Player3ComboBox);
        
        Player4ComboBox = new JComboBox<Object>(items);
        Player4ComboBox.setBounds((((screenSize.width/2)- (screenSize.width/10))-50)+130, ((screenSize.height)/3)+100, 60, 43);
        getContentPane().add(Player4ComboBox);
        
        
        txtPlayerName1 = new JTextField();
        txtPlayerName1.setToolTipText("asdasdas");
        txtPlayerName1.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)-50, 195, 32);
        getContentPane().add(txtPlayerName1);
        txtPlayerName1.setColumns(10);
        txtPlayerName1.setText("");
        txtPlayerName1.setForeground(Color.BLACK);
        

        
        

        
        
        txtPlayerName2 = new JTextField();
        txtPlayerName2.setColumns(10);
        txtPlayerName2.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3), 195, 32);
        getContentPane().add(txtPlayerName2);
        txtPlayerName2.setText("");
        txtPlayerName2.setForeground(Color.BLACK);
;
        
        txtPlayerName3 = new JTextField();
        txtPlayerName3.setColumns(10);
        txtPlayerName3.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250,  ((screenSize.height)/3)+50, 195, 32);
        getContentPane().add(txtPlayerName3);
        txtPlayerName3.setText("");
        txtPlayerName3.setForeground(Color.BLACK);
        
        txtPlayerName4 = new JTextField();
        txtPlayerName4.setColumns(10);
        txtPlayerName4.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+100, 195, 32);
        getContentPane().add(txtPlayerName4);
        txtPlayerName4.setText("");
        txtPlayerName4.setForeground(Color.BLACK);
        
        backgroundComboBox = new JComboBox<String>();
        backgroundComboBox.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+yValue, 116, 32);
        backgroundComboBox.addItem("Default");
        backgroundComboBox.addItem("Desert");
        backgroundComboBox.addItem("Snow");
        backgroundComboBox.addItem("Forest");
        
        getContentPane().add(backgroundComboBox);
        
        backgroundComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateBackgroundPreview(backgroundComboBox.getSelectedIndex());
				
			}
		});
        musicBtn =new MusicButton("/hawk_images/volumeBtn1.png","/hawk_images/mute_btn.png");

        musicBtn.setOpaque(false);
        musicBtn.setContentAreaFilled(false);
        musicBtn.setBorderPainted(false);
        musicBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/volumeBtn1.png")));
        musicBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        musicBtn.setBackground(Color.BLACK);
        musicBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        //btnNewButton.setFont(barriecitoFont);
        
        musicBtn.setForeground(Color.WHITE);
        musicBtn.setVisible(true);
        musicBtn.setBounds(screenSize.width - 200, 50, 67, 65);
        getContentPane().add(musicBtn);
        
        
        playBtn = new JButton("");
        playBtn.setBounds(1023, 703, 149, 51);
        playBtn.setBounds(((screenSize.width/2)- (screenSize.width/10))+100, ((screenSize.height)/3)+255, 149, 51);
        playBtn.setOpaque(false);
        playBtn.setContentAreaFilled(false);
        playBtn.setBorderPainted(false);
		ImageIcon originalImageIconnn = new ImageIcon(PlayerCustomizationView.class.getResource("/hawk_images/playBtn.png"));
        Image originalImagehhh = originalImageIconnn.getImage();   
        Image scaledImagehhh = originalImagehhh.getScaledInstance( playBtn.getWidth(), playBtn.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIconhhh = new ImageIcon(scaledImagehhh);        // Set the scaled image as the icon for the label
        playBtn.setIcon(scaledImageIconhhh); 
        playBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isError = false;
				boolean validTexts = false;
				int numPlayers = Integer.parseInt(numPlayersComboBox.getSelectedItem().toString());
				int index1;int index2;int index3;int index4;
				String image1;String image2;String image3;String image4;
				Player player1;Player player2;Player player3;Player player4;
				switch(numPlayers) {
				case 2:
					index1 = player1comboBox.getSelectedIndex();
					index2 = player2Combobox.getSelectedIndex();
					if(index1 == index2) { // if the players have the same icon
					JOptionPane.showMessageDialog(null, "Error: Players must have different icons");
					isError = true;
					}
					validTexts = CheckValidText(2); //Check valid inputs
					if(isError != true && validTexts == true) {
					image1 = getPathByIndex(index1);
					image2 = getPathByIndex(index2);
					player1 = new Player(txtPlayerName1.getText(), PlayerColor.Black, PlayerController.getInstance().getPlayerObject(image1));
					player2 = new Player(txtPlayerName2.getText(), PlayerColor.Orange, PlayerController.getInstance().getPlayerObject(image2));
					playerList.add(player1);
					playerList.add(player2);
					
					System.out.println(player1.getObject().toString());
					System.out.println(player2.getObject().toString());
					}
					break;
					case 3:

						index1 = player1comboBox.getSelectedIndex();
						index2 = player2Combobox.getSelectedIndex();
						index3 = Player3ComboBox.getSelectedIndex();
						//if the player have the same icons
						if(index1 == index2 || index2 == index3 || index1 == index3) {
							JOptionPane.showMessageDialog(null, "Error: Players must have different icons");
							isError = true;
						}
						validTexts = CheckValidText(3); //Check valid inputs
						if(isError != true && validTexts == true) {
						image1 = getPathByIndex(index1);
						image2 = getPathByIndex(index2);
						image3 = getPathByIndex(index3);
						
						player1 = new Player(txtPlayerName1.getText(), PlayerColor.Black, PlayerController.getInstance().getPlayerObject(image1));
						player2 = new Player(txtPlayerName2.getText(), PlayerColor.Orange, PlayerController.getInstance().getPlayerObject(image2));
						player3 = new Player(txtPlayerName3.getText(), PlayerColor.Orange,  PlayerController.getInstance().getPlayerObject(image3));
						playerList.add(player1);
						playerList.add(player2);
						playerList.add(player3);
						}
						break;
					case 4:

						index1 = player1comboBox.getSelectedIndex();
						index2 = player2Combobox.getSelectedIndex();
						image1 = getPathByIndex(index1);
						image2 = getPathByIndex(index2);
						index3 = Player3ComboBox.getSelectedIndex();
						image3 = getPathByIndex(index3);
						index4 = Player4ComboBox.getSelectedIndex();
						image4 = getPathByIndex(index4);
						// if the players have the same icons
						if(index1 == index2 || index1 == index3 || index1 == index4 || index2 == index3 || index2 == index4 || index3 == index4) {
							JOptionPane.showMessageDialog(null, "Error: Players must have different icons");
							isError = true;
						}
						validTexts = CheckValidText(4); //Check valid inputs
						if(isError != true && validTexts == true) {
						
						player1 = new Player(txtPlayerName1.getText(), PlayerColor.Black, PlayerController.getInstance().getPlayerObject(image1));
						player2 = new Player(txtPlayerName2.getText(), PlayerColor.Orange, PlayerController.getInstance().getPlayerObject(image2));
						player3	= new Player(txtPlayerName3.getText(), PlayerColor.Orange,  PlayerController.getInstance().getPlayerObject(image3));
					    player4 = new Player(txtPlayerName4.getText(), PlayerColor.Red, PlayerController.getInstance().getPlayerObject(image4));
					    playerList.add(player1);
					    playerList.add(player2);
					    playerList.add(player3);
					    playerList.add(player4);
						}
						break;
				}
				if(isError == false && validTexts == true) {
				GameController.getInstance().startGame(getGameDifficulty((String)difficultyComboBox.getSelectedItem()), playerList);
				PlayerCustomizationView.super.dispose();
				h.setVisible(true);
				h.dispose();
				  new GameView(getGameDifficulty((String)difficultyComboBox.getSelectedItem()),h,backgroundComboBox.getSelectedIndex()).setVisible(true);
				}

			}
			
		});
        getContentPane().add(playBtn);

//		JButton homeBtn = new JButton("");
//        homeBtn.setBounds(1085, 42, 52, 43);
//		ImageIcon originalImageIconh = new ImageIcon(PlayerCustomizationView.class.getResource("/hawk_images/homeBtn.png"));
//        Image originalImageh = originalImageIconh.getImage();   
//        Image scaledImageh = originalImageh.getScaledInstance( homeBtn.getWidth(), homeBtn.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon scaledImageIconh = new ImageIcon(scaledImageh);        // Set the scaled image as the icon for the label
//        homeBtn.setIcon(scaledImageIconh);
//        homeBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				dispose();
//				h.setContentPane(h.c);
//				h.getContentPane().setVisible(true);
//				
//			}
//        	
//        });
//		getContentPane().add(homeBtn);
//        
        JButton homeBtn = new JButton("");
		homeBtn.setBounds(screenSize.width - 100 , 50, 65, 65);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		homeBtn.setIcon(new ImageIcon(AdminLogIn.class.getResource("/hawk_images/home.png")));
		homeBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		homeBtn.setBackground(Color.BLACK);
		homeBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
	        //btnNewButton.setFont(barriecitoFont);
	        
		homeBtn.setForeground(Color.WHITE);
        homeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				dispose();
				h.setContentPane(h.c);
				h.getContentPane().setVisible(true);
			}
        	
        });
		getContentPane().add(homeBtn);
        lblArrow = new JLabel(">>>>");
        lblArrow.setForeground(Color.WHITE);
        lblArrow.setFont(new Font("Barriecito", Font.PLAIN, 28));
        lblArrow.setBounds(((screenSize.width/2)- (screenSize.width/10))+100, ((screenSize.height)/3)+getyValue(), 116, 32);
        getContentPane().add(lblArrow);
        
        lblPreviewImage1 = new JLabel("");
        lblPreviewImage1.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+getyValue(), 186, 99);
        ImageIcon originalImageIcon = new ImageIcon(PlayerCustomizationView.class.getResource("/hawk_images/defaultBackground.png"));
       // Get the image from the ImageIcon
        Image originalImage = originalImageIcon.getImage();
       // Get the size of the labe
        int labelWidth = lblPreviewImage1.getWidth();
       int  labelHeight = lblPreviewImage1.getHeight();
       // Scale the image to fit the label while maintaining aspect ratio
       Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
       // Create a new ImageIcon with the scaled image
        ImageIcon scaledImageIcon1 = new ImageIcon(scaledImage);
       
       // Set the scaled image as the icon for the label
        lblPreviewImage1.setIcon(scaledImageIcon1);
       getContentPane().add(lblPreviewImage1);
       
       lblPreviewImage2 = new JLabel("");
       lblPreviewImage2.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+getyValue(), 186, 99);
       ImageIcon originalImageIcon2 = new ImageIcon(PlayerCustomizationView.class.getResource("/hawk_images/desertBackground.png"));
      // Get the image from the ImageIcon
       Image originalImage2 = originalImageIcon2.getImage();
      // Get the size of the labe
       int labelWidth2 = lblPreviewImage2.getWidth();
      int  labelHeight2 = lblPreviewImage2.getHeight();
      // Scale the image to fit the label while maintaining aspect ratio
      Image scaledImage2 = originalImage2.getScaledInstance(labelWidth2, labelHeight2, Image.SCALE_SMOOTH);
      // Create a new ImageIcon with the scaled image
       ImageIcon scaledImageIcon2 = new ImageIcon(scaledImage2);
      
      // Set the scaled image as the icon for the label
       lblPreviewImage2.setIcon(scaledImageIcon2);
      getContentPane().add(lblPreviewImage2);
      
      lblPreviewImage3 = new JLabel("");
      lblPreviewImage3.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+getyValue(), 186, 99);
      ImageIcon originalImageIcon3 = new ImageIcon(PlayerCustomizationView.class.getResource("/hawk_images/snowBackground.png"));
     // Get the image from the ImageIcon
      Image originalImage3 = originalImageIcon3.getImage();
     // Get the size of the labe
      int labelWidth3 = lblPreviewImage1.getWidth();
     int  labelHeight3 = lblPreviewImage1.getHeight();
     // Scale the image to fit the label while maintaining aspect ratio
     Image scaledImage3 = originalImage3.getScaledInstance(labelWidth3, labelHeight3, Image.SCALE_SMOOTH);
     // Create a new ImageIcon with the scaled image
      ImageIcon scaledImageIcon3 = new ImageIcon(scaledImage3);
     
     // Set the scaled image as the icon for the label
      lblPreviewImage3.setIcon(scaledImageIcon3);
     getContentPane().add(lblPreviewImage3);
     
     lblPreviewImage4 = new JLabel("");
     lblPreviewImage4.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
     ImageIcon originalImageIcon4 = new ImageIcon(PlayerCustomizationView.class.getResource("/hawk_images/forestBackground.png"));
    // Get the image from the ImageIcon
     Image originalImage4 = originalImageIcon4.getImage();
    // Get the size of the labe
     int labelWidth4 = lblPreviewImage4.getWidth();
    int  labelHeight4 = lblPreviewImage4.getHeight();
    // Scale the image to fit the label while maintaining aspect ratio
    Image scaledImage4 = originalImage4.getScaledInstance(labelWidth4, labelHeight4, Image.SCALE_SMOOTH);
    // Create a new ImageIcon with the scaled image
     ImageIcon scaledImageIcon4 = new ImageIcon(scaledImage4);
    
    // Set the scaled image as the icon for the label
     lblPreviewImage4.setIcon(scaledImageIcon4);
    getContentPane().add(lblPreviewImage4);
       
        
        // Bolb Label
        JLabel bolbBackground = new JLabel();
        int xb = (screenSize.width / 2 ) - ( (screenSize.width / 2 )+(screenSize.width / 8 ))/2 ;
        int yb = ((screenSize.height- 1000)/2);
        int widthb = ( (screenSize.width / 2 )+(screenSize.width / 8 ));
        
        bolbBackground.setBounds(xb, yb,widthb , 1000);
        try {
            Image bolblImage = ImageIO.read(PlayerCustomizationView.class.getResource("/hawk_images/backgroundInsideShape.png"));
            int bolbWidth = bolbBackground.getWidth();
            int bolbHeight = bolbBackground.getHeight();
            Image scaledImagebolb = bolblImage.getScaledInstance(bolbWidth, bolbHeight, Image.SCALE_DEFAULT);
            ImageIcon bolbscaledImageIcon = new ImageIcon(scaledImagebolb);
            bolbBackground.setIcon(bolbscaledImageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getContentPane().add(bolbBackground);


        
		JLabel background = new JLabel("");
		background.setBounds(0, 0, screenSize.width, screenSize.height);
		// Load the image from a resource (adjust the path as needed)
        ImageIcon originalImageIcon1 = new ImageIcon(PlayerCustomizationView.class.getResource("/hawk_images/backgroundRectangle.png"));
        // Get the image from the ImageIcon
        Image originalImage1 = originalImageIcon1.getImage();
        // Get the size of the label
        int labelWidth1 = background.getWidth();
        int labelHeight1 = background.getHeight();
        // Scale the image to fit the label while maintaining aspect ratio
        Image scaledImage1 = originalImage1.getScaledInstance(labelWidth1, labelHeight1, Image.SCALE_SMOOTH);
        // Create a new ImageIcon with the scaled image
        ImageIcon scaledImageIcon11 = new ImageIcon(scaledImage1);
        
        // Set the scaled image as the icon for the label
        background.setIcon(scaledImageIcon11);

        getContentPane().add(background);
        
        JLabel lblArrow1 = new JLabel(">>>>");
        lblArrow1.setForeground(Color.WHITE);
        lblArrow1.setFont(new Font("Barriecito", Font.PLAIN, 28));
        lblArrow1.setBounds(455, 654, 133, 32);
        getContentPane().add(lblArrow1);
        
      
        
        
       
        


        

		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        


        
		UpdateView();
		UpdateBackgroundPreview(backgroundComboBox.getSelectedIndex());
	//	UpdateBackgroundSelection();
        
        
	}
	//update the View of the labels and text based on player number
	public void UpdateView(){
		if(Integer.parseInt(numPlayersComboBox.getSelectedItem().toString()) == 3) {
			lblPlayer3.setVisible(true);
			txtPlayerName3.setVisible(true);
			lblPlayer4.setVisible(false);
			txtPlayerName4.setVisible(false);
			Player3ComboBox.setVisible(true);
			Player4ComboBox.setVisible(false);
	        
	        
			
		}else if(Integer.parseInt(numPlayersComboBox.getSelectedItem().toString()) == 4) {
			lblPlayer3.setVisible(true);
			txtPlayerName3.setVisible(true);
			lblPlayer4.setVisible(true);
			txtPlayerName4.setVisible(true);
			Player3ComboBox.setVisible(true);
			Player4ComboBox.setVisible(true);
			

	        
		}else {
			lblPlayer3.setVisible(false);
			txtPlayerName3.setVisible(false);
			lblPlayer4.setVisible(false);
			txtPlayerName4.setVisible(false);
			Player3ComboBox.setVisible(false);
			Player4ComboBox.setVisible(false);
		}
		
	}
	//update the background selection position based on player number selected;
	public void UpdateBackgroundSelection() {
		if(Integer.parseInt(numPlayersComboBox.getSelectedItem().toString()) == 3) {

		
        yValue = 186;
        yValue2 = 126;
        playBtn.setBounds(((screenSize.width/2)- (screenSize.width/10))+100, ((screenSize.height)/3)+305, 149, 51);
        lblPreview.setBounds(((screenSize.width/2)- (screenSize.width/10))+250, ((screenSize.height)/3)+yValue2, 116, 32);
        lblArrow.setBounds(((screenSize.width/2)- (screenSize.width/10))+100, ((screenSize.height)/3)+yValue, 116, 32);
        lblPreviewImage1.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
        lblPreviewImage2.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
        lblPreviewImage3.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
        lblPreviewImage4.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
        lblChooseBackground.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+yValue2, 171, 32);
        backgroundComboBox.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+yValue, 116, 32);
        
        playBtn.revalidate();
        lblArrow.revalidate();
        backgroundComboBox.revalidate();
        lblChooseBackground.revalidate();
        lblPreview.revalidate();
        lblPreviewImage1.revalidate();
        lblPreviewImage2.revalidate();
        lblPreviewImage3.revalidate();
        lblPreviewImage4.revalidate();
        
        
		}else if(Integer.parseInt(numPlayersComboBox.getSelectedItem().toString()) == 4) {
			yValue = 236;
			yValue2 = 176;
			playBtn.setBounds(((screenSize.width/2)- (screenSize.width/10))+100, ((screenSize.height)/3)+355, 149, 51);
	        lblPreview.setBounds(((screenSize.width/2)- (screenSize.width/10))+250, ((screenSize.height)/3)+yValue2, 116, 32);
	        lblArrow.setBounds(((screenSize.width/2)- (screenSize.width/10))+100, ((screenSize.height)/3)+yValue, 116, 32);
	        lblPreviewImage1.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
	        lblPreviewImage2.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
	        lblPreviewImage3.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
	        lblPreviewImage4.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
	        lblChooseBackground.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+yValue2, 171, 32);
	        backgroundComboBox.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+yValue, 116, 32);
	        
	        playBtn.revalidate();
	        lblArrow.revalidate();
	        backgroundComboBox.revalidate();
	        lblChooseBackground.revalidate();
	        lblPreview.revalidate();
	        lblPreviewImage1.revalidate();
	        lblPreviewImage2.revalidate();
	        lblPreviewImage3.revalidate();
	        lblPreviewImage4.revalidate();

	        
		}else {

	        yValue = 136;
	        yValue2 = 76;
	        playBtn.setBounds(((screenSize.width/2)- (screenSize.width/10))+100, ((screenSize.height)/3)+255, 149, 51);
	        lblPreview.setBounds(((screenSize.width/2)- (screenSize.width/10))+250, ((screenSize.height)/3)+yValue2, 116, 32);
	        lblArrow.setBounds(((screenSize.width/2)- (screenSize.width/10))+100, ((screenSize.height)/3)+yValue, 116, 32);
	        lblPreviewImage1.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
	        lblPreviewImage2.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
	        lblPreviewImage3.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
	        lblPreviewImage4.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+yValue, 186, 99);
	        lblChooseBackground.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+yValue2, 171, 32);
	        backgroundComboBox.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+yValue, 116, 32);
	        
	        playBtn.revalidate();
	        lblArrow.revalidate();
	        backgroundComboBox.revalidate();
	        lblChooseBackground.revalidate();
	        lblPreview.revalidate();
	        lblPreviewImage1.revalidate();
	        lblPreviewImage2.revalidate();
	        lblPreviewImage3.revalidate();
	        lblPreviewImage4.revalidate();
		}
	}
	
	public int getyValue() {
		return yValue;
	}

	public void setyValue(int yValue) {
		this.yValue = yValue;
	}

	public int getyValue2() {
		return yValue2;
	}

	public void setyValue2(int yValue2) {
		this.yValue2 = yValue2;
	}

	public String getPathByIndex(int index) {
		String toReturnPath = "";
		switch(index) {
		case 0:
			toReturnPath =  path1;
			break;
		case 1:
			toReturnPath =  path2;
			break;
		case 2:
			toReturnPath =  path3;
			break;
		case 3:
			toReturnPath = path4;
			break;
		case 4:
			toReturnPath = path5;
			break;
		case 5:
			toReturnPath = path6;
			break;
		}
		return toReturnPath;
	}
	
	public int getGameDifficulty(String str) {
		int toReturn = 0;;
		switch(str) {
			case "Easy":
			toReturn = 1;
			break;
			case "Medium":
				toReturn = 2;
				break;
			case "Hard":
				toReturn = 3;
				break;
		}
		return toReturn;
	}
	public ArrayList<Player> getPlayersList() {
		return playerList;
		
	}
	
	public boolean CheckValidText(int playersNum) {
		boolean isValid = true;
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
		switch(playersNum) {
		case 2:
			Matcher matcher1 = pattern.matcher(txtPlayerName1.getText().toString());
			Matcher matcher2 = pattern.matcher(txtPlayerName2.getText().toString());
			if(matcher1.find() || matcher2.find()) {
				JOptionPane.showMessageDialog(null, "Error: Separators are not allowed in names");
				isValid = false;
			}
			//check if the name has more than 20 characters
			else if(txtPlayerName1.getText().length() > 20 || txtPlayerName2.getText().length() > 20)
			{
				JOptionPane.showMessageDialog(null, "Error:Names are too long must be less than 20 letters");
				isValid = false;
			}
			//check if the fields are empty
			else if(txtPlayerName1.getText().equals("Enter Name") || txtPlayerName1.getText().isEmpty() ||
			   txtPlayerName2.getText().equals("Enter Name") || txtPlayerName2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error: Enter Players Names");
				isValid = false;
			}
			//check if the players have same name
			else if (txtPlayerName1.getText().equals(txtPlayerName2.getText())) {
				JOptionPane.showMessageDialog(null, "Error: Players must have different names");
				isValid = false;	
			}
			//check if names contain only spaces
			else if (txtPlayerName1.getText().trim().isEmpty() ||
				txtPlayerName2.getText().trim().isEmpty())
				{
				JOptionPane.showMessageDialog(null, "Error: Names contain only spaces not allowed");
				isValid = false;	
					}
			else {
				isValid = true;
			}
			break;
		case 3:
			Matcher matcher11 = pattern.matcher(txtPlayerName1.getText().toString());
			Matcher matcher22 = pattern.matcher(txtPlayerName2.getText().toString());
			Matcher matcher33 = pattern.matcher(txtPlayerName3.getText().toString());
			if(matcher11.find() || matcher22.find() ||  matcher33.find()) {
				JOptionPane.showMessageDialog(null, "Error: Separators are not allowed in names");
				isValid = false;
			}
			//check if the name has more than 20 characters
			else if(txtPlayerName1.getText().length() > 20 || txtPlayerName2.getText().length() > 20 || txtPlayerName3.getText().length() > 20)
			{
				JOptionPane.showMessageDialog(null, "Error:Names are too long must be less than 20 letters");
				isValid = false;
			}
			//check if the players have the same icon
			else if(txtPlayerName1.getText().equals("Enter Name") || txtPlayerName1.getText().isEmpty() || txtPlayerName2.getText().equals("Enter Name") || txtPlayerName2.getText().isEmpty() || txtPlayerName3.getText().equals("Enter Name") || txtPlayerName3.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: Enter Players Names");
			isValid = false;
			}
			//check if the players have the same name
			else if (txtPlayerName1.getText().equals(txtPlayerName2.getText()) ||
			txtPlayerName1.getText().equals(txtPlayerName3.getText()) || 
			txtPlayerName2.getText().equals(txtPlayerName3.getText()) ){
			JOptionPane.showMessageDialog(null, "Error: Players must have different names");
			isValid = false;	
			}
			//check if names contain only spaces
			else if (txtPlayerName1.getText().trim().isEmpty() ||
				txtPlayerName2.getText().trim().isEmpty() ||
				txtPlayerName3.getText().trim().isEmpty())
				{
				JOptionPane.showMessageDialog(null, "Error: Names contain only spaces not allowed");
				isValid = false;	
					}
			else {
			isValid = true;
		}
		break;
		case 4:
			//check if the inputs have separators
			Matcher matcher111 = pattern.matcher(txtPlayerName1.getText().toString());
			Matcher matcher222 = pattern.matcher(txtPlayerName2.getText().toString());
			Matcher matcher333 = pattern.matcher(txtPlayerName3.getText().toString());
			Matcher matcher444 = pattern.matcher(txtPlayerName4.getText().toString());
			if(matcher111.find() || matcher222.find() ||  matcher333.find() || matcher444.find()) {
				JOptionPane.showMessageDialog(null, "Error: Separators are not allowed in names");
				isValid = false;
			}
			//check if the name has more than 20 characters
			else if(txtPlayerName1.getText().length() > 20 || txtPlayerName2.getText().length() > 20 || txtPlayerName3.getText().length() > 20
			|| txtPlayerName4.getText().length() > 20)
			{
				JOptionPane.showMessageDialog(null, "Error: Names are too long must be less than 20 letters");
				isValid = false;
			}
			//check the players have the same icon
			else if(txtPlayerName1.getText().equals("Enter Name") || txtPlayerName1.getText().isEmpty() || txtPlayerName2.getText().equals("Enter Name") || txtPlayerName2.getText().isEmpty() || txtPlayerName3.getText().equals("Enter Name") || txtPlayerName3.getText().isEmpty() || txtPlayerName4.getText().equals("Enter Name") || txtPlayerName4.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error: Enter Players Names");
				isValid = false;
			}
			//check if the players have the same name
			else if (txtPlayerName1.getText().equals(txtPlayerName2.getText()) ||
				txtPlayerName1.getText().equals(txtPlayerName3.getText()) || 
				txtPlayerName1.getText().equals(txtPlayerName4.getText()) ||
				txtPlayerName2.getText().equals(txtPlayerName3.getText()) ||
				txtPlayerName2.getText().equals(txtPlayerName4.getText()) ||
				txtPlayerName3.getText().equals(txtPlayerName4.getText())
				
					){
				JOptionPane.showMessageDialog(null, "Error: Players must have different names");
				isValid = false;	
					}
			//check if names contain only spaces
			else if (txtPlayerName1.getText().trim().isEmpty() ||
				txtPlayerName2.getText().trim().isEmpty() ||
				txtPlayerName3.getText().trim().isEmpty() ||
				txtPlayerName4.getText().trim().isEmpty())
				{
				JOptionPane.showMessageDialog(null, "Error: Names cannot contain only spaces");
				isValid = false;	
					}
			else {
				isValid = true;
			}
		break;	
		}
		return isValid;
	}
	
	public void UpdateBackgroundPreview(int index) {
		switch(index) {
		case 0:
			lblPreviewImage1.setVisible(true);
			lblPreviewImage2.setVisible(false);
			lblPreviewImage3.setVisible(false);
			lblPreviewImage4.setVisible(false);
			break;
		case 1:
			lblPreviewImage1.setVisible(false);
			lblPreviewImage2.setVisible(true);
			lblPreviewImage3.setVisible(false);
			lblPreviewImage4.setVisible(false);
			break;
		case 2:
			lblPreviewImage1.setVisible(false);
			lblPreviewImage2.setVisible(false);
			lblPreviewImage3.setVisible(true);
			lblPreviewImage4.setVisible(false);
			break;
		case 3:
			lblPreviewImage1.setVisible(false);
			lblPreviewImage2.setVisible(false);
			lblPreviewImage3.setVisible(false);
			lblPreviewImage4.setVisible(true);
			break;
			

	}
		
	}
}
