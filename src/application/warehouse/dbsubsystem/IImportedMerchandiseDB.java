package application.warehouse.dbsubsystem;

import java.util.List;

import application.warehouse.model.ImportedMerchandiseModel;

public interface IImportedMerchandiseDB {
	public List<ImportedMerchandiseModel> getAllItems(String merchandiseCode);
	public boolean updateQuantity(String merchandiseCode ,int receivedNumberOfItem);
}
