package com.company;

import java.io.IOException;

public class User {

   public User(String surname, String name, String patronymic) throws IOException {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        shoppingCart = new ShoppingCart(this, 27);
    }
    public ShoppingCart shoppingCart;

    public String getFullName() {
        return surname + " " + name + " " + patronymic;
    }

    private final String name, surname, patronymic;
}
