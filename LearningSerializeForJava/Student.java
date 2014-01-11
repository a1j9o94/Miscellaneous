import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
        //make sure each student gets a different ID
	private static int lastUsedId = 0;
	private int idNumber;
	private String name = null;
        //list of classes
	private ArrayList<SchoolClass> classList = null;
	
	public Student(int id, String name){
		if(name == null){
			this.name = "Unknown";
		}else{
			this.name = name;
		}
		idNumber = id;
	}
	
	public void addClass(String name, int classId){
		if(classList == null){
			classList = new ArrayList<SchoolClass>();
		}
		classList.add(new SchoolClass(classId, name));
	}
	
	public String viewClassList(){
		StringBuilder sb = new StringBuilder();
		for(SchoolClass currentClass : classList){
			sb.append(currentClass.getName() + ": " + currentClass.getClassId() + "\n");
		}
		return sb.toString();
	}
	
        //used to add classes to help test the addClassesFromFile
	public void getClasses(ArrayList<SchoolClass> list){
		for(SchoolClass currentClass : classList){
			list.add(currentClass);
		}
	}
	
        //Attempting to read in several objects from a file
	public void addClassesFromFile(ObjectInputStream in){
		SchoolClass readIn = null;
		if(classList == null){
			classList = new ArrayList<SchoolClass>();
		}
		try {
			do{
				readIn = (SchoolClass) in.readObject();
				if(readIn != null){
					classList.add(readIn);
				}
			}while(readIn != null);
			in.close();
		} catch(EOFException e){
                    //withouth this cath, it prints an error when the end of the file is reached
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public String toString(){
		return this.name + " " + this.idNumber;
	}
	
	public static int nextAvailableId(){
		lastUsedId += 1;
		return lastUsedId;
	}
}
