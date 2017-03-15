<div class="container">
	<c:choose>
		<c:when test="${sessionUser.firstName==null}">
			<div class="alert alert-danger">
    			Session time out. Please login..
 			</div>
 		</c:when>
 		<c:otherwise>
 			<div class="col-md-10 col-sm-offset-1">
				<div class="panel panel-primary">
                   	<div class="panel-heading"><h4>Showing-${Display}</h4></div>
                   	<div class="panel-body">
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
						<c:if test="${Display=='Categories'}">
							<c:if test="${not empty message}">
           						<div class="row alert alert-info text-center"> ${message} </div>
                    		</c:if> 
							<div class="col-md-12 ">
								 <table class="table table-hover ">
							  	  	<thead>
								      	<tr>
									      	<th width="50">No</th>
									      	<th > Icon</th>
									        <th>Category Id</th>
									        <th>Category Name</th>
									        <th class="text-center">Category Description</th>
									        <th width="25"></th>
									        <th width="25"> </th>
								      	</tr>
							    	</thead>
							    	<tbody>
									    <c:set var="sequence" value="1" />
									    <c:forEach var="listValue" items="${sessionCategoryList}">								
									    	<tr>
												<td>${sequence}</td>
											    <td><img src="<c:url value="/Resources/Images/Category/${listValue.id}.jpg"/>" class="img-rounded" width="100" height="100" border="1px" /></td>
											    <td>${listValue.id}</td>
											    <td>${listValue.name}</td>
											    <td>${listValue.description}</td>
										 		<td align="center"><a href ="Admin_modifyCategory?id=${listValue.id}"><span class="fa fa-pencil" style="color:Green" data-toggle="showtooltip" title="Edit"></span></a></td>
												<td align="center" colspan="2"> <a href ="#addBookDialog" class="open-AdminDeleteDialog" data-id="category<->${listValue.name}<->${listValue.id}" data-toggle="modal" title="Info" data-target="#adminDeleteModal" ><span class=" fa fa-trash " style="color:Red"data-toggle="showtooltip" title="Delete"></span></a></td>
											</tr>
											<c:set var="sequence" value="${sequence+1}" />
									    </c:forEach>
									</tbody>
  								</table>
 							</div>
 						</c:if>
  						<c:if test="${Display=='Suppliers'}">
 							<div class="col-md-12 ">
								<table class="table table-hover ">
								    <thead>
								      <tr>
								      	<th width="50">No</th>
								        <th width="100">Supplier Id</th>
								        <th>Supplier Name</th>
								        <th>Supplier Description</th>
								        <th width="50"></th>
								        <th width="50"></th>
								        <th width="50"> </th>
								      </tr>
								    </thead>
						    		<tbody>
							    		<c:set var="sequence" value="1" />
							    		<c:forEach var="listValue" items="${sessionSupplierList}">
											<tr>
											 	<td>${sequence}</td>
										        <td>${listValue.id}</td>
										        <td>${listValue.name}</td>
										        <td>${listValue.description}</td>
										        <td align="center"><a href="#supplierInfo" class="open-supplierInfo" data-id="${listValue.id}<->${listValue.name}<->${listValue.mobile}<->${listValue.email}<->${listValue.description}<->${listValue.address}<->${listValue.addedOn}" title="Info" data-toggle="modal" title="Info" data-target="#displaySupp" ><span class="fa fa-info-circle" style="color:blue"data-toggle="showtooltip" title="Info"></span></a></td>
										        <td align="center"><a href ="Admin_modifySupplier?id=${listValue.id}"data-toggle="showtooltip" title="Modify"><span class="fa fa-pencil" style="color:Green"></span></a></td>
							       				<td align="center" colspan="2"><a href ="#addBookDialog" class="open-AdminDeleteDialog" data-id="supplier<->${listValue.name}<->${listValue.id}" data-toggle="modal" title="Info" data-target="#adminDeleteModal" ><span class=" fa fa-trash " style="color:Red"data-toggle="showtooltip" title="Delete"></span></a></td>
							      			</tr>
							      			<c:set var="sequence" value="${sequence+1}" />
							      		</c:forEach>
						    		</tbody>
				  				</table>
							</div>
						</c:if>
  						<c:if test="${Display=='Products'}">
 							<div class="col-md-12 ">
								 <table class="table table-hover">
							  	  	<thead>
								      	<tr>
									      	<th width="50">No</th>
									      	<th with="100" > Icon</th>
									        <th width="100">Product Id</th>
									        <th>Product Name</th>
									        <th>Product Description</th>
									        <th width="50"></th>
									        <th width="50"></th>
									        <th width="50"> </th>
								      </tr>
							    	</thead>
							    	<tbody>
    									<c:set var="sequence" value="1" />					
										<c:forEach var="listValue" items="${productList}">	
											<tr>
								       			<td>${sequence}</td>
										      	<td><img src="<c:url value="/Resources/Images/Product/${listValue.id}.jpg"/>" width=100 height=100 id="output" ></td>
										        <td>${listValue.id}<c:if test="${listValue.supplierId==null || listValue.categoryId==null }"><label style="color:red">*</label></c:if></td>
										        <td>${listValue.name}</td>
										        <td>${listValue.description}</td>
								                <td align="center"><a href="#productInfo" class="open-productInfo" data-id="${listValue.id}<->${listValue.name}<->${listValue.description}<->${listValue.category.name}<->${listValue.supplier.name}<->${listValue.price}<->${listValue.stock}<->${listValue.addedOn}<-><c:url value="/Resources/Images/Product/"/>" title="Info" data-toggle="modal" title="Info" data-target="#displayModal" ><span class="fa fa-info-circle" style="color:blue"data-toggle="showtooltip" title="Info"></span></a></td>
											    <td align="center"><a href ="Admin_modifyProduct?id=${listValue.id}"data-toggle="showtooltip" title="Modify"><span class="fa fa-pencil" style="color:Green"></span></a></td>
											    <td align="center" colspan="2"><a href ="#addBookDialog" class="open-AdminDeleteDialog" data-id="product<->${listValue.name}<->${listValue.id}" data-toggle="modal" title="Info" data-target="#adminDeleteModal" ><span class=" fa fa-trash " style="color:Red"data-toggle="showtooltip" title="Delete"></span></a></td>
								       		</tr>
      										<c:set var="sequence" value="${sequence+1}" />
      									</c:forEach>
      								</tbody>
  								</table>
 							</div>
  						</c:if>
 					</div>
 				</div>
 			</div>
		</c:otherwise>
	</c:choose>
	<div id="adminDeleteModal" class="modal fade" role="dialog">
  		<div class="modal-dialog">
			<!-- Modal content-->
   			<div class="modal-content">
      			<div class="modal-header">
      				<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4 class="modal-title" id="deleteHeader"></h4>
      			</div>
      			<div class="modal-body">
      				<input type="text" name="bookId" id="bookId" style="border:none; text-align: center; width:100%">    
      			</div>
      			<div class="modal-footer">
     				<div class="col-md-6">
						<a href="" id="but1"class="btn btn-danger btn-block" role="button">Delete</a>
					</div>
					<div class="col-md-6">
						<a href="Index" class="btn btn-success btn-block" role="button"data-dismiss="modal" >Cancel</a>
					</div>
				</div>
    		</div>
		</div>
	</div>
	<div id="displayModal" class="modal fade" role="dialog">
 		<div class="modal-dialog modal-lg"  role="document">
			<!-- Modal content-->
    		<div class="modal-content">
      			<div class="modal-header">
      				<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4 class="modal-title" id="displayHeader"></h4>
      			</div>
      			<div class="modal-body">
      				<div class="col-md-10 col-md-offset-1">
      					<div class="panel panel-default" style="background: rgba(255, 255,255, 0.5 ) ;border-width: 2px; border-color:Transparent">
							<div class="panel-body">
								<div class="col-md-6"><img id="productImage"  width=200px height=100%></div>
								<div class="col-md-6">
									<div class="row">
										<label><b>Product Id</b></label><br>
										<label id="prdId"></label>
										<hr>
									</div>
									<div class="row">
										<label><b>Product description</b></label><br>
										<label id="prdDes"></label>
										<hr>
									</div>
									<div class="row">
										<label><b>Product category</b></label><br>
										<label id="prdCat"></label>
										<br><hr>
									</div>
									<div class="row">
										<label><b>Product Supplier</b></label><br>
										<label id="prdSup"></label>
										<hr>
									</div>
									<div class="row">
										<label><b>Product Price</b></label><br>
										<label id="prdPrice"></label>
										<hr>
									</div>
									<div class="row">
										<label><b>Product Stock</b></label><br>
										<label id="prdQty"></label>
										<hr>
									</div>
									<div class="row">
										<label><b>Product Added on</b></label><br>
										<label id="prdAdded"></label>
									</div>
								</div>
							</div>
						</div>
      				</div>
      				<div class="modal-footer">
     					<div class="col-md-6 col-md-offset-3">
							<a href="Index" class="btn btn-success btn-block" role="button"data-dismiss="modal" >Close</a>
						</div>
					 </div>
    			</div>
			</div>
		</div>
	</div>
	<div id="displaySupp" class="modal fade" role="dialog">
  		<div class="modal-dialog modal-sm" >
		<!-- Modal content-->
    		<div class="modal-content">
      			<div class="modal-header">
       		 		<button type="button" class="close" data-dismiss="modal">&times;</button>
       		 		<h4 class="modal-title" id="suppHeader"></h4>
      			</div>
     			<div class="modal-body">
     				<div class="row col-md-offset-1">
						<label><b>Supplier Id</b></label><br>
						<label id="supId"></label>
						<hr>
					</div>
					<div class="row col-md-offset-1">
						<label><b>Supplier contact</b></label><br>
						<label id="supContact"></label>
						<hr>
					</div>
					<div class="row col-md-offset-1">
						<label><b>Supplier email</b></label><br>
						<label id="supEmail"></label>
						<br><hr>
					</div>
					<div class="row col-md-offset-1">
						<label><b>Supplier Description</b></label><br>
						<label id="supDesc"></label>
						<hr>
					</div>
					<div class="row col-md-offset-1">
						<label><b>Supplier Address</b></label><br>
						<label id="supAddr"></label>
						<hr>
					</div>
					<div class="row col-md-offset-1">
						<label><b>Supplier Since</b></label><br>
						<label id="supSince" ></label>
					</div>
				</div>
				<div class="modal-footer">
     				<div class="col-md-6 col-md-offset-3">
						<a href="Index" class="btn btn-success btn-block" role="button"data-dismiss="modal" >Close</a>
					</div>
				</div>
    		</div>
		</div>
	</div>
</div>