package game;

import java.awt.Color;
import java.awt.Graphics;

public class Bull extends GameObject {

	private GameObject EnemyPlayer;
	private Color c;
	public Bull(int x, int y, ID id, GameObject temp, Color c) {
		super(x, y, id);
		this.EnemyPlayer=temp;
		this.c =c;
	}

	@Override
	public void tick() 
	{
		
		if(EnemyPlayer.x>x)x+=2;
		else if (EnemyPlayer.x<x) x-=2;
		
		if(EnemyPlayer.y>y)y+=2;
		else if (EnemyPlayer.y<y) y-=2; 
		
		
		if (Bullkill().intersects(EnemyPlayer.Bullkill()))
		{
			if (EnemyPlayer.id == ID.Drone1 && id == ID.Bull2){
				Game.handler.removeObject(EnemyPlayer);
				Game.handler.removeObject(this);	
			}
			else if (EnemyPlayer.id == ID.Drone2 && id == ID.Bull1){
				Game.handler.removeObject(EnemyPlayer);
				Game.handler.removeObject(this);
			}
			else if (EnemyPlayer.id == ID.Player1 && id == ID.Bull2)
			{
				Game.handler.removeObject(this);
				Game.hud1.tick();
			}
			else if (EnemyPlayer.id == ID.Player2 && id == ID.Bull1)
			{
				Game.handler.removeObject(this);
				Game.hud2.tick();
			}

		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(c);
		g.fillRect(x,y,10,5);
		
	}

}
