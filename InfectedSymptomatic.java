import java.awt.Color;

public class InfectedSymptomatic extends Person {
	
	public InfectedSymptomatic(World w, Location loc) {
		super(w,loc);
		this.myColor = Color.RED;
		this.myType = 0;
	}
	
	public void move() {
		
	}

}
