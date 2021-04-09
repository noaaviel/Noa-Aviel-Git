package Model;

public class Song extends BroadcastCon {
	
	protected String bandName;
	
	public Song() {
		super();
		bandName = "Pantera";
	}
	
	public Song(String title, int start , int duration,String bandName) throws InvalidInput {
		super(title,start,duration);
		this.bandName = bandName;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	@Override
	public String toString() {
		return super.toString() +"Song [bandName=" + bandName + "]";
	}
	
	

}
