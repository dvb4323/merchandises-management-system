package application.sales.dbsubsystem;

import java.util.List;

import application.sales.model.MerchandiseModel;

public interface ICartDB {
	 public List<MerchandiseModel> getlistItem();
}
