package uniqu_billing_system.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uniqu_billing_system.dao.ClientDao;
import uniqu_billing_system.dao.DescriptionOfGoodsDao;
import uniqu_billing_system.dao.ExtraDao;
import uniqu_billing_system.dao.GoodsDao;
import uniqu_billing_system.dao.OrderDao;
import uniqu_billing_system.helper.CreateHtml;
import uniqu_billing_system.helper.HTML2PDF;
import uniqu_billing_system.model.DescriptioOfGoods;
import uniqu_billing_system.model.Extra;
import uniqu_billing_system.model.Order;


/**
 * Servlet implementation class SaveOrder
 */
@WebServlet("/saveorder")
public class SaveOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SaveOrder() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String client_name=request.getParameter("client_name");
		ClientDao cdao=new ClientDao();
		GoodsDao gdao=new GoodsDao();
		List<DescriptioOfGoods> l=new ArrayList<DescriptioOfGoods>();
		for(int i=1;i<13;i++) {
			String d=request.getParameter("dog"+i);
			if(d!=null && !(d.equals(""))) {
				long id=Long.parseLong(request.getParameter("sr"+i));
				Double h=Double.parseDouble(request.getParameter("h"+i));
				Double w=Double.parseDouble(request.getParameter("w"+i));
				long q=Long.parseLong(request.getParameter("q"+i));
				Double sqrfi=Double.parseDouble(request.getParameter("sqrfi"+i));
				Double r=Double.parseDouble(request.getParameter("r"+i));
				Double a=Double.parseDouble(request.getParameter("a"+i));
				
				DescriptioOfGoods dog=new DescriptioOfGoods();
				dog.setDescription(d);
				gdao.addGoods(d);
				dog.setId(id);
				dog.setHeight(h);
				dog.setWidth(w);
				dog.setQuantity(q);
				dog.setAmount_sqr_f_i(sqrfi);
				dog.setRate(r);
				dog.setAmount(a);
				l.add(dog);
			}else {
				break;
			}
		}
		String ex1=request.getParameter("extra1");
		String ex2=request.getParameter("extra2");
		double ea1=0,ea2=0;
		String eea1=request.getParameter("extra_amount1");
		String eea2=request.getParameter("extra_amount2");
		
		if(!(eea1.equals("") || eea2.equals("")) && !(eea1==null || eea2==null)) {
			ea1=Double.parseDouble(request.getParameter("extra_amount1"));
			ea2=Double.parseDouble(request.getParameter("extra_amount2"));
		}
		
		double total=0,grand_total=0;
		for(DescriptioOfGoods ll:l) {
			total+=ll.getAmount();
		}
		grand_total=total-(ea1+ea2);
		
		Order o=new Order();
		o.setClient_name(client_name);
		o.setTotal(total);
		o.setGrand_total(grand_total);
		OrderDao odao=new OrderDao();
		boolean addOrder = odao.addOrder(o);
		if(addOrder) {
			long iv=odao.getLastlyAddedInvoiceNumber();
			DescriptionOfGoodsDao dogDao=new DescriptionOfGoodsDao();
			boolean bbb=false;
			for(DescriptioOfGoods ll:l) {
				ll.setInvoice_number(iv);
				boolean addDog = dogDao.addDog(ll);
				if(addDog) {
					bbb=true;
				}else {
					bbb=false;
					break;
				}
			}
			if(bbb) {
				Extra e1=new Extra();
				e1.setId(1);
				e1.setInvoice_number(iv);
				e1.setDescription(ex1);
				e1.setAmount(ea1);
				Extra e2=new Extra();
				e2.setId(2);
				e2.setInvoice_number(iv);
				e2.setDescription(ex2);
				e2.setAmount(ea2);
				ExtraDao edao=new ExtraDao();
				boolean addExtra1 = edao.addExtra(e1);
				boolean addExtra2 = edao.addExtra(e2);
				if(addExtra1 && addExtra2) {
					CreateHtml ch=new CreateHtml();
					Order ooo=odao.getLastlyAddedOrder();
					List<DescriptioOfGoods> ddd=dogDao.getAllDogByInvoiceNumber(ooo.getInvoice_number());
					List<Extra> eee=edao.getExtraByInvoiceNumber(ooo.getInvoice_number());
					boolean htmlCreated = ch.createHtml(ooo, ddd, eee);
					if(htmlCreated) {
						boolean convert2pdf = HTML2PDF.convert2pdf(""+ooo.getInvoice_number());
						if(convert2pdf) {
							 
							cdao.addClientByName(client_name);
							
							response.sendRedirect("bill.jsp");
						}else {
							response.getWriter().print("Cannot create pdf");
						}
						
					}else {
						response.getWriter().print("Cannot create html");
					}
					
				}else {
					response.getWriter().print("failed to add extra");
				}
			}else {
				response.getWriter().print("Description cannot be added");
			}
		}else {
			response.getWriter().print("Order cannot be added");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
