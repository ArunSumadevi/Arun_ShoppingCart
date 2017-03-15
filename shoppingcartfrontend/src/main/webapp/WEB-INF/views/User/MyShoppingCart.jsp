<div class="container">
	<div class="col-md-12" >
		<h3 class="text-center"> Shopping cart</h3><hr>
		<c:choose>
			<c:when test="${sessionUser.firstName==null}">
				<div class="alert alert-danger">Session time out. Please login..</div>
 			</c:when>
 			<c:otherwise>
    			<c:choose>
    				<c:when test="${shoppingCart.size()==0}">
    					<div class="alert alert-danger">
    						Shopping cart is empty..
 						</div>
 					</c:when>
 					<c:otherwise>
    					<table class="table table-hover table-bordered">
  	  						<thead>
      							<tr>
      								<th width="100" > Icon</th>
        							<th width="300" >Product Name</th>
        							<th width="350" >Product Description</th>
        							<th width="100" >Price</th>
        							<th width="50">Qty</th>
        							<th width="100" >Total</th>
         							<th width="100" >Date added</th>
         							<th width="50"></th>
        							<th width="50"> </th>
      							</tr>
    						</thead>
    						<tbody>
								<c:forEach var="listValue" items="${shoppingCart}">										
      								<tr>
      									<td><img src="<c:url value="/Resources/Images/Product/${listValue.productId}.jpg"/>" width=100px height=100px /></td>
        								<td>${listValue.product.name}</td>
        								<td>${listValue.product.description}</td>
        								<td><span class="fa fa-inr" >${listValue.product.price}</td>
										<td>${listValue.quantity}</td>
										<td><span class="fa fa-inr" >${listValue.quantity*listValue.product.price }</td>
		 								<td>${listValue.addedOn}</td>
        								<td align="center">
        									<a href ="#cartInfo" class="open-myCartInfo" data-id="${listValue.product.name}<->${listValue.id}<->${listValue.userId}<->${listValue.productId}<->${listValue.addedOn}<->${listValue.product.stock}" data-toggle="modal" title="Info" data-target="#cartInfo" ><span class="fa fa-pencil" style="color:Green" data-toggle="showtooltip" title="Modify"></span></a> 
        								</td>
        								<td align="center" colspan="2">
        									<a href ="#deleteCartModal" class="open-myCartDeleteDialog" data-id="${listValue.id}<->${listValue.product.name}<->${listValue.userId}" data-toggle="modal" title="Info" data-target="#deleteCartModal" ><span class=" fa fa-trash " style="color:Red" data-toggle="showtooltip" title="Delete"></span></a>
        								</td>
									</tr>
      							</c:forEach>
    						</tbody>
  						</table>
  						<div class="col-md-2 col-md-offset-10">
   							<a href="user_cartCheckOut?id=${sessionUser.id}" class="btn btn-success btn-block" role="button">Check Out</a>
   						</div>
  					</c:otherwise>
  				</c:choose>
			</c:otherwise>
		</c:choose>
 	</div>

	<!-- Modal to display delete message -->
	
 	<div id="deleteCartModal" class="modal fade" role="dialog">
  		<div class="modal-dialog">
			<div class="modal-content">
    			<form action="user_deleteCart" method="post">
      				<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
        				<h4 class="modal-title" id="deleteHeader"></h4>
      				</div>
      				<div class="modal-body">
      					<input type="text" name="msgId" id="msgId" style="border:none; text-align: center; width:100%">
      					<input type="hidden" id="user" name="userId" >  
      					<input type="hidden" id="cart" name="cartId" >  
      				</div>
      				<div class="modal-footer">
     					<div class="col-md-6">
							<input class="btn btn-md btn-danger btn-block" type="submit" value="Delete">
						</div>
						<div class="col-md-6">
							<a href="Index" class="btn btn-success btn-block" role="button"data-dismiss="modal" >Cancel</a>
						</div>
					</div>
      			</form>
    		</div>
		</div>
	</div>

	<!-- Modal to display information message -->
	
	<div id="cartInfo" class="modal fade" role="dialog">
  		<div class="modal-dialog">
		    <div class="modal-content">
    			<form action="user_modifyCart" method="post">
      				<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
        				<h4 class="modal-title" id="displayHeader"></h4>
      				</div>
      				<div class="modal-body">
      				<div class="col-md-6 col-md-offset-3">
						<label><b>select quantity to update   </b></label>
						<select id="quantity" name="cartQuantity" ></select>
						</div>
						<br>
						<input type="hidden" id="cartId" name="cartId">
						<input type="hidden" id="productId" name="productId">
						<input type="hidden" id="userId" name="userId">
						<input type="hidden"  id="addedOn" name="addedOn">
					</div>   
      				<div class="modal-footer">
	     				<div class="col-md-6">
							<input class="btn btn-md btn-warning btn-block" type="submit" value="Update">
						</div>
						<div class="col-md-6">
							<a href="Index" class="btn btn-success btn-block" role="button"data-dismiss="modal" >Cancel</a>
						</div>
					</div>
  				</form>      
			</div>
		</div>
	</div>
</div>