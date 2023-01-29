package goodPrice;

import handler.jsonHandler;
import item.Item;
import item.ItemCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class mainController {

    @FXML
    private ListView<Item> listview;
    private static ObservableList<Item> items = FXCollections.observableArrayList();

    //load item from Database.
    //save added item to database.
    @FXML
    protected void initialize() {
        listview.setCellFactory(listView -> new ItemCell());
        items.addAll(jsonHandler.readAllItems());
        listview.setItems(items);
    }

    public static void remove(Item item) {
        items.remove(item);
        jsonHandler.removeItem(item);
    }

    public void writeAll() {
        jsonHandler.writeAllItems(items);
    }


}

