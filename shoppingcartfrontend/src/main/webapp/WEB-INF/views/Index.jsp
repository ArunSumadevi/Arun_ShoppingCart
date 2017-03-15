<!DOCTYPE html>
<html>
<head>
<%@include file="./Site/Header.jsp"%>
</head>
<!-- <body>  -->
 <body  > 
<div id="wrapper">
		<br>
		<%@include file="./Site/Menu.jsp" %>
		
		<c:if test="${Param=='Home'}">
			<%@include file="./Site/Home.jsp"%>
		</c:if>
		
		<c:if test="${Param=='myCartShow'}">
			<%@include file="./User/MyShoppingCart.jsp"%>
		</c:if>
		
		<c:if test="${Param=='userPassword'}">
			<%@include file="./User/ChangePassword.jsp"%>
		</c:if>
		
		<c:if test="${Param=='productDisplay'}">
			<%@include file="./Site/ProductDisplay.jsp"%>
		</c:if>
		
		<c:if test="${Param=='userProfile'}">
			<%@include file="./User/UserProfile.jsp"%>
		</c:if>
		
		<c:if test="${Param=='Login'}">
			<%@include file="./User/Login.jsp"%>
		</c:if>
		
		<c:if test="${Param=='Register'}">
			<%@include file="./User/Register.jsp"%>
		</c:if>
		
		<c:if test="${Param=='ForgotPass'}">
			<%@include file="./User/ForgotPass.jsp"%>
		</c:if>
		
		<c:if test="${Param=='AboutUs'}">
			<%@include file="./Site/AboutUs.jsp"%> 
			
		</c:if>
		
		<c:if test="${Param=='SearchResult'}">
			<%@include file="./Site/searchResult.jsp"%> 
			
		</c:if>
		<c:if test="${Param=='LoginFailed'}">
			<%@include file="./User/Login.jsp"%>
		</c:if>
		
		
		<c:if test="${Param=='ActionResponce'}">
			<%@include file="./Site/Message.jsp"%>
		</c:if>
		
		<c:if test="${Param=='adminCategory'}">
			<%@include file="./Admin/AdminCategory.jsp"%>
		</c:if>
		
		<c:if test="${Param=='adminSupplier'}">
			<%@include file="./Admin/AdminSupplier.jsp"%>
		</c:if>
		
		<c:if test="${Param=='adminProduct'}">
			<%@include file="./Admin/AdminProduct.jsp"%>
		</c:if>
		
		<c:if test="${Param=='viewAll'}">
			<%@include file="./Admin/ShowAll.jsp"%>
		</c:if>
		
		<c:if test="${Param=='productShow'}">
			<%@include file="./Site/allProducts.jsp"%>
		</c:if>
		
		<c:if test="${Param=='AccessDenied'}">
			<%@include file="./AccessControl/AccessDenied.jsp"%>
		</c:if>
		
		<c:if test="${Param=='OrderDisplay'}">
			<%@include file="./User/displayOrder.jsp"%>
		</c:if>
		
		<c:if test="${Param=='orderDetails'}">
			<%@include file="./User/OrderDetails.jsp"%>
		</c:if>
		
		<c:if test="${Param=='adminUser'}">
			<%@include file="./Admin/AdminUser.jsp"%>
		</c:if>
	
		<div id="content"></div>
			<div id="footer">
				<div class="text-center" style="Color: white">&copy Arun Technologies pvt ltd.</div>
				<div class="text-center" style="Color: white"><span class="fa fa-envelope-o"> </span> <a style="Color: white" href= "mailto:ebaazar.contactus@gmail.com">ebaazar.contactus@gmail.com</a> </div>
			</div>
</div>
<%@include file="./Site/Script.jsp"%>
</body>

</html>