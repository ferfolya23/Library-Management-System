import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Student extends AccountDecorator {
	
	protected ArrayList<Course> currentCourses;
	// digitalCourseBooks = student_digItem_data.csv
	protected ArrayList<DigitalItem> digitalCourseBooks;
	
	public Student(Account account) {
		super(account);
		currentCourses = new ArrayList<Course>();
		digitalCourseBooks = new ArrayList<DigitalItem>();
	}
	
	public ArrayList<Course> getCurrentCourses() {
		return currentCourses;
	}

	public void setCurrentCourses(ArrayList<Course> currentCourses) {
		this.currentCourses = currentCourses;
	}

	public ArrayList<DigitalItem> getDigitalCourseBooks() {
		return digitalCourseBooks;
	}

	public void setDigitalCourseBooks(ArrayList<DigitalItem> digitalCourseBooks) {
		this.digitalCourseBooks = digitalCourseBooks;
	}
	
	protected void makeCopy(Item textbook) {
		//TODO - IMPLEMENT
	}
	
	protected void removeCopy(Item textbook) {
		//TODO - IMPLEMENT
	}

}
