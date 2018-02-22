package game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	private GameObject Player;
	private int tor = 0;
	
	public HUD(GameObject Player)
	{
		this.Player=Player;

	}
	public int health = 100;
	
	public void tick()
	{
		health=health-10;
		
	}
	
	public void ReturnAlive()
	{
		if (health==0)
		{ 
			Game.handler.removeObject(Player);
			tor+=2;
			if (tor>=100) {
				if(Player.id == ID.Player1)
					Player=new Player(Game.width*3/4,Game.heigth*3/4,ID.Player1,Color.black);
				else
					Player=new Player(Game.width/4,Game.heigth*3/4,ID.Player2, Color.blue);
				Game.handler.addObject(Player);
				health =100;
			}
		}
	}
	public void render(Graphics g , int x , int y)
	{
		g.setColor(Color.gray);
		g.fillRect(x, y, 100, 5);
		g.setColor(Color.green);
		g.fillRect(x, y, health, 5);
	}

}
