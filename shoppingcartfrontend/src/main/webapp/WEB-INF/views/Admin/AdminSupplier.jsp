<div class="container">
    <div id="Login-box" class="row vertical-offset-75" >
    	<div class="col-md-4 col-md-offset-4">
    		<c:choose>
				<c:when test="${sessionUser.firstName==null}">
					<div class="alert alert-danger">
	    				Session time out. Please login..
	 				</div>
	 			</c:when>
	 			<c:otherwise>
	    			<div class="panel panel-default"  style="background: rgba(255, 255, 255, 0.5 ) ;border-width: 2px; border-color:Gray">
				  		<div class="panel-heading" style="background-color: #009EEA">
				  			<c:if test="${Action=='Admin_addNewSupplier'}">
				    			<h3 class="panel-title"style="color:white" >Admin: Add new Supplier</h3>
				    		</c:if>
				    		<c:if test="${Action=='Admin_updateSupplier'}">
				    			<h3 class="panel-title"style="color:white" >Admin: Update Supplier</h3>
				    		</c:if>
				 		</div>
			  			<div class="panel-body">
			    			<form:form method="post" action="${Action}" commandName="supplier" modelAttribute="supplier">
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
										<label >Supplier ID</label>
										<c:if test="${Action=='Admin_addNewSupplier'}">
			    							<input class="form-control" placeholder="Supplier ID" name="id" type="text" value='${supplier.id}'title="Only alphabets, numbers ,'-' & '_' allowed. Maximun 25 characters." pattern="[a-z0-9A-Z-_]+" required='true' maxlength='25'>
			    						</c:if>
			    						<c:if test="${Action=='Admin_updateSupplier'}">
			    							<input class="form-control" placeholder="Supplier ID" name="id" type="text" value='${supplier.id}' readonly>
			    						</c:if>
									</div>
									<div class="form-group">
										<label >Supplier Name</label>
										<input class="form-control" placeholder="Supplier Name" name="name" type="text" value='${supplier.name}' required='true' maxlength='200'>
									</div>
									<div class="form-group">
										<label >Supplier Contact</label>
										<input class="form-control" placeholder="Supplier Contact" name="mobile" type="text" value='${supplier.mobile}'pattern="^\d{10}$" title="Mobile number should be in 10 digit mobile number format" minlength="10" required="true"  maxlength="10">
									</div>
									<div class="form-group">
										<label >Supplier email</label>
										<input class="form-control" placeholder="Supplier email" name="email" type="email" value='${supplier.email}'required='true' maxlength='200'>
									</div>
									<div class="form-group">
										<label >Supplier Description</label>
										<textarea class="form-control" placeholder="Supplier Description" style="resize: none" name ="description" rows="5" required='true' maxlength='250'>${supplier.description}</textarea>
									</div>
									<div class="form-group">
										<label >Supplier Address</label>
										<textarea class="form-control" placeholder="Supplier Address" style="resize: none" name ="address" rows="5" required='true' maxlength='250'>${supplier.address}</textarea>
									</div>
									<div class="form-group">
										<label >Supplier Since</label>
										<input class="form-control" placeholder="Supplier Since" name="addedOn" type="text" value='${AddDate}' readonly>
									</div>
									<div class="row">
										<div class="col-md-4 col-md-offset-2">
											<c:if test="${Action=='Admin_addNewSupplier'}">
			    								<input class="btn btn-md btn-warning btn-block" type="submit" value="Add">
			    							</c:if>
			   	 							<c:if test="${Action=='Admin_updateSupplier'}">
			    								<input class="btn btn-md btn-warning btn-block" type="submit" value="Update">
			   		 						</c:if>
										</div>
										<div class="col-md-4">
											<a href="Admin_displaySupplier" class="btn btn-success btn-block" role="button" >Cancel</a>
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
</div>