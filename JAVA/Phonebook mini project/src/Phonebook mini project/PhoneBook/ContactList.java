//HMW2 NOA AVIEL 318323391
package PhoneBook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class ContactList extends LinkedList<Contact> implements Iterable<Contact> {

	private static final long serialVersionUID = 1L;
	private Comparator<Contact> comparator;

	public ContactList() {
		super();
		this.comparator = new SortByName();
	}

//add contact
	public boolean add(Contact c) {
		if (c == null || checkInputErrors(c))
			return false;
		Collections.sort(this, new SortByName());
		int index = Collections.binarySearch(this, c, new SortByName());
		if (index >= 0)
			super.set(index, c);
		else
			super.add(Math.abs(index + 1), c);
		sort();
		return true;
	}

// checking if the user entered a non-valid value in one of the fields
// and checks if home and phone number is in wanted pattern
	private boolean checkInputErrors(Contact contact) {

		boolean isPhoneNumberValid = Pattern.matches("[0-9]{3}-?[0-9]{7}", contact.getPhoneNumber());
		boolean isHomeNumberValid = Pattern.matches("[0-9]{2}-?[0-9]{7}", contact.getHomeNumber());

		if (contact.getFirstName().length() <= 0 || contact.getFirstName().length() < 3)
			return true;
		else if ((contact.getLastName().length() <= 0 || contact.getLastName().length() < 3))
			return true;
		else if ((contact.getHomeNumber().length() == 0 || contact.getHomeNumber().length() < 9 || !isPhoneNumberValid)
				|| (contact.getPhoneNumber().length() == 0 || contact.getPhoneNumber().length() < 10
						|| !isHomeNumberValid))
			return true;
		return false;
	}

// sorting using Collections interface and comparator instance
	public void sort() {
		Collections.sort(this, comparator);
	}

//comparator getter
	public Comparator<Contact> getComparator() {
		return comparator;
	}

//comparator setter
	public void setComparator(Comparator<Contact> comparator) {
		this.comparator = comparator;
	}

//remove method
	public Contact remove(int index) {
		if (index >= 0 && index < size())
			return super.remove(index);
		return null;
	}

//get index method
	public Contact get(int index) {
		if (index >= 0 && index < size())
			return super.get(index);
		return null;
	}

//saving contacts to list
	public void saveContacts() {
		try (ObjectOutputStream oOut = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("Contacts.obj")))) {
			oOut.writeInt(size());
			for (int i = 0; i < size(); i++)
				oOut.writeObject(get(i));
			System.out.println(size() + " Contacts Saved!\n");
		} catch (FileNotFoundException e) {
			System.out.println("Save Exception: File recipes.obj Not Found!");
		} catch (IOException e) {
			System.out.println("Save Exception: " + e.getMessage());
		}
	}

//loading contacts from list
	public void loadContacts() {
		try (ObjectInputStream oIn = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("Contacts.obj")))) {
			int size = size(), count = oIn.readInt(), added = 0, updated = 0, err = 0;
			boolean isLoaded = false;
			while (count > (added + updated + err)) {
				isLoaded = add((Contact) oIn.readObject());
				if (isLoaded) {
					if (size == size())
						updated++;
					else {
						added++;
						size++;
					}
				} else
					err++;
			}
			if (added + updated > 0)
				System.out.printf("%d Contact Loaded! Added: %d Updated: %d\n", (added + updated), added, updated);
			else
				System.out.println("File Is Empty!");

		} catch (FileNotFoundException e) {
			System.out.println("Load Exception: File Contacts.obj Not Found!");
		} catch (IOException e) {
			System.out.println("Load Exception: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Load Exception: File Contacts.obj Not Contains Contact Data!");
		}
	}

//toString method 
	@Override
	public String toString() {
		if (size() > 0) {
			String str = "Contacts:\n";
			String ch = "";
			for (int i = 0; i < size(); i++) {
				if (comparator instanceof SortByName && !ch.equals(get(i).getFirstName().substring(0, 1))) {
					ch = get(i).getFirstName().substring(0, 1);
					str += ch.toUpperCase() + "\n";
				}
				str += get(i) + "\n";
			}
			str += "\nNumber Of Contacts: " + size() + "\n";
			return str;
		}
		return "List Is Empty.\n";
	}

//iterator method returns this(this class functions as iterator by implementation)
	@Override
	public Iterator<Contact> iterator() {
		return new ContactIterator(this);
	}

//Contact Iterator Inner Class
	class ContactIterator implements Iterator<Contact> {

		private ContactList list;
		private int indexIt;

		public ContactIterator(ContactList rlist) {
			this.list = rlist;
			this.indexIt = -1;
		}

//implemented methods of Iterator.
		@Override
		public boolean hasNext() {
			return (indexIt + 1) < list.size();
		}

		@Override
		public Contact next() {
			if (hasNext())
				return list.get(++indexIt);
			return null;
		}

		public void remove() {
			if (indexIt >= 0 && indexIt < list.size())
				list.remove(indexIt);
		}

	}

}
