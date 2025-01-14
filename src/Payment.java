import java.util.Date;

public class Payment {

	protected String paymentType;
	protected Double price;
	protected Double penalty;
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public void payPenalty(Date dueDate) {
		// TODO
	}
	
	public void buyItem(Item item) {
		// TODO
	}
	
}
