package com.company;
// import necessary packages
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
//the 5 letter dictionary, can be filled with every 5-letter word, or just the ones from the 3000 most common words
public class Dictionary {
    ArrayList<String> wordList;
    ArrayList<Country> countryList;
    //creates a new dictionary with an empty arraylist
    public Dictionary() {
        wordList = new ArrayList<>();
        countryList = new ArrayList<>();
    }
    //Fills the dictionary with the 5-letter words from the 3000 most common words
    public void Fill3000() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("Top30005's.txt"));
        // read entire line as string
        String line = bf.readLine();
        // checking for end of file
        while (line != null) {
            wordList.add(line);
            line = bf.readLine();
        }
        // closing bufferReader object
        bf.close();
    }

    //Fills the dictionary with every 5-letter word
    public void fillAll() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("all5Letter.txt"));
        // read entire line as string
        String line = bf.readLine();
        // checking for end of file
        while (line != null) {
            wordList.add(line);
            line = bf.readLine();
        }
        // closing bufferReader object
        bf.close();
    }
    //Fills the dictionary with the Alphabet.txt
    public void fillAlpha() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("Alphabet.txt"));
        // read entire line as string
        String line = bf.readLine();
        // checking for end of file
        while (line != null) {
            wordList.add(line);
            line = bf.readLine();
        }
        // closing bufferReader object
        bf.close();
    }
    //Fills the dictionary with Countries
    public void fillCountries() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("Countries.txt"));
        // read entire line as string
        Country line = new Country(bf.readLine());
        // checking for end of file
        for (int i = 0; i < (Files.lines(Paths.get("Countries.txt")).count() - 1); i++) {
            countryList.add(line);
            line = new Country(bf.readLine());
        }

        // closing bufferReader object
        bf.close();
    }

}
