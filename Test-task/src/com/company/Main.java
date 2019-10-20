package com.company;
import com.sun.source.tree.Tree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import  java.io.File;
import java.util.concurrent.ExecutionException;

public class Main {


    public static void main(String[] args) {
        try {
            User user = new User("Curtov", "Curt", "Curtovich");
            if (args[0].equals("show cart")) {
                user.shoppingCart.printContent();
            }
            else {
                if (args.length == 1) {
                    user.shoppingCart.addItem(args[0], 1);
                }
                else {
                    Integer amount = Integer.parseInt(args[1]);
                    user.shoppingCart.addItem(args[0], amount);
                    user.shoppingCart.printContent();
                }
            }
        } catch (IOException e) {
            System.out.println();
        } catch (NumberFormatException e) {
            if (e.getMessage().contains(args[1])) {
                System.out.println("Wrong arguments!");
            }
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
