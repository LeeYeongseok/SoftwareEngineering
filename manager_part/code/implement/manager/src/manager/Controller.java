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
		// DB�κ��� ȣ�� ID ���� �ޱ�
	}
	public boolean IsValid(String ID) {
		// ȣ�� ID�� ��ȿ�Ѱ�?
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
		// �޴� 1: add, 2: delete, 3: modify
	}
	private void checkReservation() {
		// ���� ���� ���ڴ� ����
	}
	private void chooseReservation(int r) {
		// Ư�� ���� ������ ���� ����
	}
	private void acceptOrReject(boolean d) {
		// ���� ����or�ź� ���� ���� (DB ���� �Լ� ���)
	}
	
}
