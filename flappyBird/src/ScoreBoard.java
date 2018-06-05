
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import flappyBird.OneFlappyBird;


public class ScoreBoard 
{

	public ScoreBoard()
	{
		//read the file into the array
				int i=0;
				try {
					File file = new File("scoreBoard.txt");

					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					String line = null;
					FileReader fr = new FileReader(file.getAbsoluteFile());
					BufferedReader br = new BufferedReader(fr);

					while((line = br.readLine()) != null)
					{
						OneFlappyBird.Highest[i] = Integer.parseInt(line);
						i++;
					}
					
					br.close();

				} 
				catch (IOException e1) 
				{
					System.out.println("the system can not find the ScoreBoard.txt");
				}
		
		//score board
		ImageIcon logo = new ImageIcon("logo.jpg");
		JFrame scoreBoard = new JFrame();
		scoreBoard.setIconImage(logo.getImage());
		JPanel score = new JPanel(new GridBagLayout());
		score.setName("score Panel");	

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		JLabel label1 = new JLabel("1. "+OneFlappyBird.Highest[0]);
		label1.setVisible(true);
		JLabel label2 = new JLabel("2. "+OneFlappyBird.Highest[1]);
		label2.setVisible(true);
		JLabel label3 = new JLabel("3. "+OneFlappyBird.Highest[2]);
		label3.setVisible(true);
		JLabel label4 = new JLabel("4. "+OneFlappyBird.Highest[3]);
		label4.setVisible(true);
		JLabel label5 = new JLabel("5. "+OneFlappyBird.Highest[4]);
		label5.setVisible(true);
		JLabel label6 = new JLabel("6. "+OneFlappyBird.Highest[5]);
		label6.setVisible(true);
		JLabel label7 = new JLabel("7. "+OneFlappyBird.Highest[6]);
		label7.setVisible(true);
		JLabel label8 = new JLabel("8. "+OneFlappyBird.Highest[7]);
		label8.setVisible(true);
		JLabel label9 = new JLabel("9. "+OneFlappyBird.Highest[8]);
		label9.setVisible(true);
		JLabel label10 = new JLabel("10. "+OneFlappyBird.Highest[9]);
		label10.setVisible(true);

		JButton back = new JButton("back");

		constraints.gridx = 0;
		constraints.gridy = 1;
		score.add(label1, constraints);
		constraints.gridy = 2;
		score.add(label2, constraints);
		constraints.gridy = 3;
		score.add(label3, constraints);
		constraints.gridy = 4;
		score.add(label4, constraints);
		constraints.gridy = 5;
		score.add(label5, constraints);
		constraints.gridy = 6;
		score.add(label6, constraints);
		constraints.gridy = 7;
		score.add(label7, constraints);
		constraints.gridy = 8;
		score.add(label8, constraints);
		constraints.gridy = 9;
		score.add(label9, constraints);
		constraints.gridy = 10;
		score.add(label10, constraints);
		constraints.gridy = 11;
		score.add(back, constraints);

		back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				scoreBoard.setVisible(false);
				FlappyBird.main(null);
			}
		});
		
		scoreBoard.add(score);
		scoreBoard.setSize(300, 500);
		scoreBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoreBoard.setVisible(true);
	}
}
