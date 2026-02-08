package com.report.gene.model;

public class Item {

    private String itemName;
    private Integer quantity;
    private Double price;
    private Double total;

    public Item(String itemName, Integer quantity, Double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTotal() {
        return total;
    }
}
