package com.company;

import java.util.ArrayList;

public class Checker {
    private static ArrayList<String> positives; //Array of all the letters that you know are yellow or green
    private static final int MAX_DISTANCE_ON_EARTH = 20000000; //the maximum distance something can be from something else, in meters
    private static final double RADIUS = 6371; //radius of the earth, in km
    public Checker(){
        positives = new ArrayList<>(); //initializes positives
    }
    //return an array of chars that represent colors, yellow meaning the letter is in the word but not the position,
    //green meaning the letter is at the position, and red meaning the letter is not in the word.
    public static char[] getColorArray(String userWord, String gameWord, Keyboard keys){
        char[] results = new char[5];
        for (int i = 0; i < 5; i++) {
            boolean contains = gameWord.contains(userWord.substring(i, i + 1)); //boolean to see if the letter is in the word
            if (contains && gameWord.charAt(i) != userWord.charAt(i)) {
                results[i] = 'y';
                for (int j = 0; j < positives.size(); j++) {
                    if (positives.get(j).equals(userWord.charAt(i) + "(Y)")) { //checks if the letter is already yellow
                        positives.remove(j);
                        j--;
                    }
                }
                positives.add(userWord.charAt(i) + "(Y)"); //add yellow to the array
            }
            else if (gameWord.charAt(i) == userWord.charAt(i)) { //checks if the letter is at the position
                results[i] = 'g';
                for (int j = 0; j < positives.size(); j++) {
                   if (positives.get(j).equals(userWord.charAt(i) + "(Y)") ||
                           positives.get(j).equals(userWord.charAt(i) + "(G)")) { //checks if the letter is already there
                       positives.remove(j);
                       j--;
                    }
                }
                positives.add(userWord.charAt(i) + "(G)"); //add green to the array
            } else {
                results[i] = 'r';
                keys.denyLetter(userWord.substring(i, i + 1));
            }
        }
        return results;
    }
    public static double getDistance (Country c1, Country c2) {
        double distance;
        // Convert degrees to radians
        double lat1Rad = Math.toRadians(c1.getLat());
        double lon1Rad = Math.toRadians(c1.getLon());
        double lat2Rad = Math.toRadians(c2.getLat());
        double lon2Rad = Math.toRadians(c2.getLon());

        // Calculate the differences between the latitudes and longitudes
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Apply the Haversine formula
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        distance = RADIUS * c;
        return distance;
    }
    public static String getDirection(Country c1, Country c2) {
        double lat1 = c1.getLat(); // Latitude of the first point
        double lon1 = c1.getLon(); // Longitude of the first point
        double lat2 = c2.getLat(); // Latitude of the second point
        double lon2 = c2.getLon();  // Longitude of the second point

        String di = "";

        if (lat1 > lat2)
            di = di + "↑"; // North
        else
            di = di +  "↓"; // South
        if(lon1 > lon2)
            di = di +  " and →"; // East
        else
            di = di +  " and ←"; // West

        return di;
    }

    public static int getProximity(Country c1, Country c2) {
        double proximity;
        double meters;
        meters = getDistance(c1, c2) * 1000; //meters are used here in the original formula
        proximity = (100 * (MAX_DISTANCE_ON_EARTH - meters) / MAX_DISTANCE_ON_EARTH); //Formula used by original game
        return (int) (proximity); //return as a whole number
    }

    public static ArrayList<String> getPositives() {
        return positives;
    } //returns the positives array

    public void setPositives(){
        positives = new ArrayList<>();
    } //resets the positives array
}
