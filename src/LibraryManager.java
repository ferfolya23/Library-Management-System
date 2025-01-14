public class LibraryManager {
	protected String email;
	protected String password;
	private LibraryDatabase database;
	
	public LibraryManager(String email, String password) throws Exception {
		this.email = email;
		this.password = password;
		database = LibraryDatabase.getInstance();
	}
	
	// Visitor Pattern could be utilized so LibraryManager visits Add class (implements a VisitorInterface)
	public void addDigitalItem(DigitalItem digItem) throws Exception {
		//Insert update to csv database file.
		database.digItemsDB.add(digItem);
		database.updateDigItems(database.digItemsDB, (database.path + "digItem_database.csv"));
	}

	public void addPhysicalItem(PhysicalItem physItem) throws Exception {
		//Insert update to csv database file.
		database.physItemsDB.add(physItem);
		database.updatePhysItems(database.physItemsDB, (database.path + "physItem_database.csv"));
	}
	
	public void enablePhysItem(PhysicalItem physItem) throws Exception {
		//Insert update to csv database file.
		physItem.enable();
	}
	
	public void disablePhysItem(PhysicalItem physItem) throws Exception {
		//Insert update to csv database file.
		physItem.disable();
	}
}
