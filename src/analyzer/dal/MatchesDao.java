package analyzer.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import analyzer.model.*;

public class MatchesDao {
	
	public ConnectionManager connectionManager;
	private static MatchesDao instance = null;
	
	protected MatchesDao () {
		connectionManager = new ConnectionManager();
	}
	
	public static MatchesDao getInstance() {
		if(instance == null) {
			instance = new MatchesDao ();
		}
		return instance;
	}
	
	// create match
	public Matches create(Matches matchNA1) throws SQLException {
		String insertMatch = "INSERT INTO MatchNA1 (MatchID, AccountID, ChampionID, PlayedRole, PlayedPosition) "
				+ "VALUES(?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMatch);

			insertStmt.setString(1, matchNA1.getMatchID());
			insertStmt.setString(2, matchNA1.getAccountID());
			insertStmt.setInt(3, matchNA1.getChampionID());
			insertStmt.setString(4, matchNA1.getPlayedRole().name());
			insertStmt.setNString(5, matchNA1.getPlayedPosition().name());
			
			insertStmt.executeUpdate();

			return matchNA1;
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
	
	// get single matches for specific player with account and match Ids
	public Matches getSpecificMatch(String matchId, String accountId) throws SQLException {
		String selectMatch = "SELECT MatchID, ChampionID, PlayedRole, PlayedPosition"
				+ " FROM MatchNA1 WHERE MatchID = ? AND AccountID = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMatch);
			selectStmt.setString(1, matchId);
			selectStmt.setNString(2, accountId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resmatchId = results.getString("MatchID");
				int championId = results.getInt("ChampionId");
				Matches.Role playedRole = Matches.Role.valueOf(results.getString("PlayedRole"));
				Matches.Position playedPosition = Matches.Position.valueOf(results.getString("PlayedPosition"));
				
				Matches matchNA1 = new Matches (resmatchId, championId, playedRole, playedPosition);
				return matchNA1;
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
	
	
	
	// get all match data for a specific player
	public List<Matches> getMatchListBySummonerName(String summonerName, String region) throws SQLException {
		List <Matches> matchesNA1 = new ArrayList<Matches> ();
		String selectMatch = "SELECT MatchID, ChampionID, PlayedRole, PlayedPosition"
				+ " FROM Match"+region
				+ " WHERE Match"+region+".AccountID IN"
				+ " (SELECT Accounts.AccountID FROM Summoner INNER JOIN Accounts ON Summoner.SummonerID = Accounts.SummonerID"
				+ " WHERE Summoner.SummonerName = ?);";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMatch);
			selectStmt.setString(1, summonerName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String matchId = results.getString("MatchID");
				int championId = results.getInt("ChampionId");
				Matches.Role playedRole = Matches.Role.valueOf(results.getString("PlayedRole"));
				Matches.Position playedPosition = Matches.Position.valueOf(results.getString("PlayedPosition"));

				Matches matchNA1 = new Matches (matchId, championId, playedRole, playedPosition);
				matchesNA1.add(matchNA1);
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
		return matchesNA1;
	}
	
	
	// For a specific player, what is the champion that they play the most often?
	public List<ChampionPlayRate> getMostPlayedChampions(String summonerName, String region) throws SQLException {
		List <ChampionPlayRate> championlist = new ArrayList<ChampionPlayRate> ();
		String selectChampion = "SELECT LOLChampions.ChampionName, Count(*) AS Count "
				+ "FROM Match"+region+" INNER JOIN LOLChampions ON Match"+region+".ChampionID = LOLChampions.ChampionID "
				+ "WHERE Match"+region+".AccountID IN "
				+ "(SELECT Accounts.AccountID FROM Summoner INNER JOIN Accounts ON Summoner.SummonerID = Accounts.SummonerID "
				+ "WHERE Summoner.SummonerName = ?) "
				+ "GROUP BY Match"+region+".ChampionID "
				+ "ORDER BY Count DESC "
				+ "LIMIT 5;";
				
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectChampion);
			selectStmt.setString(1, summonerName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String championName = results.getString("ChampionName");
				int timesPlayed = results.getInt("Count");

				ChampionPlayRate championplayrate = new ChampionPlayRate (championName, timesPlayed);
				championlist.add(championplayrate);
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
		return championlist;
	}
	
	// delete specified match
	public Matches delete(Matches matchNA1) throws SQLException {
		
		String deleteMatch = "DELETE FROM MatchNA1 WHERE MatchID = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			
			deleteStmt = connection.prepareStatement(deleteMatch);
			deleteStmt.setString(1, matchNA1.getMatchID());
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
