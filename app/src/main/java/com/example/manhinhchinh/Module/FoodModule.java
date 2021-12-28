package com.example.manhinhchinh.Module;

import java.io.Serializable;

public class FoodModule implements Serializable
{
    private String Description;

    private String Picture;

    private String ID;

    private String Unit;

    private String TypeFood;

    private String Calories;

    private String FoodName;

    public FoodModule(String description, String picture, String ID, String unit, String typeFood, String calories, String foodName) {
        Description = description;
        Picture = picture;
        this.ID = ID;
        Unit = unit;
        TypeFood = typeFood;
        Calories = calories;
        FoodName = foodName;
    }

    public FoodModule() {
    }

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getPicture ()
    {
        return Picture;
    }

    public void setPicture (String Picture)
    {
        this.Picture = Picture;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getUnit ()
    {
        return Unit;
    }

    public void setUnit (String Unit)
    {
        this.Unit = Unit;
    }

    public String getTypeFood ()
    {
        return TypeFood;
    }

    public void setTypeFood (String TypeFood)
    {
        this.TypeFood = TypeFood;
    }

    public String getCalories ()
    {
        return Calories;
    }

    public void setCalories (String Calories)
    {
        this.Calories = Calories;
    }

    public String getFoodName ()
    {
        return FoodName;
    }

    public void setFoodName (String FoodName)
    {
        this.FoodName = FoodName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+Description+", Picture = "+Picture+", ID = "+ID+", Unit = "+Unit+", TypeFood = "+TypeFood+", Calories = "+Calories+", FoodName = "+FoodName+"]";
    }
}
