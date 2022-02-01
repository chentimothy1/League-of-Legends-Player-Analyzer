package analyzer.model;

public class SummonerSpell {

	protected String SummonerSpellID;
    protected String SummonerSpellName;
    protected String SummonerSpellDesc;
    
	public SummonerSpell(String summonerSpellID, String summonerSpellName, String summonerSpellDesc) {
		SummonerSpellID = summonerSpellID;
		SummonerSpellName = summonerSpellName;
		SummonerSpellDesc = summonerSpellDesc;
	}
	
	public String getSummonerSpellID() {
		return SummonerSpellID;
	}
	public String getSummonerSpellName() {
		return SummonerSpellName;
	}
	public String getSummonerSpellDesc() {
		return SummonerSpellDesc;
	}
	public void setSummonerSpellID(String summonerSpellID) {
		SummonerSpellID = summonerSpellID;
	}
	public void setSummonerSpellName(String summonerSpellName) {
		SummonerSpellName = summonerSpellName;
	}
	public void setSummonerSpellDesc(String summonerSpellDesc) {
		SummonerSpellDesc = summonerSpellDesc;
	}

	
}
