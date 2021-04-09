package Model;

public abstract class Room implements Comparable<Room>{
	protected int number;
	protected boolean isInUse;
	
	public Room() {
		this(0);
	}
	
	public Room(int num){
		number = num;
		isInUse = false;
	}
	
	public abstract void useIt(); // ABSTRACT METHOD
	public abstract void releaseIt();
	

	public void addToList() {
	
	}
	@Override
	public int compareTo(Room other) {
		return number - other.number; // 
	}

	public boolean isInUse() {
		return isInUse;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setInUse(boolean isInUse) {
		this.isInUse = isInUse;
	}
	public String toString(){
		return "Room number: "+number+ " is room in use? "+ isInUse; 
	}
	
}
