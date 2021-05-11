<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Item Form</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="Components/item.js"></script>
<link rel="stylesheet" href="./Views/bootstrap.min.css">
</head>
<body>
	<!-- start the card  -->
	<div class="card">
		<!-- Headline of the card -->
		<h5 class="card-header border border-warning text-center ">
			<strong>Add Items</strong>
		</h5>
		<!-- start the card body -->
		<div class="card-body container border border-info">
			<!-- start the form -->
			<form id="formItem" name="formItem" method="post" action="items.jsp">
				Item Code:
				<input type="text" name="itemCode" id="itemCode" class="form-control border border-primary form-control-sm"><br>
				Item Name:
				<input type="text" name="itemName" id="itemName" class="form-control border border-primary form-control-sm"><br>
				Item Price:
				<input type="text" name="itemPrice" id="itemPrice" class="form-control border border-primary form-control-sm"><br>
				Item Description:
				<input type="text" name="itemDesc" id="itemDesc" class="form-control border border-primary form-control-sm"><br>
				
				<div id="alertSuccess" class="alert alert-success"></div><br>
				<div id="alertDanger" class="alert alert-danger"></div><br>
				
				<input type="button" id="bntSave" name="bntSave" value="Save" class="btn btn-outline-success"><br>
				
				<input type="hidden" id="hidIDItemIDSave" name="hidIDItemIDSave" value="">
			</form><br>
			<!-- end the form -->
			<div class="row">
				<div class="col-12" id="colItem">
				
				</div>
			</div>
		</div>
		<!-- end the card body -->
	</div>
	<!-- end the card  -->
</body>
</html>