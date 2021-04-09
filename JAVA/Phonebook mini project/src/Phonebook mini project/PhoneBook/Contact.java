//HMW2 318323391 NOA AVIEL
package PhoneBook;

import java.io.Serializable;
import java.util.Comparator;

public class Contact implements Serializable, Comparable<Contact> {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String homeNumber;
	public static boolean isSortByName = true;

	// Constructors
	public Contact(String fName, String lName, String pNumber, String hNumber) throws Exception {
		setFirstName(fName);
		setLastName(lName);
		setPhoneNumber(pNumber);
		setHomeNumber(hNumber);
	}

	public Contact(String firstName, String lastName) throws Exception {
		setFirstName(firstName);
		setLastName(lastName);
	}

//Setters
	public void setFirstName(String firstName) throws Exception {
		if (firstName == null || firstName.length() < 3)
			throw new Exception("ERROR!");
		this.firstName = firstName;
	}

	public void setLastName(String lastName) throws Exception {
		if (lastName == null || lastName.length() < 3)
			throw new Exception("ERROR!");
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) throws Exception {

		this.phoneNumber = phoneNumber;
	}

	public void setHomeNumber(String homeNumber) throws Exception {

		this.homeNumber = homeNumber;
	}

	public static void setSortByName(boolean isSortByName) {
		Contact.isSortByName = isSortByName;
	}

//hash code method
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((homeNumber == null) ? 0 : homeNumber.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

//equals method
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (!firstName.equals(other.firstName))
			return false;
		return true;
	}

//compareTo method
	@Override
	public int compareTo(Contact c) {
		if (isSortByName)
			return firstName.compareTo(c.firstName);
		return 0;
		// else
		// return lastName.compareTo(c.lastName);

	}

	// GETTERS AND SETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public static boolean isSortByName() {
		return isSortByName;
	}

	// printing a contact
	@Override
	public String toString() {
		return "Contact Info Is: First Name = " + firstName + ", Last Name = " + lastName + ", Phone Number = "
				+ phoneNumber + ", Home Number = " + homeNumber;
	}

}
//INNER CLASSES TO SORT BY PARAMETERS - COMPARATORS

class SortByName implements Comparator<Contact> {

	@Override
	public int compare(Contact c1, Contact c2) {
		return c1.compareTo(c2);
	}

}

class SortByPhoneNumber implements Comparator<Contact> {

	@Override
	public int compare(Contact c1, Contact c2) {
		return c1.getPhoneNumber().compareTo(c2.getPhoneNumber());
	}

}

class SortByHomeNumber implements Comparator<Contact> {

	@Override
	public int compare(Contact c1, Contact c2) {
		return c1.getHomeNumber().compareTo(c2.getHomeNumber());
	}

}
