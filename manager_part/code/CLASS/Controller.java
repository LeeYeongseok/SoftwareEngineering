package manager;

import java.util.ArrayList;

public class Controller {
	boolean val;
	String ID;
	int menu;
	RoomInfo room;
	
	public Controller() {
		room = new RoomInfo();
	}
	
	private void getIDList(ArrayList<String> list) {
		// DB로부터 호텔 ID 정보 받기
	}
	public boolean IsValid(String ID) {
		// 호텔 ID가 유효한가?
		boolean val = false;
		
		ArrayList<String> list = new ArrayList();
		getIDList(list);
		
		for(String str : list) {
			if(ID==str){
				val=true;
				break;
			}		
		}
		return val;
	}
	private void setMenu(int menu, RoomInfo roomInfo) {
		// 메뉴 1: add, 2: delete, 3: modify
	}
	private void checkReservation() {
		// 예약 정보 보겠다 선택
	}
	private void chooseReservation(int r) {
		// 특정 예약 정보에 대한 선택
	}
	private void acceptOrReject(boolean d) {
		// 예약 수락or거부 정보 전달 (DB 안의 함수 사용)
	}
	
}
