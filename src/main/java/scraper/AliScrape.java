package scraper;

import javafx.scene.image.Image;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AliScrape extends AbstractScraper {

    public AliScrape(String name, String url, String type) {
        super(name, url, type);
    }

    private String meta(String key,String value) {return doc.select(String.format("meta[%s=%s]",key,value)).first().attr("content");}

    @Override
    public Double getPrice() {
        String coinSinged = meta("property", "og:title").split(" ")[0];
        return Double.parseDouble(coinSinged.substring(0,coinSinged.length()-1));
    }


    @Override
    public String getDescription() {
        return meta("property", "og:title").split("\\|")[1];
    }

    @Override
    public Integer getDiscount() {
        String precentSigned = meta("property", "og:title").split(" ")[1];
        return Integer.parseInt(precentSigned.substring(0,precentSigned.length()-1));
    }

    @Override
    public Double getShippingPrice() {
        return null;
    }

    @Override
    public Image getImg() {
        return super.getImg(meta("property", "og:image"));
    }
}
