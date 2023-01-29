package scraperTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scraper.AbstractScraper;
import scraper.AliScrape;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class abstractScraperTest {
    private AbstractScraper scraper;

    @BeforeEach
    public void setUp() {
        URL path = abstractScraperTest.class.getResource("/aliExpress.html");
        File file = new File(path.toExternalForm());
        scraper = new AliScrape("https://he.aliexpress.com/item/1005003340501584.html?spm=a2g0o.home.15002.5.1e8c6b05SYqgnr&gps-id=pcJustForYou&scm=1007.13562.295725.0&scm_id=1007.13562.295725.0&scm-url=1007.13562.295725.0&pvid=d40af09e-651c-4530-8408-dc4f668b73b7&_t=gps-id:pcJustForYou,scm-url:1007.13562.295725.0,pvid:d40af09e-651c-4530-8408-dc4f668b73b7,tpp_buckets:668%232846%238111%231996&isseo=y&pdp_ext_f=%7B%22sku_id%22%3A%2212000025302200864%22%2C%22sceneId%22%3A%223562%22%7D&pdp_npi=2%40dis%21ILS%2117.68%211.81%21%21%21%21%21%40210323b016750076509034497edb77%2112000025302200864%21rec&curPageLogUid=qYgpel2kcVoj",file);
    }

    @Test
    public void getPriceSuccess() {
        assertEquals(1.81, scraper.getPrice());
    }
}
