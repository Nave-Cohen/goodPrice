package scraper;

import javafx.scene.image.Image;

import java.io.InputStream;

public interface ScraperIF {

    public Double getPrice();

    public String getDescription();

    public Integer getDiscount();

    public Double getShippingPrice();

    public InputStream getImg();
}
