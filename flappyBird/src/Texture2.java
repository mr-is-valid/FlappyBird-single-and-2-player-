
import java.awt.image.BufferedImage;
import flappyBird.OneFlappyBird;

public class Texture2 
{
	public static BufferedImage upColumn = FlappyBird.upColumnGreen.getSpriteColumn(0,0);
	public static BufferedImage downColumn = FlappyBird.downCloumnGreen.getSpriteColumn(0,0);

	public static void check() 
	{
		if(FlappyBird.temp <10)
		{
			upColumn = FlappyBird.upColumnGreen.getSpriteColumn(0,0);
			downColumn = FlappyBird.downCloumnGreen.getSpriteColumn(0,0);
		}
		if(FlappyBird.temp  >=10 && FlappyBird.temp<20)
		{
			upColumn = FlappyBird.upColumnBlue.getSpriteColumn(0,0);
			downColumn = FlappyBird.downColumnBlue.getSpriteColumn(0,0);
		}
		else if(FlappyBird.temp >= 20 && FlappyBird.temp < 30)
		{
			upColumn = FlappyBird.upColumnRed.getSpriteColumn(0,0);
			downColumn = FlappyBird.downColumnRed.getSpriteColumn(0,0);
		}
		else if(FlappyBird.temp >= 30 && FlappyBird.temp  < 40)
		{
			upColumn = FlappyBird.upColumnOrenge.getSpriteColumn(0,0);
			downColumn = FlappyBird.downColumnOrenge.getSpriteColumn(0,0);
		}
		else if(FlappyBird.temp  >= 40 && FlappyBird.temp  < 50)
		{
			upColumn = FlappyBird.upColumnPurple.getSpriteColumn(0,0);
			downColumn = FlappyBird.downColumnPurple.getSpriteColumn(0,0);
		}
		else if(FlappyBird.temp  >= 50)
		{
			upColumn = FlappyBird.upColumnWhite.getSpriteColumn(0,0);
			downColumn = FlappyBird.downColumnWhite.getSpriteColumn(0,0);
		}
	}
}
