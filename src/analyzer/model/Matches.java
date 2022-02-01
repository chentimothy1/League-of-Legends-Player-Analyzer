package analyzer.model;

public class Matches {
	
	protected String MatchID;
	protected String AccountID;
	protected int ChampionID;
	protected Role PlayedRole;
    protected Position PlayedPosition;
    
    public enum Role {
		Solo, Duo, Duo_Support, Duo_Carry, None
	}
	
	public enum Position {
		Top, Mid, Bottom, Jungle, None
	}
	
	public Matches(String matchID, String accountID, int championID, Role playedRole, Position playedPosition) {
		MatchID = matchID;
		AccountID = accountID;
		ChampionID = championID;
		PlayedRole = playedRole;
		PlayedPosition = playedPosition;
	}
	
	public Matches(String matchID, int championID, Role playedRole, Position playedPosition) {
		MatchID = matchID;
		ChampionID = championID;
		PlayedRole = playedRole;
		PlayedPosition = playedPosition;
	}

	public String getMatchID() {
		return MatchID;
	}

	public String getAccountID() {
		return AccountID;
	}

	public int getChampionID() {
		return ChampionID;
	}

	public Role getPlayedRole() {
		return PlayedRole;
	}

	public Position getPlayedPosition() {
		return PlayedPosition;
	}

	public void setMatchID(String matchID) {
		MatchID = matchID;
	}

	public void setAccountID(String accountID) {
		AccountID = accountID;
	}

	public void setChampionID(int championID) {
		ChampionID = championID;
	}

	public void setPlayedRole(Role playedRole) {
		PlayedRole = playedRole;
	}

	public void setPlayedPosition(Position playedPosition) {
		PlayedPosition = playedPosition;
	}

	
}
