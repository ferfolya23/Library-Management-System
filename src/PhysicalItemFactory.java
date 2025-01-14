import java.util.Date;

public abstract class PhysicalItemFactory {
	public abstract PhysicalItem getPhysicalItem(String itemType, String name, String author, String edition, String publisherName, String itemID,
			String libLocation, int copyNumber, Date dueDate, boolean rentalEnabled, double price) throws Exception;
}