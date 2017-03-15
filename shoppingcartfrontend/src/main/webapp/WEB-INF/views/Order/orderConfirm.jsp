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
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
							<div class="form-group">
								<div class="alert alert-success">Your order placed successfully..</div>
 								<label style="color:red">your order number is ${orderTable.number}</label>
							</div>
							<div class="col-md-2 col-md-offset-9">
								<a href="${flowExecutionUrl}&_eventId=final" class="btn btn-success btn-block" role="button">OK</a>
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
			<%@include file="../Site/Script.jsp"%>
		</body>
	</html>