package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.JInternalFrame;

public class TutorialView2 extends tutrialTemplate  {

	  private JInternalFrame internalFrame;
	  private HomeView h;
	  private String backgroundpath;

	  public TutorialView2(HomeView h) {
	      this.h = h;
	      this.internalFrame = new JInternalFrame();
	     // this.backgroundpath="/hawk_images/tutorial_2_background.png";
	      this.backgroundpath="/hawk_images/tutorial2.png";
	  }
	@Override
	public JInternalFrame getInternalFrame() {
		  return internalFrame;
	}

	@Override
	public String getBackgroundpath() {
		return backgroundpath;
	}



	@Override
	public void setBackButtonActionListener(JButton b) {
		 b.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (e.getSource() == b) {
                
             	  
           	    tutrialTemplate tutorialView1 = new TutorialView1(h);
				    tutorialView1.create(); // Call the create method

				    JInternalFrame internalFrame = tutorialView1.getInternalFrame();

           	    
           	    h.setContentPane(internalFrame);
           	    h.getContentPane().setVisible(true);
               }
           }
       });		
	}

	@Override
	public void setNextButtonActionListener(JButton b) {
		 b.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (e.getSource() == b) {
               	 getH().getContentPane().setVisible(false);

            	  
            	    tutrialTemplate tutorialView3 = new TutorialView3(getH());
				    tutorialView3.create(); // Call the create method

				    JInternalFrame internalFrame = tutorialView3.getInternalFrame();

            	    
				    getH().setContentPane(internalFrame);
				    getH().getContentPane().setVisible(true);
                   
            	    
            	    
        
                   
               }
           }
       });		
	}

	@Override
	public HomeView getH() {
		return h;
	}

	@Override
	public void addComponents() {
		 getExitBtn().setVisible(true);
		 getInternalFrame().getContentPane().add(getExitBtn());
		
		 getNextBtn().setVisible(true);
		 getInternalFrame().getContentPane().add(getNextBtn());
		
		 getBackButton().setVisible(true);
		 internalFrame.getContentPane().add(getBackButton());
		 getBackground().setVisible(true);
		 getInternalFrame().getContentPane().add(getBackground());

		 		
	}


}
