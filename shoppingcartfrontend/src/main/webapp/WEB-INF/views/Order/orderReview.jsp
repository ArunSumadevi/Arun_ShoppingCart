<!DOCTYPE html>
	<html>
		<head>
			<%@include file="../Site/Header.jsp"%>
		</head>
 		<body> 
			<div id="wrapper">
				<br>
				<%@include file="../Site/Menu.jsp" %>
				<div class="container">
					<h3 class="text-center"> Order Review</h3><hr>
					<c:set var="totalAmount" value="0" />
					<table class="table table-hover table-bordered">
					  	<thead>
						    <tr>
							    <th width="100" > Icon</th>
							    <th width="300" >Product Name</th>
							    <th width="350" >Product Description</th>
							    <th width="100" >Price</th>
							    <th width="50">Qty</th>
							    <th width="100" >Total</th>
							    <th width="100" >Comments</th>
							 </tr>
					    </thead>
					   	<tbody>
							<c:forEach var="listValue" items="${sessionShoppingCart}">										
					      		<tr>
					      			<td><img src="<c:url value="/Resources/Images/Product/${listValue.productId}.jpg" />" width="100px" height="100px" ></td>
					        		<td>${listValue.product.name}</td>
								    <td>${listValue.product.description}</td>
								    <td><span class="fa fa-inr" >${listValue.product.price}</td>
								    <td>${listValue.quantity}</td>
									<td><span class="fa fa-inr" >${listValue.quantity*listValue.product.price }</td>
									<c:set var="totalAmount" value="${totalAmount+listValue.quantity*listValue.product.price}" />
									<td style="color:red">${listValue.comment}</td>        
					            </tr>
					      	</c:forEach>
					    </tbody>
					</table>
					<H4><B> Total amount payable :</B><span class="fa fa-inr" > ${totalAmount}</H4><br>
					<H4><B> Billing Address  :</B><h4></h4>
					${orderTable.billingAddress}
 					<H4><B> Shipping Address  :</B><h4></h4>
 					${orderTable.shippingAddress}
 					<H4><B> Payment Method  :</B><h4></h4>
 					<c:choose>
 					<c:when test="${orderTable.paymentMode=='Credit Card' }">
 					${orderTable.paymentMode} [Card number ending with  ${orderTable.cardNumber.substring(orderTable.cardNumber.length()-4, orderTable.cardNumber.length())}]
 					</c:when>
 					<c:otherwise>
 					${orderTable.paymentMode}
 					</c:otherwise>
 					</c:choose>
 					<div class="col=md=12">
 						<br>
 						<div class="col-md-2">
							<a href="${flowExecutionUrl}&_eventId=backToPaymentDetails" class="btn btn-primary btn-block" role="button">Back</a>
						</div>
						<div class="col-md-2 col-md-offset-6">
							<a href="${flowExecutionUrl}&_eventId=cancel" class="btn btn-danger btn-block" role="button">Cancel</a>
						</div>
						<div class="col-md-2">
							<a href="${flowExecutionUrl}&_eventId=confirmOrder" class="btn btn-success btn-block" role="button">Confirm order</a>
					   		<br>
					   	</div>
					</div>
				</div>
				<div id="content"></div>
				<div id="footer">
					<div class="text-center" style="Color: white">&copy Arun Technologies pvt ltd.</div>
					<div class="text-center" style="Color: white"><span class="fa fa-envelope-o"> </span> <a style="Color: white"href= "mailto:ebaazar.contactus@gmail.com">ebaazar.contactus@gmail.com</a> </div>
				</div>
			</div>
			<%@include file="../Site/Script.jsp"%>
		</body>
	</html>
	