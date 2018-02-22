package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.security.acl.Owner;

public class Island extends GameObject {

	private Handler handler;
	private int tour;
	public Island(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		 tour =0;
		
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x,y,10,5);
	} 
	
	
	public void tick() 
	{
		
		for (int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId()==ID.Player1)
			{
				if (getBounds().intersects(temp.getBounds()))
				{
					id =ID.Player1;
				}
			}
			else if(temp.getId()==ID.Player2)
			{
				if (getBounds().intersects(temp.getBounds()))
				{
					id =ID.Player2;
				}
			}
			
		}
		
		if (id ==ID.Player1)
		{
			tour++;
			if (tour ==50){
				tour=0;
				handler.addObject(new Drone(x,y,ID.Drone1, handler));
			}
			
		}
		else if (id == ID.Player2)
			{
				tour++;
				if (tour == 50){
					tour=0;
					handler.addObject(new Drone(x,y,ID.Drone2, handler));
				}
			}
	
	}
	
	public void render(Graphics g) 
	{
		if (id == ID.Island)
		{
			g.setColor(Color.LIGHT_GRAY);
		}
		else if (id == ID.Player1)
		{
			g.setColor(Color.black);
		}
		else{
			g.setColor(Color.blue);
		}
		g.fillRect(x, y, 10, 10);
		
		
	}

}
