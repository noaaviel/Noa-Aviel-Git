package Model;

public class BroadcastCon {
	private final int MAX_DURATION = 60;
	private String title;
	private int start;
	private int duration;
	
	public BroadcastCon() {
		title = "0";
		start = 0;
		duration = 0;
	}
	public BroadcastCon(String title, int start,int duration) throws InvalidInput {
		setTitle(title);
		
	/*	if (start+duration > MAX_DURATION || duration-start<0 && duration-start>60) {
			throw new InvalidInput ("Broadcast is loger than an hour");
		}
		setStart(start);
		setDuration(duration);
		
		*/
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDuration() throws InvalidInput{
		return duration;
	}

	public void setDuration(int duration)throws InvalidInput {
		if(duration<0 || duration>60) {
			throw new InvalidInput("duration must be between 0-60");
		}
		this.duration = duration;
		
	}
	@Override
	public String toString() {
		return "Broadcast [title=" + title + ", start=" + start + ", duration=" + duration + "]";
	}
	
	

}
