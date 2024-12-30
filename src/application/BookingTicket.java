package application;

public class BookingTicket {
	
	private Room room;
	private String checkIn;
	private String checkOut;
	private Guest guest;
	private int bookingNumber;
	
	public BookingTicket() {}

	public BookingTicket(Room room, String checkIn, String checkOut, Guest guest, int bookingNumber) {
		this.room = room;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.guest = guest;
		this.bookingNumber = bookingNumber;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public int getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(int bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	@Override
	public String toString() {
		return "BookingTicket [room=" + room.getRoomType() + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", guest=" + guest.getName()
				+ ", bookingNumber=" + bookingNumber + "]";
	}
	
	
	
}
