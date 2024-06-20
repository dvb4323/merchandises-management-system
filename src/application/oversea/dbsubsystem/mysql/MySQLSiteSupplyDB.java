package application.oversea.dbsubsystem.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import application.MySqlDB;
import application.oversea.dbsubsystem.ISiteSupplyDB;
import application.oversea.model.SiteSupplyModel;

public class MySQLSiteSupplyDB extends MySqlDB implements  ISiteSupplyDB {

	@Override
	public List<SiteSupplyModel> findSite( String merchandiseCode) {
		if     (merchandiseCode == null)    return null;
		else if(merchandiseCode == "")      return null ;
		List<SiteSupplyModel> list = getSiteSupply( merchandiseCode);
		list.sort(new Comparator<SiteSupplyModel>() {
			@Override
			public int compare(SiteSupplyModel site1, SiteSupplyModel site2) {
				 int shipDeliveryDayComparison = Integer.compare(site1.getShipDeliveryDay(), site2.getShipDeliveryDay());
			     if (shipDeliveryDayComparison != 0) {
			            return shipDeliveryDayComparison;
			      }
			     int airDeliveryDayComparison = Integer.compare(site1.getAirDeliveryDay(), site2.getAirDeliveryDay());
			     if (airDeliveryDayComparison != 0) {
			            return airDeliveryDayComparison;
			      }

			        // Ưu tiên Site có lượng hàng trong kho lớn hơn
			     return Integer.compare(site2.getInStockQuantity(), site1.getInStockQuantity());
			}
			
		}); 
//		list.sort(Comparator.comparing(
//			    SiteSupplyModel::getShipDeliveryDay).thenComparing(SiteSupplyModel::getAirDeliveryDay)
//			    .thenComparing(SiteSupplyModel::getInStockQuantity, Comparator.reverseOrder()));
		return list ;
	}
	
	public List<SiteSupplyModel> getSiteSupply( String merchandiseCode){
		List<SiteSupplyModel> list = new ArrayList<SiteSupplyModel>() ;
		try {
			   String sql= ("select * from SiteSupply where merchandiseCode = ? ;");		   
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setString(1,merchandiseCode);
		       ResultSet rs = st.executeQuery();
		       while (rs.next()) {	
		    	    String siteCode            = rs.getString("siteCode");	
//		    	    String merchandiseCode	   = rs.getString("merchandiseCode") ;
//		    	    String siteName            = rs.getString("siteName");
		            int inStockQuantity        = rs.getInt("inStockQuantity");								
		            String unit 			   = getMerchandiseUnit(merchandiseCode);
		            int shipDeliveryDay       = rs.getInt("shipDeliveryDay");
		            int airDeliveryDay        = rs.getInt("airDeliveryDay");
		            SiteSupplyModel x         = new SiteSupplyModel(siteCode,merchandiseCode,inStockQuantity,unit,shipDeliveryDay,airDeliveryDay ) ;
		            list.add(x);
		        }
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		return list;
	}
	
	private String getMerchandiseUnit(String id) {
		String unit ="" ;
		try {
			 String sql   = ("select * from Merchandise WHERE merchandiseCode = ? ");	
			 PreparedStatement	st = connection.prepareStatement(sql);
			 st.setString(1,id);
			 ResultSet rs = st.executeQuery(); 
			 if(rs.next()) {
				 unit = rs.getString("unit") ;
			 }
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return unit;
	}
	
	private String getSiteName(String siteCode) {
		String name ="" ;
		try {
			 String sql   = ("select * from Site siteCode = ? ");	
			 PreparedStatement	st = connection.prepareStatement(sql);
			 st.setString(1,siteCode);
			 ResultSet rs = st.executeQuery(); 
			 if(rs.next()) {
				 name = rs.getString("siteName") ;
			 }
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return name;
	}

}
