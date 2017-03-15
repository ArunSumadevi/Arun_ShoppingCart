<div class="container">
	<div class="col-xs-12">
   		<div class="carousel slide" id="myCarousel" data-ride="carousel">
       		<div class="carousel-inner">
       			<c:if test="${carouselList.size()>0}">
					<div class="item active">
                    	<ul class="thumbnails">
                    		<c:forEach var="carouselValue" items="${carouselList}" begin="0" end="5">
                    			<li class="col-sm-2">
    								<div class="fff">
										<div class="thumbnail" >
											<a href="productDisplay?id=${carouselValue.id}"><img src="<c:url value="/Resources/Images/Product/${carouselValue.id}.jpg" />" alt="" style="width:150px; height:250px" ></a>
										</div>
									</div>
                            		<div class="caption">
										<h6 class="text-center"style=" color:Blue" >${carouselValue.name}</h6>
									</div>
                        		</li>
                        	</c:forEach> 
                   		</ul>
              		</div><!-- /Slide1 --> 
             	</c:if>
            	<c:if test="${carouselList.size()>6}">
            		<div class="item">
                    	<ul class="thumbnails" >
                        	<c:forEach var="carouselValue" items="${carouselList}" begin="6" end="11">
                        		<li class="col-sm-2">
    								<div class="fff">
										<div class="thumbnail" >
											<a href="productDisplay?id=${carouselValue.id}"><img src="<c:url value="/Resources/Images/Product/${carouselValue.id}.jpg"/>" style="width:150px; height:250px"></a>
										</div>
									</div>
                            		<div class="caption">
										<h6 class="text-center" style=" color:Blue">${carouselValue.name}</h6>
									</div>
                        		</li>
                        	</c:forEach>	
                      	</ul>
              		</div><!-- /Slide2 --> 
              	</c:if>
             	<c:if test="${carouselList.size()>12}">
             		<div class="item">
                    	<ul class="thumbnails" >
                        	<c:forEach var="carouselValue" items="${carouselList}" begin="12" end="17">
                        		<li class="col-sm-2">
    								<div class="fff">
										<div class="thumbnail">
											<a href="productDisplay?id=${carouselValue.id}"><img src="<c:url value="/Resources/Images/Product/${carouselValue.id}.jpg"/>" alt="" style="width:150px; height:250px"></a>
										</div>
									</div>
                            		<div class="caption">
										<h6 class="text-center" style=" color:Blue">${carouselValue.name}</h6>
									</div>
                        		</li>
                        	</c:forEach>	
                        </ul>
              		</div><!-- /Slide3 --> 
              	</c:if> 
        	</div>
        	<c:if test="${carouselList.size()>6}">
     			<ul class="control-box pager">
					<li><a data-slide="prev" href="#myCarousel" class="" style="background-color:rgba(0,0,0,0.1)"><i class="fa fa-angle-left"></i></a></li>
					<li><a data-slide="next" href="#myCarousel" class=""style="background-color:rgba(0,0,0,0.1)"><i class= "fa fa-angle-right"></i></a></li>
				</ul>
			</c:if>
	   	</div>
	</div> 
</div>