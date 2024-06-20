package application.app.dbsubsystem;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserDB {
	public JSONArray getAllUser() {
		JSONArray res = new JSONArray();
		JSONObject sale = new JSONObject();
		sale.put("role","sale") ;
		sale.put("username","sale") ;
		sale.put("password","123") ;
        res.put(sale);
        
        JSONObject oversea = new JSONObject();
		oversea.put("role","oversea") ;
		oversea.put("username","oversea") ;
		oversea.put("password","123") ;
        res.put(oversea);
        
        JSONObject site = new JSONObject();
		site.put("role","site") ;
		site.put("username","site") ;
		site.put("password","123") ;
        res.put(site);
        
        JSONObject warehouse = new JSONObject();
		warehouse.put("role","warehouse") ;
		warehouse.put("username","warehouse") ;
		warehouse.put("password","123") ;
        res.put(warehouse);
		
        return res;
	}
}
