package com.company;
import java.io.IOException;

public class Country {
    public String name;
    public String code;
    public double lat;
    public double lon;
    //constructor of country object, parses a string to get the code, name, latitude, and longitude
    public Country (String c) {
        String[] strings;
        strings = c.split(",");
        code = strings[0];
        name = strings[1];
        lat = Double.parseDouble(strings[2]);
        lon = Double.parseDouble(strings[3]);
    }

    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public double getLat() {
        return lat;
    }
    public double getLon() {
        return lon;
    }
    public String toString() {
        return getCode() + "," + getName() + "," + getLat() + "," + getLon();
    }
    //gets a random country from the country list
    public static Country randomCountry() throws IOException {
        int num;
        Dictionary cont = new Dictionary();
        cont.fillCountries();
        num = (int) (Math.random() * cont.countryList.size() + 1);
        return cont.countryList.get(num);
    }
}
