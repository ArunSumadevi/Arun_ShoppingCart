<div class="container">
 	<c:set var="count"  value="0"/>
	<h3 class="text-center"> Showing products containing  ${searchName}</h3>
 	<HR>
 	<c:choose>
  	  	<c:when test="${productList.size()==0}">
    		<div class="alert alert-danger text-center">No products available with this search criteria.</div>
 		</c:when>
 		<c:otherwise>
 			<div class="col-md-10 col-md-offset-1">
 				<div class="row">
 					<c:forEach var="listValue" items="${productList}">
 					<div class="col-md-2">
							<div class="row">
							
									<div class="col-md-12">
									<a href="productDisplay?id=${listValue.id}">									
									<img src="<c:url value="/Resources/Images/Product/${listValue.id}.jpg"/>" class="img-rounded" alt="<c:url value="${listValue.name}"/>" width="104" height="136" ></a>
								</div>
							</div>
							 <div class="row">
								<div class="col-md-12">
									<h6 style="color:Blue" class="text-center">${listValue.name}</h6>
								</div>
							</div> 
						</div>
						<c:set var="count"  value="${count+1}"/>
						<c:if test="${count==6}">
								</div>
								<div class="row">
								<c:set var="count"  value="0"/>
						</c:if>
					</c:forEach>
 				</div>
 			</div> 
		 </c:otherwise>
	</c:choose>
</div>