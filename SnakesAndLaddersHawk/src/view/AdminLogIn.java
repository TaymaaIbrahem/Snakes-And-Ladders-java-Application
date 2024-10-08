package view;

import java.awt.Color;
import java.awt.Container;
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

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import view.HomeView.openFrameListener;

import javax.swing.JButton;

public class AdminLogIn extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userNameTxtFild;
	private JTextField passwordTxtFild;
	private JButton signInBtn;
	Container c = getContentPane();
	private JLabel lblNewLabel;
	private JLabel userNamelabel;
	private JLabel passwordLabel;
	private MusicButton musicBtn;
	private Dimension screenSize;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogIn frame = new AdminLogIn(null);
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
	public AdminLogIn(HomeView h) {
		setBounds(100, 100, 1195, 827);
		
		getContentPane().setLayout(null);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();


		userNameTxtFild = new JTextField();
		userNameTxtFild.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+50, 195, 32);
		getContentPane().add(userNameTxtFild);
		userNameTxtFild.setColumns(10);
		
		passwordTxtFild = new JPasswordField();
		passwordTxtFild.setBounds(((screenSize.width/2)- (screenSize.width/10)-50)+250, ((screenSize.height)/3)+112, 195, 32);
		getContentPane().add(passwordTxtFild);
		passwordTxtFild.setColumns(10);
		
		try {
			
			// Load the Barriecito font using InputStream
	        InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
	        Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(64f);
	        // Create a new font with size 24
	        Font newFont = barriecitoFont.deriveFont(28f);
	        // Set the new font for the button
	        
		lblNewLabel = new JLabel("Admin Login");
		lblNewLabel.setBounds(((screenSize.width/2)- (screenSize.width/10)), ((screenSize.height)/3)- 300, 575, 128);
		lblNewLabel.setFont(barriecitoFont);
        // Set the text color to FFBE40 (hex color code)
		lblNewLabel.setForeground(new Color(255, 190, 64));  // FFBE40 in RGB
		getContentPane().add(lblNewLabel);
		
		
		userNamelabel = new JLabel("User Name");
		userNamelabel.setBounds(((screenSize.width/2)- (screenSize.width/10))-50, ((screenSize.height)/3)+50, 188, 32);
		userNamelabel.setFont(newFont);
        // Set the text color to FFBE40 (hex color code)
		userNamelabel.setForeground(new Color(255, 190, 64));
		getContentPane().add(userNamelabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(((screenSize.width/2)- (screenSize.width/10))-50,((screenSize.height)/3)+112, 175, 32);

		passwordLabel.setFont(newFont);
        // Set the text color to FFBE40 (hex color code)
		passwordLabel.setForeground(new Color(255, 190, 64));  // FFBE40 in RGB
		getContentPane().add(passwordLabel);
		
		
        
		signInBtn = new JButton("Sign In");
		signInBtn.setFont(newFont);
		signInBtn.setOpaque(false);
		signInBtn.setContentAreaFilled(false);
		signInBtn.setBorderPainted(false);
		signInBtn.setIcon(new ImageIcon(HomeView.class.getResource("/hawk_images/medbtn.png")));
		signInBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		signInBtn.setBackground(Color.BLACK);
		signInBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
		signInBtn.setBounds(((screenSize.width/2)- (screenSize.width/10))+40, 601, 188, 69);
		signInBtn.setForeground(Color.WHITE);
		signInBtn.setVisible(true);
        signInBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == signInBtn) {
					String u = userNameTxtFild.getText();
					String p = passwordTxtFild.getText();
					if(u!=null && u.equals("Admin") && p!=null && p.equals("Admin")) {
						c=getContentPane();
						getContentPane().setVisible(false);
						setContentPane(new QuestionsView(h));
						getContentPane().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "The user name or the password are incorrect");
						userNameTxtFild.setText("");
						passwordTxtFild.setText("");
					}
				}
				
			}
        	
        });
		getContentPane().add(signInBtn);
		
		
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
				dispose();
				
				h.setContentPane(h.c);
				h.getContentPane().setVisible(true);
			}
        	
        });
		getContentPane().add(homeBtn);
		}catch(FontFormatException | IOException e) {
            e.printStackTrace();
        }
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

		JLabel background = new JLabel("");
		background.setBounds(0, 0, screenSize.width, screenSize.height);
        ImageIcon originalImageIconb = new ImageIcon(AdminLogIn.class.getResource("/hawk_images/greenBackground.jpeg"));
        Image originalImageb = originalImageIconb.getImage();   
        Image scaledImageb = originalImageb.getScaledInstance( background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIconb= new ImageIcon(scaledImageb);        // Set the scaled image as the icon for the label
        background.setIcon(scaledImageIconb);
		getContentPane().add(background);
		SwingUtilities.invokeLater(() -> {
	        try {
	            this.setMaximum(true);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    });
		
		
		
	}
	
	
}
