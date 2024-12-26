package application;

import java.util.ArrayList;

public class Hotel {
	private ArrayList<Room> rooms;
	private GuestBook guestBook;
	
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
	
}
