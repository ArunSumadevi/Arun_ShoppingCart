<div class-="container">
	<div ng-app="myApp" ng-controller="adminUser" >
 		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-primary">
                <div class="panel-heading"><h4>Manage User</h4></div>
                <div class="panel-body">
                    <c:if test="${not empty message}">
           				<div class="row alert alert-info text-center"> ${message} </div>
                    </c:if> 
                    <div class="alert alert-danger text-center" ng-show="listempty">No user data available to show</div> 
					<div id="dynamic" ng-hide="listempty">
                    	<label class="col-md-1 col-md-offset-4" for="search" >Search:</label>
                        <input class="col-md-3 " type="text" ng-model="searchCondition" id="search" style="margin-bottom: 50px;">
              			<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="exampleone">
                            <thead>
                               <tr>
                                  <th>No.</th>
                                  <th>User email</th>
                                  <th>User Name</th>
                                  <th>User Role</th>
                                  <th></th>
                                  <th></th>
                                </tr>                                    
                            </thead>
                            <tbody>
                                <tr ng-repeat="user in userList | filter:searchCondition">
	                                <td>{{$index + 1}}</td>
	                                <td>{{user.email}}</td>
	                                <td>{{user.firstName}} {{user.lastName}}</td>
	                                <td>{{user.role}}</td>
									<td align="center"><a href ="#userInfo" ng-click="display(user)" data-toggle="modal" title="Info" data-target="#userInfo" ><span class="fa fa-pencil" style="color:Green" data-toggle="showtooltip" title="Modify"></span></a></td> 
									<td align="center" colspan="2"><a href ="#deleteDialog" ng-click="deleteuser(user)" data-toggle="modal" title="Info" data-target="#userDeleteModal" ><span class=" fa fa-trash " style="color:Red" data-toggle="showtooltip" title="Delete"></span></a></td>
								</tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>       
        </div> 
        <!-- Modal to display delete message -->
		<div id="userDeleteModal" class="modal fade" role="dialog">
  			<div class="modal-dialog">
				<div class="modal-content">
    				<form action="Admin_deleteUser" method="post">
      					<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
        					<h4 class="modal-title" >{{deleteHeader}}</h4>
      					</div>
      					<div class="modal-body">
      						<input type="text" name="msgId" value="{{message}}" style="border:none; text-align: center; width:100%">
      						<input type="hidden" value="{{user}}" name="id" >  
      					</div>
      					<div class="modal-footer">
     						<div class="col-md-6">
								<input class="btn btn-md btn-danger btn-block" type="submit" value="Delete">
							</div>
							<div class="col-md-6">
								<a href="Index" class="btn btn-success btn-block" role="button"data-dismiss="modal" >Cancel</a>
							</div>
						</div>
      				</form>
    			</div>
			</div>
		</div>
		<!-- Modal to display information message -->
		<div id="userInfo" class="modal fade" role="dialog">
  			<div class="modal-dialog">
		    	<div class="modal-content">
    				<form action="Admin_modifyUser" method="post">
      					<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
        					<h4 class="modal-title" >{{displayHeader}}</h4>
      					</div>
      					<div class="modal-body">
      						<div class="col-md-6 col-md-offset-3">
								<label><b>select user role   </b></label>
								<select ng-model="userRole" name="userRole">
									<option ng-repeat="x in roles" value="{{x}}">{{x}}</option>
								</select>
							</div>
							<input type="hidden" value={{userId}} name="userId">
						</div>  
						<br> 
      					<div class="modal-footer">
	     					<div class="col-md-6">
								<input class="btn btn-md btn-warning btn-block" type="submit" value="Update">
							</div>
							<div class="col-md-6">
								<a href="Index" class="btn btn-success btn-block" role="button"data-dismiss="modal" >Cancel</a>
							</div>
						</div>
  					</form>      
				</div>
			</div>
		</div>
	</div>
</div>