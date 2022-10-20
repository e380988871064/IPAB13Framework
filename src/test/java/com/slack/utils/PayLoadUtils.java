package com.slack.utils;

public class PayLoadUtils {

    public static String getSlackMessagePayLoad(String message ,String channel){
       return "{\n" +
               "    \"channel\": \""+channel+"\",\n" +
               "    \"text\": \""+message+"\"\n" +
               "}";
    }

    public static String updateSlackMessagePayLoad(String message ,String channel ,String ts){
        return"{\n" +
                "    \"channel\": \""+channel+"\",\n" +
                "    \"ts\": \""+ts+"\",\n" +
                "    \"text\": \""+message+"\"\n" +

                "}";




    }



    public static String deleteSlackMessagePayLoad(String channel ,String ts){
        return"{\n" +
                "    \"channel\": \""+channel+"\",\n" +
                "    \"ts\": \""+ts+"\"\n" +

                "}";




    }

}
