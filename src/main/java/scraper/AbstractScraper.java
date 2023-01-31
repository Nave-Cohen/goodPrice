package scraper;

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


    public AbstractScraper(String url,String html){
            this.doc = Jsoup.parse(html);
    }

    public InputStream getImg(String url) {
        URL imageUrl = null;
        InputStream in = null;
        try {
            imageUrl = new URL(url);
            in = imageUrl.openStream();
        } catch (IOException e) {
            in = getClass().getResourceAsStream("images/default.png");
        }
        return in;
    }
}
