import java.awt.Color;

public class Healthy extends Person {
	
	public Healthy(Location loc, World w) {
		super(loc,w);
		this.myColor = Color.LIGHT_GRAY;
		this.myType = 2;
	}
	
	public void move() {
		
	}
}