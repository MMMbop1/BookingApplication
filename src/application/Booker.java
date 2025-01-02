package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Random;

public class Booker implements Booking {
	
	private Hotel hotel;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private Serializer serializer = new Serializer();
	
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
		
		String roomType = roomType(checkIn, checkOut);
		
		Random random = new Random();
		int bookingNumber = getHotel().bookingNumber(1 + random.nextInt(1000));
			
		Room room = getHotel().availableRoom(roomType, checkIn, checkOut);		
				
		System.out.println("Your booking number: " + bookingNumber);
		BookingTicket bookingTicket = new BookingTicket(room, checkIn, checkOut, guest, bookingNumber);
		room.addBooking(bookingTicket);
		guest.setBookingTicket(bookingTicket);
		hotel.getGuestBook().addGuest(guest);
		
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
		int availableSingleRooms = getHotel().availableRooms(checkIn, checkOut, RoomProperties.SINGLE_ROOM.getRoomType());
		int availableDoubleRooms = getHotel().availableRooms(checkIn, checkOut, RoomProperties.DOUBLE_ROOM.getRoomType());
		int availableEnSuite = getHotel().availableRooms(checkIn, checkOut, RoomProperties.EN_SUITE.getRoomType());
		
		if (availableSingleRooms + availableDoubleRooms + availableEnSuite == 0) {
			System.out.println("Unfortunately we are fully booked this time period.");
			throw new RuntimeException("Fully booked...");
		}
		
		System.out.println("1) Single room (" + availableSingleRooms + " available)");
		System.out.println("2) Double room (" + availableDoubleRooms + " available)");
		System.out.println("3) En suite (" + availableEnSuite + " available)");
		System.out.println("0) Cancel Booking");

		String choice = "";
		
		switch(userInputInt()) {
			case 0 -> throw new RuntimeException("Booking canceled");
			case 1 -> choice = RoomProperties.SINGLE_ROOM.getRoomType();
			case 2 -> choice = RoomProperties.DOUBLE_ROOM.getRoomType();
			case 3 -> choice = RoomProperties.EN_SUITE.getRoomType();
			default -> {
				System.out.println("Wrong input, must be 0-3");
				roomType(checkIn, checkOut);
			}
		}
		
		return choice;
	}
	
	@Override
	public ArrayList<BookingTicket> getAllBookings() {
		ArrayList<BookingTicket> bookingTickets = new ArrayList<>();
		
		for (Guest guest : getHotel().getGuestBook().getGuests()) {
			bookingTickets.add(guest.getBookingTicket());
		}
				
		return bookingTickets;
	}

	@Override
	public BookingTicket getBookingByBookingId() {
		System.out.println("What bookingId?");
		int bookingNumber = userInputInt();

		ArrayList<BookingTicket> bookingTickets = getAllBookings();
		BookingTicket bookingTicket = null;
		
		for (BookingTicket ticket : bookingTickets) {
			if (ticket.getBookingNumber() == bookingNumber) {
				bookingTicket = ticket;
			}
		}
		
		return bookingTicket;
	}

	@Override
	public Guest getBookingByName() {
		System.out.println("What name should I search for to get your booking?");
		String bookingName = userInput();

		ArrayList<Guest> guests = getHotel().getGuestBook().getGuests();
		Guest guest = null;
		
		for (Guest bookedGuest : guests) {
			if (bookedGuest.getName().equals(bookingName)) {
				guest = bookedGuest;
			}
		}
		
		return guest;
	}

	@Override
	public void removeBookingByBookingNumber() {
		System.out.println("What bookingId to remove?");
		int bookingNumber = userInputInt();
		
		getHotel().getGuestBook().removeGuestByBookingNumber(bookingNumber);
	}

	@Override
	public String roomsAvailableTimePeriod() {
		System.out.println("What date for checkin? YYYY-MM-DD");
		String checkIn = reservationDate();
		
		System.out.println("What date for check out? YYYY-MM-DD");
		String checkOut = reservationDate();		
				
		
		int availableSingleRooms = getHotel().availableRooms(checkIn, checkOut, RoomProperties.SINGLE_ROOM.getRoomType());
		int availableDoubleRooms = getHotel().availableRooms(checkIn, checkOut, RoomProperties.DOUBLE_ROOM.getRoomType());
		int availableEnSuite = getHotel().availableRooms(checkIn, checkOut, RoomProperties.EN_SUITE.getRoomType());
		
		if (availableSingleRooms + availableDoubleRooms + availableEnSuite == 0) {
			System.out.println("Unfortunately we are fully booked this time period.");
			throw new RuntimeException("Fully booked...");
		}
		
	    String roomsAvailable = 
	            "1) Single room (" + availableSingleRooms + " available)\n" +
	            "2) Double room (" + availableDoubleRooms + " available)\n" +
	            "3) En suite (" + availableEnSuite + " available)";
	        
	    return roomsAvailable;	
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
	
	public int userInputInt() {
	    try {
	    	return Integer.parseInt(reader.readLine()); 
	    } catch(NumberFormatException ex1) {
	    	throw new InputMismatchException("Wrong input, must be an integer!");
	    } catch (IOException ex2) {
	        throw new RuntimeException("Something went horrible wrong!");
	    }
	}
	
	public void saveToFile() {
		serializer.writeObject(getHotel().getGuestBook().getGuests());
		System.out.println("Wrote guests to file");
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
