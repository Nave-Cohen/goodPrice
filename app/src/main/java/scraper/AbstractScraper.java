package scraper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public abstract class AbstractScraper implements ScraperIF {
    private Timer timer = new Timer(true);
    protected Document doc;
    public Object monitor = new Object();
    private Connection jsoup;

    public AbstractScraper(String url) {
        jsoup = Jsoup.connect(url);
        timer.schedule(new FetchTask(), 0, TimeUnit.MINUTES.toMillis(1));
    }

    //for testing.
    public AbstractScraper(Connection jsoup, Long waitTime) {
        this.jsoup = jsoup;
        timer.schedule(new FetchTask(), waitTime);
    }

    private Document getHtmlDoc() {
        try {
            return jsoup.get();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public InputStream getImg(String url) {
        try {
            URL imageUrl = new URL(url);
            return imageUrl.openStream();
        } catch (IOException e) {
            return getClass().getResourceAsStream("images/default.png");
        }
    }

    //fetching new doc from connection every few minutes
    public class FetchTask extends TimerTask {

        public FetchTask() {
            doc = getHtmlDoc();
        }

        @Override
        public void run() {
            doc = getHtmlDoc();
            synchronized (monitor) {
                monitor.notify();
            }
        }
    }
}
