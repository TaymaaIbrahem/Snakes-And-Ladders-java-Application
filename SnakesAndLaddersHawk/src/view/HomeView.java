package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

public class HomeView extends JFrame {
	
	private JPanel contentPane;
	private JButton startBtn;
	private JButton gameHistoryBtn;
	private JButton gameQuestionBtn;
	private JButton tuturialBtn;
	//private JButton musicBtn;
	Container c = getContentPane();
	private Font barriecitoFont;
	private MusicButton musicBtn;
	private Dimension screenSize;

	  private Clip clip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView frame = new HomeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public HomeView() {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // Set the frame to full screen
	    //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    //this.setUndecorated(true);
		// Set the frame to full screen
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    this.setUndecorated(true);
	    
	    // Get the screen dimensions
	     screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    // Set the preferred size of the frame
	    this.setPreferredSize(screenSize);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1210, 870);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(screenSize);
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		
		 
        try {
        	
        // Load the Barriecito font using InputStream
        InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
        Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(64f);
        // Create a new font with size 24
        Font newFont = barriecitoFont.deriveFont(24f);
        // Set the new font for the button
        tuturialBtn = new JButton("");
        tuturialBtn.setFont(newFont);
        tuturialBtn.setOpaque(false);
        tuturialBtn.setContentAreaFilled(false);
        tuturialBtn.setBorderPainted(false);
        tuturialBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/tutorialBtn1.png")));
        tuturialBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        tuturialBtn.setBackground(Color.BLACK);
        tuturialBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        //btnNewButton.setFont(barriecitoFont);
        
        tuturialBtn.setForeground(Color.WHITE);
        tuturialBtn.addActionListener(new  openFrameListener());
        tuturialBtn.setVisible(true);
        tuturialBtn.setBounds(((screenSize.width/2)- (screenSize.width/10))-100, ((screenSize.height)/3)- 50, 233, 98);
        getContentPane().add(tuturialBtn);
        
        
        gameQuestionBtn = new JButton("");
        gameQuestionBtn.setFont(newFont);
        gameQuestionBtn.setOpaque(false);
        gameQuestionBtn.setContentAreaFilled(false);
        gameQuestionBtn.setBorderPainted(false);
        gameQuestionBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/gameQuestionBtn.png")));
        gameQuestionBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        gameQuestionBtn.setBackground(Color.BLACK);
        gameQuestionBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        //btnNewButton.setFont(barriecitoFont);
        
        gameQuestionBtn.setForeground(Color.WHITE);
        gameQuestionBtn.addActionListener(new  openFrameListener());
        gameQuestionBtn.setVisible(true);
        
        gameQuestionBtn.setBounds(((screenSize.width/2)- (screenSize.width/10))+200, ((screenSize.height)/3)- 50 , 224, 98);
        getContentPane().add(gameQuestionBtn);
        
        
        gameHistoryBtn = new JButton("");
        gameHistoryBtn.setFont(newFont);
        gameHistoryBtn.setOpaque(false);
        gameHistoryBtn.setContentAreaFilled(false);
        gameHistoryBtn.setBorderPainted(false);
        gameHistoryBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/gameHistoryBtn1.png")));
        gameHistoryBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        gameHistoryBtn.setBackground(Color.BLACK);
        gameHistoryBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        //btnNewButton.setFont(barriecitoFont);
        
        gameHistoryBtn.setForeground(Color.WHITE);
        gameHistoryBtn.addActionListener(new  openFrameListener());
        gameHistoryBtn.setVisible(true);
        gameHistoryBtn.setBounds(((screenSize.width/2)- (screenSize.width/10))-100, ((screenSize.height)/3)+100, 224, 98);
        getContentPane().add(gameHistoryBtn);
        
        
        startBtn = new JButton("");
        startBtn.setFont(newFont);
        startBtn.setOpaque(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setBorderPainted(false);
        startBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/startBtn.png")));
        startBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        startBtn.setBackground(Color.BLACK);
        startBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        //btnNewButton.setFont(barriecitoFont);
        
        startBtn.setForeground(Color.WHITE);
        startBtn.addActionListener(new  openFrameListener());
        startBtn.setVisible(true);
        startBtn.setBounds(((screenSize.width/2)- (screenSize.width/10))+200, ((screenSize.height)/3)+100, 224, 91);
        getContentPane().add(startBtn);
        
        

        musicBtn =new MusicButton("/hawk_images/volumeBtn1.png","/hawk_images/mute_btn.png");
        musicBtn.setFont(newFont);
        musicBtn.setOpaque(false);
        musicBtn.setContentAreaFilled(false);
        musicBtn.setBorderPainted(false);
        musicBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/mute_btn.png")));
        musicBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        musicBtn.setBackground(Color.BLACK);
        musicBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        //btnNewButton.setFont(barriecitoFont);
        
        musicBtn.setForeground(Color.WHITE);
       // musicBtn.addActionListener(new  openFrameListener());
        musicBtn.setVisible(true);
        musicBtn.setBounds((screenSize.width - 78) - 50 , 50 , 78, 75);
 
        getContentPane().add(musicBtn);
        
        JButton exit = new JButton();
        exit.setFont(newFont);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/exitbtnn.png")));
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.setBackground(Color.BLACK);
        exit.setBorder(new LineBorder(Color.BLACK, 1, true));
        //btnNewButton.setFont(barriecitoFont);
        
        exit.setForeground(Color.WHITE);
       // musicBtn.addActionListener(new  openFrameListener());
        exit.setVisible(true);
        exit.setBounds((screenSize.width - 78) - 150 , 50 , 78, 75);
        exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                System.exit(0);
				
			}
		});
        getContentPane().add(exit);
      

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopMusic();  // Stop and close the audio clip when the window is closing
                System.exit(0);  // Terminate the application
            }
        });
        
        JLabel titleLebel = new JLabel("Snakes & Ladders");
        titleLebel.setBounds(((screenSize.width/2)- (screenSize.width/10))-90, ((screenSize.height)/3)- 300, 575, 128);
        titleLebel.setFont(barriecitoFont);
        // Set the text color to FFBE40 (hex color code)
        titleLebel.setForeground(new Color(255, 190, 64));  // FFBE40 in RGB
        contentPane.add(titleLebel);
        
        
        }catch(FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        
        
       
        

		
		
     // Bolb Label
        JLabel bolbBackground = new JLabel();
        int xb = (screenSize.width / 2 ) - ( (screenSize.width / 2 )+(screenSize.width / 8 ))/2 ;
        int yb = ((screenSize.height- 1000)/2);
        int widthb = ( (screenSize.width / 2 )+(screenSize.width / 8 ));
        
        bolbBackground.setBounds(xb, yb,widthb , 1000);
        try {
            Image bolblImage = ImageIO.read(QuestionsView.class.getResource("/hawk_images/bolb.png"));
            int bolbWidth = bolbBackground.getWidth();
            int bolbHeight = bolbBackground.getHeight();
            Image scaledImagebolb = bolblImage.getScaledInstance(bolbWidth, bolbHeight, Image.SCALE_SMOOTH);
            ImageIcon bolbscaledImageIcon = new ImageIcon(scaledImagebolb);
            bolbBackground.setIcon(bolbscaledImageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getContentPane().add(bolbBackground);

		
		JLabel background = new JLabel("");
		background.setBounds(0, 0,screenSize.width, screenSize.height);
		// Load the image from a resource (adjust the path as needed)
        ImageIcon originalImageIcon = new ImageIcon(QuestionsView.class.getResource("/hawk_images/greenBackground.jpeg"));
        // Get the image from the ImageIcon
        Image originalImage = originalImageIcon.getImage();
        // Get the size of the label
        int labelWidth = background.getWidth();
        int labelHeight = background.getHeight();
        // Scale the image to fit the label while maintaining aspect ratio
        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
        // Create a new ImageIcon with the scaled image
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        
        // Set the scaled image as the icon for the label
        background.setIcon(scaledImageIcon);

        getContentPane().add(background);
        
       
        
        
       
        
        
	}
	
	public Container getC() {
		
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}
	
	public MusicButton getMusicBtn() {
		return musicBtn;
	}

	public void setMusicBtn(MusicButton musicBtn) {
		this.musicBtn = musicBtn;
	}




	    private void stopMusic() {
	        if (clip != null && clip.isRunning()) {
	            clip.stop();
	            clip.close();
	        }
	    }

	class openFrameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if( e.getSource() == gameQuestionBtn) {
				setC(getContentPane());
				getContentPane().setVisible(false);
				setContentPane(new AdminLogIn(HomeView.this));
				getContentPane().setVisible(true);
			}
			if(e.getSource() == tuturialBtn) {
				 setC(getContentPane());
				 getContentPane().setVisible(false);
				 tutrialTemplate tutorialView1 = new TutorialView1(HomeView.this);
				 tutorialView1.create(); // Call the create method
				 JInternalFrame internalFrame = tutorialView1.getInternalFrame();
				 setContentPane(internalFrame);
				 getContentPane().setVisible(true);
			}
			if(e.getSource() == startBtn) {
				setC(getContentPane());
				getContentPane().setVisible(false);
				setContentPane(new PlayerCustomizationView(HomeView.this));
				getContentPane().setVisible(true);
			}
			if(e.getSource() == gameHistoryBtn) {
				setC(getContentPane());
				getContentPane().setVisible(false);
				setContentPane(new HistoryView(HomeView.this));
				getContentPane().setVisible(true);
				 
				
			}
			if(e.getSource() == musicBtn) {
              //  toggleMusic();
                

				/**
				 * we have to stop music here
				 */
			}
			
			
			
		}
		
	}
}
