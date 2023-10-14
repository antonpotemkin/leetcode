package ru.potemkin.backpack;

public class Item {
    private int weigh;
    private int price;

    public Item(int weigh, int price) {
        this.weigh = weigh;
        this.price = price;
    }

    public int getWeigh() {
        return weigh;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weigh=" + weigh +
                ", price=" + price +
                '}';
    }
}
