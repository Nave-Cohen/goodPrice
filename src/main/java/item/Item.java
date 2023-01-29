package item;

import javafx.scene.image.Image;
import scraper.AbstractScraper;
import scraper.AliScrape;

import java.util.Objects;

public class Item implements ItemIF {
    private String name;
    private final String url;
    private final String type;
    private Double price, minPrice;
    private AbstractScraper scraper;


    public Item(String name, String url, Double price, String type) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.minPrice = price;
        if (type.equals("AliExpress"))
            scraper = new AliScrape(url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return url.equals(item.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public void setMinPrice(Double minPrice) {
        if (minPrice < 0)
            throw new RuntimeException("Min price illegal");
        this.minPrice = minPrice;
    }
    @Override
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
    }

    @Override
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
