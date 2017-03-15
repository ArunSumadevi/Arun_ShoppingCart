<div class="container" ng-app="myApp" ng-controller="RegPassCompare">
	<div id="signUp-box" class="row vertical-offset-75">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-default" style="background: rgba(255, 255,255, 0.5 ) ;border-width: 2px; border-color:gray">
				<div class="panel-heading" style="background-color: #009EEA">
					<h3 class="panel-title" style="color:white">Register New User</h3>
				</div>
				<div class="panel-body">
					<form:form  method="post" action="UserReg" commandName="user" modelAttribute="user" name='form' onsubmit="return confirmPass()">
						<fieldset>
							<c:if test="${not empty errorMessage}">
								<div class="form-group">
									<div class="alert alert-danger">${errorMessage}</div>
								</div>
							</c:if>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>E-mail</label>
										<input type="email" name="email" placeholder="Someone@mail.com" Class="form-control" required="true" maxlength="200"/>
									</div>
								</div>
							</div>
							<div ng-app="frmVal">
    
							<div class="row"  >
								<div class="col-md-6 " >
									<div class="form-group">
										<label >New Password</label>
										<input   type="password" name="password" placeholder="(Minimum 8 characters)" title="Minimum 8 characters, one number, one uppercase and one lowercase letter"  pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*" required Class="form-control"  maxlength="100" id="newPass"/>
									</div>
								</div>
								<div class="col-md-6" >
									<div class="form-group">
										<label>Confirm Password</label><label style="color:red" id='errMsg'></label>
										 <input  type="password" name="cnfPassword" placeholder="Confirm password" required="true" Class="form-control" id="cnfPass" />
									</div>
									
								</div>
								
							</div>
							
							<div class="row"><div class="col-md-12"></div></div>
							<div class="row">
								<div class="col-md-6 " >
									<div class="form-group">
										<label>First Name</label>
										<input type="text" name="firstName" placeholder=" (Minimum 4 characters)"  ^[a-zA-Z ]{4,}$ title="First name should be minimum 4 character and contain alphabets only" required="true" Class="form-control" maxlength="100"/>
									</div>
								</div>
								<div class="col-md-6" >
									<div class="form-group">
										<label>Last Name</label>
										<input type="text" name="lastName" placeholder="Last Name" pattern="^[a-zA-Z ]+$" title="Last name should only contain Alphabets and space" required="true" Class="form-control" maxlength="100"/>
									</div>
								</div>
							</div>	
							<div class="row">
								<div class="col-md-6 " >
									<div class="form-group">
										<div class="row"><div class="col-md-12 " ><label>Gender</label></div></div> 
										<div class="row">
											<div class="col-md-3 col-md-offset-2"><label class="radio-inline"> <input checked type="radio" name="gender" value="Male" >Male</label></div>
											<div class="col-md-3 "><label class="radio-inline"> <input type="radio" name="gender" value="Female" >Female</label></div>
										</div>
									</div>
								</div>
								<div class="col-md-6" >
									<div class="form-group">
										<label>Mobile Number</label>
										<input type="text" name="mobile" placeholder="Mobile number " pattern="^\d{10}$" title="Mobile number should be in 10 digit mobile number format" minlength="10" required="true" Class="form-control" maxlength="10"/>
									</div>
								</div>
							</div>
							<div class="row"><div class="col-md-12"></div></div>
							<div class="row">
								<div class="col-md-12 " >
									<div class="form-group">
										<label>Address 1 </label>
										<input type="text" name="address1" placeholder="Address 1"required="true" Class="form-control" maxlength="200"/>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 " >
									<div class="form-group">
										<label>Address 2 </label>
								 		<input type="text" name="address2" placeholder="Address 2" Class="form-control" maxlength="200"/>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 " >
									<div class="form-group">
										<label>City</label>
									 	<input type="text" name="city" placeholder="City" required="true" Class="form-control" maxlength="50"/>
									</div>
								</div>
								<div class="col-md-6" >
									<div class="form-group">
										<label>State</label>
										<input type="text" name="state" placeholder="State"  required="true" Class="form-control" maxlength="50"/>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 " >
									<div class="form-group">
										<label>Pincode</label>
										<input type="text" name="pincode" placeholder="Pincode"  pattern="^[0-9]+$" title="6 digit pincode" required="true" Class="form-control" maxlength="6"/>
									</div>
								</div>
								<div class="col-md-6" >
									<div class="form-group">
										<label>Country</label><input type="text" name="country" placeholder="Country" value="India" readonly required="true" Class="form-control" style="background-color:white"/>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<input class="btn btn-md btn-success btn-block" type="submit"value="Register" onClick="check()">
								</div>
								<div class="col-md-6">
									<a href="Index" class="btn btn-danger btn-block" role="button">Cancel</a>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	function confirmPass()
	{
		
		var pass=document.getElementById('newPass').value;
		var cnf=document.getElementById('cnfPass').value;
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