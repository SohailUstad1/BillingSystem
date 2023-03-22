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
						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Modal
											title</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">Are you sure you want to delete this bill</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<form action="DeleteOrder" method="post">
											<input type="hidden" name="in" value="<%=o.getInvoice_number()%>">
											<button type="submit" class="btn btn-primary">Delete</button>
										</form>
									</div>
								</div>
							</div>
						</div>
						<a href="viewOrder.jsp?in=<%=o.getInvoice_number()%>"><img
							style="height: 30px; border-radius: 2px" src="img/logo8.jpg"></a>
						<a href="viewOrderWithoutAmount.jsp?in=<%=o.getInvoice_number()%>"><img
							style="height: 40px; border-radius: 2px" src="img/logo7.jpg"></a>
						<a href="Download?in=<%=o.getInvoice_number()%>"><img
							style="height: 40px; border-radius: 2px" src="img/logo9.jpg"></a>
						<a href="edit.jsp?in=<%=o.getInvoice_number()%>"><img
							style="height: 40px; border-radius: 2px" src="img/logo10.jpg"></a>
						<img style="height: 40px; border-radius: 2px" src="img/logo10.jpg"
							data-bs-toggle="modal" data-bs-target="#exampleModal">
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>


	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>


</body>
</html>