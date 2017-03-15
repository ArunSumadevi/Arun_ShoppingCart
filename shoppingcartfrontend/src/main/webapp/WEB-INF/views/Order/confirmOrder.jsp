<!DOCTYPE html>
	<html>
		<head>
			<%@include file="../Site/Header.jsp"%>
		</head>
 		<body > 
			<div id="wrapper">
				<br>
				<%@include file="../Site/Menu.jsp" %>
				<div class="container">
					<form action="placeOrder" method="post">
						<input type="hidden" name="userId" value="${sessionUser.id}">
						<input type="hidden" name="billingAddress" value="${orderTable.billingAddress}">
						<input type="hidden" name="shippingAddress" value="${orderTable.shippingAddress}">
						<input type="hidden" name="paymentMode" value="${orderTable.paymentMode}">
						<input type="submit" class="btn btn-success btn-block" value="Confirm Order">
					</form>
				</div>	
				<div id="footer">
					<div class="text-center" style="Color: white">&copy Arun Technologies pvt ltd.</div>
					<div class="text-center" style="Color: white"><span class="fa fa-envelope-o"> </span> <a style="Color: white"href= "mailto:ebaazar.contactus@gmail.com">ebaazar.contactus@gmail.com</a> </div>
				</div>
			</div>
			<%@include file="../Site/Script.jsp"%>
		</body>
	</html>