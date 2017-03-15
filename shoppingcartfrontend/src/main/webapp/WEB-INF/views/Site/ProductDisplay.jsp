<div class="container">
	<div id="signUp-box" class="row vertical-offset-75">
		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-default" style="background: rgba(255, 255,255, 0.5 ) ;border-width: 2px; border-color:gray">
				<div class="panel-body">
					<div class="col-md-6">
						<div class="row">
							<img src="<c:url value="/Resources/Images/Product/${product.id}.jpg"/>" width=200px height=100% />
						</div>
						<br>
						<div class="row">
							<div class="col-md-6">
								<a href="productShow?id=${product.category.id}" class="btn btn-info btn-block" role="button" >Back</a>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row">
							<b> ${product.name}</b><br><hr>
						</div>
						<div class="row">
							<b>Description:</b><br>
							${product.description }<br><hr>
						</div>
						<div class="row">
							<b>Price:</b><br>
							${product.price }<br><hr>
						</div>
						<div class="row">
							<b>Qty available:</b><br>
							${product.stock }<br><hr>
						</div>	
						<%--  display add to cart and buy noe only if loged in & stock available --%>
						<c:if test="${sessionUser.firstName!=null && product.stock!=0 && sessionUser.marked==false}">
							<div class="row">
								<div class="col-md-6">
									<a  href="user_productAddCart?id=${product.id}" class="btn btn-warning btn-block" role="submit" onclick="form.submit()"><span class="fa fa-shopping-cart"></span> Add to Cart</a>
								</div>
								<div class="col-md-6">
									<a href="user_buyNow?id=${product.id}" class="btn btn-success btn-block" role="button" ><span class="fa fa-check-square-o"></span> Buy Now</a>
								</div>
							</div>
						</c:if>
						<c:choose>
							<c:when test="${product.stock==0}">
								<div class="form-group alert alert-danger text-center">
									Out of stock..
 								</div>
							</c:when>
							<c:otherwise>
								<c:if  test="${sessionUser.firstName==null}"> 
									<div class="form-group alert alert-warning text-center">
										Need to login to buy.
 									</div>
								</c:if>
								<c:if  test="${sessionUser.marked==true}"> 
									<div class="form-group alert alert-danger text-center">
										Account is marked due to password reset. Please change password to use account.
 									</div>
								</c:if>
							</c:otherwise>
						</c:choose>
						<c:if test="${not empty errorMessage}">
							<div class="form-group alert alert-danger">
								${errorMessage}
 							</div>
						</c:if>
						<c:if test="${not empty successMessage}">
							<div class="form-group alert alert-success">
								${successMessage}
 							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>