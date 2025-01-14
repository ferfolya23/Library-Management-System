import java.util.Date;

public class Book extends PhysicalItem {
	
	public Book(Book book) {
		super(book);
	}

	public Book(String itemType, String name, String author, String edition, String publisherName, String itemID,
			String libLocation, int copyNumber, Date dueDate, boolean rentalEnabled, double price) throws Exception {
		super(itemType, name, author, edition, publisherName, itemID, libLocation, copyNumber, dueDate, rentalEnabled, price);
		// TODO Auto-generated constructor stub
	}
	private boolean available;
	
    public boolean isAvailable(String name) {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }

	@Override
	public PhysicalItem clone() {
		// TODO Auto-generated method stub
		return new Book(this);
	}
}
