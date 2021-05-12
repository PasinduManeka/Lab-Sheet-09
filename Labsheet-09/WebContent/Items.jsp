<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.Item" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Item Form</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="Components/item.js"></script>
<link rel="stylesheet" href="./Views/bootstrap.min.css">
<link rel="stylesheet" href="./Views/item.css">
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
			<form id="formItem" name="formItem" method="post" action="Items.jsp">
				Item Code:
				<input type="text" name="code" id="code" class="form-control border border-primary form-control-sm"><br>
				Item Name:
				<input type="text" name="name" id="name" class="form-control border border-primary form-control-sm"><br>
				Item Price:
				<input type="text" name="price" id="price" class="form-control border border-primary form-control-sm"><br>
				Item Description:
				<input type="text" name="description" id="description" class="form-control border border-primary form-control-sm"><br>
				
				<div id="alertSuccess" class="alert alert-success"></div><br>
				<div id="alertDanger" class="alert alert-danger"></div><br>
				
				<input type="button" id="bntSave" name="bntSave" value="Save" class="btn btn-outline-success"><br>
				
				<input type="hidden" id="hidIDItemIDSave" name="hidIDItemIDSave" value="">
			</form><br>
			<!-- end the form -->
			<!--  Display the HTML table -->
			<div class="row container ">
				<div class="col-12" id="colItem">
					<%
					Item it  = new Item();
					out.print(it.readItems());
					%>
				</div>
			</div>
			<!-- End display the HTML  -->
		</div>
		<!-- end the card body -->
		
	</div>
	<!-- end the card  -->
	
	<%
	System.out.println(request.getParameter("code"));
	System.out.println(request.getParameter("name"));
	System.out.println(request.getParameter("price"));
	System.out.println(request.getParameter("description"));
	if(request.getParameter("code")!= null){
		String 	stsMsg = "";
		System.out.println(request.getParameter("code"));
		//Save ==================================== 
		if(request.getParameter("hidIDItemIDSave") == ""){
			stsMsg = it.inserItem(request.getParameter("code"), request.getParameter("name"), request.getParameter("price"), request.getParameter("description"));
			System.out.println(stsMsg);
			/*stsMsg = it.insertIte(request.getParameter("code"),  */
					//request.getParameter("name"), request.getParameter("price"), 
					//request.getParameter("description"));
			
			
		}
		else{
			//update ==============
			stsMsg = it.updateItem(request.getParameter("hidIDItemIDSave"), request.getParameter("code"), request.getParameter("name"), request.getParameter("price"), request.getParameter("description"));
			System.out.println(stsMsg);
		}
		request.getParameter("hidIDItemIDSave").contentEquals("");
		request.getParameter("name").contentEquals("");
		request.getParameter("code").contentEquals("");
		request.getParameter("price").contentEquals("");
		request.getParameter("description").contentEquals("");
		session.setAttribute("statusMsg", stsMsg);
		
		
		
	}
	%>
	
</body>
</html>