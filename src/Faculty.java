import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Faculty extends AccountDecorator{
	
	protected ArrayList<Course> currentCourses;
	// courseBookHistory = faculty_digItem_data.csv
	protected ArrayList<DigitalItem> courseBookHistory;
	protected ArrayList<DigitalItem> textbooks;
	
	private static LibraryDatabase database;
	
	public Faculty(Account account) throws Exception {
	    super(account);
	    currentCourses = new ArrayList<Course>();
	    courseBookHistory = new ArrayList<>();
	    textbooks = new ArrayList<>(); // Initialize textbooks ArrayList
	    database = LibraryDatabase.getInstance();
	}
	
	public Faculty(Account account, ArrayList<Course> currentCourses, ArrayList<DigitalItem> courseBookHistory) throws Exception {
		super(account);
		this.currentCourses = currentCourses;
		this.courseBookHistory = courseBookHistory;
	}
	
    public void notifyManager(DigitalItem requestedTextbook) {

    } 
	
	public ArrayList<Course> getCurrentCourses() {
		return currentCourses;
	}

	public void setCurrentCourses(ArrayList<Course> currentCourses) {
		this.currentCourses = currentCourses;
	}

	public ArrayList<DigitalItem> getCourseBookHistory() {
		return courseBookHistory;
	}

	public void setCourseBookHistory(ArrayList<DigitalItem> courseBookHistory) {
		this.courseBookHistory = courseBookHistory;
	}
	
	//this one is good
    public void coursesAndTextbooks(Faculty user) {
        System.out.println("Courses taught by the " + user.getEmail() + ":" );
        for (Course course : user.currentCourses) {
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Textbook: " + course.getCurrentCourseBook().getName());
            System.out.println();
        }

        System.out.println("Textbooks previously used by the " + user.getEmail() + ":");
        for (DigitalItem textbook : user.courseBookHistory) {
            System.out.println("Textbook Name: " + textbook.getName());
        }
    }
    
//    public void notifyNewEdition(Faculty user, DigitalItem Textbook) throws Exception {
//    	String arr[] = new String[2];
//    	arr = user.getEmail().split("@");
//    	database.loadDigItems(textbooks, arr[0]);
//    	
//    	if()
//    } 

    
    
    

    	
    	
      
    

}
