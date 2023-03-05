package uniqu_billing_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uniqu_billing_system.helper.ConnectionProvider;
import uniqu_billing_system.model.Order;

public class OrderDao {
	public boolean addOrder(Order o) {
		boolean result=false;
		try {
			Connection con=new ConnectionProvider().getConnection();
			String q="insert into orders(client_name,total,grand_total) values(?,?,?)";
			PreparedStatement p=con.prepareStatement(q);
			p.setString(1, o.getClient_name());
			p.setDouble(2, o.getTotal());
			p.setDouble(3, o.getGrand_total());
			p.executeUpdate();
			con.close();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public long getLastlyAddedInvoiceNumber() {
		long iv=0;
		try {
			Connection con=new ConnectionProvider().getConnection();
			
			String q="select invoice_number from orders order by invoice_number desc limit 1";
			Statement s=con.createStatement();
			ResultSet id = s.executeQuery(q);
			if(id.next()) {
				iv=id.getLong("invoice_number");
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return iv;
	}
	
	public Order getLastlyAddedOrder() {
		Order o=null;
		try {
			Connection con=new ConnectionProvider().getConnection();
			
			String q="select * from orders order by invoice_number desc limit 1";
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(q);
			if(res.next()) {
				o=new Order();
				o.setInvoice_number(res.getLong("invoice_number"));
				o.setOrder_date(res.getTimestamp("order_date"));
				o.setClient_name(res.getString("client_name"));
				o.setTotal(res.getDouble("total"));
				o.setGrand_total(res.getDouble("grand_total"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return o;
	}
	
	public Order getOrder(String id) {
		Order o=null;
		try {
			Connection con=new ConnectionProvider().getConnection();
			
			String q="select * from orders where invoice_number="+id;
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(q);
			if(res.next()) {
				o=new Order();
				o.setInvoice_number(res.getLong("invoice_number"));
				o.setOrder_date(res.getTimestamp("order_date"));
				o.setClient_name(res.getString("client_name"));
				o.setTotal(res.getDouble("total"));
				o.setGrand_total(res.getDouble("grand_total"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return o;
	}
	
	public static List<Order> getAllOrders(){
		List<Order> orders=new ArrayList<Order>();
		try {
			Connection con=new ConnectionProvider().getConnection();
			String query="select * from orders";
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(query);
			while(res.next()) {
				Order o=new Order();
				o.setInvoice_number(res.getLong("invoice_number"));
				o.setOrder_date(res.getTimestamp("order_date"));
				o.setClient_name(res.getString("client_name"));
				o.setTotal(res.getDouble("total"));
				o.setGrand_total(res.getDouble("grand_total"));
				o.setPaid(res.getInt("paid"));
				orders.add(o);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static List<Order> getAllOrdersByClientName(String cn){
		List<Order> orders=new ArrayList<Order>();
		try {
			Connection con=new ConnectionProvider().getConnection();
			String query="select * from orders where client_name='"+cn+"'";
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(query);
			while(res.next()) {
				Order o=new Order();
				o.setInvoice_number(res.getLong("invoice_number"));
				o.setOrder_date(res.getTimestamp("order_date"));
				o.setClient_name(res.getString("client_name"));
				o.setTotal(res.getDouble("total"));
				o.setGrand_total(res.getDouble("grand_total"));
				o.setPaid(res.getInt("paid"));
				orders.add(o);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static List<Order> getUnpaidOrdersByClientName(String cn){
		List<Order> orders=new ArrayList<Order>();
		try {
			Connection con=new ConnectionProvider().getConnection();
			String query="select * from orders where client_name='"+cn+"' and paid=0";
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(query);
			while(res.next()) {
				Order o=new Order();
				o.setInvoice_number(res.getLong("invoice_number"));
				o.setOrder_date(res.getTimestamp("order_date"));
				o.setClient_name(res.getString("client_name"));
				o.setTotal(res.getDouble("total"));
				o.setGrand_total(res.getDouble("grand_total"));
				o.setPaid(res.getInt("paid"));
				orders.add(o);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static List<Order> getPaidOrdersByClientName(String cn){
		List<Order> orders=new ArrayList<Order>();
		try {
			Connection con=new ConnectionProvider().getConnection();
			String query="select * from orders where client_name='"+cn+"' and paid=1";
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(query);
			while(res.next()) {
				Order o=new Order();
				o.setInvoice_number(res.getLong("invoice_number"));
				o.setOrder_date(res.getTimestamp("order_date"));
				o.setClient_name(res.getString("client_name"));
				o.setTotal(res.getDouble("total"));
				o.setGrand_total(res.getDouble("grand_total"));
				o.setPaid(res.getInt("paid"));
				orders.add(o);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static Long getGrandTotalByClientName(String cn) {
		long gt=0;
		
		for(Order o:getAllOrdersByClientName(cn)) {
			if(o.getPaid()==1) {
				gt=(long) (gt+o.getGrand_total());
			}
		}
		
		return gt;
	}
	
	public static Long getBalanceByClientName(String cn) {
		long gt=0;
		
		for(Order o:getAllOrdersByClientName(cn)) {
			if(o.getPaid()==0) {
				gt=(long) (gt+o.getGrand_total());
			}
		}
		
		return gt;
	}
	
	public static int getTotalOrdersClientName(String cn) {
		return getAllOrdersByClientName(cn).size();
	}
	
	public static boolean updatePaid(String p,String in) {
		boolean b=false;
		try {
			Connection con=new ConnectionProvider().getConnection();
			String query="update orders set paid="+p+" where invoice_number="+in;
			Statement s=con.createStatement();
			s.executeUpdate(query);
			con.close();
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return b;
	}
	
	public static void main(String[] args) {
		System.out.println(getGrandTotalByClientName("Sohail Ustad"));
	}
	
}
