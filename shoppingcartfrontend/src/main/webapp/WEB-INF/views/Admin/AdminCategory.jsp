<div class="container">
    <div id="Login-box" class="row vertical-offset-75" >
    	<div class="col-md-6 col-md-offset-3">
    		<c:choose>
				<c:when test="${sessionUser.firstName==null}">
					<div class="alert alert-danger">
    					Session time out. Please login..
 					</div>
 				</c:when>
 				<c:otherwise>
    				<div class="panel panel-default"  style="background: rgba(255, 255, 255, 0.5 ) ;border-width: 2px; border-color:Gray">
			  			<div class="panel-heading" style="background-color: #009EEA">
			    			<c:if test="${Action=='Admin_addNewCategory'}">
			    				<h3 class="panel-title"style="color:white" >Admin: Add new Category</h3>
			    			</c:if>
			    			<c:if test="${Action=='Admin_updateCategory'}">
			    				<h3 class="panel-title"style="color:white" >Admin: Update Category</h3>
			    			</c:if>
			 			</div>
			  			<div class="panel-body">
			    			<form:form method="post" action="${Action}" enctype="multipart/form-data" commandName="category" modelAttribute="category">
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
										<label >Category ID</label>
										<c:if test="${Action=='Admin_addNewCategory'}">
			    							<input class="form-control" placeholder="Category ID" name="id" type="text" value="" required title="Only alphabets, numbers ,'-' & '_' allowed. Maximun 25 characters." pattern="[a-z0-9A-Z-_]+" maxlength="25">
			    						</c:if>
			    						<c:if test="${Action=='Admin_updateCategory'}">
			    							<input class="form-control" placeholder="Category ID" name="id" type="text" value='${category.id}' readonly>
			    						</c:if>
									</div>
									<div class="form-group">
										<label >Category Name</label>
										<input class="form-control" placeholder="Category Name" name="name" type="text" value='${category.name}' required maxlength="200">
									</div>
									<div class="form-group">
										<label >Category Description</label>
										<textarea class="form-control" placeholder="Category Description" style="resize: none" name ="description" rows="5" maxlength="250" >${category.description} </textarea>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<div class="col-md-4">
												<label for ="image" style="margin-top:30px">Category Image</label>
											</div>
											<div class="col-md-4">
												<c:if test="${Action=='Admin_addNewCategory'}">
				    								<img src="" width=100 height=100 id="output">
				  								</c:if>
				    							<c:if test="${Action=='Admin_updateCategory'}">
				    								<img src="<c:url value="/Resources/Images/Category/${category.id}.jpg"/>" width=100 height=100 id="output" />
				   					 			</c:if>
											</div>
											<div class="col-md-4">
												<a href="" ><label  for="fileBrowser" style="margin-top:30px">Click to upload</label></a>
												<input style="margin-top:20px ;opacity:0" name="images" type="file" onchange="loadFile(event)" accept="image/*" id="fileBrowser" >
											</div>
										</div>
									</div>	
									<div class="row">
										<div class="col-md-4 col-md-offset-2">
											<c:if test="${Action=='Admin_addNewCategory'}">
			    								<input class="btn btn-warning btn-block" type="submit" value="Add" style="margin-top:20px">
			    							</c:if>
			    							<c:if test="${Action=='Admin_updateCategory'}">
			   									<input class="btn btn-warning btn-block" type="submit" value="Update" style="margin-top:20px">
			   								</c:if>
										</div>
										<div class="col-md-4">
											<a href="Admin_displayCategory" class="btn btn-success btn-block" role="button" style="margin-top:20px">Cancel</a>
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