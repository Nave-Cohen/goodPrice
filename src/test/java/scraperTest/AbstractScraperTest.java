package scraperTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scraper.AbstractScraper;
import scraper.AliScrape;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractScraperTest {
    private AbstractScraper scraper;

    @BeforeEach
    public void setUp() throws IOException {
        String html = "";
        InputStream path = AbstractScraperTest.class.getResourceAsStream("/aliExpress.html");
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
    public void getDiscountSuccess(){
        Integer expected = 89;
        Integer result = scraper.getDiscount();
        assertEquals(expected, result);
    }
    @Test
    public void getDescriptionSuccess() {
        String expected = "Usb Heated Shoe Insoles Electric Foot Warming Pad Feet Warmer Sock Pad Mat Winter Outdoor Sports Heating Insole Winter Warm - Insoles - AliExpress";
        String result = scraper.getDescription();
        assertEquals(expected,result);
    }
}
