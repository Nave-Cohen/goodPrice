package handlerTest;

import handler.jsonHandler;
import item.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class jsonHandlerTest {
    //readItem,writeItem,readAllItems,writeAllItems,filePath.

    private Item item;
    private jsonHandler jHandler;
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
    public void setUp() throws IOException {
        item = new Item("name", "url", 23.4, "type");

        URL url = jsonHandlerTest.class.getResource("/items.json");
        File file = new File(url.getPath());
        jHandler = new jsonHandler(file);
        cleanFile(file);
    }

    @Test
    public void readItemsEmptySuccess() throws Exception {
        ArrayList<Item> result = jHandler.readItems(); //need to be changed to true.
        assertTrue(result.isEmpty()); //list is empty on created.
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
    public void addItemNullFail() {
        try {
            jHandler.addItem(null);
        } catch (NullPointerException e) {
            return;
        }
        fail("not need to be raise.");
    }

    @Test
    public void readItemsAfterWringSuccess() throws Exception {
        jHandler.addItem(item);
        jHandler.writeItems(); //write to file
        ArrayList<Item> result = jHandler.readItems(); //after last writing.
        assertFalse(result.isEmpty());
    }

    @Test
    public void removeItemSuccess() {
        jHandler.addItem(item);
        assertFalse(jHandler.isEmpty());
        jHandler.removeItem(item);
        assertTrue(jHandler.isEmpty());
    }
}
