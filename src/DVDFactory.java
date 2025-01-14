import java.util.Date;

public class DVDFactory extends PhysicalItemFactory {
	@Override
	public DVD getPhysicalItem(String itemType, String name, String author, String edition,
			String publisherName, String itemID, String libLocation, int copyNumber, Date dueDate,
			boolean rentalEnabled, double price) throws Exception {
		DVD DVD = new DVD(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		return DVD;
	}
}
