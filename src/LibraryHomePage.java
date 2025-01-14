import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LibraryHomePage {
	
	private static LibraryDatabase database;
    private Scanner input;
    
    public LibraryHomePage() throws Exception {
    	database = LibraryDatabase.getInstance();
		input = new Scanner(System.in);
    }
	
    protected void loggedOutHomePage() throws Exception {
        
    	while (true) {
        	System.out.println("L = Log In");
            System.out.println("R = Register");
            String nextPage = input.nextLine();
            
            if (nextPage.equals("L")) {
            	login();
            }
            
            if (nextPage.equals("R")) {
            	register();
            }
            else {
            	System.out.println("Please enter a valid input!");
            }
            
    	}
    }
    
    private void loggedInHomePageFaculty(Faculty user) throws Exception {
    	// used for testing course database updating + testing updateCourseBook in Course
//    	DigitalItem newCourseBook = new DigitalItem("Textbook","Literature","Shakespearean Plays","William Shakespeare","Complete","Norton");
//    	Course testCourse = user.getCurrentCourses().get(0);
//    	testCourse.updateCourseBook(user, newCourseBook);
    	
    	ArrayList<PhysicalItem> userPhysicalItems = user.getPhysicalItemList();
    	ArrayList<DigitalItem> courseBookHistory = user.getCourseBookHistory();
    	
    	System.out.println("Login completed!\n");
    	
    	//used for testing rent and return
//    	System.out.println("Trial Rental:");
//    	for (PhysicalItem p : database.physItemsDB) {
//    		System.out.println("Name: " + p.getName());
//			System.out.println("Author: " + p.getAuthor());
//			System.out.println("Item Type: " + p.getItemType());
//			p.rentCopy(user);
//			System.out.println("\n");
//    	}
//    	
//    	System.out.println("Trial Return:");
//    	for (PhysicalItem p : database.physItemsDB) {
//    		System.out.println("Name: " + p.getName());
//			System.out.println("Author: " + p.getAuthor());
//			System.out.println("Item Type: " + p.getItemType());
//			p.returnCopy(user);
//			System.out.println("\n");
//    	}
    	
    	for (PhysicalItem p : userPhysicalItems) {
			if (p.getDueDate() != null) {
				System.out.println("Name: " + p.getName());
				System.out.println("Author: " + p.getAuthor());
				System.out.println("Item Type: " + p.getItemType());
				System.out.println("Due Date: " + new SimpleDateFormat("MM-dd-yy HH:mm:ss").format(p.getDueDate()));
				System.out.println(((PhysicalItem) p).warningString(user));
				if (p.calculateFine() > 0.0) {
					System.out.println("Fine: " + p.calculateFine());
				}
				System.out.println("\n");

			}
    	}
    	
    	//faculty's current courses
    	System.out.println("Current Courses:");
    	for (Course c : user.currentCourses) {
    		System.out.println("Course Name: " + c.courseName);
    		System.out.println("Course Textbook: " + c.getCurrentCourseBook().getName() + "\n");
    	}
    	
    	
    	// a variation of this should ONLY be used for students / faculty
    	System.out.println("Previous Course Texts:");
    	for (DigitalItem d : courseBookHistory) {
    		System.out.println("Name: " + d.getName());
    		System.out.println("Author: " + d.getAuthor());
    		System.out.println("Item Type: " + d.getItemType() + "\n");
		
    	}
    	
    	System.exit(0);
    }
    
    private void loggedInHomePageStudent(Student user) throws Exception {
    	
    	ArrayList<PhysicalItem> userPhysicalItems = user.getPhysicalItemList();
    	ArrayList<DigitalItem> userDigitalItems = user.getDigitalCourseBooks();
    	
    	System.out.println("Login completed!\n");
    	System.out.println("Rentals:");
    	for (PhysicalItem p : userPhysicalItems) {
			if (p.getDueDate() != null) {
				System.out.println("Name: " + p.getName());
				System.out.println("Author: " + p.getAuthor());
				System.out.println("Item Type: " + p.getItemType());
				System.out.println("Due Date: " + new SimpleDateFormat("MM-dd-yy HH:mm:ss").format(p.getDueDate()));

				System.out.println(((PhysicalItem) p).warningString(user));
				if (p.calculateFine() > 0.0) {
					System.out.println("Fine: " + p.calculateFine());
				}
				System.out.println("\n");

			}
    	}
    	
    	//used for testing rent and return
//    	System.out.println("Trial Rental:");
//    	for (PhysicalItem p : database.physItemsDB) {
//    		System.out.println("Name: " + p.getName());
//			System.out.println("Author: " + p.getAuthor());
//			System.out.println("Item Type: " + p.getItemType());
//			p.rentCopy(user);
//			System.out.println("\n");
//    	}
    	
    	// a variation of this should ONLY be used for students / faculty
    	System.out.println("E-Books:");
    	for (DigitalItem d : userDigitalItems) {
    		System.out.println("Name: " + d.getName());
    		System.out.println("Author: " + d.getAuthor());
    		System.out.println("Item Type: " + d.getItemType() + "\n");
		
    	}
    	
    	System.exit(0);
    }
    
    private void loggedInHomePageGeneral(Account user) throws Exception {
    	
    	ArrayList<PhysicalItem> userPhysicalItems = user.getPhysicalItemList();
    	//ArrayList<DigitalItem> userDigitalItems = user.getDigitalCourseBooks();
    	//ArrayList<DigitalItem> userDigitalItems = user.getCourseBookHistory();
    	
    	System.out.println("Login completed!\n");
    	System.out.println("Rentals:");
    	for (PhysicalItem p : userPhysicalItems) {
			if (p.getDueDate() != null) {
				System.out.println("Name: " + p.getName());
				System.out.println("Author: " + p.getAuthor());
				System.out.println("Item Type: " + p.getItemType());
				System.out.println("Due Date: " + new SimpleDateFormat("MM-dd-yy HH:mm:ss").format(p.getDueDate()));
				System.out.println(((PhysicalItem) p).warningString(user));
				if (p.calculateFine() > 0.0) {
					System.out.println("Fine: " + p.calculateFine());
				}
				System.out.println("\n");

			}
    	}
    	
    	System.exit(0);
    	
    	// TODO
    	// Displays list of hard cover books that a user is renting, plus their due dates.
    	// Prompts warnings for books with due dates less than 24 hours.
    	// Prompts DIFF warning if book is past due date.
    	// Also contains buttons for accessing common user tasks like search, rent, etc.
    }
    
    public void managementPortal(LibraryManager manager) throws Exception {
    	for (PhysicalItem p : database.physItemsDB) {
    		System.out.println("Name: " + p.getName());
    		System.out.println("Author: " + p.getAuthor());
    		System.out.println("Item Type: " + p.getItemType());
    		System.out.println("Due Date: " + p.getDueDate());
    		System.out.println("[DISABLE]\n");
    		//Placeholder for button on each Item panel. When clicked, changes to [ENABLE].
    		//GUI panel would print info about Item, but ALSO hold a direct reference to it.
    		//This means that the search below will not need to be done to retrieve the Item.
    	}
    	
    	//Instead of following print statements, assume there is "Add Physical Item" and "Add Digital Item" buttons.
    	System.out.println("AP = Add Physical Item");
    	System.out.println("AD = Add Digital Item");
    	System.out.println("E = Add Physical Item");
    	System.out.println("D = Add Physical Item");
    	String userInput = input.nextLine();
    	
    	if (userInput.equals("AD")) {
    		System.out.println("Select Item Type: ");
    		String itemType =  input.nextLine();
    		System.out.println("Select Genre: " );
			String genre = input.nextLine();
			System.out.println("Enter Name: ");
			String name = input.nextLine();
			System.out.println("Enter Author Name: ");
			String author = input.nextLine();
			System.out.println("Enter Edition: ");
			String edition = input.nextLine();
			System.out.println("Enter Publisher: ");
			String publisherName = input.nextLine();
			
			DigitalItem newDigItem = new DigitalItem(itemType, genre, name, author, edition, publisherName);
			manager.addDigitalItem(newDigItem);
    	}
    	else if (userInput.equals("AP")) {
    		System.out.println("Select Item Type: ");
    		String itemType =  input.nextLine();
			System.out.println("Enter Name: ");
			String name = input.nextLine();
			System.out.println("Enter Author Name: ");
			String author = input.nextLine();
			System.out.println("Enter Edition: ");
			String edition = input.nextLine();
			System.out.println("Enter Publisher: ");
			String publisherName = input.nextLine();
			System.out.println("Enter Item ID: " );
			String itemID = input.nextLine();
			System.out.println("Enter Library Location: " );
			String libLocation = input.nextLine();
			//it can be assumed that an item that is only offered for sale just has rentalEnabled = false
//			System.out.println("Can this item be accessed for free? (Enter true or false.) " );
//			boolean rentalEnabled = Boolean.valueOf(input.nextLine());
//			System.out.println("If for sale, enter price: " );
//			double price = Double.parseDouble(input.nextLine());
			
			//These values will be the same by default for every book created. Everything below is needed to make generation / updating CSVs work properly.
			int copyNumber = 20;
			Date dueDate = null;
			boolean rentalEnabled = true;
			double price = -1.0;
			
			PhysicalItem newPhysItem = database.physicalItemGenerator(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
			manager.addPhysicalItem(newPhysItem);
    	}
    	else if (userInput.equals("E")) {
    		System.out.println("Item: Enabled");
    	}
    	else if (userInput.equals("D")) {
    		System.out.println("Item: Disabled");
    	}
    	
    	System.exit(0);
    }
    
    private void register() throws Exception{
    	LibraryHomePage newAccount = new LibraryHomePage();
    	String email;
    	String password;
    	
    	while (true) {
    		System.out.println("Enter email: ");
            email = input.nextLine();
            
            if (newAccount.isValidEmail(email)) {
                break;
            }
            System.out.println("Please enter a valid email.");
    	}
 	
        while (true) {          
            System.out.println("Enter password: ");
            password = input.nextLine();
            
            if (newAccount.isStrongPassword(password)) {
                break; 
            }
            
            System.out.println("Password is not strong enough. Please make a new password with the following requirements:");
            System.out.println("- At least 8 characters long");
            System.out.println("- At least one uppercase letter");
            System.out.println("- At least one lowercase letter");
            System.out.println("- At least one digit");
            System.out.println("- At least one symbol");
        }
        
        // This would be replaced with a button in implementation.
        System.out.println("Select Account Type: ");
        String accType = input.nextLine();
        
        boolean verifiedByManager = newAccount.additionalValidation(email);
        
        if ((verifiedByManager == false) && !(accType.equals("Visitor"))) {
        	System.out.println("Your account could not be validated. Please try signing up as a Visitor instead.");
            loggedOutHomePage();
        }
        
        Account accountExists = database.iterateDB(email, password);
        
        if (accountExists != null) {
        	System.out.println("You already have an account. Please try logging in instead!");
        }
        else {
            // TODO - Add some validation method prior to account creation if not Visitor.
    		database.accountGenerator(email, password, accType, 0, 0, false);
    		database.updateAccounts();
    		System.out.println("Registration successful! Please login.");
        }
        
        loggedOutHomePage();
    }
    

	private void login() throws Exception{
    	Account registeredAccount = null;
    	
    	String MGR_email = "mgr_access";
    	String MGR_password = "password";
    	
    	while (true) {
    		
    		System.out.println("Enter email:");
            String email = input.nextLine();
            
            System.out.println("Enter password:");
            String password = input.nextLine();
            
            if (email.equals(MGR_email) && password.equals(MGR_password)) {
            	LibraryManager manager = new LibraryManager(email, password);
            	managementPortal(manager);
            }
            
            else {
                registeredAccount = database.iterateDB(email, password);
                
                if (registeredAccount != null) {
                	break;
                }
            }
            
            System.out.println("Incorrect credentials, please try again!");
    	}
    	
    	// Maybe have infoExists provided as input to load user profile to loggedInHomePageGeneral.
    	
    	String accType = String.valueOf(registeredAccount.getClass());
    	
    	if (accType.equals("class Faculty")) {
    		loggedInHomePageFaculty((Faculty) registeredAccount);
    	}
    	
    	else if (accType.equals("class Student")) {
    		loggedInHomePageStudent((Student) registeredAccount);
    	}
    	else {
    		loggedInHomePageGeneral(registeredAccount);
    	}
    }

	boolean isValidEmail(String email) {
		return email.contains("@");
	}
    
    boolean additionalValidation(String email) {
		return email.endsWith("yorku.ca");
	}
    
	boolean isStrongPassword(String password) {
    	
        if (password == null || password.length() < 8) {
            return false; // Password should be at least 8 characters long
        }
        
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasNumber = false;
        boolean hasSymbol = false;
        
        // Loop through each character in the password
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else {
                // Assuming symbols are any characters that are not letters or digits
                hasSymbol = true;
            }
        }
        
        // Check if all criteria are met
        return hasUpperCase && hasLowerCase && hasNumber && hasSymbol;
    }
}
