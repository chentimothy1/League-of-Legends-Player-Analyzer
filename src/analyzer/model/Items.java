package analyzer.model;

public class Items {

	protected int ItemID;
	protected String ItemName;
	protected String ItemDesc;
	protected String ItemBlurb;
	protected int ItemPrice;
	protected int ItemSell;
	
	public Items(int itemID, String itemName, String itemDesc, String itemBlurb, int itemPrice, int itemSell) {
		ItemID = itemID;
		ItemName = itemName;
		ItemDesc = itemDesc;
		ItemBlurb = itemBlurb;
		ItemPrice = itemPrice;
		ItemSell = itemSell;
	}
	
	public int getItemID() {
		return ItemID;
	}
	public String getItemName() {
		return ItemName;
	}
	public String getItemDesc() {
		return ItemDesc;
	}
	public String getItemBlurb() {
		return ItemBlurb;
	}
	public int getItemPrice() {
		return ItemPrice;
	}
	public int getItemSell() {
		return ItemSell;
	}
	public void setItemID(int itemID) {
		ItemID = itemID;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public void setItemDesc(String itemDesc) {
		ItemDesc = itemDesc;
	}
	public void setItemBlurb(String itemBlurb) {
		ItemBlurb = itemBlurb;
	}
	public void setItemPrice(int itemPrice) {
		ItemPrice = itemPrice;
	}
	public void setItemSell(int itemSell) {
		ItemSell = itemSell;
	}
	
}
