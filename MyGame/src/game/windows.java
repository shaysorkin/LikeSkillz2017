package game;

import javax.swing.JFrame;
import java.awt.*;



public class windows extends Canvas
{
	public windows (int width, int heigth , String title , Game game)
	{
		JFrame frame = new JFrame(title);
		
	    frame.setSize(width,heigth);
	    
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);
	    frame.setLocationRelativeTo(null);
	    frame.add(game);
	    frame.setVisible(true);
	    game.start();
	    
	}

}
