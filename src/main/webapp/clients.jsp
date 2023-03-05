<%@page import="uniqu_billing_system.helper.Number2Word"%>
<%@page import="uniqu_billing_system.model.Client"%>
<%@page import="uniqu_billing_system.dao.ClientDao"%>
<%@page import="uniqu_billing_system.dao.OrderDao"%>
<%@page import="uniqu_billing_system.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unique Mirror || Clients</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
</head>
<body>
	<div class="container mt-2">
		<div class="row">
			<div class="col-md-1">
				<a href="index.jsp"><img
					style="height: 40px; border-radius: 2px" alt="" src="img/logo4.jpg"></a>
			</div>
			<div class="col-md-1">
				<a href="bill.jsp"><img alt="" src="img/logo5.jpg"
					style="height: 40px; border-radius: 2px"></a>
			</div>
			<div class="col-md-1">
				<a href="allBill.jsp"><img alt="" src="img/logo6.jpg"
					style="height: 40px; border-radius: 2px"></a>
			</div>
		</div>
	</div>


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4 ">
				<table class="table text-center">
					<thead>
						<tr>
							<th scope="col">id</th>
							<th scope="col">Client Name</th>
							<th scope="col">Total Orders</th>
							<th scope="col">Total Paid</th>
							<th scope="col">Balance</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Client c : new ClientDao().getAllClients()) {
						%>
						<tr>
							<th scope="row"><%=c.getClient_id()%></th>
							<td><a href="client.jsp?clientName=<%=c.getClient_name()%>">
									<%=c.getClient_name()%>
							</a></td>
							<td><%=OrderDao.getTotalOrdersClientName(c.getClient_name())%></td>
							<td class="btn-primary text-dark"><%=OrderDao.getGrandTotalByClientName(c.getClient_name())%></td>
							<td class="btn-danger text-dark"><%=OrderDao.getBalanceByClientName(c.getClient_name())%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>



	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

</body>
</html>