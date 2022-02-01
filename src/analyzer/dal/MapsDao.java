package analyzer.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import analyzer.model.Maps;


public class MapsDao {
	
	public ConnectionManager connectionManager;
	private static MapsDao instance = null;
	
	protected MapsDao () {
		connectionManager = new ConnectionManager();
	}
	
	public static MapsDao getInstance() {
		if(instance == null) {
			instance = new MapsDao ();
		}
		return instance;
	}
	
	
	// create new map
	public Maps create(Maps map) throws SQLException {
		String insertMap = "INSERT INTO Maps (MapID, MapName, MapDesc) "
				+ "VALUES(?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMap);

			insertStmt.setInt(1, map.getMapID());
			insertStmt.setString(2, map.getMapName());
			insertStmt.setString(3, map.getMapDesc());
			
			insertStmt.executeUpdate();

			return map;
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
	
	// get map by map Id
	public Maps getMapById(int mapId) throws SQLException {
		
		String selectMap = "SELECT MapID, MapName, MapDesc"
				+ " FROM Maps WHERE MapID = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMap);
			selectStmt.setInt(1, mapId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resMapId = results.getInt("MapID");
				String mapName = results.getString("MapName");
				String mapDesc = results.getString("MapDesc");

				Maps map = new Maps (resMapId, mapName, mapDesc);
				return map;
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
	
	public List<Maps> getAllMaps() throws SQLException {
		List<Maps> allMaps = new ArrayList<Maps>();
		String selectMap = "SELECT * FROM Maps;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMap);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int mapId = results.getInt("MapID");
				String mapName = results.getString("MapName");
				String mapDesc = results.getString("MapDesc");

				Maps map = new Maps (mapId, mapName, mapDesc);
				allMaps.add(map);
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
		return allMaps;
		
	}
		
	// update map page name
	public Maps updateName (Maps map, String mapName) throws SQLException {
		String updateMap = "UPDATE Maps SET MapName = ? WHERE MapID = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMap);
			updateStmt.setString(1, mapName);
			updateStmt.setInt(2, map.getMapID());
			updateStmt.executeUpdate();
			
			map.setMapName(mapName);;
			return map;
			
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
	
	// update map desc
	public Maps updateDesc (Maps map, String mapDesc) throws SQLException {
		String updateMap = "UPDATE Maps SET MapDesc = ? WHERE MapID = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMap);
			updateStmt.setString(1, mapDesc);
			updateStmt.setInt(2, map.getMapID());
			updateStmt.executeUpdate();
			
			map.setMapDesc(mapDesc);;
			return map;
			
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
	
	// delete specified map
	public Maps delete(Maps map) throws SQLException {
		
		String deleteMap = "DELETE FROM Maps WHERE MapID = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			
			deleteStmt = connection.prepareStatement(deleteMap);
			deleteStmt.setInt(1, map.getMapID());
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
