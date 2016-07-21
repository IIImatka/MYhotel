package by.htp3.hotel.bean;

public class Room {
	private String type;
	private int number;
	
	public Room(int number, String type){
		this.number = number;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
