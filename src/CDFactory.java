import java.util.Date;

public class CDFactory extends PhysicalItemFactory {
	@Override
	public CD getPhysicalItem(String itemType, String name, String author, String edition,
			String publisherName, String itemID, String libLocation, int copyNumber, Date dueDate,
			boolean rentalEnabled, double price) throws Exception {
		CD CD = new CD(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		return CD;
	}
}
