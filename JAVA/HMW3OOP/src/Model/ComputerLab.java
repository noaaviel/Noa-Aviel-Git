package Model;
public class ComputerLab extends Room implements Cleanable {
	private int numComputers;
	private boolean isLabClean;
	
	public ComputerLab() {
		super();
	}
	public ComputerLab(int num, int computers) {
		super(num);
		setNumComputers(computers);
		isLabClean = true;
	}
	
	public void setNumComputers(int computers) {
		numComputers = computers;
	}

	@Override
	public boolean isClean() {
		return isLabClean;
	}

	@Override
	public void clean() {
		if (isLabClean) {
			this.isLabClean = true;
		}
	}

	@Override
	public void useIt() {
		if (!isInUse) {
			this.isInUse = true;
		}
	}
	
	public String toString() {
		return super.toString()+ " Num computers: "+numComputers+
				" Is Clean? "+ isClean() + "Is In Use? " + isInUse();
	}

	@Override
	public void releaseIt() {
		// TODO Auto-generated method stub
		
	}

}
