package com.sagar.hotel;

import com.sagar.hotel.controller.HotelController;
import com.sagar.hotel.entity.Hotels;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<String, Hotels> hotel=new HashMap<>();

    static Scanner s=new Scanner(System.in);

    static HotelController h=new HotelController();

    static {
        hotel.put("Lakewood", new Hotels("Lakewood", 3, 160, 190, 80, 80));
        hotel.put("Bridgewood", new Hotels("Bridgewood", 4, 160, 160, 110, 50));
        hotel.put("Ridgewood", new Hotels("Ridgewood", 5, 220, 150, 100, 40));
    }

    public static void main(String[] args) throws ParseException {
        boolean flag=true;
        while(flag){
            System.out.println("Enter Choice");
            System.out.println("--------------------------------------");
            System.out.println("1: Add Hotel\n2: Display hotels\n3: Cheapest Hotel\n4 : Update Rate" +
                    "\n5: find Cheapest Hotel For Date Range\n6: Update Ratings" +
                    "\n7: findCheapestBestRatedHotelForDateRange\n8: Best Rated Hotel Between Date Range" +
                    "\n9: add Special Rates to reward customer\n10: cheapest Best Rated For Reward" +
                    "\n11: findCheapestBestRatedHotelForRewardCustomer\n12: findCheapestBestRatedHotelForRegularCustomer" +
                    "\n13: Exit");
            System.out.println("--------------------------------------");
            int choice=s.nextInt();
            switch (choice){
                case 1:
                {
                    hotel= h.addHotel(hotel);
                    break;
                }
                case 2:
                {
                    h.display(hotel);
                    break;
                }
                case 3:
                {
                    h.findCheapestHotel(hotel);
                    break;
                }
                case 4:{
                   hotel= h.updateHotelRates(hotel);
                    break;
                }
                case 5:{
                    h.findCheapestHotelForDateRange(hotel);
                    break;
                }
                case 6:{
                    h.updateHotelRatings(hotel);
                    break;
                }
                case 7:{
                    h.findCheapestBestRatedHotelForDateRange(hotel);
                    break;
                }
                case 8:{
                    h.findBestRatedHotel(hotel);
                    break;
                }
                case 9:{
                    h.addSpecialRates(hotel);
                    break;
                }
                case 10:{
                    h.cheapestBestRatedForReward(hotel);
                    break;
                }
                case 11:{
                    h.findCheapestBestRatedHotelForRewardCustomer(hotel);
                    break;
                }
                case 12:{
                    h.findCheapestBestRatedHotelForRegularCustomer(hotel);
                    break;
                }
                case 13:
                {
                    flag=false;
                    break;
                }
            }
        }

    }
}