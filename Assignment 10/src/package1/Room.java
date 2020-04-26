package package1;

enum RoomType {
	Single, Double, Studio
}

public class Room {
	private String roomNumber;
	private RoomType type;
	private Double pricePerNight;

	public Room(String roomNumber, RoomType type, Double pricePerNight) {
		this.roomNumber = roomNumber;
		this.type = type;
		this.pricePerNight = pricePerNight;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public Double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(Double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room number: " + this.roomNumber + '\n');
		sb.append("Type: " + this.type.toString() + '\n');
		sb.append("Price per night: " + this.pricePerNight.toString() + '\n');
		return sb.toString();
	}
}
