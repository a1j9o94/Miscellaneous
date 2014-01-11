import java.io.Serializable;


public class SchoolClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int classId;
	private String name;
	
	public SchoolClass(int id, String name){
		this.classId = id;
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getClassId(){
		return classId;
	}
}
