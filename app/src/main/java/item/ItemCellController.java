package item;

import goodPrice.Main;
import goodPrice.MainController;
import javafx.fxml.FXML;
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
    private Text price, discount, wishPrice, name, description;

    @FXML
    private ImageView statusImage;

    @FXML
    private Circle circleImage;

    private Item item;

    public void setTemplate(Item item) {
        this.item = item;
        name.setText("Name: " + item.getName());
        description.setText("Description: " + item.getDescription());
        price.setText("Price: " + item.getPrice().toString());
        discount.setText("Discount: " + item.getDiscount().toString() + "%");
        wishPrice.setText("Wish Price: " + item.getMinPrice().toString());
        statusImage.setImage(checkImage());
        circleImage.setFill(new ImagePattern(new Image(item.getImg())));
    }

    @FXML
    public void openUrl() {
        Main.hostServices.showDocument(item.getUrl());
    }

    @FXML
    void delete(MouseEvent event) {
        MainController.remove(item);
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
