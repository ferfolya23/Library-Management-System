import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.print.DocFlavor.STRING;


// NOTE - should be an interface, however trying to make it an interface does not seem possible
// when inhereting from an abstract class
public abstract class PhysicalItem extends Item {
	
	public int copyNumber;
	public Date dueDate;
	public String itemID;
	public String libLocation;
	public boolean rentalEnabled;
	// if price == null, then not for sale
	public double price;
	private static LibraryDatabase database;
	
	public PhysicalItem(PhysicalItem physicalItem) {
		super(physicalItem);
		this.itemID = physicalItem.itemID;
		this.libLocation = physicalItem.libLocation;
		this.copyNumber = physicalItem.copyNumber;
		this.dueDate = physicalItem.dueDate;
		this.rentalEnabled = physicalItem.rentalEnabled;
		this.price = physicalItem.price;
	}
	
	public PhysicalItem(String itemType, String name, String author, String edition, String publisherName, String itemID, String libLocation, int copyNumber, Date dueDate, boolean rentalEnabled, double price) throws Exception {
		super(itemType, name, author, edition, publisherName);
		this.itemID = itemID;
		this.libLocation = libLocation;
		this.copyNumber = copyNumber;
		this.dueDate = dueDate;
		this.rentalEnabled = rentalEnabled;
		this.price = price;
	}

	public void enable() throws Exception {
		database = LibraryDatabase.getInstance();
		rentalEnabled = true;
	    //Update library info in entire database.
	    String databasePath = database.path + "physItem_database.csv";
	    database.updatePhysItems(database.physItemsDB, databasePath);
	}
	
	public void disable() throws Exception {
		database = LibraryDatabase.getInstance();
		rentalEnabled = false;
		//Update library info in entire database.
	    String databasePath = database.path + "physItem_database.csv";
	    database.updatePhysItems(database.physItemsDB, databasePath);
	}
	
	public void rentCopy(Account user) throws Exception {
		
		database = LibraryDatabase.getInstance();
		
		if (user.isAccountLocked() == true) {
			System.out.println("Invalid Operation. Your account has been locked.");
		}
		
		else if (rentalEnabled == false) {
			System.out.println("Invalid Operation. Rentals are currently disabled for this item.");
		}
		
		else if (user.getItemsBorrowed() > 10) {
			System.out.println("Invalid Operation. You are not allowed to take more than 10 items out at a time.");
		}
		
		else if (database.physItemsDB.size() <= 0) {
			System.out.println("Invalid Operation. There are currently no copies of this item left in the library.");
		}
		
		else {
			PhysicalItem rentedCopy = this.clone();
			PhysicalItem databaseEntry = this;
			//copyNumber = -1 indicates that user has taken out this copy and that it is not overdue.
			rentedCopy.setCopyNumber(-1);
			ArrayList<PhysicalItem> physicalItemDatabase = database.physItemsDB;
			
		    // Get current date
		    Calendar calendar = Calendar.getInstance();
		    // Add one month to the current date
		    calendar.add(Calendar.MONTH, 1);
		    rentedCopy.dueDate = calendar.getTime();
		    
		    user.getPhysicalItemList().add(rentedCopy);
		    user.setItemsBorrowed(user.getItemsBorrowed() + 1);
		    databaseEntry.setCopyNumber(databaseEntry.getCopyNumber() - 1);
		    
		    //Update account info.
		    database.updateAccounts();
			
		    //Update account item list.
			String[] emailSplitter = user.getEmail().split("@", 2);
			String splitEmail = database.path + emailSplitter[0] + "_physItem_data.csv";
		    database.updatePhysItems(user.getPhysicalItemList(), splitEmail);
		    
		    //Update library info in entire database.
		    String databasePath = database.path + "physItem_database.csv";
		    database.updatePhysItems(database.physItemsDB, databasePath);
		    
		    //Insert some procedure here that creates a clone / prototype of the item, sets the clone copyNumber to null, -1, or some other odd value, and adds to user's itemList.
		    //Changing copyNumber of user's cloned item may not be necessary and might make returning physItems easier.
		    
		    System.out.println("Enjoy! Due Date: " + rentedCopy.dueDate);
		    
			//TODO
			//Note - this implementation will involve cloning a copy of the book, and indicating that it is its own item.
		}
	}
	public void returnCopy(Account user) throws Exception {
		//TODO
		//Note - this implementation will involve "merging" the copy that the user took out back with the database.
		
		if (rentalEnabled == false) {
			System.out.println("Invalid Operation. Rentals for this item are currently disabled.");
		}
		
		else {
			database = LibraryDatabase.getInstance();
			ArrayList<PhysicalItem> userRentalsList = user.getPhysicalItemList();
			PhysicalItem returnedCopy = this;
			
			for (PhysicalItem r : userRentalsList) {
				if (r.getItemID().equals(returnedCopy.getItemID())) {
					returnedCopy = r;
					break;
				}
			}
			
			ArrayList<PhysicalItem> databaseList = database.physItemsDB;
			
			for (PhysicalItem p : databaseList) {
				if (p.isEqualTo(returnedCopy)) {
					
					if (user.getPhysicalItemList().remove(returnedCopy)) {
						System.out.println("Return success!");
					}
					
					p.setCopyNumber(p.getCopyNumber() + 1);
					user.setItemsBorrowed(user.getItemsBorrowed() - 1);
					
					//Update account info.
				    database.updateAccounts();
					
				    //Update account item list.
					String[] emailSplitter = user.getEmail().split("@", 2);
					String splitEmail = database.path + emailSplitter[0] + "_physItem_data.csv";
				    database.updatePhysItems(user.getPhysicalItemList(), splitEmail);
				    
				    //Update library info in entire database.
				    String databasePath = database.path + "physItem_database.csv";
				    database.updatePhysItems(database.physItemsDB, databasePath);
					
					break;
				}
			}
		}
	}
	
	public double calculateFine() {

		Date curr = new Date();
		long timeDiff = dueDate.getTime() - curr.getTime(); // gets the time in miliseconds
		long timeDiffDays = timeDiff/(24*60*60*1000); // converts to days
		double overdueFee;
		
		if (timeDiffDays > 0) {
			overdueFee = 0.0;
		}
		
		else {
			overdueFee = (timeDiffDays * 0.5) * (-1);
		}
		
		return overdueFee;
	}

	public String warningString(Account user) throws Exception{
		
		Date curr = new Date();
		database = LibraryDatabase.getInstance();
		long timeDiff = dueDate.getTime() - curr.getTime(); // gets the time in miliseconds
		long timeDiffHrs = timeDiff/(60*60*1000); // converts to hours
		long timeDiffDays = timeDiff/(24*60*60*1000); // converts to days

		String output = "";
		
		//copyNumber = -3 indicates that this copy is now lost.
		if (this.getCopyNumber() == -3) {
			output = String.format("The book: %s is now lost.", this.name);
			return output;
		}
		
		else if(timeDiffHrs < 0){  // checks if the book is past the due date
			
			//copyNumber = -1 indicates that user has taken out this copy and that it is not overdue.
			if (this.getCopyNumber() == -1) {
				//copyNumber = -2 indicates that this copy is now overdue for the user but not yet lost.
				this.setCopyNumber(-2);
				user.setOverdueItems(user.getOverdueItems() + 1);
			}
			
			if (timeDiffDays <= -15) {
				//copyNumber = -3 indicates that this copy is now lost.
				this.setCopyNumber(-3);
				
				String[] emailSplitter = user.getEmail().split("@", 2);
				String splitEmail = database.path + emailSplitter[0] + "_physItem_data.csv";
			    database.updatePhysItems(user.getPhysicalItemList(), splitEmail);
			    database.updateAccounts();
				
				return output;
			}
			
			if (user.getItemsBorrowed() >= 3) {
				user.setAccountLocked(true);
			}
			
			String[] emailSplitter = user.getEmail().split("@", 2);
			String splitEmail = database.path + emailSplitter[0] + "_physItem_data.csv";
		    database.updatePhysItems(user.getPhysicalItemList(), splitEmail);
		    database.updateAccounts();
			output = String.format("The book: %s OVERDUE PLEASE RETURN IT", this.name);
		}
		
		else if (timeDiffHrs > 0 && timeDiffHrs < 24) {  // checks if there is less than 24 hours left on the due date
			output = String.format("The book: %s is due in %d hours", this.name, timeDiffHrs);
		}
		
		else if(timeDiffDays == 1){ // checks if there is 1 day before the due date make it "Day" instead of days
			output = String.format("%d Day till %s is due for return", timeDiffDays, this.name);
		}
		
		else{  // makes it "days" if more than one day
			output = String.format("%d Days till %s is due for return", timeDiffDays, this.name);
		}
		
		return output;
	}
	
	public String getItemType() {
		return itemType;
	}

	
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getAuthor() {
		return author;
	}

	
	public void setAuthor(String author) {
		this.author = author;
	}

	
	public String getEdition() {
		return edition;
	}

	
	public void setEdition(String edition) {
		this.edition = edition;
	}

	
	public String getPublisherName() {
		return publisherName;
	}

	
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	public String getLibLocation() {
		return libLocation;
	}
	public void setLibLocation(String libLocation) {
		this.libLocation = libLocation;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public int getCopyNumber() {
		return copyNumber;
	}
	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}

	public boolean isRentalEnabled() {
		return rentalEnabled;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean isEqualTo(PhysicalItem hardCopy) {
		
		boolean typeIsSame = (this.itemType).equals(hardCopy.itemType);
		boolean nameIsSame = (this.name).equals(hardCopy.name);
		boolean authorIsSame = (this.author).equals(hardCopy.author);
		boolean editionIsSame = (this.edition).equals(hardCopy.edition);
		boolean pubIsSame = (this.publisherName).equals(hardCopy.publisherName);
		boolean sameID = (this.itemID).equals(hardCopy.itemID);
		boolean sameLoc = (this.libLocation).equals(hardCopy.libLocation);
		boolean samePrice = ((this.price) == (hardCopy.price));
		boolean sameContents = typeIsSame && nameIsSame && authorIsSame && editionIsSame && pubIsSame && sameID && sameLoc && samePrice;
		
		return sameContents;
	}
	
	public abstract PhysicalItem clone();
}