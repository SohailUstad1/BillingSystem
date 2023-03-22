<%@page import="uniqu_billing_system.dao.ExtraDao"%>
<%@page import="uniqu_billing_system.model.Extra"%>
<%@page import="java.util.List"%>
<%@page import="uniqu_billing_system.model.DescriptioOfGoods"%>
<%@page import="uniqu_billing_system.dao.OrderDao"%>
<%@page import="uniqu_billing_system.model.Order"%>
<%@page import="uniqu_billing_system.dao.ClientDao"%>
<%@page import="uniqu_billing_system.dao.GoodsDao"%>
<%@page import="uniqu_billing_system.dao.DescriptionOfGoodsDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unique Mirror || Billing</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css" />

</head>
<body>

	<%
	String invoice_number = request.getParameter("in");
	Order order = OrderDao.getOrder(invoice_number);
	List<DescriptioOfGoods> dog = new DescriptionOfGoodsDao().getAllDogByInvoiceNumber(Long.parseLong(invoice_number));
	List<Extra> extra = ExtraDao.getExtraByInvoiceNumber(Long.parseLong(invoice_number));
	%>

	<div class="alert alert-primary d-flex align-items-center" role="alert">
		<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
			fill="currentColor"
			class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2"
			viewBox="0 0 16 16" role="img" aria-label="Warning:">
    <path
				d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
  </svg>
		<div>You are updating <button class="btn btn-danger"><%= invoice_number %></button> </div>
	</div>

	<div class="container-fluid mt-1">
		<form action="updateOrder" method="post">
			<input type="hidden" name="in"
				value="<%=request.getParameter("in")%>">
			<div class="row">

				<div class="col-md-4">
					<input type="text" class="form-control tags"
						style="font-weight: bolder;" id="tags"
						aria-describedby="emailHelp" name="client_name"
						placeholder="Client name" required
						value="<%=order.getClient_name()%>" />
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
				<div class="col-md-1">
					<a href="index.jsp"><img
						style="height: 40px; border-radius: 2px" alt=""
						src="img/logo4.jpg"></a>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="container-fluid mt-1">

						<%
						for (int i = 1; i <= dog.size(); i++) {
						%>
						<div class="row">
							<div class="col-md-1 m-0 p-0 mt-3">
								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="sr<%=i%>"
									aria-describedby="emailHelp" name="sr<%=i%>" readonly
									value="<%=i%>" />

							</div>
							<div class="col-md-1 m-0 p-0 mt-3">
								<input
									style="height: 150%; font-size: 10px; font-weight: bolder;"
									class="form-control" type="text" id="unit<%=i%>"
									placeholder="inch" name="unit<%=i%>" >
							</div>
							<div class="col-md-4 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="text" class="form-control dog" id="dog<%=i%>"
									aria-describedby="emailHelp" name="dog<%=i%>"
									placeholder="Dedcription of goods"
									value="<%=dog.get(i - 1).getDescription()%>" />

							</div>

							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="h<%=i%>"
									aria-describedby="emailHelp" name="h<%=i%>" placeholder="H"
									step="0.000001" value="<%=dog.get(i - 1).getHeight()%>" />
							</div>
							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="w<%=i%>"
									aria-describedby="emailHelp" name="w<%=i%>" placeholder="W"
									step="0.000001" value="<%=dog.get(i - 1).getWidth()%>" />
							</div>
							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="q<%=i%>"
									aria-describedby="emailHelp" name="q<%=i%>" placeholder="Qty"
									step="0.000001" value="<%=dog.get(i - 1).getQuantity()%>" />
							</div>
							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="r<%=i%>"
									aria-describedby="emailHelp" name="r<%=i%>" placeholder="Rate"
									step="0.000001" value="<%=dog.get(i - 1).getRate()%>" />
							</div>

							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="text" class="form-control" id="sqrfi<%=i%>"
									aria-describedby="emailHelp" name="sqrfi<%=i%>"
									placeholder="SQF/I"
									value="<%=dog.get(i - 1).getAmount_sqr_f_i()%>" />
							</div>
							<div class="col-md-1 m-0 p-0  mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="text" class="form-control" id="a<%=i%>"
									aria-describedby="emailHelp" name="a<%=i%>"
									placeholder="Amount" value="<%=dog.get(i - 1).getAmount()%>" />
							</div>
						</div>
						<%
						}
						%>

						<%
						for (int i = dog.size() + 1; i < 13; i++) {
						%>
						<div class="row">
							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="sr<%=i%>"
									aria-describedby="emailHelp" name="sr<%=i%>" readonly
									value="<%=i%>" />

							</div>
							<div class="col-md-1 m-0 p-0 mt-3">
								<input
									style="height: 150%; font-size: 10px; font-weight: bolder;"
									class="form-control" type="text" id="unit<%=i%>"
									placeholder="inch" name="unit<%=i%>">
							</div>
							<div class="col-md-4 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="text" class="form-control dog" id="dog<%=i%>"
									aria-describedby="emailHelp" name="dog<%=i%>"
									placeholder="Dedcription of goods" />

							</div>

							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="h<%=i%>"
									aria-describedby="emailHelp" name="h<%=i%>" placeholder="H"
									step="0.000001" />
							</div>
							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="w<%=i%>"
									aria-describedby="emailHelp" name="w<%=i%>" placeholder="W"
									step="0.000001" />
							</div>
							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="q<%=i%>"
									aria-describedby="emailHelp" name="q<%=i%>" placeholder="Qty"
									step="0.000001" />
							</div>
							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="number" class="form-control" id="r<%=i%>"
									aria-describedby="emailHelp" name="r<%=i%>" placeholder="Rate"
									step="0.000001" />
							</div>

							<div class="col-md-1 m-0 p-0 mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="text" class="form-control" id="sqrfi<%=i%>"
									aria-describedby="emailHelp" name="sqrfi<%=i%>"
									placeholder="SQF/I" />
							</div>
							<div class="col-md-1 m-0 p-0  mt-3">

								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="text" class="form-control" id="a<%=i%>"
									aria-describedby="emailHelp" name="a<%=i%>"
									placeholder="Amount" />
							</div>
						</div>
						<%
						}
						%>
						<div class="row">
							<div class="col-md-1 p-0 m-0 mt-3">
								<input
									style="font-size: 10px; height: 150%; font-weight: bolder;"
									type="text" class="form-control dog" id="extra1"
									aria-describedby="emailHelp" name="extra1"
									placeholder="Dedcription" value="Advance" readonly />
							</div>
							<div class="col-md-2 p-0 m-0 mt-3">
								<input
									style="height: 150%; font-size: 10px; font-weight: bolder;"
									type="number" class="form-control dog" id="extra_amount1"
									aria-describedby="emailHelp" name="extra_amount1"
									placeholder="Amount" value="<%=extra.get(0).getAmount()%>" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-1 p-0 m-0 mt-3">
								<input
									style="height: 150%; font-size: 10px; font-weight: bolder;"
									type="text" class="form-control dog" id="extra2"
									aria-describedby="emailHelp" name="extra2"
									placeholder="Dedcription" value="Discount" readonly />
							</div>
							<div class="col-md-2 p-0 m-0 mt-3">
								<input
									style="height: 150%; font-size: 10px; font-weight: bolder;"
									type="number" class="form-control dog" id="extra_amount2"
									aria-describedby="emailHelp" name="extra_amount2"
									placeholder="Amount" value="<%=extra.get(1).getAmount()%>" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="container text-center">
				<button type="submit" class="btn btn-primary mt-3 mb-5">Update</button>
			</div>
		</form>
	</div>


	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script>
	$(function() {
		var goods = [ 
			<%for (String s : new GoodsDao().getAllGoods()) {%>
						"<%=s%>",
					<%}%>
		];
		$(".dog").autocomplete({
			source : goods,
		});
		var clients = [ 
			<%for (String s : new ClientDao().getAllClientNames()) {%>
						"<%=s%>",
					<%}%>
		];
		$(".tags").autocomplete({
			source : clients,
		});
		
	});
	<%for (int i = 1; i < 13; i++) {%>
			$("#r<%=i%>").keyup(function(){
				let a=parseInt(document.getElementById("h<%=i%>").value);
				let b=parseInt(document.getElementById("w<%=i%>").value);
				let q=parseInt(document.getElementById("q<%=i%>").value);
				let unit=document.getElementById("unit<%=i%>").value.trim();
				var sqr=0;
				if(unit=="mm"){
					sqr=parseFloat((a*b*q).toFixed(3));
				}else{
					sqr=parseFloat(((a*b/144)*q).toFixed(3));
				}
				let r=parseInt(document.getElementById("r<%=i%>").value);
				$("#sqrfi<%=i%>").val(sqr);
				$("#a<%=i%>").val(parseFloat((q*sqr*r)).toFixed());
			});
			$("#h<%=i%>").keyup(function(){
				let a=parseInt(document.getElementById("h<%=i%>").value);
				let b=parseInt(document.getElementById("w<%=i%>").value);
				let q=parseInt(document.getElementById("q<%=i%>").value);
				let unit=document.getElementById("unit<%=i%>").value.trim();
				var sqr=0;
				if(unit=="mm"){
					sqr=parseFloat((a*b*q).toFixed(3));
				}else{
					sqr=parseFloat(((a*b/144)*q).toFixed(3));
				}
				let r=parseInt(document.getElementById("r<%=i%>").value);
				$("#sqrfi<%=i%>").val(sqr);
				$("#a<%=i%>").val(parseFloat((q*sqr*r)).toFixed());
			});
			$("#w<%=i%>").keyup(function(){
				let a=parseInt(document.getElementById("h<%=i%>").value);
				let b=parseInt(document.getElementById("w<%=i%>").value);
				let q=parseInt(document.getElementById("q<%=i%>").value);
				let unit=document.getElementById("unit<%=i%>").value.trim();
				var sqr=0;
				if(unit=="mm"){
					sqr=parseFloat((a*b*q).toFixed(3));
				}else{
					sqr=parseFloat(((a*b/144)*q).toFixed(3));
				}
				let r=parseInt(document.getElementById("r<%=i%>").value);
				$("#sqrfi<%=i%>").val(sqr);
				$("#a<%=i%>").val(parseFloat((q*sqr*r)).toFixed());
			});
			$("#unit<%=i%>").keyup(function(){
				let a=parseInt(document.getElementById("h<%=i%>").value);
				let b=parseInt(document.getElementById("w<%=i%>").value);
				let q=parseInt(document.getElementById("q<%=i%>").value);
				let unit=document.getElementById("unit<%=i%>").value.trim();
				var sqr=0;
				if(unit=="mm"){
					sqr=parseFloat((a*b*q).toFixed(3));
				}else{
					sqr=parseFloat(((a*b/144)*q).toFixed(3));
				}
				let r=parseInt(document.getElementById("r<%=i%>").value);
				$("#sqrfi<%=i%>").val(sqr);
				$("#a<%=i%>").val(parseFloat((q*sqr*r)).toFixed());
			});
			$("#rq<%=i%>").keyup(function(){
				let a=parseInt(document.getElementById("h<%=i%>").value);
				let b=parseInt(document.getElementById("w<%=i%>").value);
				let q=parseInt(document.getElementById("q<%=i%>").value);
				let unit=document.getElementById("unit<%=i%>").value.trim();
				var sqr=0;
				if(unit=="mm"){
					sqr=parseFloat((a*b*q).toFixed(3));
				}else{
					sqr=parseFloat(((a*b/144)*q).toFixed(3));
				}
				let r=parseInt(document.getElementById("r<%=i%>").value);
				$("#sqrfi<%=i%>").val(sqr);
				$("#a<%=i%>").val(parseFloat((q*sqr*r)).toFixed());
			});
			<%}%>
	</script>
</body>
</html>