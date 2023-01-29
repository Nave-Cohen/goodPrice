package handler;

import item.Item;
import javafx.collections.ObservableList;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class jsonHandler {
    private static final File folder = new File("items");

    private static String filePath(String name){
        if (name == null || name.equals(""))
            return folder.getAbsolutePath();//path to folder
        return String.format("%s/%s.json",folder.getAbsolutePath(),name);
    }
    private static void writeItem(Item item) {
        JSONObject obj = new JSONObject();
        obj.put("name", item.getName());
        obj.put("url", item.getUrl());
        obj.put("type", item.getType());
        obj.put("minPrice", item.getMinPrice());
        StringWriter out = new StringWriter();
        obj.write(out);
        try {
            FileWriter file = new FileWriter(filePath(item.getName()));
            file.write(out.toString());
            file.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static Item readItem(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            JSONObject jsonObj = new JSONObject(reader.readLine());
            return new Item(jsonObj.getString("name"), jsonObj.getString("url"), jsonObj.getDouble("minPrice"), jsonObj.getString("type"));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public static void removeItem(Item item){
        File file = new File(filePath(item.getName()));
        file.delete();
    }
    public static void writeAllItems(ObservableList<Item> items){
        for (Item item:items)
            writeItem(item);
    }
    public static ArrayList<Item> readAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) { //iterate all files
            if (!fileEntry.getName().endsWith(".json"))
                continue;
            items.add(readItem(fileEntry.getAbsolutePath()));
        }
        return items;
    }
}
