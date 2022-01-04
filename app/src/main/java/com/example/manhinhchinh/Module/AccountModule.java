package com.example.manhinhchinh.Module;

import java.io.Serializable;

public class AccountModule implements Serializable
{
    private String ID;

    private String UserName;

    private String Password;

    private String Gmail;

    private String Age;

    private String Height;

    private String Weight;

    private String WeightLoss;

    private String WeightLossTime;

    public AccountModule(String ID, String userName, String password, String gmail, String age, String height, String weight, String weightLoss, String weightLossTime) {
        this.ID = ID;
        UserName = userName;
        Password = password;
        Gmail = gmail;
        Age = age;
        Height = height;
        Weight = weight;
        WeightLoss = weightLoss;
        WeightLossTime = weightLossTime;
    }

    public AccountModule() {
    }

    public String getUserName ()
    {
        return UserName;
    }

    public void setUserName (String UserName)
    {
        this.UserName = UserName;
    }

    public String getGmail ()
    {
        return Gmail;
    }

    public void setGmail (String Gmail)
    {
        this.Gmail = Gmail;
    }

    public String getWeightLossTime ()
    {
        return WeightLossTime;
    }

    public void setWeightLossTime (String WeightLossTime)
    {
        this.WeightLossTime = WeightLossTime;
    }

    public String getHeight ()
    {
        return Height;
    }

    public void setHeight (String Height)
    {
        this.Height = Height;
    }

    public String getWeightLoss ()
    {
        return WeightLoss;
    }

    public void setWeightLoss (String WeightLoss)
    {
        this.WeightLoss = WeightLoss;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getAge ()
    {
        return Age;
    }

    public void setAge (String Age)
    {
        this.Age = Age;
    }

    public String getWeight ()
    {
        return Weight;
    }

    public void setWeight (String Weight)
    {
        this.Weight = Weight;
    }

    public String getPassword ()
    {
        return Password;
    }

    public void setPassword (String Password)
    {
        this.Password = Password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [UserName = "+UserName+", Gmail = "+Gmail+", WeightLossTime = "+WeightLossTime+", Height = "+Height+", WeightLoss = "+WeightLoss+", ID = "+ID+", Age = "+Age+", Weight = "+Weight+", Password = "+Password+"]";
    }
}
