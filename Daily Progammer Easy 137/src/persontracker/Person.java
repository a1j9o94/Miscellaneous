package persontracker;

public class Person {
	private int inTime;
	private int outTime;
	private int id;
	private int currentVisitTime;
	private int totalTime;
	
	public void updateTotalTime(){
		currentVisitTime = outTime - inTime; 
		totalTime += currentVisitTime;
	}
	public int getTotalTime(){
		return totalTime;
	}
	public void setInTime(int timeIn){
		inTime = timeIn;
	}
	public void setOutTime(int timeOut){
		outTime = timeOut;
	}
	public void setID(int identifier){
		id =  identifier;
	}
	public int getID(){
		return id;
	}
}
