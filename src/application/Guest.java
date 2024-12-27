package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Guest implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	private String name;	
	private BookingTicket bookingTicket;
	
	public Guest() {}

	public Guest(String name, BookingTicket bookingTicket) {
		this.name = name;
		this.bookingTicket = bookingTicket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BookingTicket getBookingTicket() {
		return bookingTicket;
	}

	public void setBookingTicket(BookingTicket bookingTicket) {
		this.bookingTicket = bookingTicket;
	}
		
}
