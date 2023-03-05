package uniqu_billing_system.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uniqu_billing_system.dao.OrderDao;

/**
 * Servlet implementation class MakePaid
 */

@WebServlet("/makepaid")
public class MakePaid extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MakePaid() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String p=request.getParameter("p");
		String in=request.getParameter("in");
		String clientName=request.getParameter("cn");
		boolean updatePaid = OrderDao.updatePaid(p, in);
		if(updatePaid) {
			response.sendRedirect("client.jsp?clientName="+clientName);
		}else {
			response.getWriter().print("failed");
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
