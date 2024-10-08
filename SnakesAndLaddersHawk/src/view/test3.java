package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DiceController;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class test3 extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test3 frame = new test3();
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
	public test3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btlRollDice = new JButton("Roll");
		btlRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = DiceController.getInstance().rollDice(3);
				String s = Integer.toString(res);
				txtFieldResult.setText(s);
				txtFieldResult.setVisible(true);
				System.out.println(s);
				
			}
		});
		contentPane.add(btlRollDice);
		
		txtFieldResult = new JTextField();
		contentPane.add(txtFieldResult);
		txtFieldResult.setColumns(10);
	}

}
