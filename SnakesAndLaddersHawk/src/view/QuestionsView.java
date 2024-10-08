package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import model.Main;
import model.Question;

import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;

public class QuestionsView extends JInternalFrame {
	Font barriecito ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionsView frame = new QuestionsView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private Font barriecitoFont;
	private static JTable table;
	private static boolean editable = false;  // Variable to track edit mode
	private static HashMap<Integer,Question> allQuestions;
	private static HashMap<Integer, Question> allEasyQuestions;
	private static HashMap<Integer, Question> allMediumQuestions;
	private static HashMap<Integer, Question> allHardQuestions;
	private HashMap<Integer, TableCellEditor>editors = new HashMap<>();
	private HashMap<Integer, TableCellEditor> text_editors = new HashMap<>();
	private HashMap<Integer, Question> questions_rows;
	private static int level=0;
	private JButton plus_button;
	private JButton edit_button;
	private JButton save_button;
	private Dimension screenSize;

	


	private static JScrollPane scrollPane ;

	//private static Map<Integer, CustomComboBoxEditor> comboBoxEditors = new HashMap<>();

	public static HashMap<Integer, Question> getAllQuestions() {
		if(allQuestions==null) {
			allQuestions =Main.questionController.getAllEasyQuestions();
		}
		
		return allQuestions;

	}
	public void setAllQuestions(HashMap<Integer, Question> allQuestions) {
	
		this.allQuestions = allQuestions;
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
	
	
	public HashMap<Integer, Question> getQuestions_rows() {
		return questions_rows;
	}
	public void setQuestions_rows(HashMap<Integer, Question> questions_rows) {
		this.questions_rows = questions_rows;
	}
	
	public HashMap<Integer, TableCellEditor> getEditors() {
		return editors;
	}
	public void setEditors(HashMap<Integer, TableCellEditor> editors) {
		this.editors = editors;
	}
	public HashMap<Integer, TableCellEditor> getText_editors() {
		return text_editors;
	}
	public void setText_editors(HashMap<Integer, TableCellEditor> text_editors) {
		this.text_editors = text_editors;
	}
	
	public QuestionsView(HomeView h) {
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

			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setForeground(new Color(76, 165, 56));
			lblNewLabel.setFont(barriecitoFont.deriveFont(Font.BOLD, 16f));
			lblNewLabel.setBounds(((screenSize.width/2))-400, 158, 238, 28);
			getContentPane().add(lblNewLabel);



			JButton hard_button = new JButton("Hard");
			hard_button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					plus_button.setVisible(true);
					edit_button.setVisible(true);
					save_button.setVisible(true);
					level=3;
					lblNewLabel.setForeground(new Color(255, 92, 58));
					lblNewLabel.setText("Hard Questions");
					lblNewLabel.setFont(barriecitoFont.deriveFont(Font.BOLD, 16f));
					lblNewLabel.setBounds(((screenSize.width/2))-400, 158, 238, 28);
					allQuestions=getAllHardQuestions();
					editors = new HashMap<>();
					text_editors=new HashMap<>();
//					initializeTable(getAllEasyQuestions());
		
					//update_changes(table, questions_rows);
					scrollPane = replaceTable(null);  // Update scrollPane with the new one
					
					

					scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);
					
					//   scrollPane.setComponentZOrder(edit_button, rowHeight);r
					getContentPane().add(scrollPane);
					// Bring the scrollPane to the front
					setComponentZOrder(scrollPane, 0);
					revalidate();
					repaint();

				}
			});

			// Set the new font for the button
			hard_button.setFont(newFont);
			hard_button.setOpaque(false);
			hard_button.setContentAreaFilled(false);
			hard_button.setBorderPainted(false);
			hard_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
			hard_button.setHorizontalTextPosition(SwingConstants.CENTER);
			hard_button.setBackground(Color.BLACK);
			hard_button.setBorder(new LineBorder(Color.BLACK, 1, true));
			//btnNewButton.setFont(barriecitoFont);

			hard_button.setForeground(Color.WHITE);
			hard_button.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					//            		try {
					//               			File click= new File("clicked.wav");
					//               			btnNewButton.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
					//            				Clip c= AudioSystem.getClip();
					//            				c.open(AudioSystem.getAudioInputStream(click));
					//            				c.start();
					//            			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					//            				// TODO Auto-generated catch block
					//            				e1.printStackTrace();
					//            			}


				}
				@Override
				public void mouseEntered(MouseEvent e) {
					hard_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
				}



				@Override
				public void mouseExited(MouseEvent e) {
					hard_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
				}
			});
			hard_button.setVisible(true);
			hard_button.setBounds(22, 430, 175, 70);
			getContentPane().add(hard_button);


			JButton medium_button = new JButton("Medium");
			medium_button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					plus_button.setVisible(true);
					edit_button.setVisible(true);
					save_button.setVisible(true);
					level=2;
					lblNewLabel.setForeground(new Color(255, 190, 64));
					lblNewLabel.setText("Medium Questions");
					lblNewLabel.setFont(barriecitoFont.deriveFont(Font.BOLD, 16f));
					lblNewLabel.setBounds(((screenSize.width/2))-400, 158, 238, 28);
					allQuestions=getAllMediumQuestions();
					editors = new HashMap<>();
					text_editors=new HashMap<>();
//					initializeTable(getAllEasyQuestions());
		
					//update_changes(table, questions_rows);
					scrollPane = replaceTable(null);  // Update scrollPane with the new one
				

					scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);
					
					//   scrollPane.setComponentZOrder(edit_button, rowHeight);r
					getContentPane().add(scrollPane);
					// Bring the scrollPane to the front
					setComponentZOrder(scrollPane, 0);
					revalidate();
					repaint();

				}
			});

			// Set the new font for the button
			medium_button.setFont(newFont);
			medium_button.setOpaque(false);
			medium_button.setContentAreaFilled(false);
			medium_button.setBorderPainted(false);
			medium_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
			medium_button.setHorizontalTextPosition(SwingConstants.CENTER);
			medium_button.setBackground(Color.BLACK);
			medium_button.setBorder(new LineBorder(Color.BLACK, 1, true));
			//btnNewButton.setFont(barriecitoFont);

			medium_button.setForeground(Color.WHITE);
			medium_button.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					//            		try {
					//               			File click= new File("clicked.wav");
					//               			btnNewButton2.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
					//            				Clip c= AudioSystem.getClip();
					//            				c.open(AudioSystem.getAudioInputStream(click));
					//            				c.start();
					//            			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					//            				// TODO Auto-generated catch block
					//            				e1.printStackTrace();
					//            			}


				}
				@Override
				public void mouseEntered(MouseEvent e) {
					medium_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
				}



				@Override
				public void mouseExited(MouseEvent e) {
					medium_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
				}
			});
			medium_button.setVisible(true);
			medium_button.setBounds(22, 366, 175, 70);
			getContentPane().add(medium_button);

			JButton simple_button = new JButton("Easy");
			simple_button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					plus_button.setVisible(true);
					edit_button.setVisible(true);
					save_button.setVisible(true);
					level=1;
					lblNewLabel.setForeground(new Color(76, 165, 56));
					lblNewLabel.setText("Easy Questions");
					lblNewLabel.setFont(barriecitoFont.deriveFont(Font.BOLD, 16f));
					lblNewLabel.setBounds(((screenSize.width/2))-400, 158, 238, 28);
					allQuestions=getAllEasyQuestions();
					editors = new HashMap<>();
					text_editors=new HashMap<>();
//					initializeTable(getAllEasyQuestions());
		
					//update_changes(table, questions_rows);
					scrollPane = replaceTable(null);  // Update scrollPane with the new one
					
					

					scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);
					
					//   scrollPane.setComponentZOrder(edit_button, rowHeight);r
					getContentPane().add(scrollPane);
					// Bring the scrollPane to the front
					setComponentZOrder(scrollPane, 0);
					revalidate();
					repaint();

				}
			});

			// Set the new font for the button
			simple_button.setFont(newFont);
			simple_button.setOpaque(false);
			simple_button.setContentAreaFilled(false);
			simple_button.setBorderPainted(false);
			simple_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
			simple_button.setHorizontalTextPosition(SwingConstants.CENTER);
			simple_button.setBackground(Color.BLACK);
			simple_button.setBorder(new LineBorder(Color.BLACK, 1, true));
			//btnNewButton.setFont(barriecitoFont);

			simple_button.setForeground(Color.WHITE);
			simple_button.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					//            		try {
					//               			File click= new File("clicked.wav");
					//               			btnNewButton3.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
					//            				Clip c= AudioSystem.getClip();
					//            				c.open(AudioSystem.getAudioInputStream(click));
					//            				c.start();
					//            			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					//            				// TODO Auto-generated catch block
					//            				e1.printStackTrace();
					//            			}


				}
				@Override
				public void mouseEntered(MouseEvent e) {
					simple_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
				}



				@Override
				public void mouseExited(MouseEvent e) {
					simple_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/medbtn.png")));
				}
			});
			simple_button.setVisible(true);
			simple_button.setBounds(22, 305, 175, 70);
			getContentPane().add(simple_button);

			JButton home_button = new JButton("");
			home_button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					h.setContentPane(h.c);
					h.getContentPane().setVisible(true);
				}

			});


			home_button.setOpaque(false);
			home_button.setContentAreaFilled(false);
			home_button.setBorderPainted(false);
			home_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/home.png")));
			home_button.setHorizontalTextPosition(SwingConstants.CENTER);
			home_button.setBackground(Color.BLACK);
			home_button.setBorder(new LineBorder(Color.BLACK, 1, true));
			//btnNewButton.setFont(barriecitoFont);

			home_button.setForeground(Color.WHITE);
			home_button.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					//            		try {
					//               			File click= new File("clicked.wav");
					//               			btnNewButton4.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/home.png")));
					//            				Clip c= AudioSystem.getClip();
					//            				c.open(AudioSystem.getAudioInputStream(click));
					//            				c.start();
					//            			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					//            				// TODO Auto-generated catch block
					//            				e1.printStackTrace();
					//            			}


				}
				@Override
				public void mouseEntered(MouseEvent e) {
					home_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/home.png")));
				}



				@Override
				public void mouseExited(MouseEvent e) {
					home_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/home.png")));
				}
			});
			home_button.setVisible(true);
			home_button.setBounds(screenSize.width - 100 , 50, 65, 65);
			getContentPane().add(home_button);
			// Create a JLabel with the Barriecito font
			JLabel question_label = new JLabel("Questions");
			question_label.setBounds(((screenSize.width/2)- (screenSize.width/10))-90, ((screenSize.height)/3)- 300, 575, 128);
			question_label.setFont(barriecitoFont);
			// Set the text color to FFBE40 (hex color code)
			question_label.setForeground(new Color(255, 190, 64));  // FFBE40 in RGB

			// Add the JLabel to the JFrame
			getContentPane().add(question_label);
			// Make the JFrame visible
			setVisible(true);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		;

		// Create a JTable with the DefaultTableModel
	
		//JTable table = initializeTable(getAllQuestions());
		JTable table = initializeTable(null);
		
		
		// Set custom row height
		int rowHeight = 50;
		table.setRowHeight(rowHeight);
		// Set column widths
		setColumnWidths(table, new int[]{300, 300, 30});

		// Add the table to the frame or panel
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);
		scrollPane.setVisible(false);
		getContentPane().add(scrollPane);
		
		// Other components or configurations as needed
		 edit_button = new JButton("");



		edit_button.setOpaque(false);
		edit_button.setContentAreaFilled(false);
		edit_button.setBorderPainted(false);
		edit_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/pen.png")));
		edit_button.setHorizontalTextPosition(SwingConstants.CENTER);
		edit_button.setBackground(Color.BLACK);
		edit_button.setBorder(new LineBorder(Color.BLACK, 1, true));
		//btnNewButton.setFont(barriecitoFont);

		edit_button.setForeground(Color.WHITE);
		edit_button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				//                		try {
				//                   			File click= new File("clicked.wav");
				//                   			btnNewButton5.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/plus.png")));
				//                				Clip c= AudioSystem.getClip();
				//                				c.open(AudioSystem.getAudioInputStream(click));
				//                				c.start();
				//                			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
				//                				// TODO Auto-generated catch block
				//                				e1.printStackTrace();
				//                			}


			}
			@Override
			public void mouseEntered(MouseEvent e) {
				edit_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/pen.png")));
			}



			@Override
			public void mouseExited(MouseEvent e) {
				edit_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/pen.png")));
			}
		});
		edit_button.setBounds(((screenSize.width/2)- (screenSize.width/10))+300, 128, 67, 67);


		edit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editable = true;
				//update_changes(table, questions_rows);
				scrollPane = replaceTable(questions_rows);  // Update scrollPane with the new one
				
				
				scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);
				
				//   scrollPane.setComponentZOrder(edit_button, rowHeight);r
				scrollPane.setVisible(true);

				getContentPane().add(scrollPane);
				// Bring the scrollPane to the front
				setComponentZOrder(scrollPane, 0);
				revalidate();
				repaint();

			}
		});
		getContentPane().add(edit_button);
		 plus_button = new JButton("");
		plus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//update_changes(table, questions_rows);
				// Assuming you have the DefaultTableModel named 'model'
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				editable = false; // Set editability to false
				ArrayList<String> default_answers = new ArrayList<>();
				default_answers.add("answer1");
				default_answers.add("answer2");
				default_answers.add("answer3");
				default_answers.add("answer4");
				questions_rows.put(questions_rows.size(), new Question ("Default question",default_answers , 1 , level));

			//////////////////////////////////////////////////////////////////////////////
				   final int final_i = questions_rows.size()-1;
				   int i =questions_rows.size()-1;
					String[] items1 = questions_rows.get(i).getAnswers().toArray(new String[4]);
					
				JComboBox comboBox1 = new JComboBox(items1);
				  // Set client property to store the row information
			    comboBox1.setSelectedIndex(questions_rows.get(i).getCorrectAnswerNum() - 1);
			    addActionListenerToComboBox(comboBox1, final_i ,questions_rows);

//				comboBox1.addActionListener(new ActionListener() {
//				    @Override
//				    public void actionPerformed(ActionEvent e) {
//						System.out.println("The keyset in intialization in action performed"+rows_questions.keySet());
	//
//				        // Get the selected item and update the data accordingly
//				        int selectedValue = comboBox1.getSelectedIndex();
//				        System.out.println( rows_questions.keySet().toString());
//				        System.out.println("************************************ "+i+"final is" +final_i);
//				        System.out.println("selcted value updated to "+(selectedValue+1)+"for question in row "+ (final_i));
//				        // Update the data structure or model with the new selected value
//				        rows_questions.get(final_i).setCorrectAnswerNum(selectedValue+1);
//				    }
//				});
			 // Inside the loop where you create the editors
			    JTextField textField = new JTextField(questions_rows.get(i).getQuestion());

			    // Add a FocusListener to the JTextField
			    textField.addFocusListener(new FocusAdapter() {
			        @Override
			        public void focusLost(FocusEvent e) {
			            // Get the updated text from the JTextField and update the data accordingly
			            String updatedText = textField.getText();
			            questions_rows.get(final_i).setQuestion(updatedText);
			        }
			    });
			    DefaultCellEditor textEditor = new DefaultCellEditor(textField);
			    text_editors.put(i,textEditor);
				DefaultCellEditor dce1 = new DefaultCellEditor(comboBox1);
				editors.put(i,dce1);

				/////////////////////////////////////////////////////////////////////////////
			//	allQuestions.put(allQuestions.size()+1, new Question ("Default question",default_answers , 1 , 1));

				// Add a new row with default values (you can modify this based on your requirements)
				model.addRow(new Object[]{"Default Question", "Default Answer", createButton("Action 1", "/hawk_images/edit_ques.png"), createButton("Action 2", "/hawk_images/remove_ques.png")});
				scrollPane = replaceTable(questions_rows);  // Update scrollPane with the new one
				scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);
				scrollPane.setVisible(true);

				//   scrollPane.setComponentZOrder(edit_button, rowHeight);r
				getContentPane().add(scrollPane);
				// Bring the scrollPane to the front
				setComponentZOrder(scrollPane, 0);
				revalidate();
				repaint();
				
				
			//	update_changes(table, questions_rows);

			}
		});


		plus_button.setOpaque(false);
		plus_button.setContentAreaFilled(false);
		plus_button.setBorderPainted(false);
		plus_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/plus.png")));
		plus_button.setHorizontalTextPosition(SwingConstants.CENTER);
		plus_button.setBackground(Color.BLACK);
		plus_button.setBorder(new LineBorder(Color.BLACK, 1, true));
		//btnNewButton.setFont(barriecitoFont);

		plus_button.setForeground(Color.WHITE);
		plus_button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				//            		try {
				//               			File click= new File("clicked.wav");
				//               			btnNewButton5.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/plus.png")));
				//            				Clip c= AudioSystem.getClip();
				//            				c.open(AudioSystem.getAudioInputStream(click));
				//            				c.start();
				//            			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
				//            				// TODO Auto-generated catch block
				//            				e1.printStackTrace();
				//            			}


			}
			@Override
			public void mouseEntered(MouseEvent e) {
				plus_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/plus.png")));
			}



			@Override
			public void mouseExited(MouseEvent e) {
				plus_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/plus.png")));
			}
		});
		plus_button.setVisible(true);
		plus_button.setBounds(((screenSize.width/2)- (screenSize.width/10))+360, 125, 70, 70);
		getContentPane().add(plus_button);

		 save_button = new JButton("");
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editable = false; // Set editability to false
				  // Show a simple information dialog

		        // Show a confirmation dialog with YES/NO options
		        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to save changes?", "Confirmation", JOptionPane.YES_NO_OPTION);
		        
		        // Process the user's choice
		        if (result == JOptionPane.YES_OPTION) {
		        	if(update_changes(table, questions_rows)) {
		        

					scrollPane = replaceTable(questions_rows);  // Update scrollPane with the new one
					scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);
					//update_changes(table, questions_rows);
					scrollPane.setVisible(true);

					//   scrollPane.setComponentZOrder(edit_button, rowHeight);r
					getContentPane().add(scrollPane);
					// Bring the scrollPane to the front
					setComponentZOrder(scrollPane, 0);
					revalidate();
					repaint();
		        	}
		        	else {
		        		//JOptionPane.showMessageDialog(null, "Can't save questions , make sure the qustion isn't deafult or empty", "Confirmation", JOptionPane.WARNING_MESSAGE);

		        	}
		        
		        
		        }
				
			}
		});


		save_button.setOpaque(false);
		save_button.setContentAreaFilled(false);
		save_button.setBorderPainted(false);
		save_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/save.png")));
		save_button.setHorizontalTextPosition(SwingConstants.CENTER);
		save_button.setBackground(Color.BLACK);
		save_button.setBorder(new LineBorder(Color.BLACK, 1, true));
		//btnNewButton.setFont(barriecitoFont);

		save_button.setForeground(Color.WHITE);
		save_button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				//                		try {
				//                   			File click= new File("clicked.wav");
				//                   			btnNewButton5.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/plus.png")));
				//                				Clip c= AudioSystem.getClip();
				//                				c.open(AudioSystem.getAudioInputStream(click));
				//                				c.start();
				//                			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
				//                				// TODO Auto-generated catch block
				//                				e1.printStackTrace();
				//                			}


			}
			@Override
			public void mouseEntered(MouseEvent e) {
				save_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/save.png")));
			}



			@Override
			public void mouseExited(MouseEvent e) {
				save_button.setIcon(new ImageIcon(QuestionsView.class.getResource("/hawk_images/save.png")));
			}
		});
		save_button.setVisible(true);

		save_button.setBounds(((screenSize.width/2)- (screenSize.width/10))+240, 125, 70, 70);
		plus_button.setVisible(false);
		edit_button.setVisible(false);
		save_button.setVisible(false);
		getContentPane().add(save_button);
		
		JLabel lblNewLabel = new JLabel("Choose questions difficulity");
		lblNewLabel.setFont(new Font("Barriecito", Font.PLAIN, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(13, 267, 217, 41);
		getContentPane().add(lblNewLabel);
		
		
		 MusicButton  musicBtn =new MusicButton("/hawk_images/volumeBtn1.png","/hawk_images/mute_btn.png");

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
		background.setBounds(0, 0, screenSize.width, screenSize.height);
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

	// Method to set column widths
	private void setColumnWidths(JTable table, int[] widths) {
		for (int i = 0; i < widths.length; i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(widths[i]);
		}
	}

	public class ButtonColumnRenderer extends DefaultTableCellRenderer {
		private Icon icon;

		public ButtonColumnRenderer(String iconName) {
			setOpaque(true);
			this.setEnabled(editable);

			this.icon = new ImageIcon(getClass().getResource(iconName));
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			if (value instanceof JButton) {
				JButton button = (JButton) value;
				button.setIcon(icon);
				button.setEnabled(editable);
				button.setText("");  // Clear the text to only display the icon
				return button;
			}
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}

	// Custom cell editor for JButton columns
	 class ButtonColumnEditor extends AbstractCellEditor implements TableCellEditor {
		private JButton button;
		private String icon;
		public ButtonColumnEditor(JButton button, String actionCommand ,String iconName,JTable table, DefaultTableModel model ) {
			this.button = button;
			icon=iconName;
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setIcon(new ImageIcon(QuestionsView.class.getResource(iconName)));
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			button.setBackground(Color.BLACK);
			button.setBorder(new LineBorder(Color.BLACK, 1, true));
			//btnNewButton.setFont(barriecitoFont);
			button.setForeground(Color.WHITE);
			button.setActionCommand(actionCommand);
			button.setEnabled(editable);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					boolean deleted=false;
					  // Show a confirmation dialog with YES/NO options
			        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete it?", "Confirmation", JOptionPane.YES_NO_OPTION);
					System.out.println("the selcted row number"+table.getSelectedRow());

			        // Process the user's choice
			        if (result == JOptionPane.YES_OPTION) {
					int selectedRow2 = table.getSelectedRow();
				
					  if(level==1) {
						  	if(!getAllEasyQuestions().containsKey(questions_rows.get(table.getSelectedRow()).getQuestionNumber())) {
						  		if (selectedRow2 != -1 && selectedRow2 < table.getRowCount()) {

									if (table.isEditing()) {
										table.getCellEditor().stopCellEditing();
									}

									questions_rows.remove(selectedRow2);
									HashMap<Integer, Question> after_deleting= new HashMap<>();
									for(Integer i :questions_rows.keySet() ) {
										if(questions_rows.get(i)!=null) {
										if(i>selectedRow2) {
											after_deleting.put(i-1, questions_rows.get(i));
											editors.put(i-1, editors.get(i));
											text_editors.put(i-1, text_editors.get(i));
											
										}
										else {
											after_deleting.put(i, questions_rows.get(i));
											editors.put(i, editors.get(i));
											text_editors.put(i, text_editors.get(i));



										}
										
										}
									}
									questions_rows=after_deleting;						
									model.removeRow(selectedRow2);
								
									//update_changes(table, questions_rows);

									scrollPane=replaceTable(questions_rows);
									scrollPane.setVisible(true);
									scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);

									getContentPane().add(scrollPane);
									setComponentZOrder(scrollPane, 0);
									revalidate();
									repaint();
									//update_changes(table, questions_rows);
								
								}
						  		JOptionPane.showMessageDialog(null, "Question deleted successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
						  		deleted=true;
						  	}

			    	    }
			    	    else if(level==2) {
			    	    	
			    	      	if(!getAllMediumQuestions().containsKey(questions_rows.get(table.getSelectedRow()).getQuestionNumber())) {
			    	      	System.out.println("the ques is" +questions_rows.get(table.getSelectedRow()).getQuestionNumber());
			    	      	System.out.println("med quess are "+getAllMediumQuestions());
			    	      	System.out.println(!getAllMediumQuestions().containsKey(questions_rows.get(table.getSelectedRow()).getQuestionNumber()));
			    	      		if (selectedRow2 != -1 && selectedRow2 < table.getRowCount()) {

									if (table.isEditing()) {
										table.getCellEditor().stopCellEditing();
									}

									questions_rows.remove(selectedRow2);
									HashMap<Integer, Question> after_deleting= new HashMap<>();
									for(Integer i :questions_rows.keySet() ) {
										if(questions_rows.get(i)!=null) {
										if(i>selectedRow2) {
											after_deleting.put(i-1, questions_rows.get(i));
											editors.put(i-1, editors.get(i));
											text_editors.put(i-1, text_editors.get(i));
											
										}
										else {
											after_deleting.put(i, questions_rows.get(i));
											editors.put(i, editors.get(i));
											text_editors.put(i, text_editors.get(i));



										}
										
										}
									}
									questions_rows=after_deleting;						
									model.removeRow(selectedRow2);
								
									//update_changes(table, questions_rows);

									scrollPane=replaceTable(questions_rows);
									scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);

									scrollPane.setVisible(true);
									scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);

									getContentPane().add(scrollPane);
								

									setComponentZOrder(scrollPane, 0);
									revalidate();
									repaint();
									//update_changes(table, questions_rows);
								
								}
			    	      		System.out.println("deleted from surfCE");
						  		JOptionPane.showMessageDialog(null, "Question deleted successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
						  		deleted=true;
						  	}
			    	    }
			    	    else if(level==3) {
			    	    	
			    	    	if(!getAllHardQuestions().containsKey(questions_rows.get(table.getSelectedRow()).getQuestionNumber())) {
			    	    		if (selectedRow2 != -1 && selectedRow2 < table.getRowCount()) {

									if (table.isEditing()) {
										table.getCellEditor().stopCellEditing();
									}

									questions_rows.remove(selectedRow2);
									HashMap<Integer, Question> after_deleting= new HashMap<>();
									for(Integer i :questions_rows.keySet() ) {
										if(questions_rows.get(i)!=null) {
										if(i>selectedRow2) {
											after_deleting.put(i-1, questions_rows.get(i));
											editors.put(i-1, editors.get(i));
											text_editors.put(i-1, text_editors.get(i));
											
										}
										else {
											after_deleting.put(i, questions_rows.get(i));
											editors.put(i, editors.get(i));
											text_editors.put(i, text_editors.get(i));



										}
										
										}
									}
									questions_rows=after_deleting;						
									model.removeRow(selectedRow2);
								
									//update_changes(table, questions_rows);

									scrollPane=replaceTable(questions_rows);
									scrollPane.setVisible(true);
									scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);

									getContentPane().add(scrollPane);
									
									setComponentZOrder(scrollPane, 0);
									revalidate();
									repaint();
									//update_changes(table, questions_rows);
								
								}
						  		JOptionPane.showMessageDialog(null, "Question deleted successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
						  		deleted=true;
						  	}
			    	    }
			
					  if(deleted==false) {
					if(Main.questionController.removeQuestion(questions_rows.get(selectedRow2))) {

					if (selectedRow2 != -1 && selectedRow2 < table.getRowCount()) {

						if (table.isEditing()) {
							table.getCellEditor().stopCellEditing();
						}

						questions_rows.remove(selectedRow2);
						HashMap<Integer, Question> after_deleting= new HashMap<>();
						for(Integer i :questions_rows.keySet() ) {
							if(questions_rows.get(i)!=null) {
							if(i>selectedRow2) {
								after_deleting.put(i-1, questions_rows.get(i));
								editors.put(i-1, editors.get(i));
								text_editors.put(i-1, text_editors.get(i));
								
							}
							else {
								after_deleting.put(i, questions_rows.get(i));
								editors.put(i, editors.get(i));
								text_editors.put(i, text_editors.get(i));



							}
							
							}
						}
						questions_rows=after_deleting;						
						model.removeRow(selectedRow2);
					
						//update_changes(table, questions_rows);

						scrollPane=replaceTable(questions_rows);
						scrollPane.setVisible(true);
						scrollPane.setBounds(((screenSize.width/2)- (screenSize.width/10))-150, 300, 576, 366);

						getContentPane().add(scrollPane);
						setComponentZOrder(scrollPane, 0);
						revalidate();
						repaint();
						//update_changes(table, questions_rows);
					
					}
					JOptionPane.showMessageDialog(null, "Question deleted successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

			        }
					else {
						JOptionPane.showMessageDialog(null, "Can't delete question", "Confirmation", JOptionPane.WARNING_MESSAGE);

					}
					  }
			        }
			}});
		}

		@Override
		public Object getCellEditorValue() {
			// Update the button icon based on editability status
			//           // Access the selected row using the reference to the JTable

			return button;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column) {



			return button;
		}

	}
	// Create a custom cell renderer for the first column to make it uneditable
	class UneditableCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			// Set the cell value and make it uneditable
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			setEnabled(false);
			return this;
		}
	}


	// Create a JButton with the specified action command
	private JButton createButton(String actionCommand , String iconName) {
		JButton button = new JButton();
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setIcon(new ImageIcon(QuestionsView.class.getResource(iconName)));
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setBackground(Color.BLACK);
		button.setBorder(new LineBorder(Color.BLACK, 1, true));
		//btnNewButton.setFont(barriecitoFont);

		button.setForeground(Color.WHITE);

		button.setActionCommand(actionCommand);
		return button;
	}
	// Method to set the editability of components in the JTable
	private void setComponentsEditable(JTable table, boolean editable) {
		for (int row = 0; row < table.getRowCount(); row++) {
			for (int col = 0; col < table.getColumnCount(); col++) {
				Object value = table.getValueAt(row, col);

				if (value instanceof JComboBox) {
					// ((Component) value).setEnabled(editable);
					//((Component) value).setEditable(editable);
					//                   System.out.println("i reached unable combooo");
					//((DefaultCellEditor) table.getCellEditor(row, col)).setClickCountToStart(editable ? 2 : -1);
					//	repaint();
				} else if (value instanceof JButton) {
					((Component) value).setEnabled(editable);

				}
				// Handle other components if needed

				// Make the first column uneditable
				if (col == 0) {
					table.getColumnModel().getColumn(col).setCellEditor(null);
				}
			}
		}
	}

	// Method to toggle editability
	private void toggleEditability() {
		editable = !editable;
		setComponentsEditable(table, editable);
	}
	
	private JTable initializeTable(HashMap<Integer, Question> questions) {
	
		if(questions != null) {
			questions_rows =questions;
		}
		else {
			questions_rows= new HashMap<>();
			int counter=0;
			for(Question q : getAllQuestions().values()) {
				questions_rows.put(counter, q);
				counter++;
			}
		}
		Object[][] allRowData = new Object[questions_rows.size()][questions_rows.size()] ;

		// Create the editors to be used for each row
		int counter = 0; // Counter for rows
		for (Integer i : questions_rows.keySet()) {
		    final int final_i = i;
			String[] items1 = questions_rows.get(i).getAnswers().toArray(new String[4]);
			
			JComboBox comboBox1 = new JComboBox(items1);
			  // Set client property to store the row information
		    comboBox1.putClientProperty("row", i);
		    comboBox1.setSelectedIndex(questions_rows.get(i).getCorrectAnswerNum() - 1);
		    comboBox1.setEditable(editable);
		    addActionListenerToComboBox(comboBox1, final_i ,questions_rows);
		 // Variable to store the previous selection
	        
		    addItemListenerCombobox(comboBox1, final_i ,questions_rows);


		 // Inside the loop where you create the editors
		    JTextField textField = new JTextField(questions_rows.get(i).getQuestion());

		    // Add a FocusListener to the JTextField
		    textField.addFocusListener(new FocusAdapter() {
		        @Override
		        public void focusLost(FocusEvent e) {
		            // Get the updated text from the JTextField and update the data accordingly
		            String updatedText = textField.getText();
		            questions_rows.get(final_i).setQuestion(updatedText);
		        }
		    });

		    DefaultCellEditor textEditor = new DefaultCellEditor(textField);
		    text_editors.put(final_i,textEditor);
		    
			DefaultCellEditor dce1 = new DefaultCellEditor(comboBox1);
			editors.put(final_i,dce1);

			Object[] rowData = {
					textField.getText().toString(),
					
					items1[questions_rows.get(i).getCorrectAnswerNum() - 1],
					createButton("Action 2", "/hawk_images/remove_ques.png")
			};

			allRowData[counter++] = rowData; // Use counter as the index
		}

		// Column names
		String[] columnNames = {"Questions", "Answers", ""};
		DefaultTableModel model = new DefaultTableModel(allRowData, columnNames);
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					// Make the first column uneditable if isEditEnabled is false
					return editable;
				}
				if (column == 1) {
					return editable;
				}
				return true; // Other columns are always editable
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case 0:
					return JTextField.class;
				case 1:
					return JComboBox.class;
				case 2:
					return JButton.class;
				default:
					return Object.class;
				}
			}

			//  Determine editor to be used by row
			public TableCellEditor getCellEditor(int row, int column)
			{
				int modelColumn = convertColumnIndexToModel( column );

				if (modelColumn == 1 && row >=0)
					return editors.get(row);
				else if(modelColumn==0 && row>=0) {
					return text_editors.get(row);
				}
				else
					return super.getCellEditor(row, column);
			}
//			
//		//  Determine editor to be used by row
//					public void setCellEditor(int row, int column , TableCellEditor ed)
//					{
//						int modelColumn = convertColumnIndexToModel( column );
//
//						if (modelColumn == 1 && row >=0)
//							editors.set(row, ed);
//							
//						else if(modelColumn==0 && row>=0) {
//							text_editors.set(row, ed);
//						}
//						else
//							super.setCellEditor(ed);
//					}
		};
        InputStream inputStream = getClass().getResourceAsStream("Barriecito-Regular.ttf");

	       Font barriecitoFont;
		try {
			barriecitoFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(64f);
			   // Create a new font with size 24
	           Font newFont = barriecitoFont.deriveFont(24f);
			  table.setFont(barriecitoFont.deriveFont(Font.BOLD, 13f));
	          table.setForeground(new Color(255, 255, 255));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
         // scrollPane.setViewportView(table);

          int alpha = (int) (0.03 * 255);
          Color backgroundColor = new Color(0xD9D9D9, true);
          backgroundColor = new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), alpha);
          // Set the background color of the column headers to be semi-transparent
          JTableHeader tableHeader = table.getTableHeader();
          tableHeader.setBackground(new Color(0xD9D9D9, true));
          tableHeader.setOpaque(false);
          
          table.setBackground(backgroundColor);
          table.setOpaque(false);

	//	table.setBackground(Color.WHITE);
		table.getColumnModel().getColumn(2).setCellRenderer(new ButtonColumnRenderer("/hawk_images/remove_ques.png"));
		table.getColumnModel().getColumn(2).setCellEditor(new ButtonColumnEditor(new JButton(), "Action 2", "/hawk_images/remove_ques.png", table, model));



		return table;
	}

	// Assuming this method is inside your JFrame class
	private JScrollPane replaceTable(HashMap<Integer, Question> questions) {
		
		JTable newTable = initializeTable(questions);
		// Set custom row height
		int rowHeight = 50;
		newTable.setRowHeight(rowHeight);
		// Set column widths
		setColumnWidths(newTable, new int[]{300, 300, 30});

		JScrollPane newScrollPane = new JScrollPane(newTable);

        JViewport viewport = scrollPane.getViewport();
        viewport.removeAll();

        // Revalidate and repaint to reflect the changes
        viewport.revalidate();
        viewport.repaint();
	    // Remove the old JScrollPane from the frame
	    getContentPane().remove(scrollPane);

	

		// Revalidate and repaint to reflect the changes

		revalidate();
		repaint();
		// Update the reference to scrollPane
				scrollPane = newScrollPane;
				  scrollPane.setOpaque(false);
          scrollPane.getViewport().setOpaque(false);
      	// Add the newScrollPane to the frame
  		getContentPane().add(newScrollPane);
  	  JViewport viewport2 = scrollPane.getViewport();


      // Revalidate and repaint to reflect the changes
      viewport2.revalidate();
      viewport2.repaint();
		revalidate();
		repaint();
		return scrollPane;
	}
	
	// Outside the loop, define the method to add ActionListener
	private void addActionListenerToComboBox(JComboBox comboBox, int rowIndex, HashMap<Integer, Question> rows_questions) {
	    comboBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	            // Get the selected item and update the data accordingly
	            int selectedValue = comboBox.getSelectedIndex();
	      
	            // Update the data structure or model with the new selected value
	           // rows_questions.get(rowIndex).setCorrectAnswerNum(selectedValue + 1);
	        }
	    });
	}
	
	private void addItemListenerCombobox(JComboBox comboBox1, int rowIndex, HashMap<Integer, Question> rows_questions) {

	comboBox1.addItemListener(new ItemListener() {
    	int previousIndex = comboBox1.getSelectedIndex();
		@Override
		public void itemStateChanged(ItemEvent e) {
			 if (e.getStateChange() == ItemEvent.SELECTED) {
				 int currentIndex = comboBox1.getSelectedIndex();
                    // Use the previousIndex variable as needed
                   
                    if(currentIndex==-1) {
                    	String value= (String) comboBox1.getSelectedItem();
                        comboBox1.removeItemAt(previousIndex);
                    	comboBox1.insertItemAt(value, previousIndex);
                    	
                    }
                    // Update the previousIndex variable for the next event
                    previousIndex = currentIndex;

                 
                }
			
		}
	});
	}
	private boolean update_changes(JTable table, HashMap<Integer, Question> rows_questions) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    
	    

	    for (int row = 0; row < model.getRowCount(); row++) {
	    	 if (rows_questions == null || rows_questions.isEmpty()) {
	    	        return true;
	    	    }
//	        int questionId = rows_questions.get(row+1).getQuestionNumber();
//	        String questionText = (String) model.getValueAt(row, 0);
	        
	        // Retrieve the editor for the cell in column 1
	        TableCellEditor text_editor = table.getCellEditor(row, 0);
	        // Check if the editor is a JComboBox editor
	        if (text_editor instanceof DefaultCellEditor) {
	            Component editorComponent = ((DefaultCellEditor) text_editor).getComponent();

	            // Check if the component is a JComboBox
	            if (editorComponent instanceof JTextField) {
	            	JTextField text = (JTextField) editorComponent;
	                String qu_text =text.getText().toString();
	                // Update the Question object in the HashMap
	                if(rows_questions.containsKey(row)) {
	                	if(!qu_text.isEmpty() && !qu_text.equals("Default question") && !(containsSpecialCharacters(qu_text))) {
	                		System.out.println("all good");
	                Question question = rows_questions.get(row);
	                question.setQuestion(qu_text);
	                	}
	                	else {
			        		JOptionPane.showMessageDialog(null, "Can't save questions , make sure the qustion isn't deafult or empty or contains [,] , { , }, \" , or spaces", "Confirmation", JOptionPane.WARNING_MESSAGE);

	                		return false;
	                	}
	                }

	               
	            }
	         
	        }
	        
	        
	        // Retrieve the editor for the cell in column 1
	        TableCellEditor editor = table.getCellEditor(row, 1);
	        // Check if the editor is a JComboBox editor
	        if (editor instanceof DefaultCellEditor) {
	            Component editorComponent = ((DefaultCellEditor) editor).getComponent();

	            // Check if the component is a JComboBox
	            if (editorComponent instanceof JComboBox) {
	                JComboBox comboBox = (JComboBox) editorComponent;
	                int selectedAnswer =comboBox.getSelectedIndex();
	                ArrayList<String> answers= new ArrayList<>();
	            
	                
	                Set<String> uniqueAnswers = new HashSet<String>();
	                for (int j = 0; j < 4; j++) {
	                    String ans = (String) comboBox.getItemAt(j);
	                    
	                    // Check for empty answer
	                    if (ans.isEmpty()) {
			        		JOptionPane.showMessageDialog(null, "Can't save questions ,answers can't be empty!", "Confirmation", JOptionPane.WARNING_MESSAGE);
		        			System.out.println("empty ans");

	                        // Empty answer, return false
	                        return false;
	                    }
	                    
	                    // Check for duplicates
	                    if (!uniqueAnswers.add(ans)) {
			        		JOptionPane.showMessageDialog(null, "Can't save questions ,answers can't be duplicated!", "Confirmation", JOptionPane.WARNING_MESSAGE);
		        			System.out.println("contains dupl");

	                        // Duplicate found, return false
	                        return false;
	                    }

	                    // Check for unwanted characters or patterns
	                    if (ans.contains("{") || ans.contains("}") || ans.contains("\"") || ans.contains("[") || ans.contains("]")
	                            || ans.startsWith("  ")) {
			        		JOptionPane.showMessageDialog(null, "Can't save questions ,answers can't contain [,] , { , }, \\\" , or spaces ", "Confirmation", JOptionPane.WARNING_MESSAGE);
			        			System.out.println("contains []");
	                        // Unwanted character or pattern found, return false
	                        return false;
	                    }

	                    // Add the answer to the list
	                    System.out.println(ans);
	                    System.out.println("answers perfecto");
	                    answers.add(ans);
	                }
	                // Update the Question object in the HashMap
	                if(rows_questions.containsKey(row)) {
	                Question question = rows_questions.get(row);
	                question.setAnswers(answers);
	               // question.setQuestion(questionText);
	                // Update the selected answer (assuming getCorrectAnswerNum() returns the index)
	                question.setCorrectAnswerNum(selectedAnswer+1);
	                
	                }
	            }
	         
	        }
	        
//	        Question newques=rows_questions.get(row);
//            Main.questionController.NewQuestion(newques.getQuestion(), newques., row, row)

	          
	    }
	    if(update_changes_db(rows_questions)) {
     	   if(level==1) {
     		   
    	    	setAllEasyQuestions(rows_questions);
    	    }
    	    else if(level==2) {
    	    	
    	    	setAllMediumQuestions(rows_questions);
    	    }
    	    else if(level==3) {
    	    	setAllHardQuestions(rows_questions);
    	    }
    	    replaceTable(rows_questions);
    	    return true;
     }
     else {
     	return false;
     }
	

	 
	}
	private boolean containsSpecialCharacters(String input) {
		// Define the regular expression
        String regex = ".*[\"\\[\\]{}].*";
        // Define the prefix with more than two spaces
        String prefix = "   ";  // Adjust the number of spaces as needed
System.err.println(input.matches(regex) ? "match [] or {} " : "not match []");
System.err.println(input.startsWith(prefix) ? "spacesssssssss " : "no spaces");

        // Check if the string matches the regular expression
        return input.matches(regex) || input.startsWith(prefix);
	}
	private boolean update_changes_db(HashMap<Integer, Question> rows_questions) {
		int difficulity=rows_questions.get(0).getQuestionDifficulty();
		switch(difficulity) {
		case 1:
			for(Question q : rows_questions.values()) {
				if(Main.questionController.getAllEasyQuestions().containsKey(q.getQuestionNumber())) {
					if(Main.questionController.updateQuestion(q)) {
						
					}else {

						return false;}
				}else {
					if(Main.questionController.NewQuestion(q)) {
						
					}else {	   
						return false;}
				}
				
			}return true;
		case 2:
			for(Question q : rows_questions.values()) {
				if(Main.questionController.getAllMediumQuestions().containsKey(q.getQuestionNumber())) {
					if(Main.questionController.updateQuestion(q)) {
					
					}
					else {

						return false;}
				}else {
					if(Main.questionController.NewQuestion(q)) {
			
					}else {	
return false;}
				}
			}	return true;
		case 3 :
			for(Question q : rows_questions.values()) {
				if(Main.questionController.getAllHardQuestions().containsKey(q.getQuestionNumber())) {
					if(Main.questionController.updateQuestion(q)) {
					}
					else {

						return false;}
				}else {
					if(Main.questionController.NewQuestion(q)) {
					}
					else {	
return false;}
				}
			}	return true;
		default:
		{
			return false;}
		
		}
	}	
}
	    
	