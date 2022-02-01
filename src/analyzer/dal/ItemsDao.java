package analyzer.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import analyzer.model.*;

public class ItemsDao {
	
	public ConnectionManager connectionManager;
	private static ItemsDao instance = null;
	
	protected ItemsDao () {
		connectionManager = new ConnectionManager();
	}
	
	public static ItemsDao getInstance() {
		if(instance == null) {
			instance = new ItemsDao ();
		}
		return instance;
	}
	
	// create new item
	public Items create(Items item) throws SQLException {
		String insertItem = "INSERT INTO Items (ItemID, ItemName, ItemDesc, ItemBlurb, ItemPrice, ItemSell) "
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertItem);

			insertStmt.setInt(1, item.getItemID());
			insertStmt.setString(2, item.getItemName());
			insertStmt.setString(3, item.getItemDesc());
			insertStmt.setString(4, item.getItemBlurb());
			insertStmt.setInt(5, item.getItemPrice());
			insertStmt.setInt(6, item.getItemSell());

			insertStmt.executeUpdate();

			return item;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
		
	}
	
	// get item by item Id
	public Items getItemByName(String itemName) throws SQLException {
		
		String selectItem = "SELECT ItemID, ItemName, ItemDesc, ItemBlurb, ItemPrice, ItemSell"
				+ " FROM Items WHERE ItemName = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectItem);
			selectStmt.setString(1, itemName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int itemId = results.getInt("ItemId");
				String resitemName = results.getString("ItemName");
				String itemDesc = results.getString("ItemDesc");
				String itemBlurb = results.getString("ItemBlurb");
				int itemPrice = results.getInt("ItemPrice");
				int itemSell = results.getInt("ItemSell");

				Items item = new Items (itemId, resitemName, itemDesc, itemBlurb, itemPrice, itemSell);
				return item;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
		
	}
	
	// get all items
	public List<Items> getAllItems() throws SQLException {
		List <Items> allItems = new ArrayList<Items>();
		String selectAll = "SELECT * FROM Items;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAll);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int itemId = results.getInt("ItemId");
				String resitemName = results.getString("ItemName");
				String itemDesc = results.getString("ItemDesc");
				String itemBlurb = results.getString("ItemBlurb");
				int itemPrice = results.getInt("ItemPrice");
				int itemSell = results.getInt("ItemSell");

				Items item = new Items (itemId, resitemName, itemDesc, itemBlurb, itemPrice, itemSell);
				allItems.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return allItems;
		
	}
	
	// update item name
	public Items updateName (Items item, String itemName) throws SQLException {
		String updateItem = "UPDATE Items SET ItemName = ? WHERE ItemId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateItem);
			updateStmt.setString(1, itemName);
			updateStmt.setInt(2, item.getItemID());
			updateStmt.executeUpdate();
			
			item.setItemName(itemName);
			return item;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
		
	// update item desc
	public Items updateDesc (Items item, String itemDesc) throws SQLException {
		String updateItem = "UPDATE Items SET ItemDesc = ? WHERE ItemID = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateItem);
			updateStmt.setString(1, itemDesc);
			updateStmt.setInt(2, item.getItemID());
			updateStmt.executeUpdate();
			
			item.setItemDesc(itemDesc);;
			return item;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	// delete specified item
	public Items delete(Items item) throws SQLException {
		
		String deleteItem = "DELETE FROM Items WHERE ItemName = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			
			deleteStmt = connection.prepareStatement(deleteItem);
			deleteStmt.setString(1, item.getItemName());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}
