package com.example.manhinhchinh.Module;

import java.io.Serializable;

public class ExerciseModule implements Serializable
{
    private String Description;

    private String Picture;

    private String ID;

    private String Unit;

    private String Calories;

    private String ExerciseName;

    public ExerciseModule(String description, String picture, String ID, String unit, String calories, String exerciseName) {
        Description = description;
        Picture = picture;
        this.ID = ID;
        Unit = unit;
        Calories = calories;
        ExerciseName = exerciseName;
    }

    public ExerciseModule() {
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

    public String getCalories ()
    {
        return Calories;
    }

    public void setCalories (String Calories)
    {
        this.Calories = Calories;
    }

    public String getExerciseName ()
    {
        return ExerciseName;
    }

    public void setExerciseName (String ExerciseName)
    {
        this.ExerciseName = ExerciseName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+Description+", Picture = "+Picture+", ID = "+ID+", Unit = "+Unit+", Calories = "+Calories+", ExerciseName = "+ExerciseName+"]";
    }
}
