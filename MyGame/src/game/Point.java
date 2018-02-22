package game;

import java.awt.Color;
import java.awt.Graphics;

public class Point {

	public int point = 0;
	
	public void tick()
	{
		point+=10;	
		point = Game.clamp(point , 100, 0);
		
	}
	public void render(Graphics g , int x , int y)
	{
		g.setColor(Color.gray);
		g.fillRect(x, y, 100, 5);
		g.setColor(Color.green);
		g.fillRect(x, y, point, 5);
	}
	
	public boolean IsWin()
	{
		return point==100;
	}
}
