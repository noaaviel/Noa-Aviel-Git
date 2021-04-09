//HMW01 318323391 NOA AVIEL
package PhoneBook;

import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		ContactList list = new ContactList();
		Scanner s = new Scanner(System.in);
		try {
			menuGenerator(list, s);
			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Menu
	private static void menuGenerator(ContactList list, Scanner s) throws Exception {
		boolean isFinished = false;
		char op;
		int index;
		while (!isFinished) {
			System.out.println("*** Contact Crator: ***");
			System.out.println("1. Add / Update Contact ");
			System.out.println("2. Remove Contact ");
			System.out.println("3. Search Contact By Name ");
			System.out.println("4. Save Contact To File ");
			System.out.println("5. Load Contact From File ");
			System.out.println("6. Print All Contacts ");
			System.out.println("Choose Your Option Or Press Any Other Key To Exit ");
			System.out.println("Your Option: ");
			op = s.nextLine().charAt(0);
			switch (op) {
			case '1':
				System.out.println("Add / Update Contact: ");
				int value = -1;
				try {
					value = list.addC(new Contact(getFirstNameFromUser(s), getLastNameFromUser(s),
							getPhoneNumberFromUser(s), getHomeNumberFromUser(s)));
				} catch (Exception e) {
				}
				if (value <= -1) {
					System.out.println("Error !!!! Contact Was Not Saved!");
				} else if (value == 1) {
					System.out.println("Contact Added");
				} else {
					System.out.println("Contact Updated");
				}
				break;
			case '2':
				System.out.println("Remove Contact:");
				index = list.searchByName(getFirstNameFromUser(s), getLastNameFromUser(s));
				if (index >= 0) {
					System.out.println(list.getIndex(index));
					System.out.println("Are You Sure? y/n");
					op = s.nextLine().charAt(0);
					if (op == 'y') {
						list.remove(index);
					} else
						System.out.println("Operation Canceled");
				} else {
					System.out.println("Contact Not Found");
				}

				break;
			case '3':
				System.out.println("Search Contact : ");
				index = list.searchByName(getFirstNameFromUser(s), getLastNameFromUser(s));
				if (index >= 0) {
					System.out.println(list.getIndex(index));
				} else {
					System.out.println("Contact Not Found");
				}
				break;
			case '4':
				list.saveContacts();
				System.out.println("Contacts Saved !!");
				break;
			case '5':
				list.loadContactFromFile();
				break;
			case '6':
				System.out.println(list);
				break;
			default:
				isFinished = true;
				break;
			}
		}
	}

	//methods to get fields values from user according to contact constructor
	private static String getFirstNameFromUser(Scanner s) throws Exception {
		System.out.println("Enter Contact First Name: ");
		return s.nextLine();
	}

	private static String getLastNameFromUser(Scanner s) {
		System.out.println("Enter Contact Last Name:");
		return s.nextLine();
	}

	private static String getPhoneNumberFromUser(Scanner s) {
		System.out.println("Enter Contact Phone Number:");
		return s.nextLine();
	}

	private static String getHomeNumberFromUser(Scanner s) {
		System.out.println("Enter Contact Home Number :");
		return s.nextLine();
	}

}
