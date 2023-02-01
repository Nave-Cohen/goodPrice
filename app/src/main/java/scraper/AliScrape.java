package scraper;

import org.jsoup.Connection;

import java.io.InputStream;

public class AliScrape extends AbstractScraper {

    public AliScrape(String url) {
        super(url);
    }
    //Constructor for testing, html - html string to parse.
    public AliScrape(Connection jsoup,Long waitTime) {
        super(jsoup,waitTime);
    }

    private String meta(String value) {return doc.select(String.format("meta[property=%s]" ,value)).first().attr("content");}

    @Override
    public Double getPrice() {
        String coinSinged = meta("og:title").split(" ")[0];
        return Double.parseDouble(coinSinged.substring(0,coinSinged.length()-1));
    }


    @Override
    public String getDescription() {
        return meta("og:title").split("\\|")[1];
    }

    @Override
    public Integer getDiscount() {
        String percentSigned = meta("og:title").split(" ")[1];
        return Integer.parseInt(percentSigned.substring(0,percentSigned.length()-1));
    }

    @Override
    public Double getShippingPrice() {
        return null;
    }

    @Override
    public InputStream getImg() {
        return super.getImg(meta("og:image"));
    }
}
