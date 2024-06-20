package application.sales.dbsubsystem;
import java.util.List;

import application.sales.model.DetailMerchandiseModel;
public interface IDetailMerchandiseDB {
   public List<DetailMerchandiseModel> getdetailListItem();
   public void removeFromCart(String id);
   public void editItem(String id, String date, int quantity) ;
}
