package analyzer.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import analyzer.model.*;

public class LOLChampionsDao {
	
	public ConnectionManager connectionManager;
	private static LOLChampionsDao instance = null;
	
	protected LOLChampionsDao () {
		connectionManager = new ConnectionManager();
	}
	
	public static LOLChampionsDao getInstance() {
		if(instance == null) {
			instance = new LOLChampionsDao ();
		}
		return instance;
	}
	
	
	// create new champion
	public LOLChampions create(LOLChampions champion) throws SQLException {
		String insertChampion = "INSERT INTO LOLChampions (ChampionID, ChampionName, ChampionTitle, ChampionBlub, ChampionHP, ChampionAttack, ChampionDefense, ChampionMagic) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertChampion);

			insertStmt.setInt(1, champion.getChampionID());
			insertStmt.setString(2, champion.getChampionName());
			insertStmt.setString(3, champion.getChampionTitle());
			insertStmt.setString(4, champion.getChampionBlub());
			insertStmt.setDouble(5, champion.getChampionHP());
			insertStmt.setInt(6, champion.getChampionAttack());
			insertStmt.setInt(7, champion.getChampionDefense());
			insertStmt.setInt(8, champion.getChampionMagic());

			insertStmt.executeUpdate();
			return champion;
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
	
	// get champion by champion Id
	public LOLChampions getChampionById(int championId) throws SQLException {
		String selectChampion = "SELECT ChampionID, ChampionName, ChampionTitle, ChampionBlub, ChampionHP, ChampionAttack, ChampionDefense, ChampionMagic"
				+ " FROM LOLChampions WHERE ChampionID = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectChampion);
			selectStmt.setInt(1, championId);
			results = selectStmt.executeQuery();
			if(results.next()) {				
				int resChampId = results.getInt("ChampionID");
				String championName = results.getString("ChampionName");
				String championTitle = results.getString("ChampionTitle");
				String championBlub = results.getString("ChampionBlub");
				double championHp = results.getDouble("ChampionHP");
				int championAttack = results.getInt("ChampionAttack");
				int championDefense = results.getInt("ChampionDefense");
				int championMagic = results.getInt("ChampionMagic");

				LOLChampions champion = new LOLChampions (resChampId, championName, championTitle, championBlub, championHp, championAttack, championDefense, championMagic);
				return champion;
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
	
	// get all champions
	public List<LOLChampions> getAllChampions() throws SQLException {
		List <LOLChampions> allChampions = new ArrayList<LOLChampions>();
		String selectAll = "SELECT * FROM LOLChampions;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAll);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int resChampId = results.getInt("ChampionID");
				String championName = results.getString("ChampionName");
				String championTitle = results.getString("ChampionTitle");
				String championBlub = results.getString("ChampionBlub");
				double championHp = results.getDouble("ChampionHP");
				int championAttack = results.getInt("ChampionAttack");
				int championDefense = results.getInt("ChampionDefense");
				int championMagic = results.getInt("ChampionMagic");

				LOLChampions champion = new LOLChampions (resChampId, championName, championTitle, championBlub, championHp, championAttack, championDefense, championMagic);
				allChampions.add(champion);
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
		return allChampions;
		
	}
		
	// update champion blub
	public LOLChampions updateBlub (LOLChampions champion, String championBlub) throws SQLException {
		String updateRune = "UPDATE LOLChampions SET ChampionBlub = ? WHERE ChampionName = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRune);
			updateStmt.setString(1, championBlub);
			updateStmt.setString(2, champion.getChampionName());
			updateStmt.executeUpdate();
			
			champion.setChampionBlub(championBlub);;
			return champion;
			
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
	
	
	// delete specified champion
	public LOLChampions delete(LOLChampions champion) throws SQLException {
		
		String deleteChampion = "DELETE FROM LOLChampions WHERE ChampionID = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			
			deleteStmt = connection.prepareStatement(deleteChampion);
			deleteStmt.setInt(1, champion.getChampionID());
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
