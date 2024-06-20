package application.app.service;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import application.app.dbsubsystem.UserDB;

public class SigninService {
	 private UserDB db ;
	 private JSONArray data ;
	 
	 
	 public SigninService () {
		 this.db = new UserDB();
	 }
	 public int checkLogin(String name , String password) {
		data = db.getAllUser();
		Map<String, Map<String, String>> loginMap = new HashMap<>();
        for (int i = 0; i < data.length(); i++) {
            JSONObject jsonObject = data.getJSONObject(i);
            String usernameCheck = jsonObject.getString("username");
            String passwordCheck = jsonObject.getString("password");
            String role = jsonObject.getString("role");

            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("password", passwordCheck);
            userInfo.put("role", role);
            loginMap.put(usernameCheck, userInfo);
        }

        if (loginMap.containsKey(name)) {
            Map<String, String> userInfo = loginMap.get(name);
            if (userInfo.get("password").equals(password)) {
                String role = userInfo.get("role");
                switch (role) {
                    case "sale":
                        return 1;
                    case "oversea":
                        return 2;
                    case "site":
                        return 3;
                    case "warehouse":
                        return 4;
                   
                }
            }
            return -1;
        }
        return -1;
	}
	 
	 
	 
}
