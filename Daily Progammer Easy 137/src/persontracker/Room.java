package persontracker;

import java.util.ArrayList;

public class Room implements Comparable<Room> {
	private int roomNumber;
	private ArrayList<Person> visitors = new ArrayList<Person>();

	public Room(int currentRoomNumber) {
		roomNumber = currentRoomNumber;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void trackPerson(String inOut, int personNumber, int time) {
		
		if(inOut.equals("I")){
			if(visitors.isEmpty())
				addPerson(personNumber,time);
			for( Person visitor : visitors){
				if(visitor.getID() == personNumber){
					visitor.setInTime(time);
					break;
				}
				else{
					addPerson(personNumber,time);
					break;
				}
			}
		}
		else{
			for(Person visitor : visitors){
				if(personNumber == visitor.getID()){
					visitor.setOutTime(time);
					visitor.updateTotalTime();
					break;
				}
			}
		}
		
	}


	private void addPerson(int personNumber, int time) {
		Person person = new Person();
		person.setID(personNumber);
		person.setInTime(time);
		visitors.add(person);
	}

	public int getAverageVisit() {
		int average = 0;
		for( Person visitor : visitors){
			average += visitor.getTotalTime();
		}
		average /= visitors.size();
		return average;
	}

	public int getTotalVisitors() {
		return visitors.size();
	}

	@Override
	public int compareTo(Room o) {
		if (o.getRoomNumber() > this.roomNumber) return -1;
		if (o.getRoomNumber() < this.roomNumber) return 1;
		return 0;
	}

		
	
}
