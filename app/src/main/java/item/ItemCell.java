package item;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ItemCell extends ListCell<Item> {

	private final HBox root;
	private final ItemCellController controller;

	public ItemCell() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/itemCell2.fxml"));
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
