package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	private String roomType;
	private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private ArrayList<BookingTicket> bookingTickets = new ArrayList<>();
	
	public Room() {}
	

	public Room(String roomType) {
		this.roomType = roomType;
	}
	
	public ArrayList<BookingTicket> getBookingTickets() {
		return bookingTickets;
	}


	public void setBookingTickets(ArrayList<BookingTicket> bookingTickets) {
		this.bookingTickets = bookingTickets;
	}


	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public void removeBookingTicket(int bookingNumber) {
		ArrayList<BookingTicket> bookingTickets = getBookingTickets();
		bookingTickets.removeIf(ticket -> ticket.getBookingNumber() == bookingNumber);
		setBookingTickets(bookingTickets);
	}
	
	public void addBooking(BookingTicket bookingTicket) {
		ArrayList<BookingTicket> bookingTickets = getBookingTickets();
		bookingTickets.add(bookingTicket);
		setBookingTickets(bookingTickets);
	}
	
	public boolean dateOverLapping(String checkIn, String checkOut, String bookedCheckIn, String bookedCheckout) {
		LocalDate checkInLocalDate = LocalDate.parse(checkIn, DATE_FORMAT);
		LocalDate checkOutLocalDate = LocalDate.parse(checkOut, DATE_FORMAT);		
		LocalDate bookedCheckInLocalDate = LocalDate.parse(bookedCheckIn, DATE_FORMAT);
		LocalDate bookedCheckoutLocalDate = LocalDate.parse(bookedCheckout, DATE_FORMAT);
		return !((checkOutLocalDate.isBefore(bookedCheckInLocalDate) || checkOutLocalDate.isEqual(bookedCheckInLocalDate)) || (checkInLocalDate.isAfter(bookedCheckoutLocalDate) || checkInLocalDate.isEqual(bookedCheckoutLocalDate)));
	}
}
