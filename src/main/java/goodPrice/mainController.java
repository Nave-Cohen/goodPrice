package goodPrice;

import handler.jsonHandler;
import item.Item;
import item.ItemCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class mainController {
    @FXML
    private Button addBtn;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private TextField minPriceEntry, nameEntry, urlEntry;

    @FXML
    private ListView<Item> listview;

    @FXML
    private ImageView priceError, urlError, nameError, comboError;

    private static final ObservableList<Item> items = FXCollections.observableArrayList();
    private final ObservableList<String> options = FXCollections.observableArrayList("AliExpress");

    //load item from Database.
    //save added item to database.
    @FXML
    protected void initialize() {
        listview.setCellFactory(listView -> new ItemCell());
        items.addAll(jsonHandler.readAllItems());
        combo.setItems(options);
        listview.setItems(items);
    }

    public static void remove(Item item) {
        items.remove(item);
        jsonHandler.removeItem(item);
    }

    public void writeAll() {
        jsonHandler.writeAllItems(items);
    }

    private Boolean checkInput() {
        boolean bool = true;
        try {
            Double.parseDouble(minPriceEntry.getText());
        } catch (Exception e) {
            priceError.setVisible(true);
            bool = false;
        }
        if (nameEntry.getText().isEmpty()) {
            nameError.setVisible(true);
            bool = false;
        }
        if (urlEntry.getText().isEmpty() || !urlEntry.getText().startsWith("https://") || !urlEntry.getText().contains("aliexpress.com/item")) {
            urlError.setVisible(true);
            bool = false;
        }

        if (combo.getSelectionModel().getSelectedIndex() == -1 || !urlEntry.getText().toLowerCase().contains(combo.getSelectionModel().getSelectedItem().toLowerCase())) {
            comboError.setVisible(true);
            bool = false;
        }
        return bool;
    }

    @FXML
    public void add(ActionEvent event) {
        clearErrors();
        if (!checkInput())
            return;
        String name = nameEntry.getText();
        String url = urlEntry.getText();
        Double minPrice = Double.parseDouble(minPriceEntry.getText());
        String type = combo.getSelectionModel().getSelectedItem();
        Item item = new Item(name, url, minPrice, type);
        if (!items.contains(item))
            items.add(item);
        clearFields();
    }

    private void clearFields() {
        nameEntry.clear();
        urlEntry.clear();
        minPriceEntry.clear();
        combo.setValue("Choose Site");
    }

    private void clearErrors() {
        nameError.setVisible(false);
        urlError.setVisible(false);
        comboError.setVisible(false);
        priceError.setVisible(false);
    }


}

