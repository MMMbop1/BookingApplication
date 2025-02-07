package application;

import java.util.ArrayList;

public class GuestBook {
	private ArrayList<Guest> guests = new ArrayList<>();
	
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
	
	public void addGuest(Guest guest) {
		ArrayList<Guest> guests = getGuests();
		guests.add(guest);
		setGuests(guests);
	}
	
	public void removeGuestByBookingNumber(int bookingNumber) {
		ArrayList<Guest> guests = getGuests();
		
		for (Guest guest : guests) {
			if (guest.getBookingTicket().getBookingNumber() == bookingNumber) {
				guest.getBookingTicket().getRoom().removeBookingTicket(bookingNumber);
			}
		}
		
		guests.removeIf(guest -> guest.getBookingTicket().getBookingNumber() == bookingNumber);
		setGuests(guests);
	}
}
