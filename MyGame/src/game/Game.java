package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.lang.Thread.State;
import java.util.Random;


public class Game  extends Canvas implements Runnable{
	
	private Thread thread; 
	private boolean running =false;
	public static HUD hud1 , hud2;
	public static Handler handler;
	public static Island island ,island1;
	public static  Point point1, point2;
	private Win win;
	public static GameObject Player1,Player2;
	
	public static final int  width=640 , heigth = width/12*9;
	
	public enum STATE
	{
		Game,
		Win1,
		Win2
	};
	
	public static STATE GameState=STATE.Game;
	
	public static void Reload()
	{
		
		while(!handler.object.isEmpty())
		{
			handler.removeObject(handler.object.get(0));
		} 
		
		Player1= new Player(width*3/4,heigth*3/4,ID.Player1,Color.black);
		Player2=new Player(width/4,heigth*3/4,ID.Player2, Color.blue);
		hud1 =new HUD(Player1);
		hud2=new HUD(Player2);
		point1 =new Point();
		point2 = new Point();
		island = new Island(width/2,heigth/5,ID.Island, handler);
		island1 = new Island(width/2,heigth/7,ID.Island, handler);
		
		//בונה עיר 
		handler.addObject(new BaseIsland(300,Game.heigth*3/8,ID.Base1));
		//בונה עוד עיר 
		handler.addObject(new BaseIsland(300,Game.heigth*3/8,ID.Base2));

		// בונה עיר לתקיפה
		//בונה שחקן 1
		
		handler.addObject(Player1);
		// בונה את שחקן 2
		
		handler.addObject(Player2);
		//בדיקה על הדרונס
		//handler.addObject(new Drone(10,10,ID.Player2, handler));
		
	}
	
	public Game ()
	{
		handler =new Handler();
		
		win = new Win(this,handler);
		this.addMouseListener(win);
		this.addKeyListener(new KeyInput(handler));
		new windows (width,heigth,"Like Skillz Game",this);
		
		if(GameState == STATE.Game)
		{
			Reload();			
			
		}
		else if(GameState == STATE.Win1)
		{
			
		}
		else if(GameState == STATE.Win2)
		{
			
		}
	}

	public synchronized void start()
	{
		thread=new Thread(this);
		thread.start();
		running =true;
		
	}
	public synchronized void stop()
	{
		try{
			thread.join();
			running=false;
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void run ()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns= 1000000000 /amountOfTicks;
		double delta =0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta +=(now - lastTime)/ns;
			lastTime =now ;
			while(delta>=1)
			{
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis()- timer >1000 )
			{
				timer +=1000;
				System.out.println("FPS:"+frames);
				frames =0;
			}
			
		}
		stop();
	}
	
	private void tick()
	{
		if(GameState == STATE.Game)
		{
			hud1.ReturnAlive();
			hud2.ReturnAlive();
			handler.tick();
			island.tick();
			island1.tick();
		}
		else if(GameState == STATE.Win2)
		{
			win.tick();
		}
		else if(GameState == STATE.Win2)
		{
			win.tick();
		}
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.GRAY);
		g.fillRect(0,0,width,heigth);
		
		
		if(GameState == STATE.Game)
		{
			

			handler.render(g);
			island.render(g);
			island1.render(g);
			hud1.render(g,15,15);
			hud2.render(g,520,15);
			point1.render(g, 15, 30);
			point2.render(g, 520, 30);
			
			if(point1.IsWin())
			{
				GameState = STATE.Win2;
			}
			else if(point2.IsWin())
			{
				GameState = STATE.Win1;
			}
		}
		else if(GameState == STATE.Win1)
		{
			win.render(g,"Win 1");
		}
		else if(GameState == STATE.Win2)
		{
			win.render(g,"Win 2");
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var,int max ,int min)
	{
		if (var>=max) return var=max;
		else if (var<=min) return var=min;
		return var;
	}
	
	public static void main(String[] args) 
	{
		new Game();
	}
}