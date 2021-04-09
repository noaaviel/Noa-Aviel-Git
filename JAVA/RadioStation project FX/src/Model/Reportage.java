package Model;

public class Reportage extends BroadcastCon {
	
	protected String author;

	public String getAuthor() {
		return author;
	}
	
	public Reportage() {
		super();
		author = "";
	}
	public Reportage(String title, int start , int duration,String author) throws InvalidInput {
		super(title,start,duration);
		this.author = author;
		
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return super.toString() + "Reportage [author=" + author + "]";
	}
	
	
	
	
	
}
