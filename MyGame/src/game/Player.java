package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {
	private Color c;
	private int tor =0;
	 
	public Player(int x, int y, ID id, Color c) {
		super(x, y, id);
		this.c =c;
	}


	public void tick() {
		x+=velX;
		y+=velY;
		
		x=Game.clamp(x,Game.width-32 ,0);
		y=Game.clamp(y,Game.heigth-55 ,0);
		
		for (int i=0;i<Game.handler.object.size();i++){
			GameObject temp = Game.handler.object.get(i);
			
			if(temp.getId()==ID.Player1 && id == ID.Player2)
			{
				if (Bullrange().intersects(temp.Bullrange()))
				{
					tor++;
					if (tor==10){
						Game.handler.addObject(new Bull(x, y, ID.Bull2, temp,c));
						tor=0;
					}
				}
			}
			else if (temp.getId()==ID.Player2 && id == ID.Player1)
			{
				if (Bullrange().intersects(temp.Bullrange()))
				{
					tor++;
					if (tor==10){
						Game.handler.addObject(new Bull(x, y, ID.Bull1, temp,c));
						tor=0;
					}
				}
			}
			else if (temp.getId()==ID.Drone2 && id == ID.Player1)
			{
				if (Bullrange().intersects(temp.Bullrange()))
				{
					tor++;
					if (tor==10){
						Game.handler.addObject(new Bull(x, y, ID.Bull1, temp,c));
						tor=0;
					}
				}
					
			}
			else if (temp.getId()==ID.Drone1 && id == ID.Player2)
			{
				if (Bullrange().intersects(temp.Bullrange()))
				{
					tor++;
					if (tor==10){
						Game.handler.addObject(new Bull(x, y, ID.Bull2, temp,c));
						tor =0;
					}
				}
			}
		}
	}

	public void render(Graphics g) {	
		g.setColor(c);
		g.fillRect(x,y,5,10 );
		
	}

}
