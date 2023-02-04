package handler;

import item.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class JsonHandler {
    private File itemFile;
    private JSONArray jsonArray;

    public JsonHandler() {
        itemFile = checkRunner();
        try {
            itemFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        jsonArray = new JSONArray();
    }

    public JsonHandler(File file) {
        itemFile = file;
        jsonArray = new JSONArray();
    }

    private File checkRunner(){
        String protocol = this.getClass().getResource("JsonHandler.class").getProtocol();
        if (Objects.equals(protocol, "jar"))
            return new File("items.json");
        return new File(this.getClass().getResource("/items.json").getPath());
    }
    public void addItem(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null");
        JSONObject json = itemToJson(item);
        jsonArray.add(json);
    }

    public void removeItem(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null");
        int i = 0;
        for (Object it : jsonArray) {
            Item jsonItem = jsonToItem((JSONObject) it);
            if (jsonItem.equals(item)) {
                jsonArray.remove(i);
                break;
            }
            i++;
        }
    }

    //public static void writeItems(ObservableList<Item> items)  //implementation needed.

    public void writeItems() {
        try {
            FileWriter out = new FileWriter(itemFile);
            out.write(jsonArray.toString());
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    public Boolean isEmpty() {
        return jsonArray.isEmpty();
    }

    public ArrayList<Item> readItems() throws Exception {
        ArrayList<Item> items = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        if (itemFile.length() == 0)
            return items;
        Object obj = jsonParser.parse(new FileReader(itemFile));
        jsonArray = (JSONArray) obj;
        for (Object item : jsonArray)
            items.add((jsonToItem((JSONObject) item)));
        return items;
    }

    private JSONObject itemToJson(Item item) {
        JSONObject jsonItem = new JSONObject();
        jsonItem.put("name", item.getName());
        jsonItem.put("minPrice", item.getMinPrice());
        jsonItem.put("url", item.getUrl());
        jsonItem.put("type", item.getType());
        return jsonItem;
    }

    private Item jsonToItem(JSONObject jsonItem) {
        String name = (String) jsonItem.get("name");
        String url = (String) jsonItem.get("url");
        Double minPrice = ((Number) jsonItem.get("minPrice")).doubleValue();
        String type = (String) jsonItem.get("type");
        return new Item(name, url, minPrice, type);
    }
}
