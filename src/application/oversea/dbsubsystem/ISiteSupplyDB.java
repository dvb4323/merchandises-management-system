package application.oversea.dbsubsystem;

import java.util.List;

import application.oversea.model.SiteSupplyModel;

public interface ISiteSupplyDB {
	public List<SiteSupplyModel> findSite( String merchandiseCode) ;

	List<SiteSupplyModel> getSiteSupply(String code);
	
	
}
