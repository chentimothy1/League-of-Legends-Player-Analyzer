package analyzer.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import analyzer.model.LOLChampions;
import analyzer.model.SummonerSpell;


public class SummonerSpellDao {
	
	public ConnectionManager connectionManager;
	private static SummonerSpellDao instance = null;
	
	protected SummonerSpellDao () {
		connectionManager = new ConnectionManager();
	}
	
	public static SummonerSpellDao getInstance() {
		if(instance == null) {
			instance = new SummonerSpellDao ();
		}
		return instance;
	}
	
	// create new summoner spell
	public SummonerSpell create(SummonerSpell summonerSpell) throws SQLException {
		String insertSpell = "INSERT INTO SummonerSpell (SummonerSpellID, SummonerSpellName, SummonerSpellDesc) "
				+ "VALUES(?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSpell);

			insertStmt.setString(1, summonerSpell.getSummonerSpellID());
			insertStmt.setString(2, summonerSpell.getSummonerSpellName());
			insertStmt.setString(3, summonerSpell.getSummonerSpellDesc());
			
			insertStmt.executeUpdate();

			return summonerSpell;
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
	
	// get summoner spell by spell Id
	public SummonerSpell getSpellById(String spellId) throws SQLException {
		
		String selectSpell = "SELECT SummonerSpellID, SummonerSpellName, SummonerSpellDesc"
				+ " FROM SummonerSpell WHERE SummonerSpellID = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSpell);
			selectStmt.setString(1, spellId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resSpellId = results.getString("SummonerSpellID");
				String spellName = results.getString("SummonerSpellName");
				String spellDesc = results.getString("SummonerSpellDesc");

				SummonerSpell summonerSpell = new SummonerSpell (resSpellId, spellName, spellDesc);
				return summonerSpell;
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
	
	//get all summoner spell
	public List<SummonerSpell> getAllSpells() throws SQLException {
		List <SummonerSpell> allSpells = new ArrayList<SummonerSpell>();
		String selectAll = "SELECT * FROM SummonerSpell;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAll);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				String resSpellId = results.getString("SummonerSpellID");
				String spellName = results.getString("SummonerSpellName");
				String spellDesc = results.getString("SummonerSpellDesc");

				SummonerSpell summonerSpell = new SummonerSpell (resSpellId, spellName, spellDesc);
				allSpells.add(summonerSpell);
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
		return allSpells;
		
	}
		
	// update summoner spell name
	public SummonerSpell updateName (SummonerSpell summonerSpell, String spellName) throws SQLException {
		String updateSpell = "UPDATE SummonerSpell SET SummonerSpellName = ? WHERE SummonerSpellID = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSpell);
			updateStmt.setString(1, spellName);
			updateStmt.setString(2, summonerSpell.getSummonerSpellID());
			updateStmt.executeUpdate();
			
			summonerSpell.setSummonerSpellName(spellName);;
			return summonerSpell;
			
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
	
	// update summoner spell desc
	public SummonerSpell updateDesc (SummonerSpell summonerSpell, String spellDesc) throws SQLException {
		String updateSpell = "UPDATE SummonerSpell SET SummonerSpellDesc = ? WHERE SummonerSpellID = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSpell);
			updateStmt.setString(1, spellDesc);
			updateStmt.setString(2, summonerSpell.getSummonerSpellID());
			updateStmt.executeUpdate();
			
			summonerSpell.setSummonerSpellDesc(spellDesc);;
			return summonerSpell;
			
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
	
	// delete specified summoner spell
	public SummonerSpell delete(SummonerSpell summonerSpell) throws SQLException {
		
		String deleteSpell = "DELETE FROM SummonerSpell WHERE SummonerSpellID = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			
			deleteStmt = connection.prepareStatement(deleteSpell);
			deleteStmt.setString(1, summonerSpell.getSummonerSpellID());
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
