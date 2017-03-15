<div class="container">
    <div id="Login-box" class="row vertical-offset-75" >
    	<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default" style="background: rgba(255, 255, 255, 0.5 ) ;border-width: 2px; border-color:Gray" >
			  	<div class="panel-heading" style="background-color: #009EEA">
			    	<h3 class="panel-title"style="color:white" >Login</h3>
			 	</div>
			  	<div class="panel-body">
			    	<form action="${pageContext.request.contextPath}/login" method="post" >
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
						<div class="form-group"  >
							<label style="color:Black">E-mail</label>
							<input class="form-control" placeholder="E-mail Id" name="email" type="text" value="" required>
						</div>
						<div class="form-group">
							<label style="color:Black">Password</label>
							<input class="form-control" placeholder="password" name="password" type="password" value="" required>
						</div>
						<div class="row">
							<div class="col-md-4">
						</div>
						<div class="col-md-4">
							<input class="btn btn-md btn-primary btn-block" type="submit" value="Login">
						</div>
					</div>	
					<hr>
					<div class="row">
						<input class="btn btn-md btn-link btn-block" type="button" style="color:Blue" value="Forgot password?" onClick="window.location.href='ForgotPass'">
					</div>	
			    </form>
			</div>
		</div>
	</div>
</div>
		
</div>