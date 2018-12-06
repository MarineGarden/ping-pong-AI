package core.view;

import java.awt.Color;
import java.awt.Graphics;

import core.Game;

public class Circle extends VisualObject {
	
	private Color c;
	private int radius;
	
	public Circle( Color c , int radius ) {
		
		this.c = c;
		this.radius = radius;
		setBounds( 0 , 0 , radius*2 , radius*2 );
		
	}

	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		g.setColor( c );
		g.fillOval( 0 , 0 , radius*2 , radius*2 );
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Game stage = new Game();
		Circle ball = new Circle( Color.BLACK , 30 );
		stage.add( ball );
		ball.moveByShift( 100 , 100 );
		
	}

}
