//HMW01 318323391 NOA AVIEL
package PhoneBook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class ContactList {
	private ArrayList<Contact> contacts;
	private int numOfContacts;

	//constructor
	public ContactList() {
		this.contacts = new ArrayList<>();
		this.numOfContacts = 0;
	}

	//adding contacts to list
	public int addC(Contact c) throws Exception {
		sortByName();
		int index = searchByName(c.getFirstName(), c.getLastName());
		if (checkInputErrors(c)) {
			return -1;
		} else if (index >= 0) {
			contacts.remove(index);
			contacts.add(index, c);
			return 0;
		} else {
			contacts.add(index + 1, c);
			System.out.println(c.toString());
			numOfContacts++;
			this.sortByName();
			return 1;
		}

	}

	//checking if the user entered a not valid value in one of the fields
	private boolean checkInputErrors(Contact contact) {
		if (contact.getFirstName().length() <= 0 || contact.getFirstName().length() < 3)
			return true;
		else if (contact.getLastName().length() <= 0 || contact.getLastName().length() < 3)
			return true;
		else if (contact.getPhoneNumber().length() == 0 || contact.getPhoneNumber().length() < 10)
			return true;
		else if (contact.getHomeNumber().length() == 0 || contact.getHomeNumber().length() < 9)
			return true;
		return false;
	}

	//searching contact by first and last name binary search
	public int searchByName(String firstName, String lastName) {
		sortByName();
		int left = 0, right = numOfContacts - 1, mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (firstName.compareTo(contacts.get(mid).getFirstName()) == 0) {
				if (lastName.compareTo(contacts.get(mid).getLastName()) == 0) {
					return mid;
				} else if (lastName.compareTo(contacts.get(mid).getLastName()) > 0) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else if (firstName.compareTo(contacts.get(mid).getFirstName()) > 0) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

	// sorting list by name 
	public void sortByName() {
		Contact.isSortByName = true;
		Collections.sort(contacts);
	}

	//removing a contact
	public void remove(int index) {
		if (index >= 0 && numOfContacts > 0 && numOfContacts > index) {
			contacts.remove(index);
			this.numOfContacts--;
		}
	}

	//getting index if contact
	public Contact getIndex(int index) {
		if (index >= 0 && numOfContacts > 0 && numOfContacts > index)
			return contacts.get(index);
		return null;
	}

	// save to file closes automatically because its in
	// try with resources block
	public void saveContacts() {
		try (ObjectOutputStream oOut = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("Contacts.obj")))) {
			// flag: how much objects we are going to read from file later on
			oOut.writeInt(numOfContacts);
			for (Contact c : contacts)
				oOut.writeObject(c);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// read contacts from file
	public void loadContactFromFile() throws Exception {
		try (ObjectInputStream oIn = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("Contacts.obj")))) {
			int size = oIn.readInt();
			System.out.println(size);
			while (size > 0) {
				Contact c = (Contact) oIn.readObject();
				addC(c);
				System.out.println(c.toString());
				size--;
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
	}

	//printing all contacts
	@Override
	public String toString() {
		if (numOfContacts > 0) {
			String str = "Contacts List: \n";
			String ch = "";
			for (Contact c : contacts) {
				if (!ch.equals(c.getFirstName().substring(0, 1))) {
					ch = c.getFirstName().substring(0, 1);
					str += "\n" + ch.toUpperCase() + "\n";
				}
				str += c + "\n";
			}
			return str;
		}
		return " #~~~~# List Is Empty! #~~~~# ";
	}

}
