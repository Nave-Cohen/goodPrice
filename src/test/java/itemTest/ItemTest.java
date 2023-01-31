package itemTest;

import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import scraper.AbstractScraper;
import scraperTest.AliScrapeTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemTest {
    private AbstractScraper scraper;
    private Item item;

    @BeforeEach
    public void setUp() {
        scraper = Mockito.mock(AbstractScraper.class);
        item = new Item("test-name", "test-url", 12.0, "test-type", scraper);
    }

    @Test
    public void getPriceSuccess() {
        Double expected = 15.0;
        when(scraper.getPrice()).thenReturn(expected);
        Double result = item.getPrice();
        verify(scraper).getPrice();
        assertEquals(expected, result);
    }

    @Test
    public void getDescriptionSuccess() {
        String expected = "this is description";
        when(scraper.getDescription()).thenReturn(expected);
        String result = item.getDescription();
        verify(scraper).getDescription();
        assertEquals(expected, result);
    }

    @Test
    public void getDiscountSuccess() {
        Integer expected = 30;
        when(scraper.getDiscount()).thenReturn(expected);
        Integer result = item.getDiscount();
        verify(scraper).getDiscount();
        assertEquals(expected, result);
    }

    @Test
    public void getMinPriceSuccess() {
        Double expected = 12.0;
        Double result = item.getMinPrice();
        assertEquals(expected, result);
    }

    @Test
    public void getTypeSuccess() {
        String expected = "test-type";
        String result = item.getType();
        assertEquals(expected, result);
    }

    @Test
    public void getNameSuccess() {
        String expected = "test-name";
        String result = item.getName();
        assertEquals(expected, result);
    }

    @Test
    public void getUrlSuccess() {
        String expected = "test-url";
        String result = item.getUrl();
        assertEquals(expected, result);
    }

    @Test
    public void isPriceSuccess() {
        Boolean expected = true;
        Boolean result = item.isPrice();
        assertEquals(expected, result);
    }

    @Test
    public void isPriceFail() {
        when(scraper.getPrice()).thenReturn(100.0);
        Boolean expected = false;
        Boolean result = item.isPrice();
        verify(scraper).getPrice();
        assertEquals(expected, result);
    }

    @Test
    public void setMinPriceSuccess() {
        item.setMinPrice(25.0);
        Double expected = 25.0;
        Double result = item.getMinPrice();
        assertEquals(expected, result);
    }

    @Test
    public void setMinPriceFail() {
        try {
            item.setMinPrice(-1.0);
        } catch (RuntimeException e) {
            Double expected = 12.0;
            Double result = item.getMinPrice();
            assertEquals(expected, result);
            return;
        }
        fail("RuntimeException need to be raised.");
    }

    @Test
    public void setNameSuccess() {
        String expected = "test-name2";
        item.setName(expected);
        String result = item.getName();
        assertEquals(expected, result);
    }

    @Test
    public void setNameNullFail() {
        try {
            item.setName(null);
        } catch (RuntimeException e) {
            String expected = "test-name";
            String result = item.getName();
            assertEquals(expected, result);
            return;
        }
        fail("RuntimeException need to be raised.");
    }

    @Test
    public void setNameEmptyFail() {
        try {
            item.setName("");
        } catch (RuntimeException e) {
            String expected = "test-name";
            String result = item.getName();
            assertEquals(expected, result);
            return;
        }
        fail("RuntimeException need to be raised.");
    }

    @Test
    public void getImageSuccess() throws IOException {
        InputStream expected = AliScrapeTest.class.getResourceAsStream("/aliImage.jpeg");
        when(scraper.getImg()).thenReturn(expected);
        InputStream result = item.getImg();
        verify(scraper).getImg();
        assertEquals(expected,result);
    }
}
