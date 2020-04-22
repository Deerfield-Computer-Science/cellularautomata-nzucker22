import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class WorldController extends GraphicsProgram {
	
	private World theWorld;
	private GCanvas theWorldCanvas;
	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 1000;
	GLabel itrLabel;
	GLabel popLabel;
	GLabel infLabel;
	GLabel symLabel;
	GLabel immLabel;
	
	public void run(){	
		setUpWorld();
		runWorld();
	}
	
	public void init(){
	    resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void setUpWorld(){
		theWorld = new World(60,60);
		theWorldCanvas = this.getGCanvas();
		itrLabel = new GLabel("Iteration #",(double)(theWorld.getWidth()*10+10),15);
		itrLabel.setColor(Color.DARK_GRAY);
		popLabel = new GLabel("Population:",(double)(theWorld.getWidth()*10+10),35);
		popLabel.setColor(Color.BLACK);
		infLabel = new GLabel("Total Cases:",(double)(theWorld.getWidth()*10+10),55);
		infLabel.setColor(Color.ORANGE);
		symLabel = new GLabel("Percent symptomatic:",(double)(theWorld.getWidth()*10+10),75);
		symLabel.setColor(Color.RED);
		immLabel = new GLabel("Percent of population with immunity:",(double)(theWorld.getWidth()*10+10),95);
		immLabel.setColor(Color.GREEN);
		theWorldCanvas.add(itrLabel);
		theWorldCanvas.add(popLabel);
		theWorldCanvas.add(infLabel);
		theWorldCanvas.add(symLabel);
		theWorldCanvas.add(immLabel);
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
	}
	
	public void runWorld(){
		drawWorld();
		for(int i=0; i<200;i++){
			theWorld.letTimePass(theWorld);
			recordData(i);
			pause(300);
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
	
	public void recordData(int itr) {
		int pop = theWorld.getpopList().size();
		int healthy = 0;
		int totInfected = 0;
		int infAsympt = 0;
		int infSympt = 0;
		int immune = 0;
		for(int i=0; i<theWorld.getpopList().size(); i++) {
			if(theWorld.getpopList().get(i).getMyType()==0) {
				infSympt++;
				totInfected++;
			}
			if(theWorld.getpopList().get(i).getMyType()==1) {
				infAsympt++;
				totInfected++;
			}
			if(theWorld.getpopList().get(i).getMyType()==2) {
				healthy++;
			}
			if(theWorld.getpopList().get(i).getMyType()==3) {
				immune++;
			}
		}
		String iteration = "Iteration #  "+itr+"";
		String population = "Population: "+pop+"";
		String infections = "Total cases: "+totInfected+"";
		String symptomatic = "Percent symptomatic: "+(int)(((double)infSympt/(double)totInfected)*100)+"%";
		String immunity = "Percent of population with immunity: "+(int)(((double)immune/(double)pop)*100)+"%";
		itrLabel.setLabel(iteration);
		popLabel.setLabel(population);
		infLabel.setLabel(infections);
		symLabel.setLabel(symptomatic);
		immLabel.setLabel(immunity);
	}
	
}


