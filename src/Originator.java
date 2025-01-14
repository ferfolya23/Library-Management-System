
public class Originator {
	private PhysicalItem bookEdition;
	
	public Memento save() {
		return null;
	}
	
	public Memento restore(Memento m) {
		return null;
		
	}
	
	public class Memento {
		private PhysicalItem bookEdition;
		
		private Memento(PhysicalItem bookEdition) {
			this.bookEdition = bookEdition;
		}
		
		private Memento getState() {
			return null;
		}
	}
}
