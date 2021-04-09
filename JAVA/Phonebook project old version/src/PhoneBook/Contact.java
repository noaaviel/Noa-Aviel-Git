//HMW01 318323391 NOA AVIEL
package PhoneBook;

import java.io.Serializable;

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
		if (phoneNumber == null || phoneNumber.length() < 10)
			throw new Exception("ERROR!");
		this.phoneNumber = phoneNumber;
	}

	public void setHomeNumber(String homeNumber) throws Exception {
		if (homeNumber == null || homeNumber.length() < 9) {
			throw new Exception("ERROR");
		}
		this.homeNumber = homeNumber;
	}

	public static void setSortByName(boolean isSortByName) {
		Contact.isSortByName = isSortByName;
	}

	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (!firstName.equals(other.firstName) && (!lastName.equals(other.lastName)))
			return false;
		return true;
	}

	@Override
	public int compareTo(Contact c) {
		if (isSortByName) 
			return firstName.compareTo(c.firstName) ;
		 else 
			return lastName.compareTo(c.lastName);
		
	}

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
