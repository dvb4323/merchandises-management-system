package application;


import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;

public abstract class APageForm {
	protected Parent root ;
	@FXML
	protected VBox listItem;
	@FXML
    private VBox vpage;
	private int currentPage = 0;
    private int totalPages ;
    private int itemsPerPage ;
    private int datasize ;

    public VBox getListItem() {
		return listItem;
	}
    
	public void addPaginationHandle(int datasize, int itemsPerPage) {
		  this.itemsPerPage = itemsPerPage ;
		  this.datasize     = datasize ;
		  int k = (datasize > itemsPerPage ) ? itemsPerPage : datasize  ;
		   for(int i = 0; i< k ; i++) {
			   listItem.getChildren().get(i).setVisible(true);
			   listItem.getChildren().get(i).setManaged(true);  
			}
		   
		   totalPages = (datasize + itemsPerPage - 1) / itemsPerPage;
		   
		   Pagination pagination = new Pagination();
	       pagination.setPageCount(totalPages); 
		   vpage.getChildren().add(pagination);
		   pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> {
	            updatePageContent((int) newValue);
	        });
	}
	
	public void resetPagination() {
		vpage.getChildren().clear();
	}
	
	private void updatePageContent(int currentPage) {
		 int fromIndex = currentPage * itemsPerPage;
         int toIndex = Math.min(fromIndex + itemsPerPage, datasize);
         for (int i = 0; i < datasize; i++) {
             if (i >= fromIndex && i < toIndex) {
           	  listItem.getChildren().get(i).setVisible(true);
           	  listItem.getChildren().get(i).setManaged(true);
             } else {
           	  listItem.getChildren().get(i).setVisible(false);
           	  listItem.getChildren().get(i).setManaged(false);
             }
         }
	}
}
