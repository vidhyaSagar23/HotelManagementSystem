package com.sagar.hotel.controller;

import com.sagar.hotel.entity.Hotels;
import com.sagar.hotel.enums.Customer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

        int totalDays = 1 + (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));

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

    public Map<String, Hotels> updateHotelRatings(Map<String, Hotels> hotels) {
        System.out.println("Enter the name of the hotel to update rates: ");
        String hotelName = s.nextLine();

        if (hotels.containsKey(hotelName)) {
            Hotels hotel = hotels.get(hotelName);

            System.out.println("Enter your Ratings: ");
            hotel.setRating(s.nextInt());

            hotels.put(hotelName,hotel);
            System.out.println("Ratings updated successfully for " + hotelName);
        } else {
            System.out.println("Hotel not found!");
        }
        return hotels;
    }

    public void findCheapestBestRatedHotelForDateRange(Map<String, Hotels> hotels){
        System.out.println("Enter start date : Format -> yyyy mm dd");
        int startYear=s.nextInt();
        int startMonth=s.nextInt();
        int startDay=s.nextInt();
        System.out.println("Enter end date : Format -> yyyy mm dd");
        int endYear=s.nextInt();
        int endMonth=s.nextInt();
        int endDay=s.nextInt();
        LocalDate startDate=LocalDate.of(startYear,startMonth,startDay);
        LocalDate endDate=LocalDate.of(endYear,endMonth,endDay);
        int minCost = Integer.MAX_VALUE;
        String cheapestBestRatedHotel = "";
        int maxRating = Integer.MIN_VALUE;
        for (Hotels hotel : hotels.values()) {
            int totalCost =0;
            int weekdayRate = hotel.getWeekDayRate();
            int weekendRate = hotel.getWeekEndRate();
            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                int dayOfWeek = currentDate.getDayOfWeek().getValue();
                if (dayOfWeek >= 1 && dayOfWeek <= 5) {
                    totalCost += weekdayRate;
                } else {
                    totalCost += weekendRate;
                }
                currentDate = currentDate.plusDays(1);
            }
            if (totalCost < minCost || (totalCost == minCost && hotel.getRating() > maxRating)) {
                minCost = totalCost;
                cheapestBestRatedHotel = hotel.getName();
                maxRating = hotel.getRating();
            }
        }
        System.out.println("--------------------------------------");
        System.out.println(cheapestBestRatedHotel + ", Rating: " + maxRating + " and Total Rates: $" + minCost);
        System.out.println("--------------------------------------");
    }

    public void findBestRatedHotel(Map<String, Hotels> hotels){
        System.out.println("Enter start date Format: yyyy mm dd");
        int startYear=s.nextInt();
        int startMonth=s.nextInt();
        int startDay=s.nextInt();
        System.out.println("Enter end date Format: yyyy mm dd");
        int endYear=s.nextInt();
        int endMonth=s.nextInt();
        int endDay=s.nextInt();

        LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
        LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

        int maxRating = Integer.MIN_VALUE;
        String bestRatedHotel = "";
        int totalRates = 0;
        for (Hotels hotel : hotels.values()) {
            int totalCost = 0;
            int weekdayRate = hotel.getWeekDayRate();
            int weekendRate = hotel.getWeekEndRate();
            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                int dayOfWeek = currentDate.getDayOfWeek().getValue();
                if (dayOfWeek >= 1 && dayOfWeek <= 5) {
                    totalCost += weekdayRate;
                } else {
                    totalCost += weekendRate;
                }
                currentDate = currentDate.plusDays(1);
            }
            if (hotel.getRating() > maxRating || (hotel.getRating() == maxRating && totalCost < totalRates)) {
                maxRating = hotel.getRating();
                bestRatedHotel = hotel.getName();
                totalRates = totalCost;
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("Best Rated Hotel for the date range " + startDate + " to " + endDate + ":");
        System.out.println(bestRatedHotel + " & Total Rates $" + totalRates);
        System.out.println("--------------------------------------");
    }

    public void addSpecialRates(Map<String,Hotels> hotels){
        System.out.println("Enter Choice :\n1: REWARD\n or any number to exit");

        if (s.nextInt() == 1){
            for (Hotels h:hotels.values()){
                if (h.getName().equals("Bridgewood")){
                    h.setWeekDayReward(110);
                    h.setWeekEndReward(50);
                } else if (h.getName().equals(("Ridgewood"))) {
                    h.setWeekDayReward(100);
                    h.setWeekEndReward(40);
                }
                System.out.println(h.getName()+" "+h.getWeekDayReward()+" "+h.getWeekEndReward());
            }

        }
        else{
            System.out.println("Only for Reward Customers");
        }
    }

    public void cheapestBestRatedForReward(Map<String, Hotels> hotels){
        System.out.println("Enter Customer Type\nPress 1 for -> Reward\nAny other number for Regular");
        if (s.nextInt()==1){
            System.out.println("Enter start date : Format -> yyyy mm dd");
            int startYear=s.nextInt();
            int startMonth=s.nextInt();
            int startDay=s.nextInt();
            System.out.println("Enter end date : Format -> yyyy mm dd");
            int endYear=s.nextInt();
            int endMonth=s.nextInt();
            int endDay=s.nextInt();
            LocalDate startDate=LocalDate.of(startYear,startMonth,startDay);
            LocalDate endDate=LocalDate.of(endYear,endMonth,endDay);
            int minCost = Integer.MAX_VALUE;
            String cheapestBestRatedHotel = "";
            int maxRating = Integer.MIN_VALUE;
            for (Hotels hotel : hotels.values()) {
                int totalCost =0;
                int weekdayRate = hotel.getWeekDayReward();
                int weekendRate = hotel.getWeekEndReward();
                LocalDate currentDate = startDate;
                while (!currentDate.isAfter(endDate)) {
                    int dayOfWeek = currentDate.getDayOfWeek().getValue();
                    if (dayOfWeek >= 1 && dayOfWeek <= 5) {
                        totalCost += weekdayRate;
                    } else {
                        totalCost += weekendRate;
                    }
                    currentDate = currentDate.plusDays(1);
                }
                if (totalCost < minCost || (totalCost == minCost && hotel.getRating() < maxRating)) {
                    minCost = totalCost;
                    cheapestBestRatedHotel = hotel.getName();
                    maxRating = hotel.getRating();
                }
            }
            System.out.println("--------------------------------------");
            System.out.println(cheapestBestRatedHotel + ", Rating: " + maxRating + " and Total Rates: $" + minCost);
            System.out.println("--------------------------------------");
        }

    }

    public void findCheapestBestRatedHotelForRewardCustomer(Map<String, Hotels> hotels) {
        System.out.println("Enter start date (YYYY MM DD): ");
        int startYear = s.nextInt();
        int startMonth = s.nextInt();
        int startDay = s.nextInt();
        System.out.println("Enter end date (YYYY MM DD): ");
        int endYear = s.nextInt();
        int endMonth = s.nextInt();
        int endDay = s.nextInt();

        LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
        LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

        Hotels cheapestBestRatedHotel = hotels.values().stream()
                .filter(hotel -> hotel.getWeekDayReward() > 0 && hotel.getWeekEndReward() > 0)
                .min((hotel1, hotel2) -> {
                    int totalCost1 = calculateTotalCostForRewardCustomer(hotel1, startDate, endDate);
                    int totalCost2 = calculateTotalCostForRewardCustomer(hotel2, startDate, endDate);
                    if (totalCost1 != totalCost2) {
                        return Integer.compare(totalCost1, totalCost2);
                    } else {
                        return Integer.compare(hotel2.getRating(), hotel1.getRating());
                    }
                })
                .orElse(null);

        if (cheapestBestRatedHotel != null) {
            int totalCost = calculateTotalCostForRewardCustomer(cheapestBestRatedHotel, startDate, endDate);
            System.out.println("Cheapest Best Rated Hotel: " + cheapestBestRatedHotel.getName() +
                    ", Rating: " + cheapestBestRatedHotel.getRating() + ", Total Rates: $" + totalCost);
        } else {
            System.out.println("No hotels found with valid rates.");
        }
    }

    private int calculateTotalCostForRewardCustomer(Hotels hotel, LocalDate startDate, LocalDate endDate) {
        int totalCost = 0;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            int dayOfWeek = date.getDayOfWeek().getValue();
            if (dayOfWeek >= 1 && dayOfWeek <= 5) {
                totalCost += hotel.getWeekDayReward();
            } else {
                totalCost += hotel.getWeekEndReward();
            }
        }
        return totalCost;
    }
    public void findCheapestBestRatedHotelForRegularCustomer(Map<String, Hotels> hotels) {
        System.out.println("Enter start date (YYYY MM DD): ");
        int startYear = s.nextInt();
        int startMonth = s.nextInt();
        int startDay = s.nextInt();
        System.out.println("Enter end date (YYYY MM DD): ");
        int endYear = s.nextInt();
        int endMonth = s.nextInt();
        int endDay = s.nextInt();

        LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
        LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

        Hotels cheapestBestRatedHotel = hotels.values().stream()
                .filter(hotel -> hotel.getWeekDayRate() > 0 && hotel.getWeekEndRate() > 0)
                .min((hotel1, hotel2) -> {
                    int totalCost1 = calculateTotalCostForRegularCustomer(hotel1, startDate, endDate);
                    int totalCost2 = calculateTotalCostForRegularCustomer(hotel2, startDate, endDate);
                    if (totalCost1 != totalCost2) {
                        return Integer.compare(totalCost1, totalCost2);
                    } else {
                        return Integer.compare(hotel2.getRating(), hotel1.getRating());
                    }
                })
                .orElse(null);

        if (cheapestBestRatedHotel != null) {
            int totalCost = calculateTotalCostForRegularCustomer(cheapestBestRatedHotel, startDate, endDate);
            System.out.println("Cheapest Best Rated Hotel: " + cheapestBestRatedHotel.getName() +
                    ", Rating: " + cheapestBestRatedHotel.getRating() + ", Total Rates: $" + totalCost);
        } else {
            System.out.println("No hotels found with valid rates.");
        }
    }
    private int calculateTotalCostForRegularCustomer(Hotels hotel, LocalDate startDate, LocalDate endDate) {
        int totalCost = 0;
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            int dayOfWeek = currentDate.getDayOfWeek().getValue();
            if (dayOfWeek >= 1 && dayOfWeek <= 5) {
                totalCost += hotel.getWeekDayRate();
            } else {
                totalCost += hotel.getWeekEndRate();
            }
            currentDate = currentDate.plusDays(1);
        }
        return totalCost;
    }
}
