package application;

public enum RoomProperties {
	SINGLE_ROOMS_AVAILABLE(25),
	DOUBLE_ROOMS_AVAILABLE(23),
	EN_SUITE_AVAILABLE(2),	
	SINGLE_ROOM_PRICE(1000),
	DOUBLE_ROOM_PRICE(2000),
	EN_SUITE_PRICE(3000),
	SINGLE_ROOM("Single Room"),
	DOUBLE_ROOM("Double Room"),
	EN_SUITE("En_Suite");
		
	private int value;
	private String roomType;
 	
	RoomProperties(int value) {
		this.value = value;
	}

	RoomProperties(String roomType) {
		this.roomType = roomType;
	}
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
