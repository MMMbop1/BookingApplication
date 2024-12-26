package application;

import java.util.ArrayList;

public class GuestBook {
	private ArrayList<Guest> guests;
	
	public GuestBook() {}

	public GuestBook(ArrayList<Guest> guests) {
		this.guests = guests;
	}

	public ArrayList<Guest> getGuests() {
		return guests;
	}

	public void setGuests(ArrayList<Guest> guestBook) {
		this.guests = guestBook;
	}
	
	
}
