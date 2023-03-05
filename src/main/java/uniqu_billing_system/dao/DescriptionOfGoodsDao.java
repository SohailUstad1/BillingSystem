package uniqu_billing_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uniqu_billing_system.helper.ConnectionProvider;
import uniqu_billing_system.model.DescriptioOfGoods;

public class DescriptionOfGoodsDao {
	public boolean addDog(DescriptioOfGoods dog) {
		boolean result = false;
		try {
			Connection con = new ConnectionProvider().getConnection();
			String q = "insert into dog(invoice_number,description,height,width,quantity,amount_sqr_f_i,rate,amount,id) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement p = con.prepareStatement(q);
			p.setLong(1, dog.getInvoice_number());
			p.setString(2, dog.getDescription());
			p.setDouble(3, dog.getHeight());
			p.setDouble(4, dog.getWidth());
			p.setLong(5, dog.getQuantity());
			p.setDouble(6, dog.getAmount_sqr_f_i());
			p.setDouble(7, dog.getRate());
			p.setDouble(8, dog.getAmount());
			p.setLong(9, dog.getId());
			p.executeUpdate();
			con.close();
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getAllDOG() {
		List<String> list=new ArrayList<String>();
		try {
			Connection con = new ConnectionProvider().getConnection();
			String q = "select description from dog";
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(q);
			while(res.next()) {
				list.add(res.getString("description"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public List<DescriptioOfGoods> getAllDogByInvoiceNumber(long iv){
		List<DescriptioOfGoods> list=new ArrayList<DescriptioOfGoods>();
		
		try {
			Connection con=new ConnectionProvider().getConnection();
			
			String q="select * from dog where invoice_number=?";
			PreparedStatement p=con.prepareStatement(q);
			p.setLong(1, iv);
			ResultSet res = p.executeQuery();
			while(res.next()) {
				DescriptioOfGoods dog=new DescriptioOfGoods();
				dog.setId(res.getLong("id"));
				dog.setInvoice_number(iv);
				dog.setDescription(res.getString("description"));
				dog.setHeight(res.getDouble("height"));
				dog.setWidth(res.getDouble("width"));
				dog.setQuantity(res.getLong("quantity"));
				dog.setAmount_sqr_f_i(res.getDouble("amount_sqr_f_i"));
				dog.setRate(res.getDouble("rate"));
				dog.setAmount(res.getDouble("amount"));
				list.add(dog);
			}
			
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		DescriptionOfGoodsDao d=new DescriptionOfGoodsDao();
		for(DescriptioOfGoods dd:d.getAllDogByInvoiceNumber(28)) {
			System.out.println(dd);
		}
	}
}
