package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Hotel {
	private ArrayList<Room> rooms;
	private GuestBook guestBook;
	private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Hotel() {}
	
	public Hotel(ArrayList<Room> rooms, GuestBook guestBook) {
		this.rooms = rooms;
		this.guestBook = guestBook;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	public GuestBook getGuestBook() {
		return guestBook;
	}
	
	public void setGuestBook(GuestBook guestBook) {
		this.guestBook = guestBook;
	}
	
	public DateTimeFormatter getDATE_FORMAT() {
		return DATE_FORMAT;
	}
	
	public int availableRooms(String checkIn, String checkOut, String roomType) {
		return Math.max(RoomProperties.getAvailabilityProperty(roomType) - numberOfBookedRooms(checkIn, checkOut, roomType), 0);
	}
	
	public int numberOfBookedRooms(String checkIn, String checkOut, String roomType) {		
		LocalDate localDateAsCheckIn = LocalDate.parse(checkIn, DATE_FORMAT);
		LocalDate localDateAsCheckOut = LocalDate.parse(checkOut, DATE_FORMAT);
		
		ArrayList<ArrayList<LocalDate>> bookingsRoomType = new ArrayList<>();
		
		for (Guest guest : getGuestBook().getGuests()) {
			if(guest.getBookingTicket().getRoom().equals(roomType)) {
				ArrayList<LocalDate> bookedRooms = new ArrayList<>();
				bookedRooms.add(LocalDate.parse(guest.getBookingTicket().getCheckIn(), DATE_FORMAT));
				bookedRooms.add(LocalDate.parse(guest.getBookingTicket().getCheckOut(), DATE_FORMAT));
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
	
	public int bookingNumber(int randomNumber) {		
		if (!uniqueBookingNumber(randomNumber)) {
			return randomNumber;
		}
		
		int ceilingKey = firstNonExistingKeyInCeilingMap(randomNumber);
		int floorKey = firstNonExistingKeyInFloorMap(randomNumber);

		return closestNumberToRandomNumber(randomNumber, ceilingKey, floorKey);
	}
	
	public int firstNonExistingKeyInCeilingMap(int randomNumber) {		
		if (++randomNumber > 1000) {
			return -1;
		}
		
		if (!uniqueBookingNumber(randomNumber)) {
			return randomNumber;
		}
		
		return firstNonExistingKeyInCeilingMap(randomNumber);
	}
	
	public int firstNonExistingKeyInFloorMap(int randomNumber) {
		if (--randomNumber < 1) {
			return -1;
		}
		
		if (!uniqueBookingNumber(randomNumber)) {
			return randomNumber;
		}
		
		return firstNonExistingKeyInFloorMap(randomNumber);
	}

	public int closestNumberToRandomNumber(int randomNumber, int ceilingKey, int floorKey) {
		if (ceilingKey == -1) {
			return floorKey;
		}
		
		if (floorKey == -1) {
			return ceilingKey;
		}
		
		int ceilingKeyMinusRandomNumber = ceilingKey - randomNumber;
		int randomNumberMinusFloorKey = randomNumber - floorKey;
		
		if (ceilingKeyMinusRandomNumber <= randomNumberMinusFloorKey) {
			return ceilingKey;
		}
		
		return floorKey;
	}
	
	public boolean uniqueBookingNumber(int randomNumber) {		
		for (Guest guest : getGuestBook().getGuests()) {
			if (guest.getBookingTicket().getBookingNumber() == randomNumber) {
				return true;
			}
		}
		
		return false;
	}

	public Room availableRoom(String roomType, String checkIn, String checkOut) {
		boolean overlapping = false;
		
		for (Room hotelRoom : getRooms()) {
			if (hotelRoom.getRoomType().equals(roomType)) {					
				HashMap<Integer, ArrayList<String>> roomBookings = hotelRoom.getBookings();
				for (Integer key : roomBookings.keySet()) {
					ArrayList<String> bookedDatesOnRoom = roomBookings.get(key);
					if (hotelRoom.dateOverLapping(checkIn, checkOut, bookedDatesOnRoom.get(0), bookedDatesOnRoom.get(1))) {
						overlapping = true;
					}
				}
				
				if (!overlapping) {
					return hotelRoom;
				}
			}
		}
		
		return null;
	}
}
