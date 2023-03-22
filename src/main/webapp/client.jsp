<%@page import="uniqu_billing_system.helper.Number2Word"%>
<%@page import="uniqu_billing_system.dao.OrderDao"%>
<%@page import="uniqu_billing_system.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
				<a href="clients.jsp"><img alt="" src="img/logo3.jpg"
					style="height: 40px; border-radius: 2px"></a>
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

	<!--
	<div class="container mt-3">
		<b>Paid: </b>
		<button class="btn btn-primary"><%=OrderDao.getGrandTotalByClientName(request.getParameter("clientName"))%></button>
		<b>In words</b>
		<button class="btn btn-success"><%=Number2Word.convertNumberToWord(OrderDao.getGrandTotalByClientName(request.getParameter("clientName")))%></button>
	</div>
	<div class="container mt-2">
		<b>Balance: </b>
		<button class="btn btn-primary"><%=OrderDao.getBalanceByClientName(request.getParameter("clientName"))%></button>
		<b>In words</b>
		<button class="btn btn-success"><%=Number2Word.convertNumberToWord(OrderDao.getBalanceByClientName(request.getParameter("clientName")))%></button>
	</div>  -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 ">
				<div class="container mt-3 mb-2">
					<b>Paid: </b>
					<button class="btn btn-primary"><%=OrderDao.getGrandTotalByClientName(request.getParameter("clientName"))%></button>

				</div>
				<table class="table text-center">
					<thead>
						<tr>
							<th></th>
							<th scope="col">invoice number</th>
							<th scope="col">order date</th>
							<th scope="col">grand total</th>
							<th scope="col">paid</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Order o : OrderDao.getUnpaidOrdersByClientName(request.getParameter("clientName"))) {
						%>
						<tr>
							<th><a href="edit.jsp?in=<%=o.getInvoice_number()%>"><img
									style="height: 40px; border-radius: 2px" src="img/logo10.jpg"></a></th>
							<th scope="row"><a
								href="viewOrder.jsp?in=<%=o.getInvoice_number()%>"><%=o.getInvoice_number()%></a></th>
							<td><%=o.getOrder_date().toString().substring(0, 11)%></td>
							<td><%=o.getGrand_total().longValue()%></td>
							<td>
								<form action="makepaid">
									<input type="hidden" name="in"
										value="<%=o.getInvoice_number()%>"> <input
										type="hidden" name="cn" value="<%=o.getClient_name()%>">
									<%
									if (o.getPaid() == 1) {
									%>
									<input type="hidden" value="0" name="p">
									<button class="btn btn-primary" type="submit">Yes</button>
									<%
									} else {
									%>
									<input type="hidden" value="1" name="p">
									<button class="btn btn-danger" type="submit">No</button>
									<%
									}
									%>
								</form>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div class="col-md-6 ">
				<div class="container mt-3 mb-2">
					<b>Balance: </b>
					<button class="btn btn-primary"><%=OrderDao.getBalanceByClientName(request.getParameter("clientName"))%></button>
				</div>
				<table class="table table-dark table-striped text-center">
					<thead>
						<tr>
							<th></th>
							<th scope="col">invoice number</th>
							<th scope="col">order date</th>
							<th scope="col">grand total</th>
							<th scope="col">paid</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Order o : OrderDao.getPaidOrdersByClientName(request.getParameter("clientName"))) {
						%>
						<tr>
							<th><a href="edit.jsp?in=<%=o.getInvoice_number()%>"><img
									style="height: 40px; border-radius: 2px" src="img/logo10.jpg"></a></th>
							<th scope="row"><a
								href="viewOrder.jsp?in=<%=o.getInvoice_number()%>"><%=o.getInvoice_number()%></a></th>
							<td><%=o.getOrder_date().toString().substring(0, 11)%></td>
							<td><%=o.getGrand_total().longValue()%></td>
							<td>
								<form action="makepaid">
									<input type="hidden" name="in"
										value="<%=o.getInvoice_number()%>"> <input
										type="hidden" name="cn" value="<%=o.getClient_name()%>">
									<%
									if (o.getPaid() == 1) {
									%>
									<input type="hidden" value="0" name="p">
									<button class="btn btn-primary disabled" type="submit">Yes</button>
									<%
									} else {
									%>
									<input type="hidden" value="1" name="p">
									<button class="btn btn-danger disabled" type="submit">No</button>
									<%
									}
									%>
								</form>
							</td>
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