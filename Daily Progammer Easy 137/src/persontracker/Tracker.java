package persontracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Tracker {
	

	private static ArrayList<Room> rooms = new ArrayList<Room>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int numOfLines = Integer.parseInt(in.nextLine());
		
		for(int i = 0; i < numOfLines; i++){
			String line = in.nextLine();
			int currentRoomNumber = Integer.parseInt(line.substring(line.indexOf(' ')+1,line.indexOf(' ', line.indexOf(' ')+1)));
			String inOut = line.substring(line.indexOf(' ', line.indexOf(' ')+1) + 1,line.indexOf(' ', line.indexOf(' ')+1) + 2);
			int personNumber = Integer.parseInt(line.substring(0,line.indexOf(' ')));
			int time = Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1));
			Room newRoom = getRoom(currentRoomNumber);
			newRoom.trackPerson(inOut, personNumber, time);
		}
		Collections.sort(rooms);
		for(Room room : rooms){
			System.out.println("Room " + room.getRoomNumber() + ", " + room.getAverageVisit() + " minute average visit, " +
			room.getTotalVisitors() + " visitor(s) total." );
		}
		in.close();
	}

	private static Room getRoom(int currentRoomNumber) {
		Room newRoom = new Room(currentRoomNumber);
		if(rooms.isEmpty()){
			rooms.add(newRoom);
		}
		for (Room room : rooms) {
			if(currentRoomNumber == room.getRoomNumber()){
				return room;	
			}
		}
		rooms.add(newRoom);
		return newRoom;
	}

}

