package ru.potemkin.backpack;

import java.util.Arrays;

public class BackPack {
    private Item[] items;
    private int price;

    public BackPack(Item[] items, int price) {
        this.items = items;
        this.price = price;
    }

    public Item[] getItems() {
        return items;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "BackPack{" +
                "items=" + Arrays.toString(items) +
                ", price=" + price +
                '}';
    }
}
