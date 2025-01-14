import java.util.Date;

public class CD extends PhysicalItem {
	
	public CD(CD cd) {
		super(cd);
	}

	public CD(String itemType, String name, String author, String edition, String publisherName, String itemID,
			String libLocation, int copyNumber, Date dueDate, boolean rentalEnabled, double price) throws Exception {
		super(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CD clone() {
		return new CD(this);
	}
}
