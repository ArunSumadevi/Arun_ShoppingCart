<div class="container">
    <div id="Login-box" class="row vertical-offset-75" >
    	<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default" style="background: rgba(255, 255, 255, 0.5 ) ;border-width: 2px; border-color:Gray" >
			  	<div class="panel-heading" style="background-color: #009EEA">
			    	<h3 class="panel-title"style="color:white" >Change Password</h3>
			 	</div>
			  	<div class="panel-body">
				  	<c:choose>
						<c:when test="${sessionUser.firstName==null}">
							<div class="alert alert-danger">Session time out. Please login..</div>
	 					</c:when>
	 					<c:otherwise>
				    		<form:form method="post" action="user_changePassword" commandName="password" modelAttribute="password" onsubmit="return confirmPass()">
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
									
										<label style="color:Black">Current Password</label>
										<input class="form-control"  name="email" type="hidden" value="${sessionUser.email}">
										<input class="form-control" name="password" type="password" value="">
									</div>
									<div >
										<div class="form-group">
											<label style="color:Black"> New Password</label>
											<input type="password"class="form-control" name="newPassword" placeholder="(Minimum 8 characters)" title="Minimum 8 characters, one number, one uppercase and one lowercase letter"  pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*" required Class="form-control" id="newPass" maxlength="100"">
										</div>
										<div class="form-group" >
											<div class="row">
												<div class="col-md-6">
													<label style="color:Black"> Confirm Password</label>
												</div>
												<div class="col-md-6">
													<label style="color:red" class="text-right" id="errMsg"></label>
												</div>
											</div>
											<input class="form-control" placeholder="Confirm Password" name="cnfPassword"  type="password" value=""id="cnfPass">
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<input class="btn btn-md btn-danger btn-block" type="submit" value="Change Password">
										</div>
										<div class="col-md-6">
											<a href="Index" class="btn btn-success btn-block" role="button">Cancel</a>
										</div>
									</div>	
								</fieldset>
				      		</form:form>
				      	</c:otherwise>
				    </c:choose>
				    <script type="text/javascript">
				    
				    function confirmPass()
					{
						
						var pass=document.getElementById('newPass').value;
						var cnf=document.getElementById('cnfPass').value;
						alert(pass);
						alert(cnf);
						if(pass==cnf)
							{
							return true;
							}
						else
							{
							document.getElementById('errMsg').innerHTML=" * Mismatch";
							return false;
							}
						
					};
				    </script>
				</div>
			</div>
		</div>
	</div>
</div>