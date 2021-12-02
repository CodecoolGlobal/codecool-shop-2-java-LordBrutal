package com.codecool.shop.model;

public class CartItem {
    private int id;
    private int piece;
    private int sumItemPrice;
    private String name;
    private int price; // price of an item

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSumItemPrice() {
        return sumItemPrice;
    }

    public void setSumItemPrice(int sumItemPrice) {
        this.sumItemPrice = sumItemPrice;
    }
}
