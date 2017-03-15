<nav class="navbar navbar-default" style="background: grey; border-color:transparent; background:rgba(0,0,0,0.5)")>
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar" style="color: #fff"></span>
					<span class="icon-bar" style="color: #fff"></span>
					<span class="icon-bar" style="color: #fff"></span>
				</button>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="navbar-header">
      		<a class="navbar-brand" href="Index" style="color: #fff" >e-Baazar.com</a>
    	</div>
		<div class="collapse navbar-collapse" id="myNavbar" >
			<!--  drop down Menu for products -->
			<ul class="nav navbar-nav">		
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"  style="color: #fff" >Products <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<c:forEach var="listValue" items="${sessionCategoryList}">
							<li ><a href="productShow?id=${listValue.id}">${listValue.name}</a></li>
						</c:forEach>	
						<datalist id="products">
							<c:forEach var="listValue" items="${sessionCategoryList}">
								<c:forEach var="product" items="${listValue.products}">
									<option value="${product.name}">
								</c:forEach>
							</c:forEach>
						</datalist>						
					</ul>
				</li>
				<li ><a href="AboutUs"  style="color: #fff" >About Us</a></li>
			</ul>
			<!--  Menu for search -->
			<div class="col-sm-3 col-md-3">
				<form class="navbar-form" role="search" action ="searchProduct" >
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search" name="search"  list="products">
						<div class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
			<!--  Menu for user if logged in -->
			<c:choose>
				<c:when test="${not empty sessionUser.firstName}">
					<ul class="nav navbar-nav navbar-right">
						<!-- Menu for Admin if the user is Admin -->
						<c:if test="${sessionUser.role=='ROLE_ADMIN' && sessionUser.marked==false}">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle"	data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"  style="color: #fff" ><span class="fa fa-user-plus"></span> Admin <span class="caret"></span></a>
								<ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
									<li class="dropdown-submenu" pull-left><a tabindex="-1" href="#">Categories</a>
										<ul class="dropdown-menu" pull-right>
											<li><a tabindex="-1" href="Admin_newCategory">Add new category.</a></li>
											<li><a tabindex="-1" href="Admin_displayCategory">View all categories.</a></li>
										</ul>
									</li>
									<li class="dropdown-submenu" pull-left><a tabindex="-1" href="#">Suppliers</a>
										<ul class="dropdown-menu" pull-right >
											<li><a tabindex="-1" href="Admin_newSupplier">Add new supplier.</a></li>
											<li><a tabindex="-1" href="Admin_displaySupplier">View all suppliers.</a></li>
										</ul>
									</li>
									<li class="dropdown-submenu" ><a tabindex="-1" href="#">Products</a>
										<ul class="dropdown-menu" pull-right >
											<li><a tabindex="-1" href="Admin_newProduct">Add new product.</a></li>
											<li><a tabindex="-1" href="Admin_displayProduct">View all products.</a></li>
										</ul>
									</li>
									<li class="divider"></li>
									<li class="dropdown-submenu" ><a tabindex="-1" href="Admin_adminManage">Manage Admin access</a>
										
									</li>
								</ul>
							</li>
						</c:if>
						<c:choose>
							<c:when test="${sessionUser.marked==true}">
								<li class="disabled"><a href=#  style="color: #fff" ><span class="fa fa-shopping-cart"></span> Shopping Cart<span class="badge">${sessionCartCount}</span></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="user_myCartShow?id=${sessionUser.id}"  style="color: #fff" ><span class="fa fa-shopping-cart"></span> Shopping Cart<span class="badge">${sessionCartCount}</span></a></li>
							</c:otherwise>
						</c:choose>
						<li class="dropdown"><a style="color: #fff" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="fa fa-user"></span> ${sessionUser.firstName} <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="user_Profile">My profile</a></li>
								<li><a href="user_ChangePassword">Change Password</a></li>
								<c:choose>
									<c:when test="${sessionUser.marked==true}">
										<li class="disabled"><a href=#>My orders</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="user_TrackOrder">My orders</a></li>
									</c:otherwise>
								</c:choose>
								<li role="separator" class="divider"></li>
								<li><a href="SignOut"><span class="fa fa-sign-out"></span> Sign Out</a></li>
							</ul>
						</li>
					</ul>
				</c:when>
				<c:otherwise>
					<!--  Menu for user if not logged in -->
					<ul class="nav navbar-nav navbar-right">
						<li ><a href="NewUser"  style="color: #fff" ><span class="fa fa-user"></span>
								Sign Up</a>
						</li>
						<li><a href="Login"  style="color: #fff" ><span class="fa fa-sign-in"></span>
								Login</a>
						</li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
</nav>
	
						
