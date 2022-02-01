package analyzer.model;

public class Maps {
	
	protected int MapID;
    protected String MapName;
    protected String MapDesc;
    
	public Maps(int mapID, String mapName, String mapDesc) {
		MapID = mapID;
		MapName = mapName;
		MapDesc = mapDesc;
	}
	
	public int getMapID() {
		return MapID;
	}
	public String getMapName() {
		return MapName;
	}
	public String getMapDesc() {
		return MapDesc;
	}
	public void setMapID(int mapID) {
		MapID = mapID;
	}
	public void setMapName(String mapName) {
		MapName = mapName;
	}
	public void setMapDesc(String mapDesc) {
		MapDesc = mapDesc;
	}
    
	
}
