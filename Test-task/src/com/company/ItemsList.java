package com.company;

import java.io.*;

public class ItemsList {
    private static final String ITEMS_LIST_DIRECTORY = ".//ItemsList/";
    private static final String ITEMS_LIST_FILE_NAME = "ItemsList.txt";

    public static Item FindItemByName(String itemName) throws Exception {
        Item item;
        FileReader file_reader = new FileReader(ITEMS_LIST_DIRECTORY + ITEMS_LIST_FILE_NAME);
        BufferedReader reader = new BufferedReader(file_reader);

        while ((item = ReadItem(reader)) != null) {
            if (item.name.equals(itemName)) {
                return item;
            }
        }

        throw new Exception("No such item in the item list!");
    }

    public static void PrintItemsList() throws IOException {
        FileReader file_reader = new FileReader(ITEMS_LIST_DIRECTORY + ITEMS_LIST_FILE_NAME);
        BufferedReader reader = new BufferedReader(file_reader);
        Item item;

        while ((item = ReadItem(reader)) != null) {
            System.out.print(item.name);
            System.out.print(", cost:");
            System.out.println(item.cost);
        }
    }

    private static Item ReadItem(BufferedReader reader) throws IOException {
        String [] buff_arr;// = new String[2];

        String buff = reader.readLine();
        if (buff == null) {
            return null;
        }

        buff_arr = buff.split(",");
        return new Item(buff_arr[0], Float.parseFloat(buff_arr[1]));
    }

}
