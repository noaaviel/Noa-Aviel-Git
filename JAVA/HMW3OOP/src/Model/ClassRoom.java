package Model;
public class ClassRoom extends Room implements Cleanable{
	private boolean isRoomClean;
	private int numSeats;

	public ClassRoom() {
		super();
	}
	public ClassRoom(int num, int seats) {
		super(num);
		setNumSeats(seats);
		isRoomClean = true;// any ClassRoom when it is instantiated is clean
	}
	
	public void setNumSeats(int seats) {
		numSeats = seats;
	}

	@Override
	public void useIt() {
		if (isInUse) {
			this.isInUse = true;
		}
	}

	@Override
	public boolean isClean() {
		return isRoomClean;
	}

	@Override
	public void clean() {
		if (!isRoomClean) {
			this.isRoomClean = true;
		}
	}
	
	public String toString() {
		return super.toString()+ " Num seats: "+numSeats+
				" Is Clean? "+ isClean();
	}

	@Override
	public void releaseIt() {
		// TODO Auto-generated method stub
	}
	


}
