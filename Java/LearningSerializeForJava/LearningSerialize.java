import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class LearningSerialize {

	public static void main(String[] args) {
	        //object output stream being used for all output	
		ObjectOutputStream out = null;

                //ArrayList used to holde classes and test the addClassesFromFile() method of the student class
		ArrayList<SchoolClass> classes = new ArrayList<SchoolClass>();

                //create directory to hold output files
		File directory = new File("outputfiles");
		
		if(!directory.exists()){
			directory.mkdir();
		}
		
	        //various students to test and their files.	
		Student student = new Student(Student.nextAvailableId(), "Adrian");
		String student1FileName = "outputfiles/student.ser";
		File student1File = new File(student1FileName);
		
		Student student2 = new Student(Student.nextAvailableId(), "Madelaine");
		String student2FileName = "outputfiles/student2.ser";
		File student2File = new File(student2FileName);
		
		Student student3 = new Student(Student.nextAvailableId(), "Perri");
		String student3FileName = "outputfiles/student3.ser";
		File student3File = new File(student3FileName);
		
		student.addClass("Software Development", 1302);
		student.addClass("Honors Political Science", 1105);

                //write first student to a file
		try {
			out = new ObjectOutputStream(new FileOutputStream(student1File));
			out.writeObject(student);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
                //add classes to test 
		student.getClasses(classes);
		
		student2.addClass("Biology", 1107);
		student2.addClass("Chemistry", 1102);
		try{
			out = new ObjectOutputStream(new FileOutputStream(student2File));
			out.writeObject(student2);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		student2.getClasses(classes);
		
		student3.addClass("Bioligy", 1107);
		student3.addClass("Chemistry", 1102);
		try{
			out = new ObjectOutputStream(new FileOutputStream(student3File));
			out.writeObject(student3);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		student3.getClasses(classes);
		
                //add classes to a file to test the addClassesFromFile() 
		File classListFile = new File("outputfiles/classList.ser");
		try{
			out = new ObjectOutputStream(new FileOutputStream(classListFile));
			for(SchoolClass currentClass : classes){
				out.writeObject(currentClass);
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
