import java.awt.Color;
import java.util.ArrayList;

public abstract class Person {
	
	protected World myWorld;
	protected Location myLocation;
	protected Color myColor;
	protected int myType;
	protected int myAge;
	protected boolean alive;
	
	
	
	
	public Person(World myWorld, Location myLocation) {
		super();
		this.myWorld = myWorld;
		this.myLocation = myLocation;
		this.myAge = (int)(Math.random()*3);
		alive = true;
	}
	
	public abstract void move();
	
	public boolean isDead(){
		return !alive;
	}

	public World getMyWorld() {
		return myWorld;
	}

	public void setMyWorld(World myWorld) {
		this.myWorld = myWorld;
	}

	public Location getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(Location myLocation) {
		this.myLocation = myLocation;
	}

	public Color getMyColor() {
		return myColor;
	}

	public void setMyColor(Color myColor) {
		this.myColor = myColor;
	}

	public int getMyType() {
		return myType;
	}

	public void setMyType(int myType) {
		this.myType = myType;
	}

	public int getMyAge() {
		return myAge;
	}

	public void setMyAge(int myAge) {
		this.myAge = myAge;
	}

	@Override
	public String toString() {
		return "Person [myType=" + myType + ", myAge=" + myAge + ", myLocation="
				+ myLocation + ", myColor=" + myColor + "]";
	}
}
