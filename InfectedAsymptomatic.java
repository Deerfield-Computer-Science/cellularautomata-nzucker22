import java.awt.Color;

public class InfectedAsymptomatic extends Person {
	
	public InfectedAsymptomatic(World w, Location loc) {
		super(w,loc);
		this.myColor = Color.ORANGE;
		this.myType = 1;
	}
	
	public void move() {
		
	}

}
