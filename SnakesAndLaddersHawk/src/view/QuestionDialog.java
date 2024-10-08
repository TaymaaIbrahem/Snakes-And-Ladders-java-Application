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
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Main;
import model.Question;

public class QuestionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private HashMap<Integer ,JRadioButton> answers;
	ButtonGroup group;
	private JLabel lblQuestion;

	final int x_start = 55;
	final int y_start =140;
	//final int width= 505;
	final int width= 700;
	final int hieght=29;
	private Dimension screenSize;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			QuestionDialog dialog = new QuestionDialog(1,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public QuestionDialog(int difficulty, GameView gv, Boolean[]answer) {
		super(gv, "Question", ModalityType.APPLICATION_MODAL);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		answers = new HashMap<Integer,JRadioButton>();
		Question question = Main.questionController.getQuestionByDifficultyLevel(difficulty);
		System.out.println(question.toString());
		 screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width/2)- 394, (screenSize.height /2)- 248, 788, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		

		try {
			 // Load the Barriecito font using InputStream
	        InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
	        Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(64f);
	        // Create a new font with size 24
	        Font newFont = barriecitoFont.deriveFont(18f);
	        // Set the new font for the button
		    
			
				JLabel qLabel = new JLabel(question.getQuestion());
				qLabel.setBounds(20, 38, 744, 30);
				qLabel.setForeground(Color.WHITE);
			     Font ques_font =barriecitoFont.deriveFont(20f);
			     qLabel.setFont(ques_font);

			     contentPanel.add(qLabel);
			

			for(int i = 0 ; i< question.getAnswers().size() ; i++) {
				
				JRadioButton answerRadioBtn = new JRadioButton(question.getAnswers().get(i).toString());
			    answers.put(i+1, answerRadioBtn);
			    answerRadioBtn.setBounds(x_start, y_start + (i*56), width, hieght);
			    contentPanel.add(answerRadioBtn);
			    answerRadioBtn.setForeground(Color.WHITE);
			    answerRadioBtn.setFont(newFont);
			    answerRadioBtn.setOpaque(false);
			    
			}
		    
		    group = new ButtonGroup();
		    for(JRadioButton j : answers.values()) {
		    	group.add(j);
		    }


		}catch(FontFormatException | IOException e) {
	        e.printStackTrace();
	    } 
		
		
		JLabel vectorLabel = new JLabel("");
		vectorLabel.setBounds(10, 78, 718, 307);
		ImageIcon originalImageIconv = new ImageIcon(QuestionDialog.class.getResource("/hawk_images/bolb.png"));
	    Image originalImagev = originalImageIconv.getImage();
	    Image scaledImagev = originalImagev.getScaledInstance(vectorLabel.getWidth(), vectorLabel.getHeight(),
	    Image.SCALE_SMOOTH);
	    ImageIcon scaledImageIconv= new ImageIcon(scaledImagev);
	    vectorLabel.setIcon(scaledImageIconv);
	    contentPanel.add(vectorLabel);

		JLabel background = new JLabel("");
		background.setLocation(0, 0);
		background.setSize(774, 396);
		ImageIcon originalImageIconb = new ImageIcon(QuestionDialog.class.getResource("/hawk_images/greenBackground.jpeg"));
	    Image originalImageb = originalImageIconb.getImage();
	    Image scaledImageb = originalImageb.getScaledInstance(background.getWidth(), background.getHeight(),
	    Image.SCALE_SMOOTH);
	    ImageIcon scaledImageIconb = new ImageIcon(scaledImageb);
	    background.setIcon(scaledImageIconb);
		contentPanel.add(background);
		

		
		{JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 395, 774, 65);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(null);
		
		{
			JButton okButton = new JButton("");
			okButton.setBounds(176, 0, 126, 51);
			okButton.setOpaque(false);
			okButton.setContentAreaFilled(false);
			okButton.setBorderPainted(false);
			okButton.setIcon(new ImageIcon(QuestionDialog.class.getResource("/hawk_images/submitBttn.png")));
			okButton.setHorizontalTextPosition(SwingConstants.CENTER);
			okButton.setBackground(Color.BLACK);
			okButton.setBorder(new LineBorder(Color.BLACK, 1, true));
			okButton.setForeground(Color.WHITE);
			okButton.setVisible(true);
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(group.getSelection()!= null)
	    			{
	    				//Do something later
	    				if(answers.get(question.getCorrectAnswerNum()).isSelected()) {
	    					answer[0] = true;
	    				}else {
	    					answer[0] = false;
	    				}
	    				dispose();
	    				gv.setEnabled(true);
	                    gv.requestFocus();
	    				
	    			}
					
				}
				
			});
			buttonPane.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
			
		}
		{
		JLabel supBackground = new JLabel("");
		supBackground.setBounds(0, 0, 774, 65);
		ImageIcon originalImageIconsb = new ImageIcon(QuestionDialog.class.getResource("/hawk_images/greenBackground.jpeg"));
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
