<div class="container">
	<div class="col-md-8 col-md-offset-2">
		<c:choose>
			<c:when test="${orderList.isEmpty() }">
				<div class="alert alert-danger">${errorMessage }</div>
			</c:when>
			<c:otherwise>
				<h3 class="text-center"> Showing orders for ${orderList.get(0).user.firstName} ${orderList.get(0).user.lastName}</h3><hr>
	 			<table class="table table-hover table-bordered">
	 				<thead>
      					<tr>
					      	<th > Order Number</th>
					        <th>Created On</th>
					        <th>Payment Mode</th>
					        <th>Order Status</th>
					    </tr>
    				</thead>
    				<tbody>
    					<c:forEach var="listValue" items="${orderList}">								
						    <tr>
						      	<td><a href ="user_displayOrder?id=${listValue.number}">${listValue.number}</td>
						        <td>${listValue.date}</td>
						        <td>${listValue.paymentMode}</td>
						         <td>${listValue.orderStatus}</td>
						    </tr>
      					</c:forEach>
      				</tbody>
  				</table>
  			</c:otherwise>
  		</c:choose>
 	</div>
 </div>
 