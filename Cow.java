import java.awt.Color;

public class Cow extends LifeForm{
	
	public Cow(Location l, World w) {
		super(l,w);
		myLifeSpan = 1;
		myColor = Color.black;
	}
	
	public void graze(int i) {
		int myX = myWorld.getCreatureList().get(i).getMyLocation().getX();
		int myY = myWorld.getCreatureList().get(i).getMyLocation().getY();
		Location loc = new Location(myX+1, myY+1);
		myWorld.getCreatureList().get(i).setMyLocation(loc);
	}
	
	public void reproduce() {
		int newX = (int)(Math.random()*5);
		int newY = (int)(Math.random()*5); 
		
		myWorld.getCreatureList().add(new Cow(new Location(newX,newY), myWorld));
	}
	
}
