package item;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

/**
 * CartCell is a controller class for an order cart view.
 * It displays OrderProduct information.
 * It extends ListCell class and overrides methods of it.
 */
public class ItemCell extends ListCell<Item> {

	private AnchorPane root;
	private ItemCellController controller;

	/**
	 * Constructor for loading FXML file "CartProduct".
	 */
	public ItemCell() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemCell.fxml"));
			root = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	protected void updateItem(Item item, boolean empty) {
		super.updateItem(item, empty);

		if (empty || item == null) {
			setGraphic(null);
		} else {
			controller.setTemplate(item);
			setGraphic(root);
		}
	}
}
