package analyzer.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import analyzer.model.RunePage;

public class RunePageDao {
	
	public ConnectionManager connectionManager;
	private static RunePageDao instance = null;
	
	protected RunePageDao () {
		connectionManager = new ConnectionManager();
	}
	
	public static RunePageDao getInstance() {
		if(instance == null) {
			instance = new RunePageDao ();
		}
		return instance;
	}
	
	
	// create new rune page
	public RunePage create(RunePage runePage) throws SQLException {
		String insertRune = "INSERT INTO RunePage (RunePageID, RunePageName, RunePageDesc) "
				+ "VALUES(?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRune);

			insertStmt.setInt(1, runePage.getRunePageID());
			insertStmt.setString(2, runePage.getRunePageName());
			insertStmt.setString(3, runePage.getRunePageDesc());
			
			insertStmt.executeUpdate();

			return runePage;
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
	
	// get rune by rune Id
	public RunePage getRuneById(int runeId) throws SQLException {
		
		String selectRune = "SELECT RunePageID, RunePageName, RunePath, RunePageDesc"
				+ " FROM RunePage WHERE RunePageID = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRune);
			selectStmt.setInt(1, runeId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resRuneId = results.getInt("RunePageID");
				String runeName = results.getString("RunePageName");
				String runePath = results.getString("RunePath");
				String runeDesc = results.getString("RunePageDesc");

				RunePage runePage = new RunePage (resRuneId, runeName, runePath, runeDesc);
				return runePage;
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
	
	// get all Rune Pages
	public List<RunePage> getAllRunes() throws SQLException {
		List<RunePage> allRunes = new ArrayList<RunePage>();
		String selectRune = "SELECT * FROM RunePage;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRune);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int runeId = results.getInt("RunePageID");
				String runeName = results.getString("RunePageName");
				String runePath = results.getString("RunePath");
				String runeDesc = results.getString("RunePageDesc");

				RunePage runePage = new RunePage (runeId, runeName, runePath, runeDesc);
				allRunes.add(runePage);
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
		return allRunes;
		
	}
		
	// update rune page name
	public RunePage updateName (RunePage runePage, String runeName) throws SQLException {
		String updateRune = "UPDATE RunePage SET RunePageName = ? WHERE RunePageID = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRune);
			updateStmt.setString(1, runeName);
			updateStmt.setInt(2, runePage.getRunePageID());
			updateStmt.executeUpdate();
			
			runePage.setRunePageName(runeName);;
			return runePage;
			
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
	
	// update rune page desc
	public RunePage updateDesc (RunePage runePage, String runeDesc) throws SQLException {
		String updateRune = "UPDATE RunePage SET RunePageDesc = ? WHERE RunePageID = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRune);
			updateStmt.setString(1, runeDesc);
			updateStmt.setInt(2, runePage.getRunePageID());
			updateStmt.executeUpdate();
			
			runePage.setRunePageDesc(runeDesc);;
			return runePage;
			
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
	
	// delete specified rune page
	public RunePage delete(RunePage runePage) throws SQLException {
		
		String deleteRune = "DELETE FROM RunePage WHERE RunePageID = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			
			deleteStmt = connection.prepareStatement(deleteRune);
			deleteStmt.setInt(1, runePage.getRunePageID());
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
