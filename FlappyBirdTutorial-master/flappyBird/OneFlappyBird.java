package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import bin.FlappyBird;


public class OneFlappyBird implements ActionListener, MouseListener, KeyListener
{	
	public static OneFlappyBird flappyBird1;

	public static int Highest[] = new int [10];

	public final int WIDTH = 800, HEIGHT = 750;

	public Renderer renderer;

	public Rectangle bird,cloud;

	//public static Color random = Color.GREEN;

	public ArrayList<Rectangle> columns;

	public int ticks, yMotion, score=0, speed=8;

	public static int temp=0;

	public boolean gameOver, started;

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

	public OneFlappyBird()
	{

		ImageIcon logo = new ImageIcon("logo.jpg");
		jframe = new JFrame();
		jframe.setIconImage(logo.getImage());
		Timer timer = new Timer(20, this);

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

		cloud = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 100, 100);
		bird = new Rectangle(200, HEIGHT / 2 - 10, 40, 40);
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

		g.drawImage(Texture.downColumn,columns.get(0).x, columns.get(0).y,columns.get(0).width,columns.get(0).height,null);
		g.drawImage(Texture.upColumn,columns.get(1).x, columns.get(1).y,columns.get(1).width,columns.get(1).height,null);
		//g.setColor(random.darker());
		//g.fillRect(column.x, column.y, column.width, column.height);
	}

	public void jump()
	{
		if (gameOver)
		{
			bird = new Rectangle(200, HEIGHT / 2 - 10, 40, 40);
			columns.clear();
			yMotion = 0;
			score = 0;
			temp=0;
			speed=8;
			Texture.check();
			//random = Color.GREEN;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}

		if (!started)
		{
			started = true;
		}
		else if (!gameOver)
		{
			if (yMotion > 0)
			{
				yMotion = 0;
			}

			yMotion -= 10;
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

			if (ticks % 2 == 0 && yMotion < 15)
			{
				yMotion += 2;
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

			for (Rectangle column : columns)
			{
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
				{
					score++;
					temp = score/2;
					if(temp<10)
					{
						Texture.check();
						speed=8;
					}
					else
						if(temp >= 10 && temp <20)
						{
							Texture.check();
							speed=10;
							//random = Color.BLUE;
						}
						else if(temp >= 20 && temp < 30)
						{
							Texture.check();
							speed=12;
							//random = Color.RED;
						}
						else if(temp >= 30 && temp < 40)
						{
							Texture.check();
							speed=14;
							//random = Color.ORANGE;
						}
						else if(temp >= 40 && temp < 50)
						{
							Texture.check();
							speed=16;
							//random = Color.BLACK;
						}
						else if(temp >= 50)
						{
							Texture.check();
							speed=18;
							//random = Color.WHITE;
						}else speed++;

				}

				if (column.intersects(bird))
				{
					gameOver = true;
					if (bird.x <= column.x)
					{
						bird.x = column.x - bird.width;
					}
					else
					{
						if (column.y != 0)
						{
							bird.y = column.y - bird.height;
						}
						else if (bird.y < column.height)
						{
							bird.y = column.height;
						}
					}
				}
			}

			if (bird.y > HEIGHT - 120 || bird.y < 0)
			{
				gameOver = true;
			}

			if (bird.y + yMotion >= HEIGHT - 120)
			{
				bird.y = HEIGHT - 100 - bird.height;
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

		g.drawImage(Texture.earth,0,630,800,90,null);

		g.drawImage(Texture.cloud,200, 100,602,201,null);

		g.drawImage(Texture.Bird1,bird.x,bird.y,39,39,null);

		g.drawImage(Texture.downColumn,columns.get(0).x, columns.get(0).y,columns.get(0).width,columns.get(0).height,null);

		g.drawImage(Texture.upColumn,columns.get(1).x, columns.get(1).y,columns.get(1).width,columns.get(1).height,null);

		for (Rectangle column : columns)
		{
			paintColumn(g, column);
		}

		g.setColor(Color.black);
		g.setFont(new Font("Arial", 1, 50));

		if (!started)
		{
			g.drawString("Click to start!", 300, 350);
		}

		if (gameOver)
		{
			g.drawString("Game Over!", 300,380);
		}

		if (!gameOver && started)
		{
			g.drawString(String.valueOf(temp), 5, 50);
		}
	}


	public static void main(String[] args)
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
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while((line = br.readLine()) != null)
			{
				Highest[i] = Integer.parseInt(line);
				i++;
			}
			br.close();
		} 
		catch (IOException e1) 
		{
			System.out.println("the system can not find the ScoreBoard.txt");
		}

		flappyBird1 = new OneFlappyBird();
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
			//random = Color.GREEN;
			Texture.check();
			
			int big [] = {0,0,0,0,0,0,0,0,0,0,0};
			int small [] = {0,0,0,0,0,0,0,0,0,0};
			int b=0,s=0;
			for(int i=0;i<Highest.length;i++)
			{
				if(temp<Highest[i])
				{
					big[b]=Highest[i];
					b++;
				}
				else 
				{
					small[s]=Highest[i];
					s++;
				}
			}
			big[b]=temp;

			int j=0,k=0;
			int both [] = new int [20];

			for(int i=0;i<both.length && j<big.length && k<small.length;i++)
			{
				if(big[j]==small[k])
				{
					both[i]=big[j];
					k++;
					j++;
				}
				else 
					if(big[j]>small[k])
					{
						both[i]=big[j];
						j++;
					}
					else 
						if(big[j]<small[k])
						{
							both[i]=small[k];
							k++;							
						}
			}			

			try {
				File file = new File("scoreBoard.txt");

				// if file doesnt exists, then create it
				if (!file.exists()) file.createNewFile();

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);

				for(int i=0;i<Highest.length;i++)
				{
					bw.write(Integer.toString(both[i]));
					bw.newLine();
				}
				bw.flush();
				bw.close();
			} 
			catch (IOException e1) 
			{
				System.out.println("the system can not find the ScoreBoard.txt");
			}

			jframe.dispose();
			FlappyBird.main(null);
		}
		else
			if (e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				jump();
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

