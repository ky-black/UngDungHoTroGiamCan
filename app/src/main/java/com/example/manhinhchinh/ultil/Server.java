package com.example.manhinhchinh.ultil;

public class Server {
    private static String IPaddress = "192.168.1.4";
    public static String urlGetFood = "http://"+IPaddress+":3000/foods";
    public static String urlGetExercise = "http://"+IPaddress+":3000/exercise";
    public static String urlPostFood = "http://"+IPaddress+":3000/food_details";
    public static String urlGetFoodByID = "http://"+IPaddress+":3000/food_details/";
    public static String urlGetFoodDetailsByIDTK = "http://"+IPaddress+":3000/getFoodDetails/";
    public static String urlCheckUser = "http://"+IPaddress+":3000/account";
    public static String urlPostUser = "http://"+IPaddress+":3000/account/insert";
    public static String urlCheckUserByUserName = "http://"+IPaddress+":3000/account/";
}
