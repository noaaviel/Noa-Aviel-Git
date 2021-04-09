package Model;

public class Interview extends BroadcastCon {
	protected int numOfParticipents;
	protected String author;
	
	public Interview() {
		super();
		numOfParticipents=1;
		author = "";
		
	}
	public Interview(String title, int start , int duration,String author, int numOfParticipents) throws InvalidInput {
		super(title,start,duration);
		this.author = author;
		this.numOfParticipents= numOfParticipents;
	}
	
	public int getNumOfParticipents() {
		return numOfParticipents;
	}
	
	public void setNumOfParticipents(int numOfParticipents) {
		this.numOfParticipents = numOfParticipents;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String interviewer) {
		this.author = interviewer;
	}
	@Override
	public String toString() {
		return super.toString() +"Interview [numOfParticipents=" + numOfParticipents + ", author=" + author + "]";
	}
	
	
	
	

}
