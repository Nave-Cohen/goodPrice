package scraperTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scraper.AbstractScraper;
import scraper.AliScrape;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class AliScrapeTest {
    private AbstractScraper scraper;

    @BeforeEach
    public void setUp() throws IOException {
        String html = "";
        InputStream path = AliScrapeTest.class.getResourceAsStream("/aliExpress.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(path, StandardCharsets.UTF_8));
        String inputLine;
        while ((inputLine = reader.readLine()) != null)
            html += inputLine + reader.readLine() + "\n";
        scraper = new AliScrape(null, html);
    }

    @Test
    public void getPriceSuccess() {
        Double expected = 1.83;
        Double result = scraper.getPrice();
        assertEquals(expected, result);
    }

    @Test
    public void getDiscountSuccess() {
        Integer expected = 89;
        Integer result = scraper.getDiscount();
        assertEquals(expected, result);
    }

    @Test
    public void getDescriptionSuccess() {
        String expected = "Usb Heated Shoe Insoles Electric Foot Warming Pad Feet Warmer Sock Pad Mat Winter Outdoor Sports Heating Insole Winter Warm - Insoles - AliExpress";
        String result = scraper.getDescription();
        assertEquals(expected, result);
    }
    @Test
    public void getImageSuccess() throws IOException {
        BufferedImage expected = ImageIO.read(AliScrapeTest.class.getResourceAsStream("/aliImage.jpeg"));
        BufferedImage result = ImageIO.read(scraper.getImg());
        assertEquals(expected.getWidth(),result.getWidth()); //Compare width.
        assertEquals(expected.getHeight(),result.getHeight());//Compare height.
        assertEquals(expected.getData().getDataBuffer().getSize(),result.getData().getDataBuffer().getSize()); //Compare sizes.
    }
    //Not implemented always return null.
    @Test
    public void getShippingPrice_NULL() throws IOException {
        assertNull(scraper.getShippingPrice());
    }
}
