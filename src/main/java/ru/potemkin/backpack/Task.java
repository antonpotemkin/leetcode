package ru.potemkin.backpack;

import java.util.Arrays;

public class Task {
    public static void main(String[] args) {
        int maxWeight = 4;
        Item[] items = new Item[]{
                new Item(1,1500),
                new Item(3,2000),
                new Item(1,1500),
                new Item(4,3000),
                new Item(1,1500),
                new Item(1,1500)};
        BackPack[][] backPacks = new BackPack[items.length+1][maxWeight+1];
        for (int i = 0; i < items.length+1; i++) backPacks[i][0] = new BackPack(new Item[]{}, 0);
        for (int i = 0; i < maxWeight+1; i++) backPacks[0][i] = new BackPack(new Item[]{}, 0);
        for (int i = 1; i < items.length+1; i++) {
            for (int j = 1; j < maxWeight+1; j++) {
                Item curItem = items[i-1];
                BackPack prevBackPack = backPacks[i - 1][j];
                if (curItem.getWeigh() > j ) backPacks[i][j] = prevBackPack;
                else {
                    int newPrice = curItem.getPrice() + backPacks[i-1][j-curItem.getWeigh()].getPrice();
                    if (prevBackPack.getPrice() > newPrice) backPacks[i][j] = prevBackPack;
                    else {
                        BackPack lastBackPack = backPacks[i - 1][j - curItem.getWeigh()];
                        Item[] newItems = Arrays.copyOf(lastBackPack.getItems(), lastBackPack.getItems().length + 1);
                        newItems[lastBackPack.getItems().length] = curItem;
                        backPacks[i][j] = new BackPack(newItems, newPrice);
                    }
                }
            }
        }
        for (int i = 0; i < items.length+1; i++) {
            System.out.println(Arrays.toString(backPacks[i]));
            System.out.println("-----");
        }
    }
}
