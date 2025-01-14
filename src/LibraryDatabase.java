import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class LibraryDatabase implements IterableCollection{
	private static LibraryDatabase database;
	private ArrayList<Account> users;
	protected ArrayList<DigitalItem> digItemsDB;
	protected ArrayList<PhysicalItem> physItemsDB;
	protected ArrayList<Course> coursesDB;
	protected String path;
    
    private LibraryDatabase() throws Exception {
		users = new ArrayList<Account>();
		digItemsDB = new ArrayList<DigitalItem>();
		physItemsDB = new ArrayList<PhysicalItem>();
		coursesDB = new ArrayList<Course>();
		path = "src/csv/";
    }
    
    public static LibraryDatabase getInstance() throws Exception {
    	if (database == null) {
    		database = new LibraryDatabase();
    	}
		return database;
    }
	
	public void loadAccounts() throws Exception{
		String filePath = path + "account_data.csv";
		CsvReader reader = new CsvReader(filePath);
		reader.readHeaders();
		
		while(reader.readRecord()){
			//email,password,accType,itemsBorrowed,overdueItems,accountLocked
			String email = reader.get("email");
			String password = reader.get("password");
			String accType = reader.get("accType");
			int itemsBorrowed = Integer.parseInt(reader.get("itemsBorrowed"));
			int overdueItems = Integer.parseInt(reader.get("overdueItems"));
			boolean accountLocked = Boolean.parseBoolean(reader.get("accountLocked"));
			accountGenerator(email, password, accType, itemsBorrowed, overdueItems, accountLocked);
		}
	}
	
	public void loadDigItems(ArrayList<DigitalItem> digItemList, String email) throws Exception{
		
		String filePath = path;
		
		if (email == null) {
			filePath += "digItem_database.csv";
		}
		
		else {
			filePath += email + "_digItem_data.csv";
		}
		
		CsvReader reader = new CsvReader(filePath);
		reader.readHeaders();
		
		while(reader.readRecord()){
			
			String itemType = reader.get("itemType");
			String genre = reader.get("genre");
			String name = reader.get("name");
			String author = reader.get("author");
			String edition = reader.get("edition");
			String publisherName = reader.get("publisherName");
			DigitalItem newDigItem = new DigitalItem(itemType, genre, name, author, edition, publisherName);
			
			//checks if course textbook is in database, if so uses existing textbook to add to student / faculty list
	        if (email != null) {
	        	
	        	int i = 0;
	        	for (DigitalItem c : database.digItemsDB) {
		        	DigitalItem databaseTextbook = digItemsDB.get(i);
		        	
		        	//
		        	if (databaseTextbook.isEqualTo(newDigItem)) {
		        		//make so that every list is using the same textbook object
						digItemList.add(databaseTextbook);
		        		break;
		        	}
		        	
		        	i++;
		        }
	        }
			
	        else {
	        	digItemList.add(newDigItem);
	        }
		}
	}
	
	public void loadPhysItems(ArrayList<PhysicalItem> physItemList, String email) throws Exception{
		
		String filePath = path;
		
		if (email == null) {
			filePath += "physItem_database.csv";
		}
		
		else {
			filePath += email + "_physItem_data.csv";
		}
		
		CsvReader reader = new CsvReader(filePath);
		reader.readHeaders();
		
		while(reader.readRecord()){
			
			String itemType = reader.get("itemType");
			String name = reader.get("name");
			String author = reader.get("author");
			String edition = reader.get("edition");
			String publisherName = reader.get("publisherName");
			String itemID = reader.get("itemID");
			String libLocation = reader.get("libLocation");
			int copyNumber = Integer.parseInt(reader.get("copyNumber"));
			
			String dueDateString = reader.get("dueDate");
	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
	        Date dueDate = null;
	        try {
	            dueDate = dateFormat.parse(dueDateString);
	            System.out.println(name + " Parsed Date: " + dueDate);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        
	        boolean rentalEnabled = Boolean.valueOf(reader.get("rentalEnabled"));
	        double price = Double.parseDouble(reader.get("price"));
	        
			PhysicalItem newPhysItem = physicalItemGenerator(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
			physItemList.add(newPhysItem);
		}
	}
	

	public void loadRentableBooks(ArrayList<PhysicalItem> physItemList) throws Exception{
		for (PhysicalItem p : database.physItemsDB) {
			if (p.rentalEnabled == true) {
				physItemList.add(p);
			}
		}
	}
	
	public void loadDisabledItems(ArrayList<PhysicalItem> physItemList) throws Exception{
		for (PhysicalItem p : database.physItemsDB) {
			if (p.rentalEnabled == false) {
				physItemList.add(p);
			}
		}
	}
	
	public void loadPurchasableBooks(ArrayList<PhysicalItem> physItemList) throws Exception{
		
		for (PhysicalItem p : database.physItemsDB) {
			if (p.price > 0) {
				physItemList.add(p);
			}
		}
	}

	//May have errors. Please double check
	public void loadCourses(ArrayList<Course> courseList, String email) throws Exception{
		
		String filePath = path;
		
		if (email == null) {
			filePath += "course_database.csv";
		}
		
		else {
			filePath += email + "_course_data.csv";
		}
		
		CsvReader reader = new CsvReader(filePath);
		reader.readHeaders();
		
		while(reader.readRecord()){
			
			String courseName = reader.get("courseName");
			String itemType = reader.get("itemType");
			String genre = reader.get("genre");
			String name = reader.get("name");
			String author = reader.get("author");
			String edition = reader.get("edition");
			String publisherName = reader.get("publisherName");
			
			String courseEndDateString = reader.get("courseEndDate");
	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
	        Date courseEndDate = null;
	        try {
	        	courseEndDate = dateFormat.parse(courseEndDateString);
	            System.out.println(name + " Parsed Date: " + courseEndDate);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        
	        
	        //if student is enrolled in a course, it finds it from the already imported cours_database.csv
	        if (email != null)
	        {
	        	int i = 0;
	        	for (Course c : database.coursesDB) {
		        	Course databaseCourse = coursesDB.get(i);
		        	if (databaseCourse.getCourseName().equals(courseName)) {
		        		courseList.add(databaseCourse);
		        		break;
		        	}
		        	
		        	i++;
		        }
	        }
	        
	        
	      //checks if course textbook is in database, if so uses existing textbook to make course
	        if (email == null) {
	        	
	        	DigitalItem newCourseBook = new DigitalItem(itemType, genre, name, author, edition, publisherName);
	        	
	        	int i = 0;
	        	for (DigitalItem c : database.digItemsDB) {
		        	DigitalItem databaseTextbook = digItemsDB.get(i);
		        	
		        	//
		        	if (databaseTextbook.isEqualTo(newCourseBook)) {
		        		//make so that every list is using the same course object
						Course newCourse = new Course(courseName, databaseTextbook, courseEndDate);
						courseList.add(newCourse);
		        		break;
		        	}
		        	
		        	i++;
		        }
	        }
		}
	}
	
	public void updateAccounts() throws Exception{
		try {		
				String filePath = path + "account_data.csv";
				CsvWriter csvOutput = new CsvWriter(new FileWriter(filePath, false), ',');
				//email,password,accType,itemsBorrowed,overdueItems,accountLocked
				csvOutput.write("email");
				csvOutput.write("password");
		    	csvOutput.write("accType");
		    	csvOutput.write("itemsBorrowed");
		    	csvOutput.write("overdueItems");
		    	csvOutput.write("accountLocked");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(Account u: users){
					csvOutput.write(u.getEmail());
					csvOutput.write(u.getPass());
					csvOutput.write(u.getAccType());
					csvOutput.write(String.valueOf(u.getItemsBorrowed()));
					csvOutput.write(String.valueOf(u.getOverdueItems()));
					csvOutput.write(String.valueOf(u.isAccountLocked()));
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	// Make updatePhysItemsUser
	// Make updatePhysItemsDB
	
	// Should work for both main DB file and user digItem files?
	// To update the main digItem_database.csv, you must modify this code.
	public void updateDigItems(ArrayList<DigitalItem> digItemList, String filePath) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(filePath, false), ',');
		    	//itemType,genre,name,author,edition,publisherName
				csvOutput.write("itemType");
				csvOutput.write("genre");
				csvOutput.write("name");
				csvOutput.write("author");
				csvOutput.write("edition");
				csvOutput.write("publisherName");
				csvOutput.endRecord();
				
				// else assume that the file already has the correct header line
				// write out a few records
				for(DigitalItem d: digItemList){
					csvOutput.write(d.getItemType());
					csvOutput.write(d.getGenre());
					csvOutput.write(d.getName());
					csvOutput.write(d.getAuthor());
					csvOutput.write(d.getEdition());
					csvOutput.write(d.getPublisherName());
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
		// Should work for both main DB file and user physItem files?
		// To update the main digItem_database.csv, you must modify this code.
		public void updatePhysItems(ArrayList<PhysicalItem> physItemList, String filePath) throws Exception{
			try {		
					CsvWriter csvOutput = new CsvWriter(new FileWriter(filePath, false), ',');
			    	//itemType,name,author,edition,publisherName,itemID,libLocation,copyNumber,dueDate,rentalEnabled,price
					csvOutput.write("itemType");
					csvOutput.write("name");
					csvOutput.write("author");
					csvOutput.write("edition");
					csvOutput.write("publisherName");
					csvOutput.write("itemID");
					csvOutput.write("libLocation");
					csvOutput.write("copyNumber");
					csvOutput.write("dueDate");
					csvOutput.write("rentalEnabled");
					csvOutput.write("price");
					csvOutput.endRecord();
					
					// else assume that the file already has the correct header line
					// write out a few records
					for(PhysicalItem p: physItemList){
						csvOutput.write(p.getItemType());
						csvOutput.write(p.getName());
						csvOutput.write(p.getAuthor());
						csvOutput.write(p.getEdition());
						csvOutput.write(p.getPublisherName());
						csvOutput.write(p.getItemID());
						csvOutput.write(p.getLibLocation());
						csvOutput.write(String.valueOf(p.getCopyNumber()));
						Date dueDate = p.getDueDate();
				        String dueDateString = null;

				        if (dueDate != null) {
				            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				            dueDateString = dateFormat.format(dueDate);
				        }
				        
				        else {
				        	dueDateString = "null";
				        }
				        
						csvOutput.write(dueDateString);
						csvOutput.write(String.valueOf(p.isRentalEnabled()));
						csvOutput.write(String.valueOf(p.getPrice()));
						csvOutput.endRecord();
					}
					csvOutput.close();
				
				}catch (Exception e) {
					e.printStackTrace();
				}
		}

		public void updateCourses(ArrayList<Course> courseList, String filePath) throws Exception{
			try {		
					CsvWriter csvOutput = new CsvWriter(new FileWriter(filePath, false), ',');
			    	//itemType,genre,name,author,edition,publisherName
					csvOutput.write("courseName");
					csvOutput.write("itemType");
					csvOutput.write("genre");
					csvOutput.write("name");
					csvOutput.write("author");
					csvOutput.write("edition");
					csvOutput.write("publisherName");
					csvOutput.write("courseEndDate");
					csvOutput.endRecord();
					
					// else assume that the file already has the correct header line
					// write out a few records
					for(Course c: courseList){
						DigitalItem ccb = c.getCurrentCourseBook();
						csvOutput.write(c.getCourseName());
						csvOutput.write(ccb.getItemType());
						csvOutput.write(ccb.getGenre());
						csvOutput.write(ccb.getName());
						csvOutput.write(ccb.getAuthor());
						csvOutput.write(ccb.getEdition());
						csvOutput.write(ccb.getPublisherName());
						
						Date courseEndDate = c.getCourseEndDate();
				        String courseEndDateString = null;

				        if (courseEndDate != null) {
				            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				            courseEndDateString = dateFormat.format(courseEndDate);
				        }
				        
				        else {
				        	courseEndDateString = "null";
				        }
				        
						csvOutput.write(courseEndDateString);
						csvOutput.endRecord();
					}
					csvOutput.close();
				
				}catch (Exception e) {
					e.printStackTrace();
				}
		}
		
	// This method is used for 2 things - creating brand new registered accounts, and importing pre-existing ones as Account objects.
	// Could use Factory Pattern to make less cluttered and more efficient.
	public Account accountGenerator(String email, String password, String accType, int itemsBorrowed, int itemsOverdue, boolean accountLocked) throws Exception {
		
		Account user = null;
	    String[] emailSplitter = email.split("@", 2);
	    String splitEmail = emailSplitter[0];
	    
	    if (accType.equals("Visitor")) {
	    	user = new Visitor(new ConcreteAccountDecorator(email, password, accType, itemsBorrowed, itemsOverdue, accountLocked));
	    	users.add(user);
	    	ListGenerator.getList(user, "physItem", path, splitEmail);
	    	return user;
	    }
	    else if (accType.equals("Student")) {
	    	Student userStudent = new Student(new ConcreteAccountDecorator(email, password, accType, itemsBorrowed, itemsOverdue, accountLocked));
	    	users.add(userStudent);
	    	ListGenerator.getList(userStudent, "courses", path, splitEmail);
	    	
	    	ArrayList<DigitalItem> digitalItems = new ArrayList<DigitalItem>();
	    	ListGenerator.getList(userStudent, "digItem", path, splitEmail);
	    	ListGenerator.getList(userStudent, "physItem", path, splitEmail);
	    	return userStudent;
	    }
	    
	    else if (accType.equals("Faculty")) {
	    	Faculty userFaculty = new Faculty(new ConcreteAccountDecorator(email, password, accType, itemsBorrowed, itemsOverdue, accountLocked));
	    	users.add(userFaculty);
	    	ListGenerator.getList(userFaculty, "courses", path, splitEmail);
	    	ListGenerator.getList(userFaculty, "digItem", path, splitEmail);
	    	ListGenerator.getList(userFaculty, "physItem", path, splitEmail);
	    	return userFaculty;
	    }
	    
	    else if (accType.equals("NonFaculty")) {
	    	user = new NonFaculty(new ConcreteAccountDecorator(email, password, accType, itemsBorrowed, itemsOverdue, accountLocked));
	    	users.add(user);
	    	ListGenerator.getList(user, "physItem", path, splitEmail);
	    	return user;
	    }
	    
	    else {
	    	throw new Exception("Invalid Account Type");
	    }
	}
	
	public Account iterateDB(String email, String password) throws Exception{
		
		int i = 0;
		Account account;
		while (i < users.size()) {
		    account = users.get(i);
		    
		    String DB_email = account.getEmail();
		    String DB_password = account.getPass();
		    
		    if (email.equals(DB_email)) {
		    	if (password.equals(DB_password)) {
		    		return account;
		    	}
		    	else {
		    		System.out.println("You already have an account linked to this email, but your password is incorrect.");
		    		break;
		    	}
		    }
		    
		    i++;
		}
		
		return null;
	}
	
	protected PhysicalItem physicalItemGenerator(String itemType, String name, String author, String edition, String publisherName, 
			String itemID, String libLocation, int copyNumber, Date dueDate, boolean rentalEnabled, double price) throws Exception {
		
		PhysicalItem newItem = null;
		
		if (itemType.equals("CD")) {
			CDFactory cdGen = new CDFactory();
			newItem = cdGen.getPhysicalItem(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		}
		
		else if (itemType.equals("DVD")) {
			DVDFactory DVDGen = new DVDFactory();
			newItem = DVDGen.getPhysicalItem(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		}
		
		else if (itemType.equals("Book")) {
			BookFactory bookGen = new BookFactory();
			newItem = bookGen.getPhysicalItem(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		}
		
		else if (itemType.equals("Magazine")) {
			MagazineFactory magGen = new MagazineFactory();
			newItem = magGen.getPhysicalItem(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		}
		
		else if (newItem == null) {
		    throw new NullPointerException("newPhysItem cannot be null. There was an error with determining the item type.");
		}
		
		return newItem;
	}
	
	protected ArrayList<Account> getUsers() {
		return users;
	}

	@Override
	public Iterator createIterator(ArrayList<Item> items) {
		return new ConcreteIterator(items);
	}

    // Method to get recommendations based on text similarity and same genres
    protected ArrayList<Item> getRecommendations(String searchQuery) {
    	ArrayList<Item> recommendations = new ArrayList<Item>();
    	String itemName;
    	boolean similarTitle;
    	
    	for (PhysicalItem p : database.physItemsDB) {
        	itemName = p.getName();
        	similarTitle = checkTitleSimilarity(searchQuery, itemName);
            if (similarTitle) {
                recommendations.add(p);
            }
        }
    	
        for (DigitalItem d : database.digItemsDB) {
        	itemName = d.getName();
        	similarTitle = checkTitleSimilarity(searchQuery, itemName);
            if (similarTitle) {
                recommendations.add(d);
            }
        }
        
        return recommendations;
    }

	private boolean checkTitleSimilarity(String searchQuery, String itemName) {
		String[] splitQuery = searchQuery.split(" ");
		String[] splitItemName = itemName.split(" ");
		
		int queryLength = splitQuery.length;
		int itemNameLength = splitItemName.length;
		
		for (int i = 0; i < queryLength; i++) {
			for (int j = 0; j < itemNameLength; j++) {
				boolean similarWordExists = splitQuery[i].equalsIgnoreCase(splitItemName[j]);
				if (similarWordExists) {
					return true;
				}
			}
		}
		
		return false;
	}

	public void purgeFinishedCourses() throws Exception {
		
		// creates list of students only
		ArrayList<Student> allStudents = new ArrayList<Student>();
		for (Account a : database.getUsers()) {
			if (a.getAccType().equals("Student")) {
				allStudents.add((Student) a);
			}
		}
		
		// iterates through all courses
		int i = 0;
    	for (Course c : database.coursesDB) {
        	Course course = coursesDB.get(i);
        	Date courseEndDate = course.getCourseEndDate();
        	Date curr = new Date();
    		long timeDiff = courseEndDate.getTime() - curr.getTime();
    		
    		// checks if current date is past course end date
    		if (timeDiff <= 0) {
    			
    			for (Student s : allStudents) {
    				ArrayList<Course> studentCourseList = s.getCurrentCourses();
    				ArrayList<DigitalItem> studentCourseBooks = s.getDigitalCourseBooks();
    				ArrayList<DigitalItem> studentTextbooks = s.getDigitalCourseBooks();
    				boolean courseRemoved = studentCourseList.remove(course);
    				boolean bookRemoved = studentCourseBooks.remove(course.getCurrentCourseBook());
    				if (courseRemoved && bookRemoved) {
    					String[] emailSplitter = s.getEmail().split("@", 2);
    					String studentCoursesPath = path + emailSplitter[0] + "_course_data.csv";
    					String studentDigItemsPath = path + emailSplitter[0] + "_digItem_data.csv";
        				
        				database.updateCourses(studentCourseList, studentCoursesPath);
        				database.updateDigItems(studentTextbooks, studentDigItemsPath);
    				}
    			}
    		}
    		
        	i++;
        }
    }
}
