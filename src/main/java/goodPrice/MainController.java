package goodPrice;

import handler.JsonHandler;
import item.Item;
import item.ItemCell;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class MainController {
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

    private JsonHandler jHandler = new JsonHandler();
    private static final ObservableList<Item> items = FXCollections.observableArrayList();
    private final ObservableList<String> options = FXCollections.observableArrayList("AliExpress");

    @FXML
    protected void initialize() throws Exception {
        listview.setCellFactory(listView -> new ItemCell());
        items.addAll(jHandler.readItems());
        items.addListener((ListChangeListener<Item>) c -> {
            while (c.next())
                for (Item removedItem : c.getRemoved())
                    jHandler.removeItem(removedItem);
        });
        combo.setItems(options);
        listview.setItems(items);
    }

    public static void remove(Item item) {
        items.remove(item);
    }
    public void writeItems(){
        jHandler.writeItems();
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
        if (!items.contains(item)) {
            items.add(item);
            jHandler.addItem(item);
        }
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

