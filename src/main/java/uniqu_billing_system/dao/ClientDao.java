package uniqu_billing_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uniqu_billing_system.helper.ConnectionProvider;
import uniqu_billing_system.model.Client;

public class ClientDao {
	public boolean addClient(Client c) {
		boolean result=false;
		
		try {
			Connection con=new ConnectionProvider().getConnection();
			String createClientsTableQuery="create table if not exists client("
					+ "client_id bigint not null AUTO_INCREMENT,"
					+ "client_name varchar(255) not null,"
					+ "client_contact varchar(255),"
					+ "client_address varchar(255),"
					+ "primary key(client_id));";
			
			String insertClientDataQuery="insert into client(client_name,client_contact,client_address)"
					+ "values(?,?,?)";
			
			Statement st=con.createStatement();
			PreparedStatement ps=con.prepareStatement(insertClientDataQuery);
			ps.setString(1, c.getClient_name());
			ps.setString(2, c.getClient_contact());
			ps.setString(3, c.getClient_address());
			st.executeUpdate(createClientsTableQuery);
			ps.executeUpdate();
			
			con.close();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addClientByName(String clientName) {
		boolean result=false;
		
		try {
			Connection con=new ConnectionProvider().getConnection();
			String createClientsTableQuery="create table if not exists client("
					+ "client_id bigint not null AUTO_INCREMENT,"
					+ "client_name varchar(255) not null unique,"
					+ "client_contact varchar(255),"
					+ "client_address varchar(255),"
					+ "primary key(client_id));";
			String insertClientDataQuery="insert into client(client_name)"
					+ "values(?)";
			Statement st=con.createStatement();
			PreparedStatement ps=con.prepareStatement(insertClientDataQuery);
			ps.setString(1, clientName);
			st.executeUpdate(createClientsTableQuery);
			ps.executeUpdate();
			con.close();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Client> getAllClients(){
		List<Client> allClients=new ArrayList<Client>();
		
		try {
			Connection con=new ConnectionProvider().getConnection();
			
			String retriveAllClientsQuery="select * from client";
			Statement st=con.createStatement();
			ResultSet databaseResponse = st.executeQuery(retriveAllClientsQuery);
			
			while(databaseResponse.next()) {
				Client c=new Client();
				c.setClient_id(databaseResponse.getLong("client_id"));
				c.setClient_name(databaseResponse.getString("client_name"));
				c.setClient_contact(databaseResponse.getString("client_contact"));
				c.setClient_address(databaseResponse.getString("client_address"));
				allClients.add(c);
			}
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return allClients;
	}
	
	public List<String> getAllClientNames(){
		List<String> allClientNames=new ArrayList<String>();
		try {
			Connection con=new ConnectionProvider().getConnection();
			String q="select client_name from client";
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(q);
			while(res.next()) {
				allClientNames.add(res.getString("client_name"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return allClientNames;
	}
	
	public boolean ifClientNameDoesNotExists(String clientName) {
		boolean result=false;
		try {
			Connection con=new ConnectionProvider().getConnection();
			String q="select client_name from client";
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(q);
			if(res.next()) {
				result=false;
			}else {
				result=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		
	}
}
