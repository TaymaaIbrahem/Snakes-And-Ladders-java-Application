package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.GameController;
import model.Game;
import model.Main;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTabbedPane;

public class HistoryView extends JInternalFrame {
	Font barriecito ;
	private JTable table;
	private MusicButton musicBtn;
	private Dimension screenSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryView frame = new HistoryView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public HistoryView(HomeView h) {
		 try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
        e.printStackTrace();
    }
		
		setBounds(100, 100, 1194, 823);
		SwingUtilities.invokeLater(() -> {
	        try {
	            this.setMaximum(true);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    });
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	    
        try {
            // Load the Barriecito font using InputStream
            InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
            Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(64f);
            // Create a new font with size 24
            Font newFont = barriecitoFont.deriveFont(24f);

            getContentPane().setLayout(null);
         // Create a renderer to customize the font
         DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
         
         renderer.setFont(newFont);


            
            JButton btnNewButton4 = new JButton("");
            btnNewButton4.addActionListener(new ActionListener() {

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				// TODO Auto-generated method stub
    				dispose();
					h.setContentPane(h.c);
					h.getContentPane().setVisible(true);
    			}
            	
            });
        
          
            btnNewButton4.setOpaque(false);
            btnNewButton4.setContentAreaFilled(false);
            btnNewButton4.setBorderPainted(false);
            btnNewButton4.setIcon(new ImageIcon(HistoryView.class.getResource("/hawk_images/home.png")));
            btnNewButton4.setHorizontalTextPosition(SwingConstants.CENTER);
            btnNewButton4.setBackground(Color.BLACK);
            btnNewButton4.setBorder(new LineBorder(Color.BLACK, 1, true));
            //btnNewButton.setFont(barriecitoFont);
            
            btnNewButton4.setForeground(Color.WHITE);

            btnNewButton4.setVisible(true);
            
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 175, 576, 366);
//            scrollPane.setBackground(new Color(70, 79, 63));
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            getContentPane().add(scrollPane);
            
            table = new JTable();
            table.setEnabled(false);
            
          
            table.setFont(barriecitoFont.deriveFont(Font.BOLD, 13f));
            table.setForeground(new Color(255, 255, 255));
            scrollPane.setViewportView(table);
  
            int alpha = (int) (0.03 * 255);
            Color backgroundColor = new Color(0xD9D9D9, true);
            backgroundColor = new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), alpha);
            // Set the background color of the column headers to be semi-transparent
            JTableHeader tableHeader = table.getTableHeader();
            tableHeader.setBackground(new Color(0xD9D9D9, true));
            tableHeader.setOpaque(false);
            
            table.setBackground(backgroundColor);
            table.setOpaque(false);
            
            
            table.setModel(new DefaultTableModel(
            	new Object[][] {},
            	new String[] {
            		"Date", "Winner", "Difficuly Level", "Game Duration"
            	}
            ));
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (Game game1 : Main.gameController.allGames.values()) {
            	System.out.println(game1);
                model.addRow(new Object[]{game1.getGameDate(),game1.getWinnerNickName(),game1.getGameDifficulty(),game1.getGameDuration()});
                
            }
            table.getColumnModel().getColumn(0).setPreferredWidth(95);
            table.getColumnModel().getColumn(1).setPreferredWidth(95);
            table.getColumnModel().getColumn(2).setPreferredWidth(95);
            table.getColumnModel().getColumn(3).setPreferredWidth(95);
            table.getColumnModel().getColumn(3).setMinWidth(28);
            table.setRowHeight(44);
            btnNewButton4.setBounds(screenSize.width - 100 , 50, 65, 65);
            getContentPane().add(btnNewButton4);
            // Create a JLabel with the Barriecito font
            JLabel label = new JLabel("Game History");
            label.setBounds(((screenSize.width/2)- (screenSize.width/10))-90, ((screenSize.height)/3)- 300, 575, 128);
            label.setFont(barriecitoFont);
            // Set the text color to FFBE40 (hex color code)
            label.setForeground(new Color(255, 190, 64));  // FFBE40 in RGB

            // Add the JLabel to the JFrame
            getContentPane().add(label);
            // Make the JFrame visible
            setVisible(true);
        } catch (FontFormatException | IOException e) {
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

        // Bolb Label
        int xb = (screenSize.width / 2 ) - ( (screenSize.width / 2 )+(screenSize.width / 8 ))/2 ;
        int yb = ((screenSize.height- 1000)/2);
        int widthb = ( (screenSize.width / 2 )+(screenSize.width / 8 ));
        JLabel bolbBackground = new JLabel();
        bolbBackground.setBounds(xb, yb,widthb , 1000);
        try {
            Image bolblImage = ImageIO.read(HistoryView.class.getResource("/hawk_images/bolb.png"));
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
		background.setBounds(0, 0, screenSize.width, screenSize.height);
		// Load the image from a resource (adjust the path as needed)
        ImageIcon originalImageIcon = new ImageIcon(HistoryView.class.getResource("/hawk_images/greenBackground.jpeg"));
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
}
