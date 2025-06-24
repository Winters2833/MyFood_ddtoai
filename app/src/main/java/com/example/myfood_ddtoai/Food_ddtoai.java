package com.example.myfood_ddtoai;

public class Food_ddtoai {
    public int id;
    public String name, description, size, image;
    public double price;

    public Food_ddtoai(int id, String name, String description, String size, String image, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.image = image;
        this.price = price;
    }
}
