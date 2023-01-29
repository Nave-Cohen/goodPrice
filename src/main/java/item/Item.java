package item;

import javafx.scene.image.Image;
import scraper.AbstractScraper;
import scraper.AliScrape;
import scraper.ScraperIF;

import java.io.FileInputStream;

public class Item implements ScraperIF {
    private String name, url, type;
    private Double price, minPrice;
    private AbstractScraper scraper;

    public Item(String name, String url, Double price, String type) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.minPrice = price;
        if (type == "ali")
            scraper = new AliScrape(name, url, type);
    }

    public void setMinPrice(Double minPrice) {
        if (minPrice < 0)
            throw new RuntimeException("Min price illegal");
        this.minPrice = minPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Boolean isPrice() {
        return scraper.getPrice() <= minPrice;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Double getPrice() {
        return scraper.getPrice();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        scraper.setName(name);
    }

    public String getDescription() {
        return scraper.getDescription();
    }

    @Override
    public Integer getDiscount() {
        return scraper.getDiscount();
    }

    @Override
    public Double getShippingPrice() {
        return scraper.getShippingPrice();
    }

    @Override
    public Image getImg() {
        return scraper.getImg();
    }
}
