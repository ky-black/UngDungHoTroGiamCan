package com.example.manhinhchinh;

public class Food {
    private int image;
    private String name;
    private String calo;

    public Food(int image, String name, String calo) {
        this.image = image;
        this.name = name;
        this.calo = calo;
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
}
