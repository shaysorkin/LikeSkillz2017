package game;

import java.awt.Color;
import java.awt.Graphics;

public class Drone extends GameObject {

	private Handler handler;
	public Drone(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
	}

	public void tick() 
	{
		// של פיראט 1
		if(id == ID.Drone1){
			if (x!=100) x--;
			else if (y!=Game.heigth*3/8) y++;
		}
		//של פיראט 2
		else 
		{
			if (x!=Game.width-100) x++;
			else if (y!=Game.heigth*3/8) y++;
		}
		
		for (int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			
			if(temp.getId()==ID.Base1)
			{
				if (getBounds().intersects(temp.getBounds()))
				{
					Game.point1.tick();
					handler.removeObject(this);
				}
			}
			else if(temp.getId()==ID.Base2)
			{
				if (getBounds().intersects(temp.getBounds()))
				{
					Game.point2.tick();
					handler.removeObject(this);
				}
			
			}
			
			
			
		}
		
	}

	public void render(Graphics g) 
	{
	
		g.setColor(Color.green);
		
		
		g.fillRect(x, y, 10, 10);
		
		
	}

}
