//console.log("Hello World! bye!")

//hide the alerts
$(document).ready(function(){ 
	if ($("#alertSuccess").text().trim() == "") { 
		$("#alertSuccess").hide(); 
	} 
	$("#alertDanger").hide(); }
);

//Form validation
function validationItemForm(){
	//code
	if($("#itemCode").val().trim()==""){
		return "insert the item code.";
	}
	
	//name
	if($("#itemName").val().trim() == ""){
		return "Insert the item name";
	}
	
	//price
	var itemPrice = $("#itemPrice").val().trim();
	if(!$.isNumeric(itemPrice)){
		return "Insert a numeric value for item price";
	}
	
	//description
	if($("#itemDesc").val().trim() == ""){
		return "Insert the item description";
	}
	
	return true;
}

//Save
$(document).on("click", "#btnSave", function(event){
	//clear alerts
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertDanger").text("");
	$("#alertDanger").hide();
	
	//form validation
	var status = validationItemForm();
	if(status != true){
		$("#alertDanger").text(status);
		$("#alertDanger").show();
	}
	
	//if valid
	$("#formItem").submit();
	
});


















