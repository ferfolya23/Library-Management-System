import java.util.Date;

public class DVD extends PhysicalItem {
	
	public DVD(DVD magazine) {
		super(magazine);
	}

	public DVD(String itemType, String name, String author, String edition, String publisherName, String itemID,
			String libLocation, int copyNumber, Date dueDate, boolean rentalEnabled, double price) throws Exception {
		super(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DVD clone() {
		return new DVD(this);
	}
}
