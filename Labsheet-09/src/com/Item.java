package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {
	
	//database connection
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
			
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
			output = "<div class='table-wrapper-scroll-y my-custom-scrollbar'><table border='1' class='table'><thead class='thead-dark'><tr><th>id</th><th>Item Code</th><th>Item Name</th>" +
					"<th>Item Price</th>" +  
					"<th>Item Description</th>" +
					"<th>Update</th><th>Remove</th></tr></thead>";
			
			//query
			String query = "select * from product";
			Statement stmt = con.createStatement();
			ResultSet rs  = stmt.executeQuery(query);
			
			//iterate through the rows in the result set
			while(rs.next()) {
				String itemID = Integer.toString(rs.getInt("id")); 
				String itemCode = rs.getString("code"); 
				String itemName = rs.getString("name"); 
				String itemPrice = rs.getString("price"); 
				String itemDesc = rs.getString("description");
				
				//add into HTML table
				output += "<tr><td><input type='hidden' id='hidItemIDUpdate' name='hidItemIDUpdate' value='"+itemID+"'>"+itemID+"</td>";
				output += "<td>" + itemCode + "</td>"; 
				output += "<td>" + itemName + "</td>"; 
				output += "<td>" + itemPrice + "</td>"; 
				output += "<td>" + itemDesc + "</td>"; 
				
				//buttons
				output += "<td>"
							+"<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'>"
							+"</td>"+ 
							"<td><form method='post' action='Items.jsp'>"+ 
							"<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"+ 
							"<input name='itemID' type='hidden' value='" + itemID+ "'>" + "</form></td></tr>";
			}
			//database connection is closed.
			con.close();
			
			//HTML table is completed.
			output +="</table></div>";
			
		}catch(Exception e) {
			output = "Error while reading the items";
			System.err.println("This is the error: "+e.getMessage());
		}
		
		return output;
	}
	
	public String inserItem(String code, String name, String price, String description) {
		String output="";
		
		try {
			//Database Connection
			Connection con = connect();
			if(con == null) {
				return "While connecting to the database for inserting.";
			}
			
			//SQL query
			String sql = ("insert into product (code,name,price,description) values (?,?,?,?)");
			PreparedStatement pdstm = con.prepareStatement(sql);
			
			//bind values
			pdstm.setString(1, code);
			pdstm.setString(2, name);
			pdstm.setString(3, price);
			pdstm.setString(4, description);
			
			//Execute the statement
			pdstm.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			output="Inserted successfully";
			
			
			
		}catch(Exception e) {
			output ="Error while inserting the data";
			System.err.append("This is the error in insert:"+e.getMessage());
		}
		//response.sendRedirect("confirmpage.jsp");
		return output;
		
	}
	
	public String updateItem(String id, String code, String name, String price, String description) {
		String output = "";
		
		try {
			//check the database connection
			Connection con = connect();
			if(con == null) {
				return "Error while connectintg to the database for update the records.";
			}
			
			String sql = "update product set code=? , name=?, price=?, description=? where id=? ";
			PreparedStatement pd = con.prepareStatement(sql);
			
			//bind values
			pd.setString(1, code);
			pd.setString(2, name);
			pd.setString(3, price);
			pd.setString(4, description);
			pd.setInt(5,Integer.parseInt(id));
			
			//execute the statment
			pd.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			output="Updated successfuly";
			
			
			
		}catch(Exception e) {
			output= "Error while updating the item details.";
			System.err.println("This is the error in update:"+e.getMessage());
		}
		
		return output;
	}
	
}
