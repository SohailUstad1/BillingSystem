<%@page import="uniqu_billing_system.helper.Number2Word"%>
<%@page import="uniqu_billing_system.dao.ExtraDao"%>
<%@page import="uniqu_billing_system.model.Extra"%>
<%@page import="uniqu_billing_system.dao.DescriptionOfGoodsDao"%>
<%@page import="uniqu_billing_system.model.DescriptioOfGoods"%>
<%@page import="java.util.List"%>
<%@page import="uniqu_billing_system.dao.OrderDao"%>
<%@page import="uniqu_billing_system.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1"
		style="text-align: center; border-collapse: collapse; width: 100%; height: 100%; margin: 0px; font-size: 12px;">
		<tr>
			<td colspan="5" style="width: 50%;"><img src="img/um_logo.jpg"
				style="height: 70px;"></td>
			<td colspan="4" style="width: 50%; padding: 3px; font-size: 13px;"><b>Address</b>
				:- Room 282, Siddharth Nagar, Kokri Agar, Sion, Mumbai, Maharashtra
				400037 <br> Contact :- 9594379220 / 7666223022 / 7666088587 <br>
				Email Id :- uniquemirrorglass@gmail.com</td>
		</tr>
		<%
		Order o = new OrderDao().getLastlyAddedOrder();
		%>
		<tr>
			<td>M/s</td>
			<td colspan="2" style="text-align: left; padding-left: 10px;"><%=o.getClient_name()%></td>
			<td colspan="2">Mobile Number</td>
			<td colspan="2"></td>
			<th colspan="2">Date: <%=o.getOrder_date().toString().substring(0, 11)%></th>
		</tr>
		<tr>
			<td rowspan="2">Add</td>
			<td rowspan="2" colspan="6" style="height: 25px;"></td>
		</tr>
		<tr>
			<td colspan="2">Invoice number: <b> <%=o.getInvoice_number()%>
			</b></td>
		</tr>
		<tr>
			<td>SR No</td>
			<td style="width: 50%;">Description of goods</td>
			<td colspan="3" style="width: 15%;">Size</td>
			<td style="width: 5%;">Qty</td>
			<td style="width: 10%;">SQR F/I</td>
			<td style="width: 10%;">Rate</td>
			<td style="width: 10%;">Amount</td>
		</tr>
		<%
		List<DescriptioOfGoods> dogs = new DescriptionOfGoodsDao().getAllDogByInvoiceNumber(o.getInvoice_number());
		for (DescriptioOfGoods ddd : dogs) {
		%>
		<tr>
			<th><%=ddd.getId()%></th>
			<th style="text-align: left;"><%=ddd.getDescription()%></th>
			<th style="width: 4%;"><%=ddd.getHeight().longValue()%></th>
			<th style="width: 2%;">x</th>
			<th style="width: 4%;"><%=ddd.getWidth().longValue()%></th>
			<th><%=ddd.getQuantity()%></th>
			<th><%=ddd.getAmount_sqr_f_i().longValue()%></th>
			<th><%=ddd.getRate().longValue()%></th>
			<th><%=ddd.getAmount().longValue()%></th>
		</tr>
		<%
		}
		for (int i = dogs.size() + 1; i < 13; i++) {
		%>
		<tr>
			<th style="height: 15px"></th>
			<th style="text-align: left;"></th>
			<th style="width: 4%;"></th>
			<th style="width: 2%;"></th>
			<th style="width: 4%;"></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		<%
		}
		%>


		<tr style="vertical-align: top;">
			<td style="width: 30%; height: 50px;" rowspan="4" colspan="2">Rupees
				in word <br> <%
 Long l1 = o.getGrand_total().longValue();
 String inWords = Number2Word.convertNumberToWord(l1);
 %>
				<h3><%=inWords%></h3>
			</td>
			<td style="width: 50%; height: 50px;" colspan="4" rowspan="4">Receive
				sign</td>
			<td colspan="2"
				style="vertical-align: middle; height: 20px; width: 15%;">Total</td>
			<th style="width: 35%;">INR <%=o.getTotal().longValue()%></th>
		</tr>
		<%
		List<Extra> extras = new ExtraDao().getExtraByInvoiceNumber(o.getInvoice_number());
		for (Extra eee : extras) {
		%>
		<tr>
			<td style="height: 20px;" colspan="2"><%=eee.getDescription()%></td>
			<th><%=eee.getAmount().longValue()%></th>
		</tr>
		<%
		}
		%>

		<tr>
			<td colspan="2" style="width: 15%;">Grand Total</td>
			<th style="width: 35%;">INR <%=o.getGrand_total().longValue()%></th>
		</tr>
	</table>
</body>
</html>