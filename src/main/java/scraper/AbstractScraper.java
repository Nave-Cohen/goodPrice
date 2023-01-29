package scraper;

import javafx.scene.image.Image;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public abstract class AbstractScraper implements ScraperIF {
    protected Document doc;

    public AbstractScraper(String url) {
        try {
            this.doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Image getImg(String url) {
        URL imageUrl = null;
        InputStream in = null;
        try {
            imageUrl = new URL(url);
            return new Image(imageUrl.openStream());
        } catch (IOException e) {
            in = getClass().getResourceAsStream("images/default.png");
            return new Image(in);
        }
    }
}
