import java.awt.Color;
import java.util.ArrayList;

public abstract class Person {
	
	protected World myWorld;
	protected Location myLocation;
	protected Color myColor;
	//edit
	protected int myTsi;
	protected int myType;
	protected int myAge;
//	protected int myJob;
	protected boolean alive;
	
	public Person(Location myLocation, World myWorld) {
		super();
		this.myLocation = myLocation;
		this.myWorld = myWorld;
		this.myAge = (int)(Math.random()*3);
//		if(myAge==0) {
//			myJob=0;
//		}
//		if(myAge==1) {
//			double prob = Math.random();
//		}
//		if(myAge==3) {
//			myJob=65;
//		}
		alive = true;
	}
	
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

	public int getMyTsi() {
		return myTsi;
	}

	public void setMyTsi(int myTsi) {
		this.myTsi = myTsi;
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
