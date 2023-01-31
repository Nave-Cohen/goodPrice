package goodPriceTest;

import itemTest.ItemTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import scraperTest.AliScrapeTest;

@Suite
@SelectClasses({ ItemTest.class, AliScrapeTest.class })
public class MainTest {
    public MainTest() {

    }
}
