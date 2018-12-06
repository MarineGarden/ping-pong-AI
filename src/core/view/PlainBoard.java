package core.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

public class PlainBoard extends JFrame {
	
	{
		
		setExtendedState( MAXIMIZED_BOTH );
		setLayout( null );
		setUndecorated( true );
		
		getContentPane().setBackground( Color.WHITE );
		
		addMouseListener( new MouseAdapter() {

			@Override
			public void mousePressed( MouseEvent event ) {
				
				super.mousePressed( event );
				
				if ( event.getClickCount() > 1 )
					System.exit( 0 );
				
			}
			
		} );
		
		setVisible( true );
		
	}

}
