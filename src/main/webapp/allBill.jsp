<%@page import="uniqu_billing_system.dao.OrderDao"%>
<%@page import="uniqu_billing_system.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unique Mirror || All Invoices</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css" />
</head>
<body>
	<div></div>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-1">
				<a href="clients.jsp"><img alt="" src="img/logo3.jpg"
					style="height: 40px; border-radius: 2px"></a>
			</div>
			<div class="col-md-1">
				<a href="bill.jsp"><img alt="" src="img/logo5.jpg"
					style="height: 40px; border-radius: 2px"></a>
			</div>
			<div class="col-md-1">
				<a href="index.jsp"><img alt="" src="img/logo4.jpg"
					style="height: 40px; border-radius: 2px"></a>
			</div>
		</div>
	</div>

	<div class="container mt-5">
		<div class="row">
			<%
			for (Order o : OrderDao.getAllOrders()) {
			%>
			<div class="col-sm-3">
				<div class="card" style="width: 18rem;">
					<div class="card-body">
						<h5 class="card-title"><%=o.getInvoice_number()%></h5>
						<table class="table table-striped">
							<tr>
								<td>Grand Total :</td>
								<th><%=o.getGrand_total()%></th>
							</tr>
							<tr>
								<td>Date :</td>
								<th><%=o.getOrder_date().toString().substring(0, 11)%></th>
							</tr>
							<tr>
								<td>Client :</td>
								<th><%=o.getClient_name()%></th>
							</tr>
						</table>
						<a href="viewOrder.jsp?in=<%=o.getInvoice_number()%>"
							class="btn btn-primary">View</a> <a
							href="Download?in=<%=o.getInvoice_number()%>"
							class="btn btn-primary">Download</a>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</body>
</html>
