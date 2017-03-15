<!DOCTYPE html>
	<html>
		<head>
			<%@include file="../Site/Header.jsp"%>
		</head>
		<body> 
			<div id="wrapper">
				<br>
				<%@include file="../Site/Menu.jsp" %>
				<div class="container">
					<div id="signUp-box" class="row vertical-offset-75">
						<div class="col-md-8 col-md-offset-2">
							<div class="panel panel-default" style="background: rgba(255, 255,255, 0.5 ) ;border-width: 2px; border-color:gray">
								<div class="panel-heading" style="background-color: #009EEA">
									<h3 class="panel-title" style="color:white">Payment options</h3>
								</div>
								<div class="panel-body">
									<form:form  action="${flowExecutionUrl}&_eventId=submitPaymentMethod" method="post" modelAttribute="patmentMethod" id="paymentForm"  onsubmit="return validateForm()">
										<fieldset>
											<div class="row">
												<div class="col-md-12">
													<label> Select payment option</label>
													<div class="form-group" ng-app="">
														<div class="row"><div class="col-md-10 col-md-offset-2"><label class="radio-inline"> <input type="radio" name="paymentMode" value="Cash On Delivery" ng-model="content" ng-checked='true' id="pMode">Cash On Delivery</label></div></div>
														<div class="row"><div class="col-md-10 col-md-offset-2 "><label class="radio-inline"> <input type="radio" name="paymentMode" value="Credit Card" ng-model="content" id="pMode" onclick="myFunction()">Credit Card</label></div></div>
														<br>
														<div ng-show="content == 'Credit Card'"class="col-md-8 col-md-offset-2">
															<div ng-if="content == 'Credit Card'">
										 						<div class='form-row'>
														          	<div class='col-xs-12 form-group required'>
														                <label class='control-label'>Name on Card</label>
														                <input class='form-control'autocomplete='off' size='4' type='text' pattern="^[a-zA-Z ]{4,}$" title="Name should be minimum 4 character and contain alphabets only" required="true" onclick="myFunction()">
														            </div>
														        </div>
           														<div class='form-row'>
													             	<div class='col-xs-12 form-group card required'>
														                <label class='control-label'>Card Number</label>
														                <input  class='form-control card-number' autocomplete='off'size='20' type='text'pattern="^[0-9]{16,16}$" title="Card Number should be 16 digit and contain numbers only" required="true" name="cardNumber">
													              	</div>
													            </div>
													            <div class='form-row'>
													             	<div class='col-xs-4 form-group cvc required'>
														                <label class='control-label'>CVV</label>
														                <input  class='form-control card-cvc' autocomplete='off' size='4' type='text'pattern="^[0-9]{3,3}$" title="CVV should be 3 digit and contain numbers only" required="true">
													              	</div>
													              	<div class='col-xs-4 form-group expiration required'>
													                	<label class='control-label'>Expiration</label>
													                	<select id="selectmonth"class='form-control card-expiry-month'></select>
													              	</div>
														            <div class='col-xs-4 form-group expiration required'>
														                <label class='control-label'> </label>
														                <select id="selectyear"class='form-control card-expiry-year'></select>
														            </div>
              														<label style="color:red" id="error"></label>
            													</div>
            												</div>
														</div>
													</div>
													<br>
												</div>
											</div>
											<div class="row">
												<hr>
												<div class="col-md-4">
													<a href="${flowExecutionUrl}&_eventId=backToBillingAddress" class="btn btn-primary btn-block" role="button">Back</a>
												</div>
												<div class="col-md-4">
													<a href="${flowExecutionUrl}&_eventId=cancel" class="btn btn-danger btn-block" role="button">Cancel</a>
												</div>
												<div class="col-md-4">
													<input class="btn btn-md btn-success btn-block" type="submit" value="Next">
												</div>
											</div>
										</fieldset>
									</form:form>
								</div>
							</div>
						</div>
					</div>
					<div id="content"></div>
					<div id="footer">
						<div class="text-center" style="Color: white">&copy Arun Technologies pvt ltd.</div>
						<div class="text-center" style="Color: white"><span class="fa fa-envelope-o"> </span> <a style="Color: white"href= "mailto:ebaazar.contactus@gmail.com">ebaazar.contactus@gmail.com</a> </div>
					</div>
				</div>
			</div>
			<script>
			function myFunction() 
				{
				
					var select=document.getElementById('selectmonth');
					select.options.length = 0;
					select.options[select.options.length] = new Option("Jan",1);
					select.options[select.options.length] = new Option("Feb",2);
					select.options[select.options.length] = new Option("Mar",3);
					select.options[select.options.length] = new Option("Apr",4);
					select.options[select.options.length] = new Option("May",5);
					select.options[select.options.length] = new Option("Jun",6);
					select.options[select.options.length] = new Option("Jul",7);
					select.options[select.options.length] = new Option("Aug",8);
					select.options[select.options.length] = new Option("Sep",9);
					select.options[select.options.length] = new Option("Oct",10);
					select.options[select.options.length] = new Option("Nov",11);
					select.options[select.options.length] = new Option("Dec",12);
					var select=document.getElementById('selectyear');
					select.options.length = 0;
					var year=new Date().getFullYear();
					for(i=year;i<=year+25;i++)
					select.options[select.options.length] = new Option(i, i);
				
				};
			
			function validateForm()
				{
				
					var month=document.getElementById('selectmonth').value;
					var mode=document.getElementById('pMode').value;
					if(mode=='Cash On Delivery')
						return true;
					var curMonth=new Date().getMonth() + 1;
					var curYear=new Date().getFullYear();
					var year=document.getElementById('selectyear').value;
					if((year==curYear) && (month<curMonth))
						{
						document.getElementById('error').innerHTML="Expiry cannot be less than this month";
						return false;
						}
					else
						{
						document.getElementById('error').innerHTML="";
						return truee;
						}
					
				};
			</script>
			<%@include file="../Site/Script.jsp"%>
		</body>
	</html>