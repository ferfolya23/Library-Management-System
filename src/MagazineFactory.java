import java.util.Date;

public class MagazineFactory extends PhysicalItemFactory {
	@Override
	public Magazine getPhysicalItem(String itemType, String name, String author, String edition,
			String publisherName, String itemID, String libLocation, int copyNumber, Date dueDate,
			boolean rentalEnabled, double price) throws Exception {
		Magazine magazine = new Magazine(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		return magazine;
	}
}
