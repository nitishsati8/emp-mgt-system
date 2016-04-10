package com.emp.servlet.handler;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.data.handler.AdminData;
import com.emp.db.handler.DbManager;

public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
          
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 PrintWriter out = response.getWriter();
		 String adminId=request.getParameter("login");
		 String password=request.getParameter("pass");
		 String status= "active";
		 
		/* try{
			 Connection con;
			 Class.forName("com.mysql.jdbc.Driver");
	          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empmgt","root","root");
	          String query="select * from admin_login where adminId=?";
	          PreparedStatement ps=con.prepareStatement(query);
	          ps.setString(1, adminId);
	          ResultSet rs=ps.executeQuery();
	          boolean check=false;
	          if(rs.next())
	          {
	              String pass=rs.getString(2);
	              String stat=rs.getString(3);
	              if(pass.equals(password) && stat.equalsIgnoreCase(status))
	              {
	            	  response.sendRedirect("pass.html");
	              }
	              else{
	            	  response.sendRedirect("fail.html");
	              }
	              
	          }
	          rs.close();
	          ps.close();
	          if(check == false)
	          {
	        	  response.sendRedirect("fail.html");
	          }
			 
		 }
		 catch(Exception ex){
			ex.printStackTrace();
		 }*/
		 try{
		 AdminData adata=new AdminData();
		 adata.setAdminId(adminId);
		 DbManager.selectAdmin(adata);
			 if(password.equals(adata.getAdminPassword()) && status.equalsIgnoreCase(adata.getAdminStatus()))
			   {
				 response.sendRedirect("pass.html");
			   }
			 else
			   {
				   response.sendRedirect("fail.html");
			    }
		 }
		 	catch(Exception ex)
		 {
		 		System.out.println(ex.getMessage());
		 }
	}

}
