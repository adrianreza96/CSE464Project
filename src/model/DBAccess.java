package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Users;
import com.mysql.jdbc.*;

public class DBAccess {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/aschlichtm"; 
	//final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/CSE_LOGIN";
	
	

	//  Database credentials
	static final String USER = "aschlichtm"; // Replace with your CSE_LOGIN
	static final String PASS = "LM6-dy";   // Replace with your CSE MySQL_PASSWORD
	
	
	public void addSingleUser(Users aUser) {
		  
		try {
		  stmt = conn.createStatement();
		  String sql;
		  
		  String firstName = aUser.getFirstName();
		  String lastName = aUser.getLastName();
		  String userName = aUser.getUserName();
		  String password = aUser.getPassword();
		  

		  sql = "INSERT INTO users (FirstName, LastName, Username, Password)" +
		          "VALUES ('" + firstName +
				  "', '" + lastName + 
				  "', '" + userName + 
				  "', '" + password + "')";
		  stmt.executeUpdate(sql);
		  
		  
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	public boolean findUserByUsername(String aUserName) {
		boolean userExists = false;
		String SQL = "SELECT * from users";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			
			while (rs.next()){	
				if(aUserName.equals( rs.getString("Username") )) {
					userExists = true;
				}    
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userExists;
	}
	
	public boolean findUserByPassword(String password) {
		boolean passwordMatches = false;
		String SQL = "SELECT * from users";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			
			while (rs.next()){	
				if(password.equals( rs.getString("Password") )) {
					passwordMatches = true;
				}    
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return passwordMatches;
	}
	
	public int getCustomerID(String username) {
		String SQL = "SELECT * from users where Username='"+username+"'";
	    Statement stat;
	    int cId = -1;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()){
				if(username.equals( rs.getString("Username") )) {
					cId = rs.getInt("Id");
				} 
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cId;
	}
	
	public Users returnUserByUsername(String aUserName) {
		String SQL = "SELECT * from users";
	    Statement stat;
	   
	    Users aUser = new Users();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			
			while (rs.next()){
				if(aUserName.equals( rs.getString(4) )) {
					aUser.setFirstName(rs.getString(2));
					aUser.setLastName(rs.getString(3));
					aUser.setUserName(rs.getString(4));
					aUser.setPassword(rs.getString(5));
				} 
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return aUser;
	}

	
	public void displayAllUsers() {
		String SQL = "SELECT * from users";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			
			while (rs.next()){
		        System.out.println(rs.getString(1) + " " + rs.getString(2) +  " " + rs.getString(3)
		        		+ " " + rs.getString(4) + " " + rs.getString(5));
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void connectMeIn() {
		try{
			//Register the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");			
		}catch(ClassNotFoundException e){
			System.err.println(e);
			System.exit (-1);
		}
		try {
			 //Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}