package core.view;

import java.awt.Component;
import javax.swing.JComponent;

public class VisualObject extends JComponent {
	
	public VisualObject() {}
	public VisualObject( Component... children ) {
		
		for ( Component child : children )
			add( child , 0 );
		
	}
	
	public Component moveByShift( int shiftX , int shiftY ) {
		
		setLocation( getX() + shiftX , getY() + shiftY );
		
		return this;
		
	}

}
