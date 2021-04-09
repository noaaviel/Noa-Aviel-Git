package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Program {
	
	public static void main(String[] args) {
		// define dynamic array of Rooms and Cleanables
		ArrayList<Room> allRooms = new ArrayList<>();
		ArrayList<Cleanable> allToBeCleaned = new ArrayList<>();
		
		int roomNumber = 1;
		Scanner s = new Scanner(System.in);
		int option;
		
		menu();
		option = s.nextInt();
		while(option!=0)
		{
			switch(option) {
			case 1: // add object
				int kind;
				System.out.println("To add class room press 1");
				System.out.println("To add computer lab press 2");
				System.out.println("To add office room press 3");
				System.out.println("To add caffeteria press 4");
				kind = s.nextInt();
				switch(kind) {
				case 1: // class room
					ClassRoom cr = new ClassRoom(roomNumber++, 20);
					// cr is a ROOM so copy it to allRooms 
					allRooms.add(cr);
					// cr is Cleanable so copy it to allToBeCleaned
					allToBeCleaned.add(cr);
					break;
				case 2: // ComputerLab
					ComputerLab cl = new ComputerLab(roomNumber++,15);
					allRooms.add(cl);
					allToBeCleaned.add(cl);
					break;
				case 3: // office room
					OfficeRoom or = new OfficeRoom(roomNumber++);
					allRooms.add(or);
					break;
				case 4:// Cafeteria
					Cafeteria cf = new Cafeteria();
					allToBeCleaned.add(cf);
					break;
				} // switch
				
				break;
			case 2: // book a room
				int kindOfBooking;
				System.out.println("To book class room press 1");
				System.out.println("To book computer lab press 2");
				System.out.println("To book office room press 3");
				kindOfBooking = s.nextInt();
				switch(kindOfBooking) {
				case 1: // class room
					
					
					break;
				case 2: // ComputerLab
					ComputerLab cl = new ComputerLab(roomNumber++,15);
					allRooms.add(cl);
					allToBeCleaned.add(cl);
					break;
				case 3: // office room
					OfficeRoom or = new OfficeRoom(roomNumber++);
					allRooms.add(or);
					break;
				case 4:// Cafeteria
					Cafeteria cf = new Cafeteria();
					allToBeCleaned.add(cf);
					break;
				} // switch
				
				break;
			}// outer switch
			// show all Rooms and all cleanables
			
			System.out.println("Rooms are:");
//			Collections.shuffle(allRooms);
			allRooms.sort(null);
			Collections.reverse(allRooms);
			
			show(allRooms);
			System.out.println("Cleanables are:");
			show(allToBeCleaned);
			
			menu();
			option = s.nextInt();
		}
		
		
	}
	
	public static void show(ArrayList arr) {
		for(int i =0; i<arr.size();i++)
			System.out.println(arr.get(i));
	}
	
	public static void menu() {
		System.out.println("To add object press 1, to book a room press 2");
		System.out.println("To release a room press 3, to clean object press 4");
		System.out.print("To exit press 0. What is your choice? ");
	}

}
