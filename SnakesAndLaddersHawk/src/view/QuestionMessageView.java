package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Dialog.ModalityType;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.QuestionController;
import model.Main;
import model.Question;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class QuestionMessageView extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashMap<Integer,Question> allQuestions;
	private static HashMap<Integer, Question> allEasyQuestions;
	private static HashMap<Integer, Question> allMediumQuestions;
	private static HashMap<Integer, Question> allHardQuestions;
	
	private JLabel lblQuestion;
	private JRadioButton AnswerOneRadioBtn;
	private JRadioButton AnswerTwoRadioBtn;
	private JRadioButton AnswerThreeRadioBtn;
	private JRadioButton AnswerFourRadioBtn;
	private HashMap<Integer ,JRadioButton> answers;
	private String correctAnswerString;
	final int  x_start = 67;
	final int y_start =140;
	//final int width= 505;
	final int width= 700;
	final int hieght=29;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			QuestionMessageView dialog = new QuestionMessageView(1,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static HashMap<Integer, Question> getAllEasyQuestions() {
		if(allEasyQuestions==null) {
			allEasyQuestions =Main.questionController.getAllEasyQuestions();
		}
		return allEasyQuestions;
	}
	public void setAllEasyQuestions(HashMap<Integer, Question> allEasyQuestions) {
		this.allEasyQuestions = allEasyQuestions;
	}
	public HashMap<Integer, Question> getAllMediumQuestions() {
		if(allMediumQuestions==null) {
			allMediumQuestions =Main.questionController.getAllMediumQuestions();
		}
		
		return allMediumQuestions;
	}
	public void setAllMediumQuestions(HashMap<Integer, Question> allMediumQuestions) {
		this.allMediumQuestions = allMediumQuestions;
	}
	public HashMap<Integer, Question> getAllHardQuestions() {
		if(allHardQuestions==null) {
			allHardQuestions =Main.questionController.getAllHardQuestions();
		}
		
		return allHardQuestions;

	}
	public void setAllHardQuestions(HashMap<Integer, Question> allHardQuestions) {
		this.allHardQuestions = allHardQuestions;
	}
	
	
	
	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public QuestionMessageView(int difficulty, GameView gv, Boolean[]answer) {
		super(gv, "Question", ModalityType.APPLICATION_MODAL);
		
		answers = new HashMap<Integer,JRadioButton>();
		Question question = Main.questionController.getQuestionByDifficultyLevel(difficulty);
		System.out.println(question.toString());
		setBounds(650, 400, 788, 497);
		getContentPane().setLayout(null);
		JLabel background = new JLabel("");
		background.setBounds(10, 31, 756, 420);
		
		 try {	
	        // Load the Barriecito font using InputStream
	        InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");
	        Font barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(64f);
	        // Create a new font with size 24
	        Font newFont = barriecitoFont.deriveFont(18f);
	        // Set the new font for the button
	        
			for(int i = 0 ; i< question.getAnswers().size() ; i++) {
				
				JRadioButton answerRadioBtn = new JRadioButton(question.getAnswers().get(i).toString());
			    answers.put(i+1, answerRadioBtn);
			    answerRadioBtn.setBounds(x_start, y_start + (i*56), width, hieght);
			    getContentPane().add(answerRadioBtn);
			    answerRadioBtn.setForeground(Color.WHITE);
			    answerRadioBtn.setFont(newFont);
			    answerRadioBtn.setOpaque(false);
			    
			}
	        
			Random r = new Random();
			int correctAnswerNum;
	        int random;
	        
		
	     lblQuestion = new JLabel(question.getQuestion());
	     lblQuestion.setForeground(Color.WHITE);
	     Font ques_font =barriecitoFont.deriveFont(20f);
	     lblQuestion.setFont(ques_font);
	     lblQuestion.setBounds(32, 82, 734, 29);
	    getContentPane().add(lblQuestion);
	    
	   /* AnswerOneRadioBtn = new JRadioButton("Ragda");
	    answers.put(1, AnswerOneRadioBtn);
	    AnswerOneRadioBtn.setBounds(67, 140, 505, 29);
	    getContentPane().add(AnswerOneRadioBtn);
	    AnswerOneRadioBtn.setFont(newFont);
	    AnswerOneRadioBtn.setOpaque(false);
	    
	    AnswerTwoRadioBtn = new JRadioButton("Tayma");
	    answers.put(2, AnswerTwoRadioBtn);
	    AnswerTwoRadioBtn.setBounds(67, 196, 505, 29);
	    getContentPane().add(AnswerTwoRadioBtn);
	    AnswerTwoRadioBtn.setFont(newFont);
	    AnswerTwoRadioBtn.setOpaque(false);
	    
	    AnswerThreeRadioBtn = new JRadioButton("Abed");
	    answers.put(3, AnswerThreeRadioBtn);
	    AnswerThreeRadioBtn.setBounds(67, 253, 505, 29);
	    AnswerThreeRadioBtn.setFont(newFont);
	    AnswerThreeRadioBtn.setOpaque(false);
	    
	    getContentPane().add(AnswerThreeRadioBtn);
	    
	    AnswerFourRadioBtn = new JRadioButton("Zeky");
	    answers.put(4, AnswerFourRadioBtn);

	    AnswerFourRadioBtn.setBounds(67, 309, 505, 29);
	    AnswerFourRadioBtn.setFont(newFont);
	    AnswerFourRadioBtn.setOpaque(false);
	    getContentPane().add(AnswerFourRadioBtn);*/
	    
	    
	    
	    ButtonGroup group = new ButtonGroup();
	    /*group.add(AnswerOneRadioBtn);
	    group.add(AnswerTwoRadioBtn);
	    group.add(AnswerThreeRadioBtn);
	    group.add(AnswerFourRadioBtn);*/
	    for(JRadioButton j : answers.values()) {
	    	group.add(j);
	    }
	    
	    
		
		// Load the image from a resource (adjust the path as needed)
	    ImageIcon originalImageIcon = new ImageIcon(QuestionsView.class.getResource("/hawk_images/QuestionPopUpShape.png"));
	    // Get the image from the ImageIcon
	    Image originalImage = originalImageIcon.getImage();
	    // Get the size of the label
	    int labelWidth = background.getWidth();
	    int labelHeight = background.getHeight();
	    // Scale the image to fit the label while maintaining aspect ratio
	    Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_DEFAULT);
	    // Create a new ImageIcon with the scaled image
	    ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
	    {
	    	JButton SubmitBTN = new JButton("Submit");
	    	SubmitBTN.setForeground(Color.WHITE);
	    	SubmitBTN.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			
	    			if(group.getSelection()!= null)
	    			{
	    				//Do something later
	    				if(answers.get(question.getCorrectAnswerNum()).isSelected()) {
	    					answer[0] = true;
	    				}else {
	    					answer[0] = false;
	    				}
	    				dispose();
	    				
	    			}
	    			
	    		}
	    	});
	    	SubmitBTN.setBounds(240, 343, 214, 93);
	    	SubmitBTN.setFont(newFont);
	    	SubmitBTN.setOpaque(false);
	    	SubmitBTN.setContentAreaFilled(false);
	    	SubmitBTN.setBorderPainted(false);
	    	SubmitBTN.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
	    	SubmitBTN.setHorizontalTextPosition(SwingConstants.CENTER);
	    	SubmitBTN.setBackground(Color.BLACK);
	    	SubmitBTN.setBorder(new LineBorder(Color.BLACK, 1, true));
			//btnNewButton.setFont(barriecitoFont);

	    	SubmitBTN.setForeground(Color.WHITE);
	    	getContentPane().add(SubmitBTN);
	    }
	    
	    // Set the scaled image as the icon for the label
	    background.setIcon(scaledImageIcon);

	    getContentPane().add(background);
	    
	    //Problem Null Pointer
	    
	   /* 
	    
		switch(difficulty) {
		case 1:
			random = r.nextInt(20);
			Question easyQ = getAllMediumQuestions().get(random);
			lblQuestion.setText(easyQ.getQuestion());
			AnswerOneRadioBtn.setText(easyQ.getAnswers().get(0));
			AnswerOneRadioBtn.setText(easyQ.getAnswers().get(1));
			AnswerOneRadioBtn.setText(easyQ.getAnswers().get(2));
			AnswerOneRadioBtn.setText(easyQ.getAnswers().get(3));
			correctAnswerNum = easyQ.getCorrectAnswerNum();
			correctAnswerString = easyQ.getAnswers().get(correctAnswerNum);
			break;
		
		case 2:
			getAllMediumQuestions();
			random = r.nextInt(20);
			Question mediumQ = getAllMediumQuestions().get(random);
			lblQuestion.setText(mediumQ.getQuestion());
			AnswerOneRadioBtn.setText(mediumQ.getAnswers().get(0));
			AnswerOneRadioBtn.setText(mediumQ.getAnswers().get(1));
			AnswerOneRadioBtn.setText(mediumQ.getAnswers().get(2));
			AnswerOneRadioBtn.setText(mediumQ.getAnswers().get(3));
			correctAnswerNum = mediumQ.getCorrectAnswerNum();
			correctAnswerString = mediumQ.getAnswers().get(correctAnswerNum);
			break;
			
			
			
		case 3:
			getAllHardQuestions();
			random = r.nextInt(20);
			Question HardQ = getAllMediumQuestions().get(random);
			lblQuestion.setText(HardQ.getQuestion());
			AnswerOneRadioBtn.setText(HardQ.getAnswers().get(0));
			AnswerOneRadioBtn.setText(HardQ.getAnswers().get(1));
			AnswerOneRadioBtn.setText(HardQ.getAnswers().get(2));
			AnswerOneRadioBtn.setText(HardQ.getAnswers().get(3));
			correctAnswerNum = HardQ.getCorrectAnswerNum();
			correctAnswerString = HardQ.getAnswers().get(correctAnswerNum);
			break;
		
	}
	*/
	}catch(FontFormatException | IOException e) {
        e.printStackTrace();
    } 
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(0, 0, 776, 462);
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
			lblNewLabel_1.setIcon(scaledImageIcon);

		    getContentPane().add(lblNewLabel_1);
	    
	}
	public void UpdateLabesAndButtons() {
		AnswerOneRadioBtn.setVisible(true);
		AnswerTwoRadioBtn.setVisible(true);
		AnswerThreeRadioBtn.setVisible(true);
		AnswerFourRadioBtn.setVisible(true);
		
		
	}
	/*public Question getQuestionByDiff(int dif) {
		
		Question q = null;
		switch(dif) {
		case 1:
			break;
		}
	}*/
}
