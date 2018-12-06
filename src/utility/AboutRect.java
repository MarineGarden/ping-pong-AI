package utility;

import java.awt.Rectangle;

public class AboutRect {
	
	private Rectangle target;
	
	public AboutRect( Rectangle target ) {
		
		this.target = target;
		
	}
	
	public double getFutureDistanceX( Rectangle outer , double shiftX ) {
		
		return getInnerDistanceX( new Rectangle( (int)( outer.x + shiftX ) , outer.y , outer.width , outer.height ) );
		
	}
	
	public double getInnerDistanceX( Rectangle outer ) {
		
		double rightDistance =  outer.getMaxX() - target.getMaxX();
		double leftDistance = target.getMinX() - outer.getMinX();
		
		return Math.min( rightDistance , leftDistance );
		
	}
	
	public double getCenterX() {
		
		return target.getCenterX();
		
	}
	
	public double getCenterY() {
		
		return target.getCenterY();
		
	}
	
	public boolean isFlat() {
		
		return ( target.height < target.width ) && ( target.height >= 0 );
		
	}
	
	public boolean isTall() {
		
		return ( target.width < target.height ) && ( target.width >= 0 );
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Rectangle test = new Rectangle( 100 , 100 , 200 , 100 );
		System.out.println( new AboutRect( test ).isFlat() );
		
		test = new Rectangle( 100 , 100 , 100 , 200 );
		System.out.println( new AboutRect( test ).isTall() );
		
		test = new Rectangle( 100 , 100 , 100 , 100 );
		System.out.println( new AboutRect( test ).isTall() && new AboutRect( test ).isFlat() );
		
	}

}
