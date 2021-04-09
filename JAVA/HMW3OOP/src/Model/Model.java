package Model;

import java.util.ArrayList;

public class Model {
	ArrayList<Cleanable> allToBeCleaned;
	ArrayList<Room> allRooms;
	
	public Model() {
		allRooms = new ArrayList<>();
		allToBeCleaned = new ArrayList<>();
	}

	public void updateModel(int kindOfModel, boolean isInUse, boolean IsClean) {
		if (kindOfModel == 1) {
			ClassRoom cr = new ClassRoom();
			allRooms.add(cr);
			allToBeCleaned.add(cr);
			allRooms.get(allRooms.size() -1).setInUse(isInUse);
			allToBeCleaned.get(allToBeCleaned.size() -1).isClean();
		}else if(kindOfModel == 2) {
			ComputerLab cl = new ComputerLab();
			allRooms.add(cl);
			allToBeCleaned.add(cl);
			allRooms.get(allRooms.size() -1).setInUse(isInUse);
			allToBeCleaned.get(allToBeCleaned.size() -1).isClean();
		}else if(kindOfModel == 3) {
			Cafeteria caf = new Cafeteria();
			allToBeCleaned.add(caf);
			allToBeCleaned.get(allToBeCleaned.size() -1).isClean();
		}else {
			OfficeRoom or = new OfficeRoom();
			allRooms.add(or);
			allRooms.get(allRooms.size() -1).setInUse(isInUse);
		}
	}
	
	

}
