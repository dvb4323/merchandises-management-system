package application.oversea.page.createOrder;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import application.oversea.page.AOverseaPageForm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class CreateOrderSingleForm extends AOverseaPageForm{
	
		@FXML
	    private Label siteID;	
		@FXML
	    private Label siteName;
		@FXML
	    private Label numberOfItem;
		
		
		public Parent getRoot() {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/ViewProcessOrderSingleForm.fxml"));
				loader.setController(this); 
				root = loader.load();
//				listItem.setFillWidth(true) ;
				root.setVisible(false);
				root.setManaged(false);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return root;
		}
		 public void disPlayData(String... strings) {
		    	siteID.setText(strings[0]);
				siteName.setText(strings[1]);
				numberOfItem.setText(strings[2]);
		 }
		 
		public void displayForm(JSONObject a,JSONArray list)  {
			  siteID.setText(a.getString("siteCode")) ;
//			  siteName.setText(a.getSiteName());
			  siteName.setText(a.getString("siteName"));
			  numberOfItem.setText(String.valueOf(a.getInt("numberOfItem")));
			  
			  for(int i = 0; i < list.length() ;i++) {	
					JSONObject jsonObject = list.getJSONObject(i);	
					
					ItemOrderSingleForm form = new ItemOrderSingleForm();
					listItem.getChildren().add(form.getRoot());
					System.out.println(jsonObject);
					form.disPlayData(String.valueOf(i+1),
							jsonObject.getString("merchandiseCode"),
							jsonObject.getString("merchandiseCode"),
							String.valueOf(jsonObject.getInt("quantity")),
							"cái",
//							jsonObject.getString("unit"),
							jsonObject.getString("mean")
									);
				
					
		       } ;
		       addPaginationHandle(list.length(), 2);
		}
		
}
