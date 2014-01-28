import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class LearningDeSerialize {

	public static void main(String[] args) {
                //all the files created when LearnSerialize is run
		File student1File = new File("outputfiles/student.ser");
		File student2File = new File("outputfiles/student2.ser");
		File student3File = new File("outputfiles/student3.ser");
		File classListFile = new File("outputfiles/classList.ser");
		
		ObjectInputStream in = null;
		
                //test getting input from student.ser files
		Student student1 = null;
		try{
			in = new ObjectInputStream(new FileInputStream(student1File));
			student1 = (Student) in.readObject();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(student1 + "\n" + student1.viewClassList());
		
		Student student2 = null;
		try{
			in = new ObjectInputStream(new FileInputStream(student2File));
			student2 = (Student) in.readObject();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(student2 + "\n" + student2.viewClassList());

		Student student3 = null;
		try{
			in = new ObjectInputStream(new FileInputStream(student3File));
			student3 = (Student) in.readObject();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(student3 + "\n" + student3.viewClassList());

	        //testing addClassesFromFileMethod	
		Student student4 = new Student(Student.nextAvailableId(), "Elizabeth");
		
		try {
			in = new ObjectInputStream(new FileInputStream(classListFile));
			student4.addClassesFromFile(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(student4 + "\n" + student4.viewClassList());
		
	}

}
