package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public abstract class tutrialTemplate {
	
	JButton exitBtn;
	private JButton nextBtn;
	JButton backButton;
	JLabel background ;
	Font newFont;
	private Dimension screenSize;

	
	
	//Abstract Methods
	public abstract JInternalFrame getInternalFrame() ;
	public abstract  String getBackgroundpath();
	public abstract void setBackButtonActionListener(JButton b) ;
	public abstract void setNextButtonActionListener(JButton b) ;
	public abstract HomeView getH() ;
	public abstract  void addComponents();


	//Template Method
	public final void create() {
         initializeComponents();
	     customizeExitButton(exitBtn,  newFont);
	     setExitButtonActionListener(exitBtn);
	     customizeNextButton( getNextBtn(),  newFont);
	     setNextButtonActionListener(getNextBtn());
	     customizeBackButton(backButton, newFont);
	     setBackButtonActionListener(backButton) ;
	     String s = getBackgroundpath();
	     customizeBackgroundLabel(background , s) ;
	     addComponents();
	}
	
	//Common Methods
	   public void initializeComponents() {
		   JInternalFrame internalFrame= getInternalFrame();
		   screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	        internalFrame.setBounds(0, 0, 1195, 827);
	        internalFrame.getContentPane().setLayout(null);

	        try {
	            InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
	            Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(64f);
	            newFont = barriecitoFont.deriveFont(24f);
	            backButton= new JButton("");
	            exitBtn = new JButton("Exit");
	            setNextBtn(new JButton(""));
	            background = new JLabel("");

	        } catch (IOException | FontFormatException e) {
	            e.printStackTrace();
	        }
	    }
	   
	   
	   
	    public void customizeExitButton(JButton exitButton, Font font) {
	        exitButton.setBounds(screenSize.width-150, 597, 120, 57);
	        exitButton.setOpaque(false);
	        exitButton.setContentAreaFilled(false);
	        exitButton.setBorderPainted(false);
	        exitButton.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/exiteBtn1.png")));
	        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
	        exitButton.setBackground(Color.BLACK);
	        exitButton.setBorder(new LineBorder(Color.BLACK, 1, true));
	        exitButton.setForeground(Color.WHITE);
	        exitButton.setFont(font);
	        exitButton.setVisible(true);
	    }

	    
	    public void customizeNextButton(JButton nextButton, Font font) {
	        nextButton.setBounds(screenSize.width-200, (screenSize.height/2)+320, 158, 57);
	        nextButton.setOpaque(false);
	        nextButton.setContentAreaFilled(false);
	        nextButton.setBorderPainted(false);
	        nextButton.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/nextBtn1.png")));
	        nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
	        nextButton.setBackground(Color.BLACK);
	        nextButton.setBorder(new LineBorder(Color.BLACK, 1, true));
	        nextButton.setForeground(Color.WHITE);
	      //  nextButton.setVisible(true);
	    }
	    
	    public void setExitButtonActionListener(JButton b) {
	    	b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == exitBtn) {
                    	HomeView h = getH();
                        getInternalFrame().dispose();
                        h.setContentPane(h.getC());
                        h.getContentPane().setVisible(true);
                    }
                }
            });
	    }
	    
	    
	    public void customizeBackButton(JButton backButton, Font font) {
	        backButton.setBounds((screenSize.width/3)-430,  (screenSize.height/2)+320, 152, 57);
	        backButton.setOpaque(false);
	        backButton.setContentAreaFilled(false);
	        backButton.setBorderPainted(false);
	        backButton.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/backBtn1.png")));
	        backButton.setHorizontalTextPosition(SwingConstants.CENTER);
	        backButton.setBackground(Color.BLACK);
	        backButton.setBorder(new LineBorder(Color.BLACK, 1, true));
	        backButton.setForeground(Color.WHITE);
	        backButton.setFont(font);
	   
	    }

	    
	    public void customizeBackgroundLabel(JLabel background, String imagePath) {
	        background.setBounds(0, 0, screenSize.width, screenSize.height);
	        ImageIcon originalImageIconb = new ImageIcon(HomeView.class.getResource(imagePath));
	        Image originalImageb = originalImageIconb.getImage();
	        Image scaledImageb = originalImageb.getScaledInstance(background.getWidth(), background.getHeight(),
	                Image.SCALE_SMOOTH);
	        ImageIcon scaledImageIconb = new ImageIcon(scaledImageb);
	        background.setIcon(scaledImageIconb);
	    }
		public JButton getNextBtn() {
			return nextBtn;
		}
		public void setNextBtn(JButton nextBtn) {
			this.nextBtn = nextBtn;
		}
		public void setNextButtonActionListener() {
			// TODO Auto-generated method stub
			
		}
		public JButton getExitBtn() {
			return exitBtn;
		}
		public void setExitBtn(JButton exitBtn) {
			this.exitBtn = exitBtn;
		}
		public JButton getBackButton() {
			return backButton;
		}
		public void setBackButton(JButton backButton) {
			this.backButton = backButton;
		}
		public JLabel getBackground() {
			return background;
		}
		public void setBackground(JLabel background) {
			this.background = background;
		}
		public Font getNewFont() {
			return newFont;
		}
		public void setNewFont(Font newFont) {
			this.newFont = newFont;
		}
		
	    

}
