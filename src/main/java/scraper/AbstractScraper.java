package scraper;

import javafx.scene.image.Image;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


public abstract class AbstractScraper implements ScraperIF {
    private String url, type, name;
    protected Document doc;
    protected String imagePath = "C:\\Users\\nave1\\Desktop\\GoodPrice\\goodPrice\\src\\main\\resources\\";

    public AbstractScraper(String name, String url, String type) {
        this.url = url;
        this.type = type;
        this.name = name;
        try {
            this.doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    private Path createTemp(byte[] bytes) throws IOException {
        Path tmp = Files.createTempFile(name, ".png");
        FileOutputStream out = (new FileOutputStream(tmp.toString()));
        out.write(bytes);  // resultImageResponse.body() is where the image's contents are.
        out.close();
        return tmp;
    }

    public Image getImg(String url) {
        Connection.Response resultImageResponse;
        try {
            resultImageResponse = Jsoup.connect(url)
                    .ignoreContentType(true).execute();
            Path tmp = createTemp(resultImageResponse.bodyAsBytes());
            return new Image(new FileInputStream(tmp.toString()));
        } catch (Exception e) {
            InputStream in = getClass().getResourceAsStream("/default.png");
            return new Image(in);
        }
    }
}
