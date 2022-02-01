package analyzer.model;

import analyzer.model.Matches.Position;
import analyzer.model.Matches.Role;

public class ChampionPlayRate {
	
	protected String ChampionName;
	protected int TimesPlayed;

	public ChampionPlayRate(String championName, int timesPlayed) {
		ChampionName = championName;
		TimesPlayed = timesPlayed;
	}

	public String getChampionName() {
		return ChampionName;
	}

	public int getTimesPlayed() {
		return TimesPlayed;
	}

	public void setChampionName(String championName) {
		ChampionName = championName;
	}

	public void setTimesPlayed(int timesPlayed) {
		TimesPlayed = timesPlayed;
	}

}
