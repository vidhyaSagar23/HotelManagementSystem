package com.sagar.hotel.controller;

import com.sagar.hotel.entity.Hotels;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelController {
    static Hotels hotels=new Hotels();

    static Scanner s=new Scanner(System.in);
    public Map<String, Hotels> addHotel(Map<String, Hotels> map){
        System.out.println("Enter Hotel Name");
        String hotelName=s.next();
        hotels.setName(hotelName);
        System.out.println("Enter Rating");
        hotels.setRating(s.nextInt());
        System.out.println("Enter Week Day Rate");
        hotels.setWeekDayRate(s.nextInt());
        System.out.println("Enter week end reward rate");
        hotels.setWeekEndRate(s.nextInt());
        System.out.println("Enter Week day reward price");
        hotels.setWeekDayReward(s.nextInt());
        System.out.println("Enter Weekend reward price");
        hotels.setWeekEndReward(s.nextInt());
         map.put(hotelName,hotels);
         return map;
    }

    public void display(Map<String, Hotels> map) {
        Set<String> keys=map.keySet();

        for(String key:keys){
            Hotels hotel=map.get(key);
            System.out.println(hotel);
            System.out.println("--------------------------------------");
        }
    }
}
