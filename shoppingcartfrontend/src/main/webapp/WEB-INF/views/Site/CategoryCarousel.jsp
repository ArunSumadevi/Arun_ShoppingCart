<div class="container">
	<br>
	<c:if test="${sessionCategoryList.size()> 0}">
 	 	<div class="row">
	  		<div class="col-md-12 ">
  				<div id="catCarousel" class="carousel slide" data-ride="carousel">
   					<ol class="carousel-indicators">
   						<c:forEach begin="0" end="${sessionCategoryList.size()-1}" varStatus="loop">
    						<c:choose>
    							<c:when test="${loop=='0'}">
      								<li data-target="#catCarousel" class="active" data-slide-to="loop" ></li>
      							</c:when>
	      						<c:otherwise>
    	 							<li data-target="#catCarousel" data-slide-to="loop" ></li>
      							</c:otherwise>
      						</c:choose>
     					</c:forEach>
    				</ol>
					<!-- Wrapper for slides -->
   					<div class="carousel-inner" role="listbox">
						<c:set var="count" value="0" />
						<c:forEach var="listValue" items="${sessionCategoryList}" >
							<c:choose>
								<c:when test="${count=='0'}">
     								<div class="item active">
        								<a href="productShow?id=${listValue.id}"><img src="<c:url value="/Resources/Images/Category/${listValue.id}.jpg" />" alt="${listValue.name}" style="width:100%; height:345px"></a>
        								<div class="carousel-caption">
          									<h3 style="color:rgb(255,0,0); background:rgba(255,255,255,0.1)">${listValue.name}</h3>
          								</div>
      								</div>
       								<c:set var="count" value="1" />
								</c:when>
								<c:otherwise>
      								<div class="item">
        								<a href="productShow?id=${listValue.id}"><img src="<c:url value="/Resources/Images/Category/${listValue.id}.jpg" />" alt="${listValue.name}" style="width:100%; height:345px" ></a>
        									<div class="carousel-caption">
          										<h3 style="color:rgb(255,0,0); background:rgba(255,255,255,0.1)">${listValue.name}</h3>
        									</div>
      								</div>
  								</c:otherwise>
  							</c:choose>
 						</c:forEach>  
					</div>
					<!-- Left and right controls -->
				</div>
  			</div>
  		</div>
  	</c:if>
</div>