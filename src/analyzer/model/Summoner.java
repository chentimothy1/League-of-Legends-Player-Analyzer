package analyzer.model;

public class Summoner {
	
	protected String SummonerID;
    protected String SummonerName;
    protected int Wins;
    protected int Losses;
    
	public Summoner(String summonerID, String summonerName, int wins, int losses) {
		SummonerID = summonerID;
		SummonerName = summonerName;
		Wins = wins;
		Losses = losses;
	}
	
	public Summoner (String summonerName) {
		SummonerName = summonerName;
	}
    
	public String getSummonerID() {
		return SummonerID;
	}
	
	public String getSummonerName() {
		return SummonerName;
	}
	public int getWins() {
		return Wins;
	}
	public int getLosses() {
		return Losses;
	}
	public void setSummonerID(String summonerID) {
		SummonerID = summonerID;
	}
	public void setSummonerName(String summonerName) {
		SummonerName = summonerName;
	}
	public void setWins(int wins) {
		Wins = wins;
	}
	public void setLosses(int losses) {
		Losses = losses;
	}
	
	public double getWinrate() {
		return Math.round((1.0 * Wins)/(Wins + Losses) * 100.0)/100.0;
	}
	
	public int getTotalMatches() {
		return Wins + Losses;
	}
    
    

}
