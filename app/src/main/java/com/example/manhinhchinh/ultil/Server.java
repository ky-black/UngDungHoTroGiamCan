package com.example.manhinhchinh.ultil;

public class Server {
    private static String IPaddress = "192.168.1.6";
    public static String urlGetFood = "http://"+IPaddress+":3000/foods";
    public static String urlGetExercise = "http://"+IPaddress+":3000/exercise";
    public static String urlPostFood = "http://"+IPaddress+":3000/food_details";
}
