package scraper;

import javafx.scene.image.Image;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileInputStream;

public interface ScraperIF {
    public String getType();

    public String getUrl();

    public Double getPrice();

    public String getName();
    public void setName(String name);

    public String getDescription();

    public Integer getDiscount();

    public Double getShippingPrice();

    public Image getImg();
}
