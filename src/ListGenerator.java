import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ListGenerator {
	
	private static boolean fileCreator(String filePath) throws IOException {
		File file = new File(filePath);
        boolean fileGenerated = file.createNewFile();
        
        try {
            // Create the file, returns true if new file is made successfully
            if (fileGenerated) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
            e.printStackTrace();
        }
		
        return fileGenerated;
	}

	
	public static void getList(Student user, String listType, String path, String email) throws Exception {

		LibraryDatabase database = LibraryDatabase.getInstance();
		String fileName = email;
		String filePath = path;
		
		if(listType.equals("digItem")) {
			fileName += "_digItem_data.csv";
			filePath += fileName;
			
			boolean fileGenerated = fileCreator(filePath);
			ArrayList<DigitalItem> digitalItems = new ArrayList<DigitalItem>();
			
	    	//used to create digital items list
	        for (Course c : user.getCurrentCourses()) {
	            DigitalItem item = c.getCurrentCourseBook();
	            digitalItems.add(item);
	        }
	        
	        user.setDigitalCourseBooks(digitalItems);
			database.updateDigItems(user.getDigitalCourseBooks(), filePath);
		}
		
		else if(listType.equals("courses")) {
			fileName += "_course_data.csv";
			filePath += fileName;
			
			boolean fileExists = fileCreator(filePath);
			
			if (fileExists == true) {
				database.updateCourses(user.getCurrentCourses(), filePath);
	        }
	        
	        else if (fileExists == false) {
	        	database.loadCourses(user.getCurrentCourses(), email);
	        }
		}
		
		else if(listType.equals("physItem")) {
			fileName += "_physItem_data.csv";
			filePath += fileName;
			
			boolean fileExists = fileCreator(filePath);
			
			if (fileExists == true) {
				database.updatePhysItems(user.getPhysicalItemList(), filePath);
	        }
	        
	        else if (fileExists == false) {
	        	database.loadPhysItems(user.getPhysicalItemList(), email);
	        }
		}
	}
	
	public static void getList(Faculty user, String listType, String path, String email) throws Exception {

		LibraryDatabase database = LibraryDatabase.getInstance();
		String fileName = email;
		String filePath = path;
		
		if(listType.equals("digItem")) {
			fileName += "_digItem_data.csv";
			filePath += fileName;
			
			boolean fileExists = fileCreator(filePath);
			
			if (fileExists == true) {
	        	database.updateDigItems(user.getCourseBookHistory(), filePath);
	        }
	        
	        else if (fileExists == false) {
	        	database.loadDigItems(user.getCourseBookHistory(), email);
	        }
		}
		
		else if(listType.equals("courses")) {
			fileName += "_course_data.csv";
			filePath += fileName;
			
			boolean fileExists = fileCreator(filePath);
			
			if (fileExists == true) {
				database.updateCourses(user.getCurrentCourses(), filePath);
	        }
	        
	        else if (fileExists == false) {
	        	database.loadCourses(user.getCurrentCourses(), email);
	        }
		}
		
		else if(listType.equals("physItem")) {
			fileName += "_physItem_data.csv";
			filePath += fileName;
			
			boolean fileExists = fileCreator(filePath);
			
			if (fileExists == true) {
				database.updatePhysItems(user.getPhysicalItemList(), filePath);
	        }
	        
	        else if (fileExists == false) {
	        	database.loadPhysItems(user.getPhysicalItemList(), email);
	        }
		}
	}
	
	public static void getList(Account user, String listType, String path, String email) throws Exception {

		LibraryDatabase database = LibraryDatabase.getInstance();
		String fileName = email;
		String filePath = path;
		
		if(listType.equals("physItem")) {
			fileName += "_physItem_data.csv";
			filePath += fileName;
			
			boolean fileExists = fileCreator(filePath);
			
			if (fileExists == true) {
				database.updatePhysItems(user.getPhysicalItemList(), filePath);
	        }
	        
	        else if (fileExists == false) {
	        	database.loadPhysItems(user.getPhysicalItemList(), email);
	        }
			
		}
	}
}
