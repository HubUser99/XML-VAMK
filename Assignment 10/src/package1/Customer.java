package package1;

public class Customer {
	private String firstName;
	private String lastName;
	private String roomNumber;
	private int durationOfStay;

	public Customer(String firstName, String lastName, String roomNumber, int durationOfStay) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.roomNumber = roomNumber;
		this.durationOfStay = durationOfStay;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getDurationOfStay() {
		return durationOfStay;
	}

	public void setDurationOfStay(int durationOfStay) {
		this.durationOfStay = durationOfStay;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("First name: " + this.firstName + '\n');
		sb.append("Last name: " + this.lastName + '\n');
		sb.append("Room number: " + this.roomNumber + '\n');
		sb.append("Duration of stay: " + this.durationOfStay + '\n');
		return sb.toString();
	}
}
