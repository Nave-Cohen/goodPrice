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
    private Timer timer = new Timer();
    protected Document doc;
    private Object monitor = new Object();
    private Long waitTime = TimeUnit.MINUTES.toMillis(1);
    private Connection jsoup;

    public AbstractScraper(String url) {
        jsoup = Jsoup.connect(url);
        doc = getDoc();
    }


    public AbstractScraper(Connection jsoup, Long waitTime) {
        this.jsoup = jsoup;
        this.waitTime = waitTime;
        doc = getDoc();
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

    private Document getDoc() {
        try {
            timer.schedule(createTask(), waitTime);
            return jsoup.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //for testing.
    public void waitForTask() {
        try {
            synchronized (monitor) {
                monitor.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private TimerTask createTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                doc = getDoc();
                synchronized (monitor) {
                    monitor.notify();
                }
            }
        };
        return task;
    }
}
