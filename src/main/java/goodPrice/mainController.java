package goodPrice;

import item.Item;
import item.ItemCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class mainController {

    @FXML
    private ListView<Item> listview;
    private ObservableList<Item> items = FXCollections.observableArrayList();

    @FXML
    protected void initialize() {
        listview.setCellFactory(listView -> new ItemCell());
        items.add(new Item("fas","https://he.aliexpress.com/item/1005005152222304.html?spm=a2g0o.productlist.main.17.4160lbeUlbeUSl&algo_pvid=5148f080-40e6-48e3-bf56-977531244085&algo_exp_id=5148f080-40e6-48e3-bf56-977531244085-8&pdp_ext_f=%7B%22sku_id%22%3A%2212000031882799243%22%7D&pdp_npi=2%40dis%21ILS%2124.99%2117.47%21%21%21%21%21%402100b78b16747461137428105d06fd%2112000031882799243%21sea&curPageLogUid=CsExns9jdu4Z",43.3,"ali"));

        listview.setItems(items);
    }
}
