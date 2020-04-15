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
//		makeMovements();
		spread(theWorld);
//		purgeTheDead();		
	}
	
//	public void makeMovements() {
//		
//	}
	
	public void spread(World theWorld) {
		for(int i=0; i<popList.size(); i++) {
			if(popList.get(i).getMyType()==0 || popList.get(i).getMyType()==1) {
				for(int j=0; j<popList.size(); j++) {
					int x1 = popList.get(i).getMyLocation().getX()-1;
					int y1 = popList.get(i).getMyLocation().getY()-1;
						if(popList.get(j).getMyType()==2 && popList.get(j).getMyLocation().getX()==x1 && popList.get(j).getMyLocation().getY()==y1) {
							swapHealthy(x1,y1,j,theWorld);	
						}
					int x2 = popList.get(i).getMyLocation().getX();
					int y2 = popList.get(i).getMyLocation().getY()-1;
						if(popList.get(j).getMyType()==2 && popList.get(j).getMyLocation().getX()==x2 && popList.get(j).getMyLocation().getY()==y2) {
							swapHealthy(x2,y2,j,theWorld);	
						}
					int x3 = popList.get(i).getMyLocation().getX()+1;
					int y3 = popList.get(i).getMyLocation().getY()-1;
						if(popList.get(j).getMyType()==2 && popList.get(j).getMyLocation().getX()==x3 && popList.get(j).getMyLocation().getY()==y3) {
							swapHealthy(x3,y3,j,theWorld);	
						}
					int x4 = popList.get(i).getMyLocation().getX()-1;
					int y4 = popList.get(i).getMyLocation().getY();
						if(popList.get(j).getMyType()==2 && popList.get(j).getMyLocation().getX()==x4 && popList.get(j).getMyLocation().getY()==y4) {
							swapHealthy(x4,y4,j,theWorld);	
						}
					int x5 = popList.get(i).getMyLocation().getX()+1;
					int y5 = popList.get(i).getMyLocation().getY();
						if(popList.get(j).getMyType()==2 && popList.get(j).getMyLocation().getX()==x5 && popList.get(j).getMyLocation().getY()==y5) {
							swapHealthy(x5,y5,j,theWorld);	
						}
					int x6 = popList.get(i).getMyLocation().getX()-1;
					int y6 = popList.get(i).getMyLocation().getY()+1;
						if(popList.get(j).getMyType()==2 && popList.get(j).getMyLocation().getX()==x6 && popList.get(j).getMyLocation().getY()==y6) {
							swapHealthy(x6,y6,j,theWorld);	
						}
					int x7 = popList.get(i).getMyLocation().getX();
					int y7 = popList.get(i).getMyLocation().getY()+1;
						if(popList.get(j).getMyType()==2 && popList.get(j).getMyLocation().getX()==x7 && popList.get(j).getMyLocation().getY()==y7) {
							swapHealthy(x7,y7,j,theWorld);	
						}
					int x8 = popList.get(i).getMyLocation().getX()+1;
					int y8 = popList.get(i).getMyLocation().getY()+1;
						if(popList.get(j).getMyType()==2 && popList.get(j).getMyLocation().getX()==x8 && popList.get(j).getMyLocation().getY()==y8) {
							swapHealthy(x8,y8,j,theWorld);	
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
	
	public void swapHealthy(int x, int y, int j, World theWorld) {
		double prob = Math.random();
		int age = popList.get(j).getMyAge();
		popList.remove(j);
		if(age==0) {
			if(prob<0.2) {
				popList.add(new InfectedSymptomatic(new Location(x,y),theWorld));
			}
			else {
				popList.add(new InfectedAsymptomatic(new Location(x,y),theWorld));
			}
		}
		if(age==1) {
			if(prob<0.6) {
				popList.add(new InfectedSymptomatic(new Location(x,y),theWorld));
			}
			else {
				popList.add(new InfectedAsymptomatic(new Location(x,y),theWorld));
			}
		}
		if(age==2) {
			if(prob<0.95) {
				popList.add(new InfectedSymptomatic(new Location(x,y),theWorld));
			}
			else {
				popList.add(new InfectedAsymptomatic(new Location(x,y),theWorld));
			}
		}
	}
}
