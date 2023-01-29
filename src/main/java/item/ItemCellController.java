package item;

import goodPrice.Main;
import goodPrice.mainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.InputStream;

/**
 * CartCellController class is the controller class for the CartCell FXML file.
 */
public class ItemCellController {

    @FXML
    private Text priceText, discountText, wantedText, nameText, descText;

    @FXML
    private ImageView checkImg, delIcon;
    @FXML
    private Circle img;
    @FXML
    private Button orderBtn;
    private Item item;

    public void setTemplate(Item item) {
        this.item = item;
        nameText.setText("Name: " + item.getName());
        descText.setText("Description: " + item.getDescription());
        priceText.setText("Price: " + item.getPrice().toString());
        discountText.setText("Discount: " + item.getDiscount().toString() + "%");
        wantedText.setText("Wanted Price: " + item.getMinPrice().toString());
        checkImg.setImage(checkImage());
        img.setFill(new ImagePattern(item.getImg()));
    }

    @FXML
    public void openUrl(ActionEvent event) {
        Main.hostServices.showDocument(item.getUrl());
    }

    @FXML
    void delete(MouseEvent event) {
        mainController.remove(item);
    }

    private Image checkImage() {
        if (item.isPrice()) {

            InputStream url = getClass().getResourceAsStream("/images/check.png");
            return new Image(url);
        } else {
            InputStream url = getClass().getResourceAsStream("/images/uncheck.png");
            return new Image(url);
        }
    }
}
