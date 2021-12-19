package com.example.manhinhchinh;

import java.io.Serializable;

public class Food implements Serializable {
    private int image;
    private String name;
    private String calo;
    private int id;

    public Food(int image, String name, String calo) {
        this.image = image;
        this.name = name;
        this.calo = calo;
    }

    public Food(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalo() {
        return calo;
    }

    public void setCalo(String calo) {
        this.calo = calo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
