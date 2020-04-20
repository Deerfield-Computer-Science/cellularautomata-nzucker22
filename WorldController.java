import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class WorldController extends GraphicsProgram {
	
	private World theWorld;
	private GCanvas theWorldCanvas;
	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 1000;
	
	public void run(){	
		setUpWorld();
		runWorld();
	}
	
	public void init(){
	    resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void setUpWorld(){
		theWorld = new World(60,60);
		double num = Math.random();
		double num2 = Math.random();
		int x1 = (int)(num*theWorld.getWidth());
		int y1 = (int)(num2*theWorld.getHeight());
		theWorld.getpopList().add( new InfectedAsymptomatic( new Location(x1,y1), theWorld ));
		for(int i=0; i<45; i++) {
			double num0 = Math.random();
			double num1 = Math.random();
			int x = (int)(num0*theWorld.getWidth());
			int y = (int)(num1*theWorld.getHeight());
			for(int j=0; j<i; j++) {
				if(theWorld.getpopList().get(j).getMyLocation().getX()!=x || theWorld.getpopList().get(j).getMyLocation().getY()!=y) {
					theWorld.getpopList().add( new Healthy( new Location(x,y), theWorld ));
				}
			}
		}
		theWorldCanvas = this.getGCanvas();
	}
	
	public void runWorld(){
		drawWorld();
		for(int i=0; i<50;i++){
			theWorld.letTimePass(theWorld);
			pause(400);
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
