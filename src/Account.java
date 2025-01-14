import java.util.ArrayList;
import java.util.Date;

public interface Account {
	
	public void openOnlineBook(DigitalItem digItem);
	public ArrayList<Item> search(String search);
	public void subToNews(DigitalItem digItem);
	public void rentBook(PhysicalItem physItem);
	public void returnBook(PhysicalItem physItem);
	public void request(DigitalItem b);
	public void printReqs();
	public void sort();
	public void purchaseItem(Item item);
	
	public ArrayList<PhysicalItem> getPhysicalItemList();

	public String getEmail();
	public String getPass();
	public String getAccType();
	public int getItemsBorrowed();
	public int getOverdueItems();
	public boolean isAccountLocked();
	
	public void setEmail(String email);
	public void setPassword(String pass);
	public void setAccType(String type);
	public void setItemsBorrowed(int itemsBorrowed);
	public void setOverdueItems(int overdueItems);
	public void setAccountLocked(boolean accountLocked);
	public boolean newerEdition(ArrayList<DigitalItem> digItemList, DigitalItem selectedItem);
	public void notifyNewEdition(ArrayList<DigitalItem> digItemList, DigitalItem selectedItem);
	boolean textbookAvailable(ArrayList<DigitalItem> digItemList, String searchQuery);
	void notifyManagement(ArrayList<DigitalItem> digItemList, String searchQuery);
}