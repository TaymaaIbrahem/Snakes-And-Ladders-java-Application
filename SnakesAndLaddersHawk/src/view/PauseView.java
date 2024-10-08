package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.JLabel;

public class PauseView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension screenSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PauseView dialog = new PauseView(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PauseView( GameView gv) {
    super(gv, "Pause", ModalityType.APPLICATION_MODAL);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width/2)- 394, (screenSize.height /2)- 248, 788, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		try {
			InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
	         Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(24f);
	         // Create a new font with size 24
	         Font newFont1 = barriecitoFont.deriveFont(40f);
	         JLabel lblNewLabel = new JLabel("click to resume the game ");
	         lblNewLabel.setBounds(130, 288, 495, 62);
	         lblNewLabel.setFont(newFont1);
		        // Set the text color to FFBE40 (hex color code)
	         lblNewLabel.setForeground(new Color(255, 190, 64));  // FFBE40 in RGB
	         contentPanel.add(lblNewLabel);
		}catch(FontFormatException | IOException e) {
            e.printStackTrace();
        }

		JButton playBtn = new JButton("");
		playBtn.setBounds(281, 70, 159, 146);
		playBtn.setOpaque(false);
		playBtn.setContentAreaFilled(false);
		playBtn.setBorderPainted(false);
		playBtn.setIcon(new ImageIcon(PauseView.class.getResource("/hawk_images/playPauseBtn.png")));
		playBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		playBtn.setBackground(Color.BLACK);
		playBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
		playBtn.setForeground(Color.WHITE);
		playBtn.setVisible(true);
		playBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		contentPanel.add(playBtn);

		JLabel background = new JLabel("");
		background.setLocation(0, 0);
		background.setSize(774, 460);
		ImageIcon originalImageIconb = new ImageIcon(PauseView.class.getResource("/hawk_images/greenBackground.jpeg"));
	    Image originalImageb = originalImageIconb.getImage();
	    Image scaledImageb = originalImageb.getScaledInstance(background.getWidth(), background.getHeight(),
	    Image.SCALE_SMOOTH);
	    ImageIcon scaledImageIconb = new ImageIcon(scaledImageb);
	    background.setIcon(scaledImageIconb);
		contentPanel.add(background);
		
		

	}
}
