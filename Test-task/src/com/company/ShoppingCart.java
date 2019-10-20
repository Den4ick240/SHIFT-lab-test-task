package com.company;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShoppingCart {
    private static final String CARTS_DIRECTORY = ".//ShoppingCarts/";
    public final String CARTS_FILE_NAME;

    public ShoppingCart(User owner, Integer ID) throws IOException {
        this.owner = owner;
        this.ID = ID;
        CARTS_FILE_NAME = CARTS_DIRECTORY + "ShoppingCartNumber" + ID.toString() + ".txt";
        contentFile = new File(CARTS_FILE_NAME);
        if (!contentFile.exists()) {
            contentFile.createNewFile();
        }

        itemsMap = new TreeMap<Item, Integer>(new Comparator<Item>()
        {
            public int compare(Item o1, Item o2)
            {
                return o1.name.compareTo(o2.name);
            }
        });
        ReadItems();

    }

    private void ReadItems() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(contentFile));
        String buff;
        while ((buff = reader.readLine()) != null) {
            String [] buff_arr = buff.split(",");
            itemsMap.put(new Item(buff_arr[0], Float.parseFloat(buff_arr[1])), Integer.parseInt(buff_arr[2]));
        }
        reader.close();
    }

    private void UpdateCartFile() throws IOException {
        FileWriter writer = new FileWriter(contentFile);
        for (Map.Entry<Item, Integer> item : itemsMap.entrySet()) {
            writer.write(item.getKey().name + "," +
                    item.getKey().cost.toString() + "," +
                    item.getValue().toString() + ",\n");
        }
        writer.close();
    }

    public void addItem(String itemName, int amount) throws Exception {
        Item item = ItemsList.FindItemByName(itemName);

        amount += itemsMap.getOrDefault(item, 0);
        itemsMap.put(item, amount);

        UpdateCartFile();
    }

    public void printContent()
    {
        System.out.print("Order №");
        System.out.print(ID);
        System.out.print(" " + owner.getFullName() + " ");

        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println(date.format(new Date()));

        System.out.printf("%-20s%-15s%-15s%s%n", "Name", "Cost", "Amount", "Total");

        for (Map.Entry<Item, Integer> item : itemsMap.entrySet()) {
            Float total = item.getKey().cost * item.getValue();

            System.out.printf("%-20s%-15s%-15s%s%n",
                    item.getKey().name,
                    item.getKey().cost,
                    item.getValue().toString(),
                    total.toString());
        }

    }

    private User owner;
    private Map<Item, Integer> itemsMap;
    private final Integer ID;
    private File contentFile;
}
