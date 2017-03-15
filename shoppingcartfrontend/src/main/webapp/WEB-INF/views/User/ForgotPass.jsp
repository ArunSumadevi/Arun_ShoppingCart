<div class="container">
	<div id="ForgotPass-box" class="row vertical-offset-75">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-default" style="background: rgba(255, 255,255, 0.5 ) ;border-width: 2px; border-color:Gray">
				<div class="panel-heading" style="background-color: #009EEA">
					<h3 class="panel-title" style="color:white" >Forgot Password</h3>
				</div>
				<div class="panel-body">
				<c:if test="${not empty errorMessage}">
					<div class="form-group">
						<div class="alert alert-danger">${errorMessage}</div>
					</div>
				</c:if>
				<form accept-charset="UTF-8" role="form" action="resetPassword" method="post">
					<fieldset>
						<div class="row">
							<div class="col-md-4" >
								<label>Email</label>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" >
								<div class="form-group">
									<input  class="form-control" placeholder="Email" name="forgot_pass_username" type="text" value="">
								</div>
							</div>
							</div>
							<div class="row">
								<div class="col-md-4">
								</div>
								<div class="col-md-4">
							   		<input class="btn btn-md btn-primary btn-block" type="submit" value="Reset password">
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>