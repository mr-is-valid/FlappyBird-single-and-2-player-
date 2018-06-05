package flappyBird;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Texture 
{
	public static BufferedImage Bird1 = OneFlappyBird.b.getSprite(0,0);
	public static BufferedImage Bird2 = OneFlappyBird.b.getSprite(0,16);
	public static BufferedImage cloud = OneFlappyBird.clouds.getSpriteClouds(0,0);
	public static BufferedImage earth = OneFlappyBird.earth.getSpriteEarth(0,0);
	//public static BufferedImage trees1 = OneFlappyBird.trees1.getSpriteTrees(0,0);
	//public static BufferedImage trees2 = OneFlappyBird.trees2.getSpriteTrees(0,0);
	public static BufferedImage background = OneFlappyBird.BackGround.getSpriteBG(0,0);
	public static BufferedImage upColumn = OneFlappyBird.upColumnGreen.getSpriteColumn(0,0);
	public static BufferedImage downColumn = OneFlappyBird.downCloumnGreen.getSpriteColumn(0,0);

	public static void check() 
	{
		if(OneFlappyBird.temp <10)
		{
			upColumn = OneFlappyBird.upColumnGreen.getSpriteColumn(0,0);
			downColumn = OneFlappyBird.downCloumnGreen.getSpriteColumn(0,0);
		}
		if(OneFlappyBird.temp >=10 && OneFlappyBird.temp<20)
		{
			upColumn = OneFlappyBird.upColumnBlue.getSpriteColumn(0,0);
			downColumn = OneFlappyBird.downColumnBlue.getSpriteColumn(0,0);
		}
		else if(OneFlappyBird.temp >= 20 && OneFlappyBird.temp < 30)
		{
			upColumn = OneFlappyBird.upColumnRed.getSpriteColumn(0,0);
			downColumn = OneFlappyBird.downColumnRed.getSpriteColumn(0,0);
		}
		else if(OneFlappyBird.temp >= 30 && OneFlappyBird.temp  < 40)
		{
			upColumn = OneFlappyBird.upColumnOrenge.getSpriteColumn(0,0);
			downColumn = OneFlappyBird.downColumnOrenge.getSpriteColumn(0,0);
		}
		else if(OneFlappyBird.temp  >= 40 && OneFlappyBird.temp  < 50)
		{
			upColumn = OneFlappyBird.upColumnPurple.getSpriteColumn(0,0);
			downColumn = OneFlappyBird.downColumnPurple.getSpriteColumn(0,0);
		}
		else if(OneFlappyBird.temp  >= 50)
		{
			upColumn = OneFlappyBird.upColumnWhite.getSpriteColumn(0,0);
			downColumn = OneFlappyBird.downColumnWhite.getSpriteColumn(0,0);
		}
	}
}
