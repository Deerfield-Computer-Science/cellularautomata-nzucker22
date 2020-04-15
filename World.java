import java.util.ArrayList;

public class World {
	
	private int width;
	private int height;
	private ArrayList<Person> popList;
	
	public World(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.popList = new ArrayList<Person>();
	}
	
	public void letTimePass(World theWorld){
		makeMovements();
		spread(theWorld);
//		purgeTheDead();		
	}
	
	public void makeMovements() {
		
	}
	
	public void spread(World theWorld) {
		for(int i=0; i<popList.size(); i++) {
			if(popList.get(i).getMyType()==2) {
				int x = popList.get(i).getMyLocation().getX();
				int y = popList.get(i).getMyLocation().getY();
				for(int j=0; j<popList.size(); j++) {
					if(popList.get(j).getMyType()==0 || popList.get(j).getMyType()==1) {
						if((popList.get(j).getMyLocation().getX()==x-1 && (popList.get(j).getMyLocation().getY()==y-1 || popList.get(j).getMyLocation().getY()==y || popList.get(j).getMyLocation().getY()==y+1))
						|| (popList.get(j).getMyLocation().getX()==x && (popList.get(j).getMyLocation().getY()==y-1 || popList.get(j).getMyLocation().getY()==y+1))
						|| (popList.get(j).getMyLocation().getX()==x+1 && (popList.get(j).getMyLocation().getY()==y-1 || popList.get(j).getMyLocation().getY()==y || popList.get(j).getMyLocation().getY()==y+1)))
						{
							double prob = Math.random();
							int age = popList.get(i).getMyAge();
							popList.remove(i);
							if(age==0) {
								if(prob<0.2) {
									popList.add(new InfectedSymptomatic(new Location(2,0),theWorld));
								}
								else {
									popList.add(new InfectedAsymptomatic(new Location(2,0),theWorld));
								}
							}
							if(age==1) {
								if(prob<0.6) {
									popList.add(new InfectedSymptomatic(new Location(4,0),theWorld));
								}
								else {
									popList.add(new InfectedAsymptomatic(new Location(4,0),theWorld));
								}
							}
							if(age==2) {
								if(prob<0.95) {
									popList.add(new InfectedSymptomatic(new Location(6,0),theWorld));
								}
								else {
									popList.add(new InfectedAsymptomatic(new Location(6,0),theWorld));
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void purgeTheDead(){
		int i=0;
		while(i<popList.size()){
			if(popList.get(i).isDead())
				popList.remove(i);
			else
				i++;
		}	
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public ArrayList<Person> getpopList() {
		return popList;
	}
	public void setpopList(ArrayList<Person> popList) {
		this.popList = popList;
	}

	@Override
	public String toString() {
		return "World [width=" + width + ", height=" + height
				+ ", popList=" + popList + "]";
	}
}
