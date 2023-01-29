package scraper;

import javafx.scene.image.Image;

public interface ScraperIF {

    public Double getPrice();

    public String getDescription();

    public Integer getDiscount();

    public Double getShippingPrice();

    public Image getImg();
}
