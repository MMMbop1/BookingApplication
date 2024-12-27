package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	private String roomType;
	private Hotel hotel;
	private HashMap<Integer, ArrayList<String>> bookings = new HashMap<>();		
	private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Room() {}
	
	public Room(String roomType, Hotel hotel) {
		this.roomType = roomType;
		this.hotel = hotel;
	}

	public HashMap<Integer, ArrayList<String>> getBookings() {
		return bookings;
	}
	
	public void setBookings(HashMap<Integer, ArrayList<String>> bookings) {
		this.bookings = bookings;
	}
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public void addBooking(int bookingNumber, String checkIn, String checkOut) {
		HashMap<Integer, ArrayList<String>> bookings = getBookings();
		ArrayList<String> dates = new ArrayList<>();
		dates.add(checkIn);
		dates.add(checkOut);		
		bookings.put(bookingNumber, dates);
		setBookings(bookings);		
	}
	
	public boolean dateOverLapping(String checkIn, String checkOut, String bookedCheckIn, String bookedCheckout) {
		LocalDate checkInLocalDate = LocalDate.parse(checkIn, DATE_FORMAT);
		LocalDate checkOutLocalDate = LocalDate.parse(checkOut, DATE_FORMAT);		
		LocalDate bookedCheckInLocalDate = LocalDate.parse(bookedCheckIn, DATE_FORMAT);
		LocalDate bookedCheckoutLocalDate = LocalDate.parse(bookedCheckout, DATE_FORMAT);
		return !((checkOutLocalDate.isBefore(bookedCheckInLocalDate) || checkOutLocalDate.isEqual(bookedCheckInLocalDate)) || (checkInLocalDate.isAfter(bookedCheckoutLocalDate) || checkInLocalDate.isEqual(bookedCheckoutLocalDate)));
	}
}
