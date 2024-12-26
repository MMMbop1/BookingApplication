package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Booker implements Booking {
	
	private Hotel hotel;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Booker() {}
	
	public Booker(Hotel hotel) {
		this.hotel = hotel;
	}

	@Override
	public void addBooking() {
		System.out.println("What date would you like to check in? YYYY-MM-DD");
		String checkIn = reservationDate();
		
		System.out.println("What date would you like to check out? YYYY-MM-DD");
		String checkOut = reservationDate();		
				
		Guest guest = guestName();
		
	}
	
	public String reservationDate() {
		String reservationDate = userInput();
		
		if (!validateDateFormat(reservationDate)) {
			throw new IllegalArgumentException("Invalid format, must be YYYY-MM-DD");
		}
		
		if (!validateThatBookingIsNotBeforeTodaysDate(reservationDate)) {
			throw new IllegalArgumentException("ReservationDate can not be earlier then todays Date.");
		}
		
		return reservationDate;
	}
	
	public boolean validateDateFormat(String dateFormat) {
		if (dateFormat.length() != 10) {
			return false;
		}
				
		if (dateFormat.charAt(4) != 45 || dateFormat.charAt(7) != 45) {
			return false;
		}
		
		for (int i = 0; i < dateFormat.length(); i++) {
			if(i == 4 || i == 7) {
				continue;
			}
			
			if(dateFormat.charAt(i) < 48 || dateFormat.charAt(i) > 57) {
				return false;
			}
		}
		
		return true;
	}

	public boolean validateThatBookingIsNotBeforeTodaysDate(String reservationDate) {
	    LocalDate todaysDate = LocalDate.now();
	    LocalDate localDateAsCheckIn = LocalDate.parse(reservationDate, DATE_FORMAT);

	    return todaysDate.isBefore(localDateAsCheckIn) || todaysDate.isEqual(localDateAsCheckIn);
	}
	
	public Guest guestName() {
		Guest guest = new Guest();
		
		System.out.println("What is your name?");
		
		guest.setName(userInput());
		return guest;
	}

	public String roomType(String checkIn, String checkOut) {
		int availableSingleRooms = availableRooms(checkIn, checkOut, RoomProperties.SINGLE_ROOM.getRoomType());
		int availableDoubleRooms = availableRooms(checkIn, checkOut, RoomProperties.DOUBLE_ROOM.getRoomType());
		int availableEnSuite = availableRooms(checkIn, checkOut, RoomProperties.EN_SUITE.getRoomType());

		
		return null;
	}
	
	public int availableRooms(String checkIn, String checkOut, String roomType) {
		return Math.max(RoomProperties.SINGLE_ROOMS_AVAILABLE.getValue() - numberOfBookedRooms(checkIn, checkOut, roomType), 0);
	}
	
	public int numberOfBookedRooms(String checkIn, String checkOut, String roomType) {		
		LocalDate localDateAsCheckIn = LocalDate.parse(checkIn, DATE_FORMAT);
		LocalDate localDateAsCheckOut = LocalDate.parse(checkOut, DATE_FORMAT);
		
		ArrayList<ArrayList<LocalDate>> bookingsRoomType = new ArrayList<>();		
		ArrayList<BookingTicket> Bookingtickets = new ArrayList<>();
		
		for (Guest guest : getHotel().getGuestBook().getGuests()) {
			Bookingtickets.addAll(guest.getBookingTickets());
		}
		
		for (BookingTicket bookingTicket : Bookingtickets) {
			if(bookingTicket.getRoom().getRoomType().equals(roomType)) {
				ArrayList<LocalDate> bookedRooms = new ArrayList<>();
				bookedRooms.add(LocalDate.parse(bookingTicket.getCheckIn(), DATE_FORMAT));
				bookedRooms.add(LocalDate.parse(bookingTicket.getCheckIn(), DATE_FORMAT));
				bookingsRoomType.add(bookedRooms);
			}	
		}
		
		return (int) bookingsRoomType.stream()
				.filter(val -> isOverLapping(localDateAsCheckIn, localDateAsCheckOut, val.get(0), val.get(1)))
				.count();
	}
	
	public boolean isOverLapping(LocalDate checkIn, LocalDate checkOut, LocalDate bookedCheckIn, LocalDate bookedCheckout) {
		return !((checkOut.isBefore(bookedCheckIn) || checkOut.isEqual(bookedCheckIn)) || (checkIn.isAfter(bookedCheckout) || checkIn.isEqual(bookedCheckout)));
	}
	
	@Override
	public ArrayList<BookingTicket> getAllBookings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingTicket getBookingByBookingId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingTicket getBookingByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeBookingByBookingNumber() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Room> roomsAvailableTimePeriod() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String userInput() {
		try {
			String userInput = reader.readLine();
			
			if (userInput.equals("")) {
				throw new IllegalArgumentException("Field cannot be empty!");
			}
			
			return userInput;
		} catch(IOException ex) {
	        throw new RuntimeException("Something went horrible wrong!");
		}
	}
	
	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public DateTimeFormatter getDATE_FORMAT() {
		return DATE_FORMAT;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
