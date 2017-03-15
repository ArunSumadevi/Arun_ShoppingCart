<div class="container">
	<div id="Login-box" class="row vertical-offset-75">
		<div class="col-md-6 col-md-offset-3">
			<c:choose>
				<c:when test="${sessionUser.firstName==null}">
						<div class="alert alert-danger">
	    						Session time out. Please login..
	 					</div>
	 			</c:when>
	 			<c:otherwise>
					<div class="panel panel-default" style="background: rgba(255, 255, 255, 0.5); border-width: 2px; border-color: Gray">
						<div class="panel-heading" style="background-color: #009EEA">
							<c:if test="${Action=='Admin_addNewProduct'}">
								<h3 class="panel-title" style="color: white">Admin: Add new Product</h3>
							</c:if>
							<c:if test="${Action=='Admin_updateProduct'}">
								<h3 class="panel-title" style="color: white">Admin: Update Product</h3>
							</c:if>
						</div>
						<div class="panel-body">
							<form:form method="post" action="${Action}" enctype="multipart/form-data" commandName="Product" modelAttribute="product">
								<fieldset>
									<c:if test="${not empty errorMessage}">
										<div class="form-group">
											<div class="alert alert-danger">${errorMessage}</div>
										</div>
									</c:if>
									<c:if test="${not empty successMessage}">
										<div class="form-group">
											<div class="alert alert-success">${successMessage}</div>
										</div>
									</c:if>
									<div class="form-group">
										<label for="id">Product ID</label>
										<c:if test="${Action=='Admin_addNewProduct'}">
											<input class="form-control" placeholder="Product ID" required='true' name="id" type="text" value=""title="Only alphabets, numbers ,'-' & '_' allowed. Maximun 25 characters." mxaxlength='25' pattern="[a-z0-9A-Z-_]+">
										</c:if>
										<c:if test="${Action=='Admin_updateProduct'}">
											<input class="form-control" placeholder="Product ID" name="id" type="text" value='${product.id}' } readonly>
										</c:if>
		
									</div>
									<div class="form-group">
										<label for="name">Product Name</label> 
										<input class="form-control"  placeholder="Product Name" name="name" type="text" value='${product.name}' required='true' maxlength='200'>
									</div>
									<div class="form-group">
										<label for="description" >Product Description</label>
										<textarea class="form-control" placeholder="Product Description" style="resize: none" name="description" rows="3" required='true' maxlength='2500'>${product.description}</textarea>
									</div>
									<div class="form-group">
										<label for="categoryId">Category ID ${product.categoryId}</label> 
										<select class="form-control" name="categoryId">
											<c:if
												test="${sessionCategoryList.size()==0 || product.categoryId==null || product.categoryId=='Null'}">
												<option>Null</option>
											</c:if>
											<c:forEach var="listValue" items="${sessionCategoryList}">
												<c:choose>
													<c:when test="${listValue.id==product.categoryId}">
														<option selected>${listValue.id}<->${listValue.name}</option>
													</c:when>
													<c:otherwise>
														<option>${listValue.id}<->${listValue.name}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="suoolierId">Supplier ID</label> 
										<select class="form-control" name="supplierId">
											<c:if test="${sessionSupplierList.size()==0 || product.supplierId==null || product.supplierId=='Null'}">
												<option>Null</option>
											</c:if>
											<c:forEach var="listValue" items="${sessionSupplierList}">
												<c:choose>
													<c:when test="${listValue.id==product.supplierId}">
														<option selected>${listValue.id}<->${listValue.name}</option>
													</c:when>
													<c:otherwise>
														<option>${listValue.id}<->${listValue.name}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="price">Product Price</label>
										 <input class="form-control" placeholder="Product Price" name="price" type="text" value='${product.price}' required title="Only numbers & '.' allowed. please use format xxxx.xx" pattern="[0-9]+(\.[0-9][0-9]?)?" maxlength='12'>
									</div>
									<div class="form-group">
										<label for="stock">Stock Available</label> 
										<input class="form-control" placeholder="Stock Available" name="stock" type="text" value='${product.stock}'required title="Only numbers allowed." pattern="[0-9]+"  maxlength='10'>
		
									</div>
									<div class="form-group">
										<label for="addedOn">Product Added Since</label> 
										<input class="form-control" placeholder="Product Added Since" name="addedOn" type="date" value='${AddDate}'readonly>
		
									</div>
									<div class="form-group">
									<div class="col-md-12">
									<div class="col-md-4">
										<label for ="image" style="margin-top:30px">Product Image</label>
									</div>
									<div class="col-md-4" style="width:100px; height:100px">
											<img src="<c:url value="/Resources/Images/Product/${product.id}.jpg"/>" style="width:100px; height:100px" id="output" />
										</div>
										<div class="col-md-4">
											<a href="" ><label  for="fileBrowser" style="margin-top:30px">Click to upload</label></a>
											<input style="margin-top:20px ;opacity:0" name="images" type="file" onchange="loadFile(event)" accept="image/*" id="fileBrowser" >
										</div>
									</div>
									</div><hr>
									<div class="row">
											<div class="col-md-4 col-md-offset-2">
											<c:if test="${Action=='Admin_addNewProduct'}">
												<input class="btn btn-md btn-warning btn-block" type="submit" value="Add" style="margin-top:20px">
											</c:if>
											<c:if test="${Action=='Admin_updateProduct'}">
												<input class="btn btn-md btn-warning btn-block" type="submit" value="Update" style="margin-top:20px">
											</c:if>
										</div>
										<div class="col-md-4">
											<a href="Admin_displayProduct" class="btn btn-success btn-block" role="button" style="margin-top:20px">Cancel</a>
										</div>
									</div>
								</fieldset>
							</form:form>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript">
		var loadFile = function(event) 
			{
				var output = document.getElementById('output');
				output.src = URL.createObjectURL(event.target.files[0]);
			};
	</script>
</div>