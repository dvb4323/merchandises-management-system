package application.oversea.model;

public class ListOrderToSiteModel {

	private String listToBeOrderedID ;
	private String siteCode          ;
	private String siteName          ;
	private int    numberOfItem      ;
//	private List<EntityItemOrderToSite> list ;
	
	public String getListToBeOrderedID() {
		return listToBeOrderedID;
	}
	public void setListToBeOrderedID(String listToBeOrderedID) {
		this.listToBeOrderedID = listToBeOrderedID;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public int getNumberOfItem() {
		return numberOfItem;
	}
	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}
//	public List<EntityItemOrderToSite> getListItem() {
//		return list;
//	}
//	public EntityListOrderToSite(String listToBeOrderedID, String siteCode, String siteName, int numberOfItem,
//			List<EntityItemOrderToSite> list) {
//	
//		this.listToBeOrderedID = listToBeOrderedID;
//		this.siteCode = siteCode;
//		this.siteName = siteName;
//		this.numberOfItem = numberOfItem;
//		this.list = list;
//	}
//	
	public ListOrderToSiteModel(String listToBeOrderedID, String siteCode, String siteName, int numberOfItem) {
	
		this.listToBeOrderedID = listToBeOrderedID;
		this.siteCode = siteCode;
		this.siteName = siteName;
		this.numberOfItem = numberOfItem;

	}
}
