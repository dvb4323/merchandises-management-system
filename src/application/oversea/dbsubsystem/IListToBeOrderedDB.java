package application.oversea.dbsubsystem;

import java.util.List;
import application.oversea.model.ItemToBeOrderedModel;
import application.oversea.model.ListToBeOrderedModel;

public interface IListToBeOrderedDB {
	public List<ListToBeOrderedModel> getListToBeOrdered(String listToBeOrderedID, String status);
	public List<ItemToBeOrderedModel> getListToBeOrderedItem(String orderId);
	public String getListToBeOrderedStatus(String orderId);	

}
