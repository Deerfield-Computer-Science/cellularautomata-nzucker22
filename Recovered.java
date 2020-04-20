import java.awt.Color;

public class Recovered extends Person {
	
	public Recovered(Location loc, World w) {
		super(loc,w);
		this.myColor = Color.GREEN;
		this.myType = 3;
	}
}