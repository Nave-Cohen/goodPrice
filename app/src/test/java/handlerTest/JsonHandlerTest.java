package handlerTest;

import handler.JsonHandler;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonHandlerTest {
    //readItem,writeItem,readAllItems,writeAllItems,filePath.

    private Item item;
    private JsonHandler jHandler;


    private void cleanFile(File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            fail("clean file method not work as expected.");
        }
    }

    @BeforeEach
    public void setUp() {
        item = new Item("name", "url", 23.4, "type");
        URL url = JsonHandlerTest.class.getResource("/items.json");
        File file = new File(url.getPath());
        jHandler = new JsonHandler(file);
        cleanFile(file);
    }

    @Test
    public void readItemsEmptySuccess() throws Exception {
        ArrayList<Item> result = jHandler.readItems(); //need to be changed to true.
        assertTrue(result.isEmpty()); //list is empty on created.
    }

    @Test
    public void addItemNullFail() {
        try {
            jHandler.addItem(null);
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(),"Item cannot be null");
            return;
        }
        fail("not need to be raise.");
    }

    @Test
    public void addItemSuccess() {
        jHandler.addItem(item);
        assertFalse(jHandler.isEmpty());
    }

    @Test
    public void removeItemNullFail() {
        try {
            jHandler.removeItem(null);
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(),"Item cannot be null");
            return;
        }
        fail("not need to be raise.");
    }

    @Test
    public void removeItemSuccess() {
        jHandler.addItem(item);
        assertFalse(jHandler.isEmpty());
        jHandler.removeItem(item);
        assertTrue(jHandler.isEmpty());
    }


    @Test
    public void writeItemsEmptySuccess() {
        try {
            jHandler.writeItems(); //write to file
        } catch (Exception e) {
            fail("not need to be raise.");
        }
    }

    @Test
    public void writeItemsSuccess() {
        try {
            jHandler.addItem(item);
            jHandler.writeItems(); //write to file
        } catch (Exception e) {
            fail("not need to be raise.");
        }
    }


    @Test
    public void readItemsAfterWritingSuccess() throws Exception {
        jHandler.addItem(item);
        jHandler.writeItems(); //write to file
        ArrayList<Item> result = jHandler.readItems(); //after last writing.
        assertFalse(result.isEmpty());
    }


}
