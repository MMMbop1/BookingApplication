package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Guest implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	private String name;	
	private ArrayList<BookingTicket> bookingTickets;
	
	public Guest() {}

	public Guest(String name, ArrayList<BookingTicket> bookingTickets) {
		this.name = name;
		this.bookingTickets = bookingTickets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<BookingTicket> getBookingTickets() {
		return bookingTickets;
	}

	public void setBookingTickets(ArrayList<BookingTicket> bookingTickets) {
		this.bookingTickets = bookingTickets;
	}
		
}
