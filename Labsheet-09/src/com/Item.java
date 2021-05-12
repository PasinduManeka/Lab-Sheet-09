package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {
	
	//database connection
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql:127.0.0.1:3306/paf", "root", "");
			
		}catch(Exception e) {
			System.out.println("This is the error: "+e);
		}
		return con;
	}
	
	public String readItems() {
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "error while connecting to the database for reading.";
			}
			//prepare the HTML table
			output = "<table border='1'><tr><th>Item Code</th><th>Item Name</th>" +
					"<th>Item Price</th>" +  
					"<th>Item Description</th>" +
					"<th>Update</th><th>Remove</th></tr>";
			
			//query
			String query = "select * from product";
			Statement stmt = con.createStatement();
			ResultSet rs  = stmt.executeQuery(query);
			
			//iterate through the rows in the result set
			while(rs.next()) {
				String itemID = Integer.toString(rs.getInt("itemID")); 
				String itemCode = rs.getString("itemCode"); 
				String itemName = rs.getString("itemName"); 
				String itemPrice = rs.getString("itemPrice"); 
				String itemDesc = rs.getString("itemDesc");
				
				//add into HTML table
				output += "<tr><td>" + itemCode + "</td>"; 
				output += "<td>" + itemName + "</td>"; 
				output += "<td>" + itemPrice + "</td>"; 
				output += "<td>" + itemDesc + "</td>"; 
				
				//buttons
				output += "<td><form method='post' action='Items.jsp'>"
							+"<input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'>"
							+ "<input name='itemID' type='hidden' value='" + itemID+ "'>"+"</form></td>"+ 
							"<td><form method='post' action='Items.jsp'>"+ 
							"<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"+ 
							"<input name='itemID' type='hidden' value='" + itemID+ "'>" + "</form></td></tr>";
			}
			//database connection is closed.
			con.close();
			
			//HTML table is completed.
			output +="</table>";
			
		}catch(Exception e) {
			output = "Error while reading the items";
			System.err.println("This is the error: "+e.getMessage());
		}
		
		return output;
	}
	
}
