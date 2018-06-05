import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Guide 
{
	public Guide()
	{
		JLabel Title = new JLabel("HOW TO PLAY FLAPPY BIRD");
			   Title.setVisible(true);
		JLabel explanation = new JLabel("if you select one player you jump by press the space button.");
			   explanation.setVisible(true);
		JLabel explanation1 = new JLabel("if you select two player you jump by press the space and enter button.");
			   explanation1.setVisible(true);
		JLabel explanation2 = new JLabel("in order to do a duoble jump press space + enter it work only in single player mode.");
			   explanation2.setVisible(true);
		JLabel explanation3 = new JLabel("every 10 points you get your speedup and the columns chenge it color.");
			   explanation3.setVisible(true);
		JLabel explanation4 = new JLabel("you need to fly between the space in the cloumn.");
			   explanation4.setVisible(true);
		JLabel explanation5 = new JLabel("dont touch the floor or columns or sky if you do you loss.");
			   explanation5.setVisible(true);
		JLabel explanation6 = new JLabel("in the main manu under the option score board you can see your highest");
			   explanation6.setVisible(true);
   	    JLabel explanation7 = new JLabel("score but its only for single player mode.");
			   explanation7.setVisible(true);
		JLabel explanation8 = new JLabel("in the two player the bird1 is red and bird2 is yellow");
			   explanation8.setVisible(true);
		JLabel explanation9 = new JLabel("to restart the game you click space in singale mode and multi player space+enter.");
			   explanation9.setVisible(true);
		JLabel explanation10 = new JLabel("to exit from the game press escap and then click on exit at main manu.");
			   explanation10.setVisible(true);
			   
		ImageIcon logo = new ImageIcon("logo.jpg");
		JButton Back = new JButton("Back");
		JFrame Gaide = new JFrame();
		Gaide.setIconImage(logo.getImage());
		JPanel P = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(3, 1, 10, 0);
		
		Back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				Gaide.setVisible(false);
				FlappyBird.main(null);
			}
		});
		 
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		P.add(Title, constraints);

		constraints.gridy = 1;
		P.add(explanation, constraints);
		
		constraints.gridy = 2;
		P.add(explanation1, constraints);
		
		constraints.gridy = 3;
		P.add(explanation2, constraints);
		
		constraints.gridy = 4;
		P.add(explanation3, constraints);
		
		constraints.gridy = 5;
		P.add(explanation4, constraints);
		
		constraints.gridy = 6;
		P.add(explanation5, constraints);
		
		constraints.gridy = 7;
		P.add(explanation6, constraints);

		constraints.gridy = 8;
		P.add(explanation7, constraints);
		
		constraints.gridy = 9;
		P.add(explanation8, constraints);
		
		constraints.gridy = 10;
		P.add(explanation9, constraints);
		
		constraints.gridy = 11;
		P.add(explanation10, constraints);
		
		constraints.gridy = 12;
		P.add(Back, constraints);

		
		Gaide.add(P);
		Gaide.setVisible(true);
		Gaide.setSize(600, 600);
		Gaide.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
