package uniqu_billing_system.model;

public class DescriptioOfGoods {
	private long id;
	private long invoice_number;
	private String description;
	private Double height;
	private Double width;
	private long quantity;
	private Double amount_sqr_f_i;
	private Double rate;
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
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Double getAmount_sqr_f_i() {
		return amount_sqr_f_i;
	}
	public void setAmount_sqr_f_i(Double amount_sqr_f_i) {
		this.amount_sqr_f_i = amount_sqr_f_i;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "DescriptioOfGoods [id=" + id + ", invoice_number=" + invoice_number + ", description=" + description
				+ ", height=" + height + ", width=" + width + ", quantity=" + quantity + ", amount_sqr_f_i="
				+ amount_sqr_f_i + ", rate=" + rate + ", amount=" + amount + "]";
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
