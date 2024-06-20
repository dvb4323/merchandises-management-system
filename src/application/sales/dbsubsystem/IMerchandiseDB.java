package application.sales.dbsubsystem;
import java.util.List;

import application.sales.model.MerchandiseModel;

public interface IMerchandiseDB {
	public List<MerchandiseModel> getlistItem();
}
