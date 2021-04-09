//HMW2 318323391 NOA AVIEL
package PhoneBook;

import java.util.Collections;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		ContactList list = new ContactList();
		Scanner s = new Scanner(System.in);

		try {
			loadMenu(list, s);
		} catch (Exception e) {
		}
	}
//menu method
	private static void loadMenu(ContactList list, Scanner s) {
		boolean isFinished = false;

		while (!isFinished) {
			System.out.println("CONTACT CREATOR:");
			System.out.println("1. Add/Update Contact");
			System.out.println("2. Remove Contact");
			System.out.println("3. Search Contact");
			System.out.println("4. Sort Contact ");
			System.out.println("5. Save Contacts To File");
			System.out.println("6. Load Contacts From File");
			System.out.println("7. Print All Contacts");
			System.out.println("Choose Your Option Or Press Any Key To EXIT");
			System.out.print("Your Option: ");
			char op = s.next().charAt(0);
			try {
				switch (op) {
				case '1':
					add(list, s);
					break;
				case '2':
					remove(list, s);
					break;
				case '3':
					search(list, s);
					break;
				case '4':
					sort(list, s);
					break;
				case '5':
					list.saveContacts();
					break;
				case '6':
					list.loadContacts();
					break;
				case '7':
					System.out.println(list);
					break;
				default:
					isFinished = true;
					break;
				}
			} catch (Exception e) {
			}
		}
	}
//sort by fields method
	private static void sort(ContactList list, Scanner s) {
		System.out.println("SORT CONTACTS:");
		System.out.println("1. By Full Name");
		System.out.println("2. By Phone Number");
		System.out.println("3. By Home Number");
		System.out.println("Choose Your Option Or Press Any Key To EXIT");
		System.out.print("Your Option: ");
		char op = s.next().charAt(0);
		switch (op) {
		case '1':
			list.setComparator(new SortByName());
			Collections.sort(list, new SortByName());
			System.out.println("List Sorted ! :) ");
			System.out.println(list);
			break;
		case '2':
			list.setComparator(new SortByPhoneNumber());
			Collections.sort(list, new SortByPhoneNumber());
			System.out.println("List Sorted ! :) ");
			System.out.println(list);
			break;
		case '3':
			list.setComparator(new SortByHomeNumber());
			Collections.sort(list, new SortByHomeNumber());
			System.out.println("List Sorted ! :) ");
			System.out.println(list);
			break;
		default:
			break;
		}
		list.sort();
	}
//search by fields method
	private static void search(ContactList list, Scanner s) throws Exception {
		System.out.println("SEARCH CONTACTS:");
		System.out.println("1. By Name");
		System.out.println("2. By Phone Number");
		System.out.println("3. By Home Number");
		System.out.println("Choose Your Option Or Press Any Key To EXIT");
		System.out.print("Your Option: ");
		char op = s.next().charAt(0);
		Contact c = null;
		int index = -1;
		switch (op) {
		case '1':
			String name = getFirstNameFromUser(s);
			String fname = getLastNameFromUser(s);
			c = new Contact(name, fname, "", "");
			Collections.sort(list, new SortByName());// sort for binary search
			index = Collections.binarySearch(list, c, new SortByName());// found index by binarySearch
			c = list.get(index);
			break;
		case '2':
			c = new Contact("", "", getPhoneNumberFromUser(s), "");
			Collections.sort(list, new SortByPhoneNumber());// sort for binary search
			index = Collections.binarySearch(list, c, new SortByPhoneNumber());// found index by binarySearch
			c = list.get(index);
			break;
		case '3':
			c = new Contact("", "", "", getHomeNumberFromUser(s));
			Collections.sort(list, new SortByHomeNumber());// sort for binary search
			index = Collections.binarySearch(list, c, new SortByHomeNumber());// found index by binarySearch
			c = list.get(index);
			break;
		default:
			break;
		}
		if (c != null)// if contact exists print c if not inform to user
			System.out.println(c);
		else
			System.out.println("contact not found!");
	}
//method to remove object by fields 
	private static void remove(ContactList list, Scanner s) throws Exception {
		System.out.println("REMOVE CONTACT:");
		System.out.println("1. By Name");
		System.out.println("2. By Home");
		System.out.println("3. By Mobile");
		System.out.print("Your Option: ");
		char op = s.next().charAt(0);
		Contact c = null;
		int index = -1;
		switch (op) {
		case '1':
			c = new Contact(getFirstNameFromUser(s), getLastNameFromUser(s), "", "");
			Collections.sort(list, new SortByName());
			index = Collections.binarySearch(list, c, new SortByName());
			c = list.get(index);
			break;

		case '2':
			c = new Contact("", "", "", getHomeNumberFromUser(s));
			Collections.sort(list, new SortByHomeNumber());
			index = Collections.binarySearch(list, c, new SortByHomeNumber());
			c = list.get(index);
			break;
		case '3':
			c = new Contact("", "", getPhoneNumberFromUser(s), "");
			Collections.sort(list, new SortByPhoneNumber());
			index = Collections.binarySearch(list, c, new SortByPhoneNumber());
			c = list.get(index);
			break;

		}
		if (c != null) {
			System.out.println(c);
			System.out.print("Are You Sure? y/n");
			op = s.next().charAt(0);
			if (op == 'y') {
				list.remove(c);
				System.out.println("Contact Removed!");
			} else
				System.out.println("Operation Canceled!");
		} else
			System.out.println("Contact Not Found!");
		list.sort();

	}
//adding contact
	private static void add(ContactList list, Scanner s) throws Exception {
		System.out.println("ADD/UPDATE Contact:");
		int size = list.size();
		boolean isLoaded = list.add(new Contact(getFirstNameFromUser(s), getLastNameFromUser(s),
				getPhoneNumberFromUser(s), getHomeNumberFromUser(s)));
		if (!isLoaded)
			System.out.println("CONTACT CAN NOT BE NULL!\n");
		else if (size != list.size())
			System.out.println("Contact Added!\n");
		else
			System.out.println("Contact Updated!\n");
	}

	// methods to get fields values from user according to contact constructor

	private static String getPhoneNumberFromUser(Scanner s) {
		System.out.print("Enter Contact Phone Number :");
		return s.next();
	}

	private static String getHomeNumberFromUser(Scanner s) {
		System.out.print("Enter Contact Home Number :");
		return s.next();
	}

	private static String getFirstNameFromUser(Scanner s) throws Exception {
		System.out.print("Enter Contact First Name: ");
		return s.next();
	}

	private static String getLastNameFromUser(Scanner s) {
		System.out.print("Enter Contact Last Name: ");
		return s.next();
	}

}
