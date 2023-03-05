package uniqu_billing_system.model;

import java.sql.Timestamp;

public class Order {
	private long invoice_number;
	private Timestamp order_date;
	private String logo_path;
	private String our_address;
	private String client_name;
	private String client_address;
	private Double total;
	private Double grand_total;
	private int paid;
	public long getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(long invoice_number) {
		this.invoice_number = invoice_number;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public String getLogo_path() {
		return logo_path;
	}
	public void setLogo_path(String logo_path) {
		this.logo_path = logo_path;
	}
	public String getOur_address() {
		return our_address;
	}
	public void setOur_address(String our_address) {
		this.our_address = our_address;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_address() {
		return client_address;
	}
	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Double getGrand_total() {
		return grand_total;
	}
	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}
	public int getPaid() {
		return paid;
	}
	public void setPaid(int paid) {
		this.paid = paid;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public void setGrand_total(Double grand_total) {
		this.grand_total = grand_total;
	}
	
	
}
