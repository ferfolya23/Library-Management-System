import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Client {
	public static void main(String [] args) throws Exception{
		LibraryDatabase database = LibraryDatabase.getInstance();
		LibraryHomePage homePage = new LibraryHomePage();
		// All loaders for main DB assume that CSV files already exist
		// DO NOT CHANGE ORDER OF THESE LOADERS! they are in this order for a reason
		database.loadPhysItems(database.physItemsDB, null);
		database.loadDigItems(database.digItemsDB, null);
		database.loadCourses(database.coursesDB, null);
		
		//tests search function
//		ArrayList<Item> recommendations = database.getRecommendations("Animal Farm");
//		
//		for (Item r : recommendations) {
//			System.out.println("Name: " + r.getName());
//			System.out.println("Author: " + r.getAuthor());
//			System.out.println("\n");
//		}
		
		database.loadAccounts();
		database.purgeFinishedCourses();
		//database.updateAccounts();
		//database.loadBankData();
		homePage.loggedOutHomePage();
	}
}