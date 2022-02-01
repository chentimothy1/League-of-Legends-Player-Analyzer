package analyzer.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import analyzer.model.*;

public class TopTenDao {
	
	public ConnectionManager connectionManager;
	private static TopTenDao instance = null;
	
	protected TopTenDao () {
		connectionManager = new ConnectionManager();
	}
	
	public static TopTenDao getInstance() {
		if(instance == null) {
			instance = new TopTenDao();
		}
		return instance;
	}
	
	// get top ten summoners with highest win rates
	public List<TopTen> topTenWin (String region) throws SQLException {
		
		List <TopTen> topTen = new ArrayList<TopTen>();
		String selectTopTen = "SELECT `Summoner`.`SummonerName`, `Wins`/(`Wins` + `losses`) AS `WinRate`, `Wins`,`losses` " + 
							"FROM `Summoner` INNER JOIN `Accounts` ON `Summoner`.`SummonerID` = `Accounts`.`SummonerID` " + 
							"WHERE `Region` = ? " + 
							"ORDER BY `Wins`/(`Wins` + `losses`) DESC " + 
							"LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTopTen);
			selectStmt.setString(1, region);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				String summonername = results.getString("SummonerName");
				double winrate = results.getDouble("WinRate");
				int wins = results.getInt("Wins");
				int losses = results.getInt("losses");
				
				TopTen summonerInfo = new TopTen (summonername, winrate, wins, losses);
				topTen.add(summonerInfo);
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
		
		return topTen;
		
	}
	
	// get top ten summoners who played specific role the most often
		public String[][] topTenRole (String region) throws SQLException {
			String[][] topTen = new String[10][5];
			String selectTopTen = "SELECT `SummonerName`, Count(*) AS Count " + 
					"		FROM (SELECT `SummonerID`, `PlayedRole` " + 
					"			FROM `Match"+region+"` INNER JOIN `Accounts` ON `Match"+region+"`.`AccountID` = `Accounts`.`AccountID` " + 
					"		 	) AS Position " + 
					"		 INNER JOIN `Summoner` ON `Summoner`.`SummonerID` = `Position`.`SummonerID` " + 
					"        WHERE `PlayedRole` = ? " + 
					"        GROUP BY `Position`.`SummonerID` " + 
					"        ORDER BY Count Desc\n" + 
					"    LIMIT 10;";
			String[] positions = {"Solo","Duo","Duo_Support","Duo_Carry","None"};
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectTopTen);
				
				for(int i=0; i<positions.length; i++) {
					selectStmt.setString(1, positions[i]);
					results = selectStmt.executeQuery();
				
					int j = 0;
					while(results.next()) {
						String summonerName = results.getString("SummonerName");
						topTen[j][i] = summonerName;
						j++;
					}
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
			
			return topTen;
			
		}
	
	// get top ten summoners who played specific position the most often
	public String[][] topTenPosition (String region) throws SQLException {
		String[][] topTen = new String[10][5];
		String selectTopTen = "SELECT `SummonerName`, Count(*) AS Count " + 
				"		FROM (SELECT `SummonerID`, `PlayedPosition` " + 
				"			FROM `Match"+region+"` INNER JOIN `Accounts` ON `Match"+region+"`.`AccountID` = `Accounts`.`AccountID` " + 
				"		 	) AS Position " + 
				"		 INNER JOIN `Summoner` ON `Summoner`.`SummonerID` = `Position`.`SummonerID` " + 
				"        WHERE `PlayedPosition` = ? " + 
				"        GROUP BY `Position`.`SummonerID` " + 
				"        ORDER BY Count Desc\n" + 
				"    LIMIT 10;";
		String[] positions = {"Top","Mid","Bottom","Jungle","None"};
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTopTen);
			
			for(int i=0; i<positions.length; i++) {
				selectStmt.setString(1, positions[i]);
				results = selectStmt.executeQuery();
			
				int j = 0;
				while(results.next()) {
					String summonerName = results.getString("SummonerName");
					topTen[j][i] = summonerName;
					j++;
				}
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
		
		return topTen;
		
	}
	
	// get top ten players for specific champion
	public List<TopTen> topTenPlayer (String champion) throws SQLException {
		
		List <TopTen> topTen = new ArrayList<TopTen>();
		String selectTopTen = "SELECT `SummonerName`, Count " + 
							"FROM (SELECT `AccountID`, Count(*) AS Count " + 
									"FROM (SELECT * FROM `MatchNA1` " + 
										"UNION " + 
									"SELECT * FROM `MatchEUW1` " + 
										"UNION " + 
									"SELECT * FROM `MatchEUN1`) AS `AllMatch` " + 
								"INNER JOIN `LOLChampions` ON `AllMatch`.`ChampionID` = `LOLChampions`.`ChampionID` " + 
								"WHERE `ChampionName` = ? " + 
								"GROUP BY `AccountID`) AS `TopPlayers` " + 
							"INNER JOIN `Accounts` ON `TopPlayers`.`AccountID` = `Accounts`.`AccountID` " + 
							"INNER JOIN `Summoner` ON `Summoner`.`SummonerID` = `Accounts`.`SummonerID` " + 
							"ORDER BY Count DESC " + 
							"LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTopTen);
			selectStmt.setString(1, champion);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				String summonername = results.getString("SummonerName");
				int count = results.getInt("Count");
				
				TopTen summonerInfo = new TopTen (summonername, count);
				topTen.add(summonerInfo);
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
		
		return topTen;
		
	}

}
