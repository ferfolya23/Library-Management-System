
abstract class Item {

	public String itemType;
	public String name;
	public String author;
	public String edition;
	public String publisherName;
	
	public Item(Item item) {
		this.itemType = item.itemType;
		this.name = item.name;
		this.author = item.author;
		this.edition = item.edition;
		this.publisherName = item.publisherName;
	}
	
	public Item(String itemType, String name, String author, String edition, String publisherName) {
		this.itemType = itemType;
		this.name = name;
		this.author = author;
		this.edition = edition;
		this.publisherName = publisherName;
	}
	
	public abstract String getItemType();
	public abstract void setItemType(String itemType); 
	
	public abstract String getName();
	public abstract void setName(String name); 

	public abstract String getAuthor();
	public abstract void setAuthor(String author);

	public abstract String getEdition();
	public abstract void setEdition(String edition);

	public abstract String getPublisherName();
	public abstract void setPublisherName(String publisherName);
	
	public abstract Item clone();
}
