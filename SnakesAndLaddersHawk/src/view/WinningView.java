package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Game;
import model.Player;

import javax.swing.JLabel;

public class WinningView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinningView dialog = new WinningView(null,null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinningView(Player p, Game g , GameView gv,HomeView h) {
		super(gv, "Winning View", ModalityType.APPLICATION_MODAL);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(650, 400, 458, 313);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		try {
			
	         InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
	         Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(24f);
	         // Create a new font with size 24
	         Font newFont1 = barriecitoFont.deriveFont(20f);
	         Font newFont2 = barriecitoFont.deriveFont(15f);
		     JLabel winnerNameLabel = new JLabel("The Winner : "+p.getNickname());
		     winnerNameLabel.setBounds(89, 109, 248, 31);
		     winnerNameLabel.setFont(newFont1);
		        // Set the text color to FFBE40 (hex color code)
		     winnerNameLabel.setForeground(new Color(255, 190, 64));  // FFBE40 in RGB
			 contentPanel.add(winnerNameLabel);

		
		     JLabel durationLabel = new JLabel("Game duration: "+g.getGameDuration());
		     durationLabel.setBounds(89, 153, 239, 21);
		     durationLabel.setFont(newFont2);
		        // Set the text color to FFBE40 (hex color code)
		     durationLabel.setForeground(new Color(255, 190, 64));  // FFBE40 in RGB
		     contentPanel.add(durationLabel);
		}catch(FontFormatException | IOException e) {
            e.printStackTrace();
        }
		
		JButton homeBtn = new JButton("");
		homeBtn.setBounds(381, 10, 45, 35);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		homeBtn.setIcon(new ImageIcon(AdminLogIn.class.getResource("/hawk_images/smallehomeBtn.png")));
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
				 gv.dispose();	
				 new HomeView().setVisible(true);
				}
        	
        });
		contentPanel.add(homeBtn);

		
		JLabel winningLabel = new JLabel("");
		winningLabel.setBounds(162, 31, 91, 68);
		ImageIcon originalImageIcons = new ImageIcon(WinningView.class.getResource("/hawk_images/cup.png"));
	    Image originalImages = originalImageIcons.getImage();
	    Image scaledImages = originalImages.getScaledInstance(winningLabel.getWidth(), winningLabel.getHeight(),
	    Image.SCALE_SMOOTH);
	    ImageIcon scaledImageIcons = new ImageIcon(scaledImages);
	    winningLabel.setIcon(scaledImageIcons);
		contentPanel.add(winningLabel);
		
		JLabel vectorLabel = new JLabel("");
		vectorLabel.setBounds(74, 21, 288, 184);
		ImageIcon originalImageIconv = new ImageIcon(WinningView.class.getResource("/hawk_images/bolb.png"));
	    Image originalImagev = originalImageIconv.getImage();
	    Image scaledImagev = originalImagev.getScaledInstance(vectorLabel.getWidth(), vectorLabel.getHeight(),
	    Image.SCALE_SMOOTH);
	    ImageIcon scaledImageIconv= new ImageIcon(scaledImagev);
	    vectorLabel.setIcon(scaledImageIconv);
		contentPanel.add(vectorLabel);
		
		JLabel background = new JLabel("");
		background.setLocation(0, 0);
		background.setSize(444, 232);
		ImageIcon originalImageIconb = new ImageIcon(WinningView.class.getResource("/hawk_images/greenBackground.jpeg"));
	    Image originalImageb = originalImageIconb.getImage();
	    Image scaledImageb = originalImageb.getScaledInstance(background.getWidth(), background.getHeight(),
	    Image.SCALE_SMOOTH);
	    ImageIcon scaledImageIconb = new ImageIcon(scaledImageb);
	    background.setIcon(scaledImageIconb);
		contentPanel.add(background);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 444, 44);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			
			{
				JButton okButton = new JButton("");
				okButton.setBounds(335, 1, 91, 31);
				buttonPane.add(okButton);
				okButton.setOpaque(false);
				okButton.setContentAreaFilled(false);
				okButton.setBorderPainted(false);
				okButton.setIcon(new ImageIcon(SurpriseView.class.getResource("/hawk_images/startNGameBtn.png")));
				okButton.setHorizontalTextPosition(SwingConstants.CENTER);
				okButton.setBackground(Color.BLACK);
				okButton.setBorder(new LineBorder(Color.BLACK, 1, true));
				okButton.setForeground(Color.WHITE);
				okButton.setVisible(true);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						
						dispose();
						gv.dispose();
						h.setVisible(true);
						h.getContentPane().setVisible(false);
						h.setContentPane(new PlayerCustomizationView(h));
						h.getContentPane().setVisible(true);
						

					}
					
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setBounds(0, 0, 444, 45);
				// Load the image from a resource (adjust the path as needed)
				ImageIcon originalImageIcon = new ImageIcon(WinningView.class.getResource("/hawk_images/greenBackground.jpeg"));
				// Get the image from the ImageIcon
				Image originalImage = originalImageIcon.getImage();
				// Get the size of the label
				int labelWidth = lblNewLabel_1.getWidth();
				int labelHeight = lblNewLabel_1.getHeight();
				// Scale the image to fit the label while maintaining aspect ratio
				Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
				// Create a new ImageIcon with the scaled image
				ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
	        
				// Set the scaled image as the icon for the label
				lblNewLabel_1.setIcon(scaledImageIcon);
				buttonPane.add(lblNewLabel_1);
			}
			
		}
	}
}
