import java.awt.Color;

public class InfectedSymptomatic extends Person {
	
	public InfectedSymptomatic(Location loc, World w) {
		super(loc,w);
		this.myColor = Color.RED;
		this.myType = 0;
		this.myTsi = 0;
	}
}
