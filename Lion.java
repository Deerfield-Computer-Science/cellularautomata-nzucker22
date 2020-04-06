import java.awt.Color;

public class Lion extends LifeForm{

	public Lion(Location l, World w) {
		super(l,w);
		myLifeSpan = 2;
		myColor = Color.orange;
	}
	
	public void eatCow(int i) {
		if(myWorld.getCreatureList().get(i).getMyColor()==Color.black) {
			myWorld.getCreatureList().remove(i);	
		}
	}
	
	public void reproduce() {
		int newX = (int)(Math.random()*20);
		int newY = (int)(Math.random()*20); 
		
		myWorld.getCreatureList().add(new Lion(new Location(newX,newY), myWorld));
	}
}
