import java.util.ArrayList;
import java.util.Date;

public class Course {
	protected String courseName;
	protected DigitalItem currentCourseBook;
	protected Date courseEndDate;
	private static LibraryDatabase database;
	
	public Course(String courseName, DigitalItem currentCourseBook, Date courseEndDate) throws Exception {
		this.courseName = courseName;
		this.currentCourseBook = currentCourseBook;
		this.courseEndDate = courseEndDate;
		database = LibraryDatabase.getInstance();
	}

	public String getCourseName() {
		return courseName;
	}
	
	//adds original course book to course book history, sets current course book to new course book
	public void updateCourseBook(Faculty user, DigitalItem newCourseBook) throws Exception {
		user.courseBookHistory.add(currentCourseBook);
		currentCourseBook = newCourseBook;
		
	    String path = database.path + "course_database.csv";
		
	    //updates main courseDB
		database.updateCourses(database.coursesDB, path);
		
		//updates all use course CSVs
		for (Account a : database.getUsers()) {
			String[] emailSplitter = a.getEmail().split("@", 2);
		    String splitEmail = database.path + emailSplitter[0] + "_course_data.csv";
		    
		    if (a.getAccType().equals("Student")) {
		    	database.updateCourses(((Student) a).getCurrentCourses(), splitEmail);
		    }
		    
		    else if (a.getAccType().equals("Faculty")) {
		    	database.updateCourses(((Faculty) a).getCurrentCourses(), splitEmail);
		    }
		}
		
		//updates courseBookHistory of prof who updated the currentCourseBook
		String[] emailSplitter = user.getEmail().split("@", 2);
		String splitEmail = database.path + emailSplitter[0] + "_digItem_data.csv";
		database.updateDigItems(user.getCourseBookHistory(), splitEmail);
	}

	public DigitalItem getCurrentCourseBook() {
		return currentCourseBook;
	}

	public void setCurrentCourseBook(DigitalItem currentCourseBook) {
		this.currentCourseBook = currentCourseBook;
	}
	
	public Date getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(Date courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
