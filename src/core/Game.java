package core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

import core.view.Bar;
import core.view.Circle;
import core.view.PlainBoard;
import core.view.VisualObject;
import utility.AboutRect;

public class Game extends PlainBoard {
	
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final Rectangle stageArea = measureStageArea();
	
	private static Rectangle measureStageArea() {
		
		Rectangle result = new Rectangle();
		result.x = screenSize.width/4;
		result.y = 0;
		result.width = screenSize.width/2;
		result.height = screenSize.height;
		
		return result;		
		
	}
	
	private static final Circle ball = new Circle( Color.BLACK , 30 );
	private Point2D.Double ballLocation = getStageCenter( ball );
	private Point2D.Double ballDirection = chooseDirection( Math.PI*2*Math.random() );
	
	private static Point2D.Double getStageCenter( Component centerView ) {
		
		double centerX = stageArea.getCenterX() - centerView.getWidth()/2;
		double centerY = stageArea.getCenterY() - centerView.getHeight()/2;
		
		return new Point2D.Double( centerX , centerY );
		
	}
	
	private Point2D.Double chooseDirection( double angleInRadians ) {
		
		return new Point2D.Double( Math.sin( angleInRadians ) , Math.cos( angleInRadians ) );
		
	}
	
	private static final Bar sample = createBar();
	private static final Component opponent = createBar().moveByShift( (int)( getStageCenter( sample ).x ) , 50 );
	private static final Component challenger = createBar().moveByShift( (int)( getStageCenter( sample ).x ) , screenSize.height - sample.getHeight() - 50 );
	private static Bar createBar() {
		
		return new Bar( Color.BLACK , 30 , 100 );
		
	}
	
	private static final int WALL_WIDTH = 30;
	
	private static final Rectangle gameArea = beThinner( stageArea , WALL_WIDTH );
	private static final Rectangle movableArea = beThinner( gameArea , sample.getWidth()/2 - ( sample.getWidth() > ball.getWidth() ? ball.getWidth()/2 : 0 ) );
	private static Rectangle beThinner( Rectangle original , int howThinnerItGets ) {
		
		Rectangle result = new Rectangle();
		result.x = original.x + howThinnerItGets;
		result.y = original.y;
		result.width = original.width - howThinnerItGets*2;
		result.height = original.height;
		
		return result;
		
	}
	
	{
		
		setTitle( "Pingpong AI" );
		
		letBallAppear();
		letOpponentAppear();
		letChallengerAppear();
		
		letBallMove();
		letPlayersFollowTheBall();
		
	}
	
	private void letBallAppear() {
		
		add( ball );
		
		int centerX = ( int )( ballLocation.x );
		int centerY = ( int )( ballLocation.y );
		ball.setLocation( centerX , centerY );
		
	}
	
	private void letOpponentAppear() {
		
		add( opponent );
		
	}
	
	private void letChallengerAppear() {
		
		add( challenger );
		
	}
	
	private void letBallMove() {
		
		new Timer().schedule( new TimerTask() {

			@Override
			public void run() {
				
				considerReflection();
				
				ballLocation.x += ballDirection.x*3;
				ballLocation.y += ballDirection.y*3;
				Point exactLocation = new Point( ( int )ballLocation.x , ( int )ballLocation.y );
				
				ball.setLocation( exactLocation );
				
				repaint();
				
			}
			
		} , 100 , 100 );
		
	}
	
	private void considerReflection() {
		
		if ( ! gameArea.contains( ball.getBounds() ) ) {
			
			AboutRect reflection = new AboutRect( gameArea.intersection( ball.getBounds() ) );
			
			if ( reflection.isTall() )
				ballDirection.x *= -1;
			
			if ( reflection.isFlat() )
				ballDirection.y *= -1;
			
		}
		
		if ( opponent.getBounds().intersects( ball.getBounds() ) ) {
			
			AboutRect reflection = new AboutRect( opponent.getBounds().intersection( ball.getBounds() ) );
			
			if ( reflection.isTall() )
				ballDirection.x *= -1;
			
			if ( reflection.isFlat() )
				ballDirection.y *= -1;
			
		}
		
		if ( challenger.getBounds().intersects( ball.getBounds() ) ) {
			
			AboutRect reflection = new AboutRect( challenger.getBounds().intersection( ball.getBounds() ) );
			
			if ( reflection.isTall() )
				ballDirection.x *= -1;
			
			if ( reflection.isFlat() )
				ballDirection.y *= -1;
			
		}
		
	}
	
	private void letPlayersFollowTheBall() {
		
		new Timer().schedule( new TimerTask() {

			@Override
			public void run() {
				
				followTheBall( (VisualObject)( opponent ) );
				followTheBall( (VisualObject)( challenger ) );
				
			}
			
		}, 100 , 100 );
		
	}
	
	private void followTheBall( VisualObject player ) {
			
		AboutRect ballRegion = new AboutRect( ball.getBounds() );
		AboutRect playerRegion = new AboutRect( player.getBounds() );
		
		double moveShouldBe = ( ballRegion.getCenterX() - playerRegion.getCenterX() )/2;
		if ( movableArea.contains( ball.getBounds() ) )
			player.moveByShift( (int)( moveShouldBe ) , 0 );
		
	}

	@Override
	public void paint( Graphics g ) {
		
		super.paint( g );
		
		drawStageWalls( g );
		
	}
	
	private void drawStageWalls( Graphics g ) {
		
		g.fillRect( stageArea.x , stageArea.y , WALL_WIDTH , stageArea.height );
		g.fillRect( ( int )( stageArea.getMaxX() ) - WALL_WIDTH , stageArea.y , WALL_WIDTH , stageArea.height );
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Game();
		
	}

}
