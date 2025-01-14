import java.util.Date;

public class BookFactory extends PhysicalItemFactory {
	@Override
	public Book getPhysicalItem(String itemType, String name, String author, String edition,
			String publisherName, String itemID, String libLocation, int copyNumber, Date dueDate,
			boolean rentalEnabled, double price) throws Exception {
		Book book = new Book(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		return book;
	}
}
