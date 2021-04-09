package Model;
public class OfficeRoom extends Room {
	public final int MAX_PEOPLE = 2;
	private String []names;
	private int numPeople;
	
	public OfficeRoom() {
		super();
	}
	
	public OfficeRoom(int num) {
		super(num);
		names = new String[MAX_PEOPLE];
		numPeople = 0;
	}
	
	@Override
	public void useIt() {
		if (!isInUse) {
			this.isInUse = true;
		}
	}
	
	public String toString()
	{
		String str = super.toString() + " numPeople: "+numPeople;
		for(int i=0;i<numPeople;i++)
			str+= names[i]+ " ";
				
		return str;
	}

	@Override
	public void releaseIt() {
		// TODO Auto-generated method stub
		
	}

}
