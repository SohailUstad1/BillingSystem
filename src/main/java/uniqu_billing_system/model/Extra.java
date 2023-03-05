package uniqu_billing_system.model;

public class Extra {
	private long id;
	private long invoice_number;
	private String description;
	private Double amount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(long invoice_number) {
		this.invoice_number = invoice_number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
