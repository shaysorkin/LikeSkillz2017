package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import game.Game.STATE;

public class Win extends MouseAdapter {
	private Game game;
	private Handler handler;
	public Win(Game game,Handler handler)
	{
		this.game=game;
		this.handler=handler;
	}
	
	public void mousePressed(MouseEvent m)
	{
		int mx = m.getX();
		int my = m.getY();
		
		if(mouseOver(mx,my,200, 300, 260, 100) && Game.GameState != STATE.Game)
		{
			Game.GameState=STATE.Game;
			//בונה עיר 
			Game.Reload();
			
			Game.point1 =new Point();
			Game.point2 = new Point();
		}
	}
	
	public void mouseReleased(MouseEvent m)
	{
		
	}
	
	private boolean mouseOver(int mx, int my,int x,int y, int width,int heigth)
	{
		if (mx>x && mx< x + width)
			if (my>y && my<y+heigth)
				return true;
		return false;
	}
	public void tick()
	{
		
	}
	
	public static void render(Graphics g, String w)
	{
		g.setColor(Color.white);
		
		Font fn = new Font("arial",1,50);
		g.setFont(fn);
		g.drawString(w, 270, 160);
		g.drawRect(225, 100, 200, 100);
		
		
		g.drawString("play again", 210, 360);
		g.drawRect(200, 300, 260, 100);
		
	}
}
