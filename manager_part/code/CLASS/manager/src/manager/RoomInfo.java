package manager;

public class RoomInfo {
	private int priceOfDay; //1�� ����
	private String iTime; //üũ�� �ð�??
	private String oTime; //üũ�ƿ� �ð�??
	private String roomType; //�� Ÿ��
	private int capacity; //�� �����ο�(�ִ�)
	
	public RoomInfo() {
		
	}
	
	public int getPrice() {
		return priceOfDay;
	}
	public void setPrice(int price) {
		this.priceOfDay=price;
	}
	public String getiTime() {
		return iTime;
	}
	public void setiTime(String iTime) {
		this.iTime=iTime;
	}
	public String getoTime() {
		return oTime;
	}
	public void setoTime(String oTime) {
		this.oTime=oTime;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType=roomType;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity=capacity;
	}
}
