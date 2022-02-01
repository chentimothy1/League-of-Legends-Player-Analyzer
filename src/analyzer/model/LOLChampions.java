package analyzer.model;

public class LOLChampions {
	
	protected int ChampionID;
	protected String ChampionName;
	protected String ChampionTitle;
	protected String ChampionBlub;
	protected double ChampionHP;
	protected int ChampionAttack;
	protected int ChampionDefense;
	protected int ChampionMagic;
	
	public LOLChampions(int championID, String championName, String championTitle, String championBlub,
			double championHP, int championAttack, int championDefense, int championMagic) {
		ChampionID = championID;
		ChampionName = championName;
		ChampionTitle = championTitle;
		ChampionBlub = championBlub;
		ChampionHP = championHP;
		ChampionAttack = championAttack;
		ChampionDefense = championDefense;
		ChampionMagic = championMagic;
	}
	
	public int getChampionID() {
		return ChampionID;
	}
	public String getChampionName() {
		return ChampionName;
	}
	public String getChampionTitle() {
		return ChampionTitle;
	}
	public String getChampionBlub() {
		return ChampionBlub;
	}
	public double getChampionHP() {
		return ChampionHP;
	}
	public int getChampionAttack() {
		return ChampionAttack;
	}
	public int getChampionDefense() {
		return ChampionDefense;
	}
	public int getChampionMagic() {
		return ChampionMagic;
	}
	public void setChampionID(int championID) {
		ChampionID = championID;
	}
	public void setChampionName(String championName) {
		ChampionName = championName;
	}
	public void setChampionTitle(String championTitle) {
		ChampionTitle = championTitle;
	}
	public void setChampionBlub(String championBlub) {
		ChampionBlub = championBlub;
	}
	public void setChampionHP(double championHP) {
		ChampionHP = championHP;
	}
	public void setChampionAttack(int championAttack) {
		ChampionAttack = championAttack;
	}
	public void setChampionDefense(int championDefense) {
		ChampionDefense = championDefense;
	}
	public void setChampionMagic(int championMagic) {
		ChampionMagic = championMagic;
	}

}
