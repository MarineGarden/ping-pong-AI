package core.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import core.Game;

public class Bar extends VisualObject {
	
	public Bar( Color c , int endRadius , int length ) {
		
		super( left( Color.RED , endRadius ) , middle( Color.GREEN , endRadius , length ) , right( Color.YELLOW , endRadius , length ) );
		setBounds( 0 , 0 , length + endRadius*2 , endRadius*2 );
		
	}
	
	private static Component left( Color c , int endRadius ) {
		
		return new Circle( c , endRadius );
		
	}
	
	private static Component middle( Color c , int endRadius , int length ) {
		
		return new Rectangle( c , new Point( length , endRadius*2 ) ).moveByShift( endRadius , 0 );
		
	}
	
	private static Component right( Color c , int endRadius , int length ) {
		
		return new Circle( c , endRadius ).moveByShift( length , 0 );
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Game stage = new Game();
		Bar player = new Bar( Color.BLACK , 30 , 100 );
		stage.add( player );
		player.moveByShift( 100 , 100 );
		
	}

}
