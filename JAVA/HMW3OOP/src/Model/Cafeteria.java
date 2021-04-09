package Model;
public class Cafeteria implements Cleanable {
	private boolean isCfeteriaClean;

	public Cafeteria() {
		isCfeteriaClean = true;

	}
	
	@Override
	public boolean isClean() {
		return isCfeteriaClean;
	}


	@Override
	public void clean() {
		if (!isCfeteriaClean) {
			this.isCfeteriaClean = true;
		}
	}
	
	public String toString() {
		return "This is Cafeteria." + "is it clean? "+ isClean();
	}

}
