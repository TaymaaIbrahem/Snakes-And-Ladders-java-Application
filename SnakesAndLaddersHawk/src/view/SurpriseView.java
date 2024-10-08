package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import view.HomeView.openFrameListener;

import javax.swing.JLabel;

public class SurpriseView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SurpriseView dialog = new SurpriseView(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SurpriseView(String message, GameView gv) {
		super(gv, "Winning View", ModalityType.APPLICATION_MODAL);
		setBounds(650, 400, 468, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		try {
	        // Load the Barriecito font using InputStream
	        InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
	        Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(24f);
	        // Create a new font with size 24
	        Font newFont = barriecitoFont.deriveFont(15f);
	        // Set the new font for the button
		    JLabel surpriseMsgLabel = new JLabel(message);
		    surpriseMsgLabel.setBounds(89, 150, 238, 31);
		    
		    surpriseMsgLabel.setFont(newFont);
	        // Set the text color to FFBE40 (hex color code)
		    surpriseMsgLabel.setForeground(new Color(255, 190, 64));  // FFBE40 in RGB
		    contentPanel.add(surpriseMsgLabel);
		    

			
		}catch(FontFormatException | IOException e) {
            e.printStackTrace();
        }
		
		
		JLabel surpriseLabel = new JLabel("");
		surpriseLabel.setBounds(162, 44, 91, 78);
		ImageIcon originalImageIcons = new ImageIcon(SurpriseView.class.getResource("/hawk_images/surprise.png"));
	    Image originalImages = originalImageIcons.getImage();
	    Image scaledImages = originalImages.getScaledInstance(surpriseLabel.getWidth(), surpriseLabel.getHeight(),
	    Image.SCALE_SMOOTH);
	    ImageIcon scaledImageIcons = new ImageIcon(scaledImages);
	    surpriseLabel.setIcon(scaledImageIcons);
		contentPanel.add(surpriseLabel);
		
		JLabel vectorLabel = new JLabel("");
		vectorLabel.setBounds(74, 21, 288, 184);
		ImageIcon originalImageIconv = new ImageIcon(SurpriseView.class.getResource("/hawk_images/bolb.png"));
	    Image originalImagev = originalImageIconv.getImage();
	    Image scaledImagev = originalImagev.getScaledInstance(vectorLabel.getWidth(), vectorLabel.getHeight(),
	    Image.SCALE_SMOOTH);
	    ImageIcon scaledImageIconv= new ImageIcon(scaledImagev);
	    vectorLabel.setIcon(scaledImageIconv);
		contentPanel.add(vectorLabel);
		
		JLabel background = new JLabel("");
		background.setLocation(0, 0);
		background.setSize(454, 232);
		ImageIcon originalImageIconb = new ImageIcon(SurpriseView.class.getResource("/hawk_images/greenBackground.jpeg"));
	    Image originalImageb = originalImageIconb.getImage();
	    Image scaledImageb = originalImageb.getScaledInstance(background.getWidth(), background.getHeight(),
	    Image.SCALE_SMOOTH);
	    ImageIcon scaledImageIconb = new ImageIcon(scaledImageb);
	    background.setIcon(scaledImageIconb);
		contentPanel.add(background);
		


		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 454, 50);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			
			{
				JButton okButton = new JButton("");
				okButton.setBounds(335, 1, 91, 31);
				okButton.setOpaque(false);
				okButton.setContentAreaFilled(false);
				okButton.setBorderPainted(false);
				okButton.setIcon(new ImageIcon(SurpriseView.class.getResource("/hawk_images/continueBtn.png")));
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
						
					}
					
				});
				buttonPane.add(okButton);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				
			}
			{
			JLabel supBackground = new JLabel("");
			supBackground.setBounds(0, 0, 454, 51);
			ImageIcon originalImageIconsb = new ImageIcon(SurpriseView.class.getResource("/hawk_images/greenBackground.jpeg"));
		    Image originalImagesb = originalImageIconsb.getImage();
			Image scaledImagesb = originalImagesb.getScaledInstance(supBackground.getWidth(), supBackground.getHeight(),
			Image.SCALE_SMOOTH);
		    ImageIcon scaledImageIconsb = new ImageIcon(scaledImagesb);
			supBackground.setIcon(scaledImageIconsb);
		    buttonPane.add(supBackground);
			
			}
		}
	}
}
