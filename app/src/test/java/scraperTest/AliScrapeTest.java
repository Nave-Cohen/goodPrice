package scraperTest;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import scraper.AbstractScraper;
import scraper.AliScrape;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


public class AliScrapeTest {
    private AbstractScraper scraper;
    private Connection jsoup;


    private String getHtml(InputStream stream) throws IOException {
        String html = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        String inputLine;
        while ((inputLine = reader.readLine()) != null)
            html += inputLine + reader.readLine() + "\n";
        return html;
    }

    @BeforeEach
    public void setUp() throws IOException {
        InputStream firstStream = AliScrapeTest.class.getResourceAsStream("/aliExpress.html");
        jsoup = Mockito.mock(Connection.class);
        String html = getHtml(firstStream);
        when(jsoup.get()).thenReturn(Jsoup.parse(html));
        scraper = new AliScrape(jsoup, TimeUnit.SECONDS.toMillis(10));
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
        assertEquals(expected.getWidth(), result.getWidth()); //Compare width.
        assertEquals(expected.getHeight(), result.getHeight());//Compare height.
        assertEquals(expected.getData().getDataBuffer().getSize(), result.getData().getDataBuffer().getSize()); //Compare sizes.
    }

    //Not implemented always return null.
    @Test
    public void getShippingPrice_NULL() {
        assertNull(scraper.getShippingPrice());
    }

    @Test
    public void timerTask() throws IOException, InterruptedException {
        InputStream secondStream = AliScrapeTest.class.getResourceAsStream("/aliExpress2.html");
        String html = getHtml(secondStream);
        when(jsoup.get()).thenReturn(Jsoup.parse(html));
        scraper.waitForTask();
        Double expected =  4.83;
        Double result = scraper.getPrice();
        assertEquals(expected,result);
    }
}
