package game;

import java.awt.Color;
import java.awt.Graphics;

public class BaseIsland extends GameObject {

	public BaseIsland(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
		y= Game.heigth*3/8;
		if (id == ID.Base2)
		{
			x=Game.width-100;
			
		}
		else
		{
			x=100;
		}
		
	}

	
	public void render(Graphics g) 
	{
		if (id == ID.Base2)
		{
			g.setColor(Color.blue);
		}
		else
		{
			g.setColor(Color.black);
		}
		
		g.fillRect(x, y, 20, 20);
		
	}

}
