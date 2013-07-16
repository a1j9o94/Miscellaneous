package persontracker;

import java.util.ArrayList;

public class Room implements Comparable<Room> {
	private int roomNumber;
	private ArrayList<Visitor> visitors = new ArrayList<Visitor>();

	public Room(int currentRoomNumber){
		roomNumber = currentRoomNumber;
	}

	public int getRoomNumber(){
		return roomNumber;
	}

	public void trackVisitor(String inOut, int personNumber, int time){
		
		if(inOut.equals("I")){
			if(visitors.isEmpty())
				addPerson(personNumber,time);
			for( Visitor visitor : visitors){
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
			for(Visitor visitor : visitors){
				if(personNumber == visitor.getID()){
					visitor.setOutTime(time);
					visitor.updateTotalTime();
					break;
				}
			}
		}
		
	}


	private void addPerson(int visitorNumber, int time){
		Visitor visitor = new Visitor(visitorNumber);
		visitor.setInTime(time);
		visitors.add(visitor);
	}

	public int getAverageVisit(){
		int average = 0;
		for( Visitor visitor : visitors){
			average += visitor.getTotalTime();
		}
		average /= visitors.size();
		return average;
	}

	public int getTotalVisitors(){
		return visitors.size();
	}

	@Override
	public int compareTo(Room o){
		if (o.getRoomNumber() > this.roomNumber) return -1;
		if (o.getRoomNumber() < this.roomNumber) return 1;
		return 0;
	}

		
	
}
