package flappyBird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Birdi 
{
	
	public BufferedImage bi;
	
	public Birdi(String path)
	{
		try {
			bi = ImageIO.read(getClass().getResource(path));
		}
		catch (IOException E){
			System.out.println("fail to load the image");
		}
	}

	public BufferedImage getSprite(int x,int y)
	{
		return bi.getSubimage(x, y, 23, 16);
	}
	
	public BufferedImage getSpriteClouds(int x,int y)
	{
		return bi.getSubimage(x, y, 602, 201);
	}
	
	public BufferedImage getSpriteEarth(int x,int y)
	{
		return bi.getSubimage(x, y, 500, 39);
	}
	
	public BufferedImage getSpriteTrees(int x,int y)
	{
		return bi.getSubimage(x, y, 600, 396);
	}
	
	public BufferedImage getSpriteBG(int x ,int y)
	{
		return bi.getSubimage(x,y,900,500);
	}
	
	public BufferedImage getSpriteColumn(int x,int y)
	{
		return bi.getSubimage(x, y, 48, 68);
	}

}
