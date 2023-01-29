package scraper;

import javafx.scene.image.Image;

public class AliScrape extends AbstractScraper {

    public AliScrape(String url) {
        super(url);
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
        String precentSigned = meta("og:title").split(" ")[1];
        return Integer.parseInt(precentSigned.substring(0,precentSigned.length()-1));
    }

    @Override
    public Double getShippingPrice() {
        return null;
    }

    @Override
    public Image getImg() {
        return super.getImg(meta("og:image"));
    }
}
