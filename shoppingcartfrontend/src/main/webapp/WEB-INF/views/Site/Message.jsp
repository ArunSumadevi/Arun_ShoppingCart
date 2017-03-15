<div class="container">
	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
    	<div class="modal-dialog">
     		<!-- Modal content-->
      		<div class="modal-content">
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
          			<h4 class="modal-title">e-Baazar</h4>
        		</div>
        		<div class="modal-body">
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
        		</div>
        		<div class="modal-footer">
        			<a href="Index" class="btn btn-primary" id="btn_delete_group">Close</a>
        		</div>
      		</div>
    	</div>
  	</div>
</div>