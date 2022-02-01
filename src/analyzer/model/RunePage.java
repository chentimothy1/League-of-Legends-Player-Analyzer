package analyzer.model;

public class RunePage {
	
	protected int RunePageID;
    protected String RunePageName;
    protected String RunePath;
    protected String RunePageKey;
    protected String RunePageDesc;
    
	public RunePage(int runePageID, String runePageName, String runePath, String runePageKey, String runePageDesc) {
		RunePageID = runePageID;
		RunePageName = runePageName;
		RunePath = runePath;
		RunePageKey = runePageKey;
		RunePageDesc = runePageDesc;
	}
    
	public RunePage(int runePageID, String runePageName, String runePath, String runePageDesc) {
		RunePageID = runePageID;
		RunePageName = runePageName;
		RunePath = runePath;
		RunePageDesc = runePageDesc;
	}
	
	public String getRunePath() {
		return RunePath;
	}

	public String getRunePageKey() {
		return RunePageKey;
	}

	public int getRunePageID() {
		return RunePageID;
	}
	
	public String getRunePageName() {
		return RunePageName;
	}
	
	public String getRunePageDesc() {
		return RunePageDesc;
	}
	
	public void setRunePageID(int runePageID) {
		RunePageID = runePageID;
	}
	
	public void setRunePageName(String runePageName) {
		RunePageName = runePageName;
	}
	
	public void setRunePageDesc(String runePageDesc) {
		RunePageDesc = runePageDesc;
	}

	public void setRunePath(String runePath) {
		RunePath = runePath;
	}

	public void setRunePageKey(String runePageKey) {
		RunePageKey = runePageKey;
	}
    
}
