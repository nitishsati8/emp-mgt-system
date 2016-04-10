package com.emp.db.handler;
import com.emp.data.handler.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class DbManager {
	
	 private static Connection con;
	 HttpServletResponse response;
	    static
	    {
	        try
	        {
	          Class.forName("com.mysql.jdbc.Driver");
	          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empmgt","root","root");
	        }
	        catch(Exception ex)
	      {
	          throw new RuntimeException(ex.getMessage());
	      }
	    }
	    public static void insertUser(AdminData aData)throws SQLException
	    {
	        String query="insert into reguser values(?,?,?)";
	        PreparedStatement ps=con.prepareStatement(query);
	        ps.setString(1,aData.getAdminId());
	        ps.setString(2,aData.getAdminPassword());
	       ps.setString(3, aData.getAdminStatus());
	        ps.executeUpdate();
	        
	    }
	    public static void deleteUser(AdminData aData) throws SQLException
	    {
	        String query=" ";
	        PreparedStatement ps=con.prepareStatement(query);
	        ps.setString(1,aData.getAdminId());
	       int val=ps.executeUpdate();
	       if(val==0)
	       {
	           throw new SQLException("User Not Exist");
	       }
	       
	    }
	   public static void updateStatus(AdminData aData)throws SQLException
	    {
	        String query="update reguser set pass=? where uname=?";
	        PreparedStatement ps=con.prepareStatement(query);
	        
	        ps.setString(1,aData.getAdminId());
	        ps.setString(2, aData.getAdminStatus());
	        int val=ps.executeUpdate();
	        if(val==0)
	        {
	            throw new SQLException("Data not Found");
	        
	    }
	        else
	        {
	            System.out.println("Update Success");
	        }
	        
	        
	    }
	    public void updateAdminPassword(AdminData aData) throws SQLException
	    {
	        String query="update user set pass=? where username=?";
	        PreparedStatement ps=con.prepareStatement(query);
	        
	        ps.setString(1,aData.getAdminPassword());
	        int val=ps.executeUpdate();
	        if(val==0)
	        {
	            throw new SQLException("Data no Found");
	        
	        }
	        else{
	        	System.out.println("Update Success");
	        }
	        
	    }
	    public static void selectAdmin(AdminData aData)throws SQLException
	    {
	        try{
	        PreparedStatement ps=con.prepareStatement("select * from admin_login where adminId=?");
	        ps.setString(1,aData.getAdminId());
	        ResultSet rs=ps.executeQuery();
	        boolean check=false;
	        if(rs.next())
	        {
	            check=true;      
	            aData.setAdminPassword(rs.getString(2));
	            aData.setAdminStatus(rs.getString(3));
	        }
	            
	        rs.close();
	        ps.close();
	        
	        if(check == false)
	        {	
	        	HttpServletResponse response=null;
	            response.sendRedirect("fail.html");
	            //throw new SQLException("No Record");
	          
	        }
	        }
	        catch(SQLException ex){
	        	ex.printStackTrace();
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	    
	     public static ArrayList<AdminData> getUserList()throws SQLException 
	     {
	       Statement st = con.createStatement();
	       ResultSet rs = st.executeQuery("Select * from reguser");
	       ArrayList<AdminData> al = new ArrayList<AdminData>();
	       while(rs.next())
	       {
	           AdminData aData = new AdminData();
	          aData.setAdminId(rs.getString(1));
	           aData.setAdminPassword(rs.getString(2));
	           
	           
	           al.add(aData);
	       }
	       st.close();
	       return  al;
	     }
}
