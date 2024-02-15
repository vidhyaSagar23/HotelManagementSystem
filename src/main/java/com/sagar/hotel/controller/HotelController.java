package com.sagar.hotel.controller;

import com.sagar.hotel.entity.Hotels;
import com.sagar.hotel.enums.Customer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelController {
    static Hotels hotels = new Hotels();

    static Scanner s = new Scanner(System.in);

    public Map<String, Hotels> addHotel(Map<String, Hotels> map) {
        System.out.println("Enter Hotel Name");
        String hotelName = s.next();
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
        map.put(hotelName, hotels);
        return map;
    }

    public void display(Map<String, Hotels> map) {
        Set<String> keys = map.keySet();

        for (String key : keys) {
            Hotels hotel = map.get(key);
            System.out.println(hotel);
            System.out.println("--------------------------------------");
        }
    }

    public void findCheapestHotel(Map<String, Hotels> hotel) {
        int minCost = Integer.MAX_VALUE;
        String cheapestHotel = "";
        int maxRating = Integer.MIN_VALUE;
        List<String> date = new ArrayList<>();
        String customerType;
        System.out.println("Enter the Customer Type :\n1: Regular\n2: Reward");
        int type = s.nextInt();
        if (type == 1) {
            customerType = String.valueOf(Customer.REGULAR);
        } else {
            customerType = String.valueOf(Customer.REWARD);
        }
        System.out.println("Enter how many days you need hotel");
        int days = s.nextInt();
        System.out.println("Enter " + days + " dates that u need");
        for (int i = 1; i <= days; i++) {
            System.out.println("DATE FORMAT => DD/MON/YYYY/DAY(SAT)");
            String dateFormat = s.next();
            date.add(dateFormat);
        }
        System.out.println("--------------------------------------");
        for (Hotels h : hotel.values()) {
            int total = 0;
            for (String d : date) {
                d.toUpperCase();
                if (d.contains("SAT") || d.contains("SUN")) {
                    total += customerType.equalsIgnoreCase("REWARD") ? h.getWeekEndReward() : h.getWeekDayRate();
                } else {
                    total += customerType.equalsIgnoreCase("REWARD") ? h.getWeekDayReward() : h.getWeekDayRate();
                }
            }
            System.out.println(h.getName() + " : " + total);
            if (total < minCost || (total == minCost && h.getRating() > maxRating)) {
                minCost = total;
                cheapestHotel = h.getName();
                maxRating = h.getRating();
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("Cheapest Hotel: " + cheapestHotel);
        System.out.println("--------------------------------------");
    }

    public Map<String, Hotels> updateHotelRates(Map<String, Hotels> hotels) {
        System.out.println("Enter the name of the hotel to update rates: ");
        String hotelName = s.nextLine();

        if (hotels.containsKey(hotelName)) {
            Hotels hotel = hotels.get(hotelName);

            System.out.println("Enter new Weekday Rate: ");
            hotel.setWeekDayRate(s.nextInt());

            System.out.println("Enter new Weekend Rate: ");
            hotel.setWeekEndRate(s.nextInt());

            System.out.println("Enter new Weekday Reward Price: ");
            hotel.setWeekDayReward(s.nextInt());

            System.out.println("Enter new Weekend Reward Price: ");
            hotel.setWeekEndReward(s.nextInt());

            hotels.put(hotelName,hotel);
            System.out.println("Rates updated successfully for " + hotelName);
        } else {
            System.out.println("Hotel not found!");
        }
        return hotels;
    }


    public void findCheapestHotelForDateRange(Map<String, Hotels> hotels) throws ParseException, ParseException {
        int minCost = Integer.MAX_VALUE;
        String cheapestHotel = "";
        int maxRating = Integer.MIN_VALUE;
        // Input date range
        DateFormat df = new SimpleDateFormat("ddMMMyyyy");
        System.out.println("Enter the start date (DDMMMYYYY): ");
        Date startDate = df.parse(s.next());
        System.out.println("Enter the end date (DDMMMYYYY): ");
        Date endDate = df.parse(s.next());

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        // Calculate total days between start and end dates
        int totalDays = 1 + (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));

        // Iterate over each hotel to calculate the total cost
        for (Hotels hotel : hotels.values()) {
            int totalCost = 0;
            Calendar cal = (Calendar) startCal.clone();
            while (cal.before(endCal) || cal.equals(endCal)) {
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    totalCost += hotel.getWeekEndRate();
                } else {
                    totalCost += hotel.getWeekDayRate();
                }
                cal.add(Calendar.DATE, 1);
            }
            if (totalCost < minCost || (totalCost == minCost && hotel.getRating() > maxRating)) {
                minCost = totalCost;
                cheapestHotel = hotel.getName();
                maxRating = hotel.getRating();
            }
        }
        System.out.println("Cheapest Hotel : "+cheapestHotel+"\nTotal Days: "+totalDays);
    }
}
