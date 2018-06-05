import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import flappyBird.Birdi;
import flappyBird.OneFlappyBird;
import flappyBird.Texture;

public class FlappyBird implements ActionListener, MouseListener,KeyListener
{

	public static FlappyBird flappyBird;

	public final int WIDTH = 800, HEIGHT = 750;

	public Renderer renderer;

	public Rectangle bird,bird2,cloud;

	//public static Color random = Color.GREEN;

	public ArrayList<Rectangle> columns;

	public int ticks, yMotion,yMotion2, score,speed=8;

	public static int temp;

	public boolean gameOver, started , b_bird1 , b_bird2;

	public Random rand;

	public static Birdi b = new Birdi("/Untitled.png");

	public static Birdi clouds = new Birdi("/cloud.png");

	public static Birdi earth = new Birdi("/earth.jpg");

	public static Birdi BackGround = new Birdi("/bg.png");
	
	public static Birdi upColumnGreen = new Birdi("/upColumn.png");

	public static Birdi downCloumnGreen = new Birdi("/downColumn.png");

	public static Birdi upColumnBlue = new Birdi("/upColumnBlue.png");

	public static Birdi downColumnBlue = new Birdi("/downColumnBlue.png");

	public static Birdi upColumnRed = new Birdi("/upColumnRed.png");

	public static Birdi downColumnRed = new Birdi("/downColumnRed.png");

	public static Birdi upColumnOrenge = new Birdi("/upColumnOrenge.png");

	public static Birdi downColumnOrenge = new Birdi("/downColumnOrenge.png");

	public static Birdi upColumnPurple = new Birdi("/upColumnPurple.png");

	public static Birdi downColumnPurple = new Birdi("/downColumnPurple.png");

	public static Birdi upColumnWhite = new Birdi("/upColumnWhite.png");

	public static Birdi downColumnWhite = new Birdi("/downColumnWhite.png");

	JFrame jframe;
	
	Timer timer;

	public FlappyBird()
	{
		ImageIcon logo = new ImageIcon("logo.jpg");
		jframe = new JFrame();
		jframe.setIconImage(logo.getImage());

		timer = new Timer(20, this);

		renderer = new Renderer();
		rand = new Random();

		jframe.add(renderer);
		jframe.setTitle("Flappy Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		bird = new Rectangle(200, HEIGHT / 2 - 10, 40, 40);
		bird2 = new Rectangle(200, HEIGHT / 2 - 40, 40, 40);
		cloud = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 100, 100);
		columns = new ArrayList<Rectangle>();

		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);

		timer.start();
	}

	public void addColumn(boolean start)
	{
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);

		if (start)
		{
			columns.add(new Rectangle(WIDTH + width + columns.size() * 440, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 440, 0, width, HEIGHT - height - space));
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 880, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	public void paintColumn(Graphics g, Rectangle column)
	{
		g.drawImage(Texture2.downColumn,columns.get(0).x, columns.get(0).y,columns.get(0).width,columns.get(0).height,null);
		g.drawImage(Texture2.upColumn,columns.get(1).x, columns.get(1).y,columns.get(1).width,columns.get(1).height,null);
		//g.setColor(random.darker());
		//g.fillRect(column.x, column.y, column.width, column.height);
	}

	public void jump()
	{
		if (gameOver)
		{
			bird = new Rectangle(200, HEIGHT / 2 - 10, 40, 40);
			bird2 = new Rectangle(200, HEIGHT / 2 - 40, 40, 40);
			columns.clear();
			yMotion = 0;
			yMotion2 = 0;
			temp=0;
			score = 0;
			speed=8;
			Texture2.check();
			//random = Color.GREEN;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}

		if (!started)started = true;

		else if (!gameOver)
		{  
			if (yMotion > 0 )
			{
				yMotion = 0;
			}

			yMotion -= 10;
		}
	}

	public void jump2()
	{
		if (gameOver)
		{
			bird = new Rectangle(200, HEIGHT / 2 - 10, 40, 40);
			bird2 = new Rectangle(200, HEIGHT / 2 - 40, 40, 40);
			//random = Color.GREEN;
			yMotion = 0;
			yMotion2 = 0;
			temp=0;
			speed=8;
			score = 0;
			Texture2.check();

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}

		if (!started) started = true;

		else if (!gameOver)
		{
			if ( yMotion2 > 0)
			{
				yMotion2 = 0;
			}

			yMotion2 -= 10;
		}
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		ticks++;

		if (started)
		{
			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);
				column.x -= speed;
			}

			if (ticks % 2 == 0 && (yMotion < 15 && yMotion2 <15))
			{
				yMotion += 2;
				yMotion2 += 2;
			}

			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);

				if (column.x + column.width < 0)
				{
					columns.remove(column);

					if (column.y == 0)
					{
						addColumn(false);
					}
				}
			}

			bird.y += yMotion;
			bird2.y += yMotion2;

			for (Rectangle column : columns)
			{
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10 
						&& bird2.x + bird2.width / 2 > column.x + column.width / 2 - 10 && bird2.x + bird2.width / 2 < column.x + column.width / 2 + 10)
				{
					score++;
					temp = score/2;
					if(temp<10)
					{
						Texture2.check();
						speed=8;
					}
					else
						if(temp >= 10 && temp < 20)
						{
							//random = Color.BLUE;
							Texture2.check();
							speed=10;
						}
						else if(temp >= 20 && temp < 30)
						{
							//random = Color.RED;
							Texture2.check();
							speed=12;
						}
						else if(temp >= 30 && temp < 40)
						{
							speed=14;
							Texture2.check();
							//random = Color.ORANGE;
						}
						else if(temp >= 40 && temp < 50)
						{
							speed=16;
							Texture2.check();
							//random = Color.BLACK;
						}
						else if(temp >= 50)
						{
							speed=18;
							Texture2.check();
							//random = Color.WHITE;
						}else speed++;
				}

				if (column.intersects(bird) || column.intersects(bird2))
				{	
					gameOver = true;
					if (column.intersects(bird) && column.intersects(bird2))
					{
						b_bird1=true;
						b_bird2=true;
					}
					else 
						if(column.intersects(bird))
						{
							b_bird1=false;
							b_bird2=true;
						}
						else if(column.intersects(bird2))
						{
							b_bird1=true;
							b_bird2=false;
						}

					if (bird.x <= column.x && bird2.x <= column.x)
					{
						bird.x = column.x - bird.width;
						bird2.x = column.x - bird2.width;

					}
					else
					{
						if (column.y != 0)
						{
							bird.y = column.y - bird.height;
							bird2.y = column.y - bird2.height;
						}
						else if (bird.y < column.height || bird2.y < column.height)
						{
							bird.y = column.height;
							bird2.y = column.height;
						}
					}
				}
			} 

			if ((bird.y > HEIGHT - 120 || bird.y < 0) || ( bird2.y > HEIGHT - 120 || bird2.y < 0))
			{
				gameOver = true;
				renderer.repaint();
			}

			if ((bird.y + yMotion > HEIGHT - 120) || (bird2.y + yMotion2 > HEIGHT - 120))
			{
				bird.y = HEIGHT -90 - bird.height;
				bird2.y = HEIGHT - 120 - bird2.height;

				gameOver = true;
			}
		}

		renderer.repaint();
	}

	public void repaint(Graphics g)
	{	
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.orange);
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);

		g.setColor(Color.green);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

		g.drawImage(Texture.background,0,0,WIDTH,HEIGHT-70,null);

		//g.drawImage(Texture.trees1,200,540,200,100,null);

		//g.drawImage(Texture.trees2,550,540,200,100,null);

		g.drawImage(Texture.cloud,200, 100,602,201,null);

		g.drawImage(Texture.earth,0,630,800,90,null);

		g.drawImage(Texture.Bird1,bird.x,bird.y,39,39,null);

		g.drawImage(Texture.Bird2,bird.x,bird2.y,39,39,null);

		g.drawImage(Texture2.downColumn,columns.get(0).x, columns.get(0).y,columns.get(0).width,columns.get(0).height,null);

		g.drawImage(Texture2.upColumn,columns.get(1).x, columns.get(1).y,columns.get(1).width,columns.get(1).height,null);


		for (Rectangle column : columns)
		{
			paintColumn(g, column);
		}

		g.setColor(Color.black);
		g.setFont(new Font("Arial", 1, 50));

		if (!started)
		{
			g.drawString("Click to start!", 300, 330);
		}

		if (gameOver)
		{	
			if (b_bird1 && b_bird2)
			{
				b_bird1=false;
				b_bird2=false;
				g.drawString("draw", 300, 380);
				timer.stop();
			}
			else if (b_bird1)
			{
				b_bird1=true;
				b_bird2=false;
				g.drawString("bird1 win !", 300, 380);
				timer.stop();
			}
			else if (b_bird2)
			{
				b_bird1=false;
				b_bird2=true;
				g.drawString("bird2 win !", 300, 380);
				timer.stop();
			}
		}

		if (!gameOver && started)
		{

			g.drawString(String.valueOf(temp), 5, 50);
		}
	} 

	public static void main(String[] args)
	{	
		//main manu
		ImageIcon logo = new ImageIcon("logo.jpg");
		JFrame Main_Manu = new JFrame();
		Main_Manu.setIconImage(logo.getImage());
		Main_Manu.setTitle("Main Manu");

		JButton OnePlayer = new JButton("Single player");
		JButton TwoPlayer = new JButton("Multi Player");
		JButton scoredBoard = new JButton("Score Board");
		JButton Exit = new JButton("Exit");
		JButton Gaide = new JButton("Guide");

		JPanel Panel_MainManu = new JPanel(new GridBagLayout());
		Panel_MainManu.setName("Main Manu Panel");	

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 1, 10, 0);

		constraints.gridx = 0;
		constraints.gridy = 1;
		Panel_MainManu.add(OnePlayer, constraints);

		constraints.gridy = 2;
		Panel_MainManu.add(TwoPlayer, constraints);

		constraints.gridy = 3;
		Panel_MainManu.add(scoredBoard, constraints);

		constraints.gridy = 4;
		Panel_MainManu.add(Gaide, constraints);

		constraints.gridy = 5;
		Panel_MainManu.add(Exit, constraints);


		TwoPlayer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				Main_Manu.setVisible(false);
				flappyBird = new FlappyBird();
			}
		});

		OnePlayer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				Main_Manu.setVisible(false);
				OneFlappyBird.main(null);
			}
		});


		scoredBoard.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				Main_Manu.setVisible(false);
				ScoreBoard sb = new ScoreBoard();
			}
		});


		Gaide.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				Main_Manu.setVisible(false);
				Guide g = new Guide();
			}
		});

		Exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				try {
					File file = new File("scoreBoard.txt");

					// if file doesnt exists, then create it
					if (!file.exists()) file.createNewFile();

					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);

					for(int i=0;i<OneFlappyBird.Highest.length;i++)
					{
						bw.write(Integer.toString(OneFlappyBird.Highest[i]));
						bw.newLine();
					}
					bw.flush();
					bw.close();

				} 
				catch (IOException e1) 
				{
					System.out.println("the system can not find the ScoreBoard.txt");
				}
				System.exit(0);
			}
		});

		Main_Manu.add(Panel_MainManu);
		Main_Manu.setSize(300, 300);
		Main_Manu.setLocationRelativeTo(null);
		Main_Manu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main_Manu.setVisible(true);
	}


	public void Go_To_Main_Manu()
	{
		main(null);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		jump();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			Texture2.check();
			//random = Color.GREEN;
			jframe.setVisible(false);

			try {
				File file = new File("scoreBoard.txt");

				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);

				for(int i=0;i<OneFlappyBird.Highest.length;i++)
				{
					bw.write(Integer.toString(OneFlappyBird.Highest[i]));
					bw.newLine();
				}
				bw.flush();
				bw.close();
			} 
			catch (IOException e1) 
			{
				System.out.println("the system can not find the ScoreBoard.txt");
			}

			Go_To_Main_Manu();
		}
		else
		{
			if (e.getKeyCode() == KeyEvent.VK_SPACE)jump();
			else if (e.getKeyCode() == KeyEvent.VK_ENTER) jump2();
		}
	}

	@Override
	public void mousePressed(MouseEvent e){}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}
}