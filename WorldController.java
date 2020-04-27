import java.awt.Color;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class WorldController extends GraphicsProgram {

	private World theWorld;
	private GCanvas theWorldCanvas;
	public static final int APPLICATION_WIDTH = 1420;
	public static final int APPLICATION_HEIGHT = 1000;
	GLabel iterationLabel;
	GLabel popLabel;
	GLabel infLabel;
	GLabel symLabel;
	GLabel immLabel;
	GLabel deaLabel;
	GLabel dtrLabel;
	GLabel ctcLabel;
	GLabel orpLabel;
	int iteration;
	int cases;
	int prevCases;
	int origPop;
	int totalCases;
	int originX;
	int originY;
	int prevX;
	int prevY;
	
	public void run(){	
		createGraph();
		createLabels();
		setUpPeople();
		runWorld();
	}
	
	public void init(){
	    resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	    theWorld = new World(60,60);
		theWorldCanvas = this.getGCanvas();
	}
	
	public void createGraph() {
		originX = 870;
		originY = 400;
		GLine xAxis = new GLine(originX,originY,1400,originY);
		GLine yAxis = new GLine(originX,originY,originX,20);
		theWorldCanvas.add(xAxis);
		theWorldCanvas.add(yAxis);
	}
	
	public void createLabels() {
		iterationLabel = new GLabel("Iteration #",(double)(theWorld.getWidth()*10+10),15);
		iterationLabel.setColor(Color.DARK_GRAY);
		popLabel = new GLabel("Current Population:",(double)(theWorld.getWidth()*10+10),75);
		popLabel.setColor(Color.BLACK);
		ctcLabel = new GLabel("Current cases:",(double)(theWorld.getWidth()*10+10),95);
		ctcLabel.setColor(Color.ORANGE);
		infLabel = new GLabel("Total Cases:",(double)(theWorld.getWidth()*10+10),115);
		infLabel.setColor(Color.BLACK);
		symLabel = new GLabel("Percent symptomatic:",(double)(theWorld.getWidth()*10+10),195);
		symLabel.setColor(Color.BLACK);
		immLabel = new GLabel("Percent of population with immunity:",(double)(theWorld.getWidth()*10+10),215);
		immLabel.setColor(Color.GREEN);
		deaLabel = new GLabel("Deaths:",(double)(theWorld.getWidth()*10+10),155);
		deaLabel.setColor(Color.RED);
		dtrLabel = new GLabel("Death Rate:",(double)(theWorld.getWidth()*10+10),175);
		dtrLabel.setColor(Color.RED);
		orpLabel = new GLabel("Starting Population:",(double)(theWorld.getWidth()*10+10),55);
		orpLabel.setColor(Color.BLACK);
		theWorldCanvas.add(iterationLabel);
		theWorldCanvas.add(popLabel);
		theWorldCanvas.add(infLabel);
		theWorldCanvas.add(symLabel);
		theWorldCanvas.add(immLabel);
		theWorldCanvas.add(deaLabel);
		theWorldCanvas.add(dtrLabel);
		theWorldCanvas.add(ctcLabel);
		theWorldCanvas.add(orpLabel);
	}
	
	public void setUpPeople(){
		int x1 = 10;
		int y1 = 10;
		theWorld.getpopList().add( new InfectedAsymptomatic( new Location(x1,y1), theWorld ));
		cases = 1;
		for(int i=0; i<1000; i++) {
			double num0 = Math.random();
			double num1 = Math.random();
			int x = (int)(num0*theWorld.getWidth());
			int y = (int)(num1*theWorld.getHeight());
			boolean found = false;
			for(int j=0; j<theWorld.getpopList().size(); j++) {
				if(theWorld.getpopList().get(j).getMyLocation().getX()==x && theWorld.getpopList().get(j).getMyLocation().getY()==y) {
					found = true;
				}
			}
			if(found==false) {
				theWorld.getpopList().add( new Healthy( new Location(x,y), theWorld )); 
			}
		}
		origPop = theWorld.getpopList().size();
	}
	
	public void runWorld(){
		drawWorld();
		iteration = 0;
		prevCases = 0;
		while(cases>0){
			iteration++;
			int n = theWorld.letTimePass(theWorld);
			totalCases += n;
			recordData();
			updateGraph();
			pause(300);
			drawWorld();
			prevCases = cases;
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
	
	public void recordData() {
		int pop = theWorld.getpopList().size();
		cases = 0;
		int healthy = 0;
		int infAsympt = 0;
		int infSympt = 0;
		int immune = 0;
		for(int i=0; i<theWorld.getpopList().size(); i++) {
			if(theWorld.getpopList().get(i).getMyType()==0) {
				infSympt++;
				cases++;
			}
			if(theWorld.getpopList().get(i).getMyType()==1) {
				infAsympt++;
				cases++;
			}
			if(theWorld.getpopList().get(i).getMyType()==2) {
				healthy++;
			}
			if(theWorld.getpopList().get(i).getMyType()==3) {
				immune++;
			}
		}
		String itr = "Iteration #  "+iteration+"";
		String population = "Current Population: "+pop+"";
		String infections = "Total cases: "+totalCases+"";
		String symptomatic = "Percent symptomatic: "+(int)(((double)infSympt/(double)cases)*100)+"%";
		String immunity = "Percent of population with immunity: "+(int)(((double)immune/(double)pop)*100)+"%";
		String deaths = "Deaths: "+(origPop-pop)+"";
		String deathRate = "Death Rate: "+(int)(((double)(origPop-pop)/(double)totalCases)*100)+"%";
		String currentCases = "Current Cases: "+cases+"";
		String originalPop = "Starting Population: "+origPop+"";
		iterationLabel.setLabel(itr);
		popLabel.setLabel(population);
		infLabel.setLabel(infections);
		symLabel.setLabel(symptomatic);
		immLabel.setLabel(immunity);
		deaLabel.setLabel(deaths);
		dtrLabel.setLabel(deathRate);
		ctcLabel.setLabel(currentCases);
		orpLabel.setLabel(originalPop);
	}
	
	public void updateGraph() {
		GLine plot;
		if(iteration<=1) {
			 plot = new GLine (originX+iteration,originY-cases,originX+iteration,originY-cases);
			 prevX = originX+iteration;
			 prevY = originY-cases;
			 plot.setColor(Color.RED);
			 theWorldCanvas.add(plot);
		}
		else {
			plot = new GLine (prevX,prevY,prevX+2,prevY-(cases-prevCases));
			prevX = prevX+2;
			prevY = prevY-(cases-prevCases);
			plot.setColor(Color.RED);
			theWorldCanvas.add(plot);
		}
	}
	
}


