
public class DigitalItem extends Item {
	
	String genre;

	public DigitalItem(String itemType, String genre, String name, String author, String edition, String publisherName) {
		super(itemType, name, author, edition, publisherName);
		this.genre = genre;
	}
	
	public DigitalItem(DigitalItem digitalItem) {
		super(digitalItem);
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public boolean isEqualTo(DigitalItem courseBook) {
		
		boolean typeIsSame = (this.itemType).equals(courseBook.itemType);
		boolean genreIsSame = (this.genre).equals(courseBook.genre);
		boolean nameIsSame = (this.name).equals(courseBook.name);
		boolean authorIsSame = (this.author).equals(courseBook.author);
		boolean editionIsSame = (this.edition).equals(courseBook.edition);
		boolean pubIsSame = (this.publisherName).equals(courseBook.publisherName);
		boolean sameContents = typeIsSame && genreIsSame && nameIsSame && authorIsSame && editionIsSame && pubIsSame;
		
		return sameContents;
	}
	
	@Override
	public DigitalItem clone() {
		return new DigitalItem(this);
	}
}
