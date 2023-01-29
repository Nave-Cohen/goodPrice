package itemTest;

import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import scraper.AbstractScraper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ItemTest {
    private AbstractScraper scraper;
    private Item item;

    @BeforeEach
    public void setUp() {
        scraper = Mockito.mock(AbstractScraper.class);
        item = new Item("test-item", "test-url", 20.0, "test-type", scraper);
    }

    @Test
    public void getPriceSuccess() {
        Double expected = 15.0;
        when(scraper.getPrice()).thenReturn(expected);
        Double result = item.getPrice();
        assertEquals(expected, result);
    }

    @Test
    public void getDescriptionSuccess() {
        String expected = "this is description";
        when(scraper.getDescription()).thenReturn(expected);
        String result = item.getDescription();
        assertEquals(expected, result);
    }

    @Test
    public void getDiscountSuccess() {
        Integer expected = 30;
        when(scraper.getDiscount()).thenReturn(expected);
        Integer result = scraper.getDiscount();
        assertEquals(expected, result);
    }


}
