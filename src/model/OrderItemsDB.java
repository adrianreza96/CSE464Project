package model;

import java.util.List;

public class OrderItemsDB {
	public static List<OrderItems> getOrderItemsbyOrderID(int oID){
		DBAccess db = new DBAccess();
		db.connectMeIn();
		List<OrderItems> o = db.getOrderItemsbyOrderID(oID);
		db.closeConnection();
		return o;
	}
}