package application;


public class Room {
	private String roomType;
	private Hotel hotel;
	
	public Room() {}
	
	public Room(String roomType, Hotel hotel) {
		this.roomType = roomType;
		this.hotel = hotel;
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
}
