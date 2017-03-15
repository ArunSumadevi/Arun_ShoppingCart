<!DOCTYPE html>
	<html>
		<head>
			<%@include file="../Site/Header.jsp"%>
		</head>
		<body>
			<br><br>
			<div class="container">
				<div class="alert alert-danger">
					<strong><i>Ooops!! something is wrong here, Please contact Administrator</i></strong>
					<div class="alert alert-info">${message}${exception}</div>
				</div>
			</div>
		</body>
	</html>