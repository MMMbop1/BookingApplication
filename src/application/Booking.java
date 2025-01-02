package application;

import java.util.ArrayList;

public interface Booking {
	
	public void addBooking();
	
	public ArrayList<BookingTicket> getAllBookings();
	
	public BookingTicket getBookingByBookingId();
	
	public Guest getBookingByName();
	
	public void removeBookingByBookingNumber();
	
	public String roomsAvailableTimePeriod();
}
