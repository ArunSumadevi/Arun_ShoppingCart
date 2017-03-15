<div class="contaier">
	<div id="Login-box" class="row vertical-offset-75">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-default" style="background: rgba(255, 255,255, 0.5 ) ;border-width: 2px; border-color:gray">
				<div class="panel-heading">
					<h3 class="panel-title" >Hello  ${sessionUser.firstName} ${sessionUser.lastName}</h3>
				</div>
				<div class="panel-body">
					<c:choose>
						<c:when test="${sessionUser.firstName==null}">
							<div class="alert alert-danger">Session time out. Please login..</div>
 						</c:when>
 						<c:otherwise>
 							<label style="color:red"> * fields are not editable.</label>			 
							<form:form  method="post" action="user_updateUser" commandName="user" modelAttribute="user">
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
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>E-mail </label><label style="color:red">*</label>
												<input type="email" name="email"  Class="form-control" readonly style="background-color:white" value="${sessionUser.email }"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 " >
											<div class="form-group">
												<label>First Name</label><label style="color:red"> *</label>
												<input type="text" name="firstName" readonly Class="form-control" style="background-color:white" value="${sessionUser.firstName}"/>
											</div>
										</div>
										<c:if test="${!editEnable}">
											<div class="col-md-6" >
												<div class="form-group">
													<label>Last Name</label>
													<input type="text" name="lastName" placeholder="Last Name" pattern="^[a-zA-Z ]+$" title="Last name should only contain Alphabets" required="true" Class="form-control" readonly style="background-color:white" value="${sessionUser.lastName}" maxlength="100"/>
												</div>
											</div>
									</div>	
									<div class="row">
										<div class="col-md-6 " >
											<div class="form-group">
												<div class="row"><div class="col-md-12 " ><label>Gender</label><label style="color:red"> *</label></div></div> 
												<div class="row">
													<c:if test="${sessionUser.gender == 'Male' }">
														<div class="col-md-3 col-md-offset-2"><label class="radio-inline"> <input checked type="radio" name="gender" value="Male"style="background-color:white" disabled>Male</label></div>
														<div class="col-md-3 "><label class="radio-inline"> <input type="radio" name="gender" value="Female"style="background-color:white" disabled>Female</label></div>
													</c:if>
													<c:if test="${sessionUser.gender == 'Female' }">
														<div class="col-md-3 col-md-offset-2"><label class="radio-inline"> <input  type="radio" name="gender" value="Male"style="background-color:white" disabled>Male</label></div>
														<div class="col-md-3 "><label class="radio-inline"> <input type="radio" checked name="gender" value="Female"style="background-color:white" disabled>Female</label></div>
													</c:if>
												</div>
											</div>
										</div>
										<div class="col-md-6" >
											<div class="form-group">
												<label>Mobile Number</label>
									 			<input type="text" name="mobile" placeholder="Mobile number " pattern="^\d{10}$" title="Mobile number should be in 10 digit mobile number format" minlength="10" maxlength="10" required="true" Class="form-control" readonly style="background-color:white" value="${sessionUser.mobile}" />
											</div>
										</div>
									</div>
									<div class="row"><div class="col-md-12"></div></div>
										<div class="row">
											<div class="col-md-12 " >
												<div class="form-group">
													<label>Address 1 </label>
													<input type="text" name="address1" placeholder="Address 1"required="true" Class="form-control" readonly style="background-color:white" value="${sessionUser.address1}"/>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12 " >
												<div class="form-group">
													<label>Address 2 </label>
													<input type="text" name="address2" placeholder="Address 1" Class="form-control" readonly style="background-color:white" value="${sessionUser.address2}"/>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 " >
												<div class="form-group">
													<label>City</label>
													<input type="text" name="city" placeholder="City" required="true" Class="form-control" readonly style="background-color:white" value="${sessionUser.city}"/>
												</div>
											</div>
											<div class="col-md-6" >
												<div class="form-group">
													<label>State</label>
													<input type="text" name="state" placeholder="State"  required="true" Class="form-control" readonly style="background-color:white" value="${sessionUser.state}"/>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 " >
												<div class="form-group">
													<label>Pincode</label>
													<input type="text" name="pincode" placeholder="Pincode"  pattern="^[0-9]+$" title="6 digit pincode" required="true" Class="form-control" readonly style="background-color:white" value="${sessionUser.pincode}"/>
												</div>
											</div>
											<div class="col-md-6" >
												<div class="form-group">
													<label>Country</label><label style="color:red"> *</label><input type="text" name="country" placeholder="Country" value="India" readonly required="true" Class="form-control" style="background-color:white"/>
												</div>
											</div>
										</div>
									</c:if>
								<c:if test="${editEnable}">
									<div class="col-md-6" >
										<div class="form-group">
											<label>Last Name</label>
											<input type="text" name="lastName" placeholder="Last Name" pattern="^[a-zA-Z ]+$" title="Last name should only contain Alphabets" required="true" Class="form-control" style="background-color:white" value="${sessionUser.lastName}"/>
										</div>
									</div>
								</div>	
								<div class="row">
									<div class="col-md-6 " >
										<div class="form-group">
											<div class="row"><div class="col-md-12 " ><label>Gender</label><label style="color:red"> *</label></div></div> 
											<div class="row">
												<c:if test="${sessionUser.gender == 'Male' }">
													<div class="col-md-3 col-md-offset-2"><label class="radio-inline"> <input checked type="radio" name="gender" value="Male"style="background-color:white" disabled>Male</label></div>
													<div class="col-md-3 "><label class="radio-inline"> <input type="radio" name="gender" value="Female"style="background-color:white" disabled>Female</label></div>
												</c:if>
												<c:if test="${sessionUser.gender == 'Female' }">
													<div class="col-md-3 col-md-offset-2"><label class="radio-inline"> <input  type="radio" name="gender" value="Male"style="background-color:white" disabled>Male</label></div>
													<div class="col-md-3 "><label class="radio-inline"> <input type="radio" checked name="gender" value="Female"style="background-color:white" disabled>Female</label></div>
												</c:if>
											</div>
										</div>
									</div>
									<div class="col-md-6" >
										<div class="form-group">
											<label>Mobile Number</label>
											<input type="text" name="mobile" placeholder="Mobile number " pattern="^\d{10}$" title="Mobile number should be in 10 digit mobile number format" minlength="10" maxlength="10" required="true" Class="form-control" style="background-color:white" value="${sessionUser.mobile}" />
										</div>
									</div>
								</div>
								<div class="row"><div class="col-md-12"></div></div>
									<div class="row">
										<div class="col-md-12 " >
											<div class="form-group">
												<label>Address 1 </label>
												<input type="text" name="address1" placeholder="Address 1"required="true" Class="form-control"  style="background-color:white" value="${sessionUser.address1}" maxlength="200"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 " >
											<div class="form-group">
												<label>Address 2 </label>
												<input type="text" name="address2" placeholder="Address 1" Class="form-control"  style="background-color:white" value="${sessionUser.address2}" maxlength="200"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 " >
											<div class="form-group">
												<label>City</label>
												<input type="text" name="city" placeholder="City" required="true" Class="form-control"  style="background-color:white" value="${sessionUser.city}"maxlength="50"/>
											</div>
										</div>
										<div class="col-md-6" >
											<div class="form-group">
												<label>State</label>
												<input type="text" name="state" placeholder="State"  required="true" Class="form-control"  style="background-color:white" value="${sessionUser.state}"maxlength="50"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 " >
											<div class="form-group">
												<label>Pincode</label>
												<input type="text" name="pincode" placeholder="Pincode"  pattern="^[0-9]+$" title="6 digit pincode" required="true" Class="form-control"  minlength="6" maxlength="6"style="background-color:white" value="${sessionUser.pincode}"/>
											</div>
										</div>
										<div class="col-md-6" >
											<div class="form-group">
												<label>Country </label><label style="color:red">*</label><input type="text" name="country" placeholder="Country" value="India" readonly required="true" Class="form-control" style="background-color:white"/>
											</div>
										</div>
									</div>
								</c:if>
								<c:choose>
									<c:when test="${editEnable}">
										<div class="col-md-6">
											<input class="btn btn-md btn-success btn-block" type="submit" value="Update">
										</div>
										<div class="col-md-6">
											<a href="user_Profile" class="btn btn-danger btn-block" role="button">Cancel</a>
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-md-6">
											<a href="user_editProfile" class="btn btn-danger btn-block" role="button">Edit</a>
										</div>
										<div class="col-md-6">
											<a href="Index" class="btn btn-success btn-block" role="button">Close</a>
										</div>
									</c:otherwise>
								</c:choose>	
							</fieldset>
						</form:form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>