package Model;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Model {
	private BroadcastCon construction;
	private ArrayList<BroadcastCon> allBroadCasts;
	private ArrayList<Rectangle> allRectangles;

	public Model() {
		construction = new Reportage();
		allBroadCasts = new ArrayList<>();
		allRectangles = new ArrayList<>();
	}

	public BroadcastCon getConstruction() {
		return construction;
	}

	public ArrayList<BroadcastCon> getAllBroadCasts() {
		return allBroadCasts;
	}

	public void setAllBroadCasts(ArrayList<BroadcastCon> allBroadCasts) {
		this.allBroadCasts = allBroadCasts;
	}

	public ArrayList<Rectangle> getAllRectangles() {
		return allRectangles;
	}

	public void setAllRectangles(ArrayList<Rectangle> allRectangles) {
		this.allRectangles = allRectangles;
	}
	

}



