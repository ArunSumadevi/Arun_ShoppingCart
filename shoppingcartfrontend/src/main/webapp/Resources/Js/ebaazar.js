
var app = angular.module('myApp', []);
app.controller('adminUser', function($scope,$http) // for manage admin page...
		{
	
        var responEC = $http.get('Admin_getAllUser', {cache: true, transformResponse: function(data, headersGetter)
        	{
             	var jsonObject = JSON.parse(data);
             	keepGoing = true;
             	$scope.userList=jsonObject;
             	if(jsonObject.length==0)
             		$scope.listempty=true;
             	else
             		$scope.listempty=false;
             	return {};
        	}});
        
       $scope.display=function(user)
       {
    	   $scope.displayHeader="Modify Role for "+user.firstName+" "+user.lastName;
    	   $scope.userId=user.id;
    	   $scope.userRole=user.role;
    	   $scope.roles = ["ROLE_USER", "ROLE_ADMIN"];
       };
       
       $scope.deleteuser=function(user)
       {
    	   $scope.deleteHeader="Delete User " +user.firstName+" "+user.lastName;
    	   $scope.message="Delete  user with email Id "+ user.email +" ?";
    	   $scope.user=user.id;
       };
       
		});
  

$(document).on("click", ".open-productInfo",function() { // for product info modal in show all page
	var Parameter = $(this).data('id');
	var index = Parameter.indexOf("<->");
	var ProductId = Parameter.substring(0, index);
	var Parameter = Parameter.substring(index + 3);
	var index = Parameter.indexOf("<->");
	var ProductName = Parameter.substring(0, index);
	var Parameter = Parameter.substring(index + 3);
	var index = Parameter.indexOf("<->");
	var ProductDesc = Parameter.substring(0, index);
	var Parameter = Parameter.substring(index + 3);
	var index = Parameter.indexOf("<->");
	var ProductCatagory = Parameter.substring(0, index);
	var Parameter = Parameter.substring(index + 3);
	var index = Parameter.indexOf("<->");
	var ProductSupplier = Parameter.substring(0, index);
	var Parameter = Parameter.substring(index + 3);
	var index = Parameter.indexOf("<->");
	var Productprice = Parameter.substring(0, index);
	var Parameter = Parameter.substring(index + 3);
	var index = Parameter.indexOf("<->");
	var ProductStock = Parameter.substring(0, index);
	var Parameter = Parameter.substring(index + 3);
	var index = Parameter.indexOf("<->");
	var ProductAddedOn = Parameter.substring(0, index);
	var ProductImage = Parameter.substring(index + 3);	
	var element = document.getElementById("displayHeader");
	element.innerHTML = ProductName;
	var element = document.getElementById("productImage");
	element.src=ProductImage+ProductId+".jpg";
	var element = document.getElementById("prdId");
	element.innerHTML = ProductId;
	var element = document.getElementById("prdDes");
	element.innerHTML = ProductDesc;
	var element = document.getElementById("prdCat");
	element.innerHTML = ProductCatagory;
	var element = document.getElementById("prdSup");
	element.innerHTML = ProductSupplier;
	var element = document.getElementById("prdPrice");
	element.innerHTML = Productprice ;
	var element = document.getElementById("prdQty");
	element.innerHTML = ProductStock;
	 var element = document.getElementById("prdAdded");
	element.innerHTML = ProductAddedOn; 
});


$(document).on("click", ".open-supplierInfo",function() {// for supplier info modal in show all page
var Parameter = $(this).data('id');
var index = Parameter.indexOf("<->");
var SupplierId = Parameter.substring(0, index);
var Parameter = Parameter.substring(index + 3);
var index = Parameter.indexOf("<->");
var SupplierName = Parameter.substring(0, index);
var Parameter = Parameter.substring(index + 3);
var index = Parameter.indexOf("<->");
var SupplierContact = Parameter.substring(0, index);
var Parameter = Parameter.substring(index + 3);
var index = Parameter.indexOf("<->");
var SupplierEmail = Parameter.substring(0, index);
var Parameter = Parameter.substring(index + 3);
var index = Parameter.indexOf("<->");
var SupplierDesc = Parameter.substring(0, index);
var Parameter = Parameter.substring(index + 3);
var index = Parameter.indexOf("<->");
var SupplierAddress = Parameter.substring(0, index);
var SupplierSince = Parameter.substring(index + 3);
var element = document.getElementById("suppHeader");
element.innerHTML = SupplierName;
var element = document.getElementById("supId");
element.innerHTML = SupplierId;
var element = document.getElementById("supContact");
element.innerHTML = SupplierContact;
var element = document.getElementById("supEmail");
element.innerHTML = SupplierEmail;
var element = document.getElementById("supDesc");
element.innerHTML = SupplierDesc;
var element = document.getElementById("supAddr");
element.innerHTML = SupplierAddress ;
var element = document.getElementById("supSince");
element.innerHTML = SupplierSince;

});

$(document).on("click", ".open-AdminDeleteDialog", function () // For delete modal in Admin show all page
		{
    var myBookId = $(this).data('id');
    var index = myBookId.indexOf("<->"); 
    var cat = myBookId.substring(0, index);
    var myBookId=myBookId.substring(index+3);
    var index = myBookId.indexOf("<->"); 
    var name = myBookId.substring(0, index); 
    var ItemId=myBookId.substring(index+3);
    var element = document.getElementById("deleteHeader");
    element.innerHTML = "Delete "+cat;
    $(".modal-body #bookId").val("Do you want to delete "+cat+" : "+name );
        if(cat =="category")
    document.getElementById("but1").href = "Admin_deleteCategory?id="+ItemId;
        else if(cat =="supplier")
            document.getElementById("but1").href = "Admin_deleteSupplier?id="+ItemId;
        else if(cat =="product")
            document.getElementById("but1").href = "Admin_deleteProduct?id="+ItemId;
}); 




<!-- script to display delete modal -->

$(document).on("click", ".open-myCartDeleteDialog", function () // For delete modal in my cart page
	{
		var MsgId = $(this).data('id');
		var index = MsgId.indexOf("<->"); 
		var cartId = MsgId.substring(0, index);
		var MsgId=MsgId.substring(index+3); 
		var index = MsgId.indexOf("<->"); 
		var name= MsgId.substring(0, index);
		var MsgId=MsgId.substring(index+3);
		document.getElementById("user").value=MsgId;
		document.getElementById("cart").value=cartId
		document.getElementById("deleteHeader").innerHTML = "Delete from cart";
		document.getElementById("msgId").value="Do you want to delete  from cart?";
	}); 

<!-- script to display information modal -->

$(document).on("click", ".open-myCartInfo",function() // For info modal in my cart page
	{
		var Parameter = $(this).data('id');
		var index = Parameter.indexOf("<->");
		var temp = Parameter.substring(0, index);
		document.getElementById("displayHeader").innerHTML =temp;
		var Parameter = Parameter.substring(index + 3);
		var index = Parameter.indexOf("<->");
		var temp = Parameter.substring(0, index);
		document.getElementById("cartId").value =temp;
		var Parameter = Parameter.substring(index + 3);
		var index = Parameter.indexOf("<->");
		var temp = Parameter.substring(0, index);
		document.getElementById("userId").value =temp;
		var Parameter = Parameter.substring(index + 3);
		var index = Parameter.indexOf("<->");
		var temp = Parameter.substring(0, index);
		document.getElementById("productId").value =temp;
		var Parameter = Parameter.substring(index + 3);
		var index = Parameter.indexOf("<->");
		var temp = Parameter.substring(0, index);
		document.getElementById("addedOn").value =temp;
		var Parameter = Parameter.substring(index + 3);
		var select=document.getElementById('quantity');
		select.options.length = 0;
		for(i=1;i<=Parameter;i++)
		select.options[select.options.length] = new Option(i, i);
		
	});

<!-- script to display delete modal -->

	$(document).on("click", ".open-receiveDialog", function () // For delete modal in my cart page
		{
		
			var MsgId = $(this).data('id');
			document.getElementById("receiveHeader").innerHTML =MsgId;
			var index = MsgId.indexOf("<->"); 
			var orderId = MsgId.substring(0, index);
			var name= MsgId.substring(index+3);
			document.getElementById("order").value=orderId;
			document.getElementById("receiveHeader").innerHTML = "Receive Product";
			document.getElementById("msgId").value="Do you want to set "+name+" as received?";
		}); 


		$(document).ready(function()
			{
   				$('[data-toggle="showtooltip"]').tooltip();
			});
		
		$(document).ready(function(){
			$("#myModal").modal('show');
		});
	
		
		