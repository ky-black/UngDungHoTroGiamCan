package com.example.manhinhchinh.Module;

public class FoodDetailsModule
{
    private String IDTK;

    private String Quantily;

    private String IDTA;

    private String ID;

    private String Date;

    public FoodDetailsModule(String IDTK, String quantily, String IDTA, String ID, String date) {
        this.IDTK = IDTK;
        this.Quantily = quantily;
        this.IDTA = IDTA;
        this.ID = ID;
        this.Date = date;
    }

    public FoodDetailsModule() {
    }

    public String getIDTK ()
    {
        return IDTK;
    }

    public void setIDTK (String IDTK)
    {
        this.IDTK = IDTK;
    }

    public String getQuantily ()
    {
        return Quantily;
    }

    public void setQuantily (String Quantily)
    {
        this.Quantily = Quantily;
    }

    public String getIDTA ()
    {
        return IDTA;
    }

    public void setIDTA (String IDTA)
    {
        this.IDTA = IDTA;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IDTK = "+IDTK+", Quantily = "+Quantily+", IDTA = "+IDTA+", ID = "+ID+", Date = "+Date+"]";
    }
}