<div class="container">
	<div class="col-md-10 col-md-offset-1">
		<h3 class="text-center"> Showing details of order :${orderNumber}</h3><hr>
		<c:choose>
			<c:when test="${orderList.isEmpty()}">
				<div class="alert alert-danger">${errorMessage }</div>
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-md-12">
						<b>UserName </b>: ${orderList.get(0).user.firstName} ${orderList.get(0).user.lastName}<br>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<b>Shipping Address </b>: ${orderList.get(0).shippingAddress }<br> 
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<b>Billing Address </b>: ${orderList.get(0).billingAddress }<br>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<b>Payment Mode </b>: ${orderList.get(0).paymentMode }<br>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<b>Order Created </b>: ${orderList.get(0).date }<br>
					</div>
				</div>
				<hr>
				<h4> Products ordered</h4>
	 			<table class="table table-hover table-bordered">
			  	  	<thead>
				      	<tr>
					      	<th > Product Name</th>
					        <th > Product Price</th>
					        <th > Product Quantity</th>
					        <th > Product Supplier</th>
					        <th > Order Status</th>
					        <th width="50"> </th>
				      	</tr>
			    	</thead>
    				<tbody>
    					<c:forEach var="listValue" items="${orderList}">								
					      	<tr>
							    <td>${listValue.productName}</td>
							    <td>${listValue.productPrice}</td>      
							    <td>${listValue.quantity}</td>
							    <td>${listValue.supplierName}</td>
							    <td>${listValue.productStatus}</td>
							    <c:choose>
							    	<c:when test="${listValue.productStatus!='Received by user' }">
							    		<td align="center" colspan="2">
        									<a href ="#receiveOrder" class="open-receiveDialog" data-id="${listValue.id}<->${listValue.productName}" data-toggle="modal" title="Info" data-target="#receiveOrder" ><span class="fa fa-gift" style="color:ORANGE" data-toggle="showtooltip" title="Receive"></span></a>
        								</td>
        							</c:when>
        							<c:otherwise>
        								<td align="center" colspan="2">
        									<a><span class="fa fa-check" style="color:green" data-toggle="showtooltip" title="Received on ${listValue.updateDate}"></span></a>
        								</td>
        							</c:otherwise>
        						</c:choose>	
					      	</tr>
					    </c:forEach>
    				</tbody>
				</table>
			</c:otherwise>
		</c:choose>
			<div id="receiveOrder" class="modal fade" role="dialog">
  		<div class="modal-dialog">
			<div class="modal-content">
    			<form action="user_receiveOrder" method="post">
      				<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
        				<h4 class="modal-title" id="receiveHeader"></h4>
      				</div>
      				<div class="modal-body">
      					<input type="text" name="msgId" id="msgId" style="border:none; text-align: center; width:100%">
      					<input type="hidden" id="order" name="orderId" >   
      				</div>
      				<div class="modal-footer">
     					<div class="col-md-6">
							<input class="btn btn-md btn-danger btn-block" type="submit" value="Receive">
						</div>
						<div class="col-md-6">
							<a class="btn btn-success btn-block" role="button"data-dismiss="modal" >Cancel</a>
						</div>
					</div>
      			</form>
    		</div>
		</div>
	</div>
 	</div>
 	<script type="text/javascript">
	
 	</script>
</div>
 