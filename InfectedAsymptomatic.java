import java.awt.Color;

public class InfectedAsymptomatic extends Person {
	
	public InfectedAsymptomatic(Location loc, World w) {
		super(loc,w);
		this.myColor = Color.YELLOW;
		this.myType = 1;
		this.myTsi = 0;
	}
}
