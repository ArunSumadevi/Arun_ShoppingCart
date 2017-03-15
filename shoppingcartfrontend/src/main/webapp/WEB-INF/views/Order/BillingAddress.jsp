<!DOCTYPE html>
	<html>
		<head>
			<%@include file="../Site/Header.jsp"%>
		</head>
 		<body > 
			<div id="wrapper">
				<br>
				<%@include file="../Site/Menu.jsp" %>
				<div class="container">
					<div id="signUp-box" class="row vertical-offset-75">
						<div class="col-md-6 col-md-offset-3">
							<div class="panel panel-default" style="background: rgba(255, 255,255, 0.5 ) ;border-width: 2px; border-color:gray">
								<div class="panel-heading" style="background-color: #009EEA">
									<h3 class="panel-title" style="color:white">Billing Address</h3>
								</div>
								<div class="panel-body">
									<form:form  action="${flowExecutionUrl}&_eventId=submitBillingAddress" method="post" modelAttribute="billingAddress">
										<fieldset>
											<c:if test="${not empty errorMessage}">
												<div class="form-group">
													<div class="alert alert-danger">${errorMessage}</div>
												</div>
											</c:if>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<div class="row"><div class="col-md-12"></div></div>
														<div class="row">
															<div class="col-md-12 " >
																<div class="form-group">
																	<label>Address 1 </label>
																 	<input type="text" name="address1" placeholder="Address 1"required="true" Class="form-control" />
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12 " >
																<div class="form-group">
																	<label>Address 2 </label>
																	 <input type="text" name="address2" placeholder="Address 1" Class="form-control" />
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-6 " >
																<div class="form-group">
																	<label>City</label>
																	<input type="text" name="city" placeholder="City" required="true" Class="form-control" />
																</div>
															</div>
															<div class="col-md-6" >
																<div class="form-group">
																	<label>State</label>
																	<input type="text" name="state" placeholder="State"  required="true" Class="form-control" />
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-6 " >
																<div class="form-group">
																	<label>Pincode</label>
																	<input type="text" name="pincode" placeholder="Pincode"  pattern="^[0-9]+$" title="6 digit pincode" required="true" Class="form-control" />
																</div>
															</div>
															<div class="col-md-6" >
																<div class="form-group">
																	<label>Country</label><input type="text" name="country" placeholder="Country" value="India" readonly required="true" Class="form-control" style="background-color:white"/>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-2">
																<a href="${flowExecutionUrl}&_eventId=backToShippingAddress" class="btn btn-primary btn-block" role="button">Back</a>
															</div>
															<div class="col-md-2 col-md-offset-6">
																<a href="${flowExecutionUrl}&_eventId=cancel" class="btn btn-danger btn-block" role="button">Cancel</a>
															</div>
															<div class="col-md-2">
																<input class="btn btn-md btn-success btn-block" type="submit" value="Next">
															</div>
														</div>
													</div>
												</div>
											</div>
										</fieldset>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="content"></div>
				<div id="footer">
					<div class="text-center" style="Color: white">&copy Arun Technologies pvt ltd.</div>
					<div class="text-center" style="Color: white"><span class="fa fa-envelope-o"> </span> <a style="Color: white" href= "mailto:ebaazar.contactus@gmail.com">ebaazar.contactus@gmail.com</a> </div>
				</div>
			</div>
			<%@include file="../Site/Script.jsp"%>
		</body>
	</html>