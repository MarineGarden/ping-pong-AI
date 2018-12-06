package core.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import core.Game;

public class Rectangle extends VisualObject {
	
	private Color c;
	private Point size;
	
	public Rectangle( Color c , Point size ) {
		
		this.c = c;
		this.size = size;
		setBounds( 0 , 0 , size.x , size.y );
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		g.setColor( c );
		g.fillRect( 0 , 0 , size.x , size.y );
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Game stage = new Game();
		Rectangle brick = new Rectangle( Color.BLACK , new Point( 300 , 100 ) );
		stage.add( brick );
		brick.moveByShift( 100 , 200 );
		
	}

}
