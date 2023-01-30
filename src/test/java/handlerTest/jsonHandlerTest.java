package handlerTest;

import handler.jsonHandler;
import item.Item;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class jsonHandlerTest {
    //readItem,writeItem,readAllItems,writeAllItems,filePath.
    private static File getFileInstance() {
        URL url = jsonHandlerTest.class.getResource("/");
        return new File(url.getPath()+"/items.json");
    }

    @BeforeEach
    public void setUp()  {
        File file = getFileInstance();
        jsonHandler.setFolder(file);
    }

    @AfterEach
    public void tearUp() throws IOException {
        File file = getFileInstance();
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }

    @Test
    public void readItemsEmpty() throws Exception {
        ArrayList<Item> result = jsonHandler.readItems();
        assertTrue(result.isEmpty()); //list is empty on created.
    }

    @Test
    public void readItemsAfterWring() throws Exception {
        jsonHandler.addItem(new Item("name", "url", 23.4, "type"));
        jsonHandler.writeItems(); //write to file
        ArrayList<Item> result = jsonHandler.readItems(); //after last writing.
        assertFalse(result.isEmpty());
    }
}
