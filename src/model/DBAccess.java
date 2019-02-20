package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	
// <---------------REVIEWS------------------>
	public String addReview(Review r) {
		String result = "Review Submitted";
		try {
		  stmt = conn.createStatement();
		  String sql;
		  int concertID = r.getConcertID();
		  int userID = r.getUserID();
		  String reviewDate = r.getReviewDate();
		  int rating = r.getRating();
		  String review = r.getReview();
		  

		  sql = "INSERT INTO customerreviews (concertID, userID, ReviewDate, Rating, Review)" +
		          "VALUES ('" + concertID +
				  "', '" + userID + 
				  "', '" + reviewDate + 
				  "', '" + rating + 
				  "', '" + review + "')";
		  stmt.executeUpdate(sql);
		  
		  
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "Review Submission Failed";
		}
		return result;
	}
	
	
	// <---------------Performances------------------>
	public List<Performance> getPerformancebyDate(Date d) {
		List<Performance> p  = new ArrayList<Performance>();
		String SQL = "SELECT * from performance WHERE StartTime>'"+d+"'";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()){
				Performance PerformanceHolder = new Performance();
				PerformanceHolder.setStartTime(rs.getDate("StartTime"));
				PerformanceHolder.setEndTime(rs.getDate("EndTime"));
				PerformanceHolder.setConcertID(rs.getInt("concertID"));
				PerformanceHolder.setVenueID(rs.getInt("venueID"));
				PerformanceHolder.setId(rs.getInt("Id"));
				p.add(PerformanceHolder);
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
		
	}
	
	public List<Performance> getPerformancebyID(int pID) {
		List<Performance> p  = new ArrayList<Performance>();
		String SQL = "SELECT * from performance WHERE Id='"+pID+"'";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()){
				Performance PerformanceHolder = new Performance();
				PerformanceHolder.setStartTime(rs.getDate("StartTime"));
				PerformanceHolder.setEndTime(rs.getDate("EndTime"));
				PerformanceHolder.setConcertID(rs.getInt("concertID"));
				PerformanceHolder.setVenueID(rs.getInt("venueID"));
				PerformanceHolder.setId(rs.getInt("Id"));
				p.add(PerformanceHolder);
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
		
	}
	// <---------------Venues------------------>
	public List<Venue> getVenue(String VenueName, Date date) {
		List<Venue> v  = new ArrayList<Venue>();
		String SQL = "SELECT * from venue WHERE Name='"+VenueName+"'";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()){
				Venue VenueHolder = new Venue();
				VenueHolder.setName(rs.getString("Name"));
				VenueHolder.setAddress(rs.getString("Address"));
				VenueHolder.setCity(rs.getString("City"));
				VenueHolder.setState(rs.getString("State"));
				VenueHolder.setPostalCode(rs.getString("PostalCode"));
				v.add(VenueHolder);
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
		
	}
	
	// <---------------Orders------------------>
	public List<Orders> getOrders(int cID) {
		List<Orders>  o = new ArrayList<Orders>();
		String SQL = "SELECT * from orders WHERE CustomerId='"+cID+"'";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()){
				Orders OrderHolder = new Orders();
				OrderHolder.setId(rs.getInt("Id"));
				OrderHolder.setTotalCost(rs.getInt("TotalCost"));
				OrderHolder.setOrderDate(rs.getString("OrderDate"));
				OrderHolder.setBillingAddress(rs.getString("BillingAddress"));
				OrderHolder.setCreditCardNumber(rs.getString("CreditCardNumber"));
				o.add(OrderHolder);
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
		
	}
	
	// <---------------OrderItems------------------>
	public List<OrderItems> getOrderItemsbyOrderID(int cID) {
		List<OrderItems>  o = new ArrayList<OrderItems>();
		String SQL = "SELECT * from orderitems WHERE OrderId='"+cID+"'";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()){
				OrderItems OrderItemHolder = new OrderItems();
				OrderItemHolder.setId(rs.getInt("Id"));
				OrderItemHolder.setOrderId(rs.getInt("OrderId"));
				OrderItemHolder.setPerformanceId(rs.getInt("PerformanceId"));
				OrderItemHolder.setQuantity(rs.getInt("Quantity"));
				o.add(OrderItemHolder);
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
		
	}
	
	
	// <---------------CreditCards------------------>
	
	public List<CreditCards> getCreditCards(int cID){
		List<CreditCards>  c = new ArrayList<CreditCards>();
		String SQL = "SELECT * from creditcards WHERE UserId='"+cID+"'";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()){
				CreditCards CreditHolder = new CreditCards();
				CreditHolder.setId(rs.getInt("Id"));
				CreditHolder.setCardHolderName(rs.getString("CardHolderName"));
				CreditHolder.setBalance(rs.getDouble("Balance"));
				CreditHolder.setCardType(rs.getString("CardType"));
				CreditHolder.setUserId(rs.getInt("UserId"));
				CreditHolder.setCVV(rs.getString("CVV"));
				CreditHolder.setExpirationDate(rs.getDate("ExpirationDate"));
				c.add(CreditHolder);
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	// <---------------Users------------------>
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
	
	public String getUsersName(String username) {
		String SQL = "SELECT * from users where Username='"+username+"'";
	    Statement stat;
	    String fname = "";
	    String lname = "";
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			while (rs.next()){
				if(username.equals( rs.getString("Username") )) {
					fname = rs.getString("FirstName");
					lname = rs.getString("LastName");
				} 
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String fullname = fname + " " + lname;
		return fullname;
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

	// <---------------Connection------------------>
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
