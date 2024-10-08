package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class test12 extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			test12 dialog = new test12();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public test12() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(0, 0, 436, 232);
			
			// Load the image from a resource (adjust the path as needed)
	        ImageIcon originalImageIcon = new ImageIcon(QuestionsView.class.getResource("/hawk_images/greenBackground.jpeg"));
	        // Get the image from the ImageIcon
	        Image originalImage = originalImageIcon.getImage();
	        // Get the size of the label
	        int labelWidth = lblNewLabel.getWidth();
	        int labelHeight = lblNewLabel.getHeight();
	        // Scale the image to fit the label while maintaining aspect ratio
	        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
	        // Create a new ImageIcon with the scaled image
	        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
	        
	        // Set the scaled image as the icon for the label
	        lblNewLabel.setIcon(scaledImageIcon);

			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 231, 436, 32);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);

	    
	        
			{
				JButton okButton = new JButton("OK");
				okButton.setBounds(308, 5, 45, 21);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(363, 5, 63, 21);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setBounds(0, 0, 436, 32);
				// Load the image from a resource (adjust the path as needed)
		        ImageIcon originalImageIcon = new ImageIcon(QuestionsView.class.getResource("/hawk_images/greenBackground.jpeg"));
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
