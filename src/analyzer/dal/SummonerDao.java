package analyzer.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import analyzer.model.*;

public class SummonerDao {

	public ConnectionManager connectionManager;
	private static SummonerDao instance = null;
	
	protected SummonerDao () {
		connectionManager = new ConnectionManager();
	}
	
	public static SummonerDao getInstance() {
		if(instance == null) {
			instance = new SummonerDao ();
		}
		return instance;
	}
	
	// create new summoner
	public Summoner create(Summoner summoner) throws SQLException {
		String insertSummoner = "INSERT INTO Summoner(SummonerID, SummonerName, Wins, Losses) "
				+ "VALUES(?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSummoner);

			insertStmt.setString(1, summoner.getSummonerID());
			insertStmt.setString(2, summoner.getSummonerName());
			insertStmt.setInt(3, summoner.getWins());
			insertStmt.setInt(4,  summoner.getLosses());

			insertStmt.executeUpdate();

			return summoner;
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
	
	// get summoner by summoner name
	public Summoner getAccountBySummonerName(String summonerName) throws SQLException {
		
		String selectSummoner = "SELECT SummonerID, SummonerName, Wins, Losses"
				+ " FROM Summoner WHERE SummonerName = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSummoner);
			selectStmt.setString(1, summonerName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String summonerId = results.getString("SummonerID");
				String resName = results.getString("SummonerName");
				int wins = results.getInt("Wins");
				int losses = results.getInt("Losses");
				Summoner summoner = new Summoner (summonerId, resName, wins, losses);
				return summoner;
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
		
	// update player's summoner name
	public Summoner updateName (Summoner summoner, String summonerName) throws SQLException {
		String updateSummoner = "UPDATE Summoner SET SummonerName = ? WHERE SummonerID = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSummoner);
			updateStmt.setString(1, summonerName);
			updateStmt.setString(2, summoner.getSummonerID());
			updateStmt.executeUpdate();
			
			summoner.setSummonerName(summonerName);;
			return summoner;
			
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
	
	public String getRegion (String summonerName) throws SQLException {
		
		String getRegion = "SELECT `Accounts`.`Region` " + 
				"	FROM `Summoner` INNER JOIN `Accounts` ON `Summoner`.`SummonerID` = `Accounts`.`SummonerID` " + 
				"    WHERE `Summoner`.`SummonerName` = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getRegion);
			selectStmt.setString(1, summonerName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String region = results.getString("Region");
				return region;
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
	
	// delete specified summoner
	public Summoner delete(Summoner summoner) throws SQLException {
		
		String deleteSummoner = "DELETE FROM Summoner WHERE SummonerName = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			
			deleteStmt = connection.prepareStatement(deleteSummoner);
			deleteStmt.setString(1, summoner.getSummonerName());
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
