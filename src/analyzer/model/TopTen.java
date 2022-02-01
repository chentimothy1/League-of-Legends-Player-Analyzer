package analyzer.model;

public class TopTen {
	
	protected String SummonerName;
	protected Double WinRate;
	protected Integer Wins;
	protected Integer Losses;
	protected Integer Count;

	public TopTen(String summonerName, double winRate, int wins, int losses) {
		SummonerName = summonerName;
		WinRate = winRate;
		Wins = wins;
		Losses = losses;
	}

	public TopTen(String summonerName, Integer count) {
		super();
		SummonerName = summonerName;
		Count = count;
	}

	public TopTen(String summonerName) {
		SummonerName = summonerName;
		WinRate = null;
		Wins = null;
		Losses = null;
	}

	public String getSummonerName() {
		return SummonerName;
	}
	
	public double getWinRate() {
		return WinRate;
	}
	
	public int getWins() {
		return Wins;
	}
	
	public int getLosses() {
		return Losses;
	}
	
	public int getCount() {
		return Count;
	}
	
	public void setSummonerName(String summonerName) {
		SummonerName = summonerName;
	}
	
	public void setWinRate(double winRate) {
		WinRate = winRate;
	}
	
	public void setWins(int wins) {
		Wins = wins;
	}
	
	public void setLosses(int losses) {
		Losses = losses;
	}
	
	public void setCount(int count) {
		Count = count;
	}

}
