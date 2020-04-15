import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class WorldController extends GraphicsProgram {
	
	private World theWorld;
	private GCanvas theWorldCanvas;
	public static final int APPLICATION_WIDTH = 200;
	public static final int APPLICATION_HEIGHT = 200;
	
	public void run(){	
		setUpWorld();
		runWorld();
	}
	
	public void init(){
	    resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void setUpWorld(){
		theWorld = new World(20,20);
		theWorld.getpopList().add( new Healthy( new Location(3,6), theWorld ));
		theWorld.getpopList().add( new Healthy( new Location(4,6), theWorld ));
		theWorld.getpopList().add( new Healthy( new Location(1,1), theWorld ));
		theWorld.getpopList().add( new Healthy( new Location(5,6), theWorld ));
		theWorld.getpopList().add( new Healthy( new Location(2,6), theWorld ));
		theWorld.getpopList().add( new Healthy( new Location(4,9), theWorld ));
		theWorld.getpopList().add( new Healthy( new Location(3,7), theWorld ));
		theWorld.getpopList().add( new Healthy( new Location(8,1), theWorld ));
		theWorld.getpopList().add( new Healthy( new Location(6,5), theWorld ));
		theWorld.getpopList().add( new Healthy( new Location(7,7), theWorld ));
		theWorld.getpopList().add( new InfectedAsymptomatic( new Location(6,6), theWorld ));
		theWorldCanvas = this.getGCanvas();
	}
	
	public void runWorld(){
		drawWorld();
		for(int i=0; i<1;i++){
			theWorld.letTimePass(theWorld);
			pause(1000);
			drawWorld();
		}
	}	
	
	public void drawWorld(){
		drawBlankWorld();
		drawCreatures();
	}
	
	public void drawBlankWorld(){
		for(int row = 0 ; row<theWorld.getWidth(); row++)
			for(int col=0; col<theWorld.getHeight(); col++){
				GRect r = new GRect(row*10, col*10, 10, 10);
				r.setFillColor(Color.WHITE);
				r.setFilled(true);
				theWorldCanvas.add(r);
			}
	}
	
	public void drawCreatures(){
		for(Person x: theWorld.getpopList()){
			GRect r = new GRect (x.getMyLocation().getX()*10, x.getMyLocation().getY()*10,10,10);
			r.setFillColor(x.getMyColor());
			r.setFilled(true);
			theWorldCanvas.add(r);
		}
	}
}
