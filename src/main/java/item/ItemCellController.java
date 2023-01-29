package item;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.InputStream;

/**
 * CartCellController class is the controller class for the CartCell FXML file.
 */
public class ItemCellController {

    @FXML
    private Text priceText, discountText, wantedText,nameText,descText;

    @FXML
    private ImageView img, checkImg;

    private Item item;

    public void setTemplate(Item item) {
        this.item = item;
        nameText.setText("Name: "+item.getName());
        descText.setText("Description: " + item.getDescription());
        priceText.setText("Price: " + item.getPrice().toString());
        discountText.setText("Discount: " + item.getDiscount().toString()+"%");
        wantedText.setText("Wanted Price: " + item.getMinPrice().toString());
        checkImg.setImage(checkImage());
        img.setImage(item.getImg());
    }

    private Image checkImage() {
        if (item.isPrice()) {

            InputStream url = getClass().getResourceAsStream("/check.png");
            return new Image(url);
        } else {
            InputStream url = getClass().getResourceAsStream("/uncheck.png");
            return new Image(url);
        }
    }
}
