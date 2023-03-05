package uniqu_billing_system.model;

public class Client {
	private long client_id;
	private String client_name;
	private String client_contact;
	private String client_address;
	public long getClient_id() {
		return client_id;
	}
	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_contact() {
		return client_contact;
	}
	public void setClient_contact(String client_contact) {
		this.client_contact = client_contact;
	}
	public String getClient_address() {
		return client_address;
	}
	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}
	
	
}
