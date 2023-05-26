package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//the main game Wordle, plays a single player or multiplayer game of Wordle
public class Game {
    private final ArrayList<String> fullDict; //the full 5-letter word dictionary
    private final ArrayList<Country> fullCont; //the full 5-letter word dictionary
    private final Checker check;
    private final Word theWord; //the word that players will try to guess
    private Country theCountry; //the country that players will try to guess
    private String guess; //the player's guess
    private Country countryGuess; //the country that the player guesses
    private boolean gameWon; //True if the player guessed correctly, false otherwise
    private final int numGuesses;

    //creates a new game and instantiates instance data
    public Game() throws IOException {
        Dictionary dict = new Dictionary();
        Dictionary cont = new Dictionary();
        dict.fillAll(); //fill a full 5-letter word dictionary
        cont.fillCountries(); //fill a country dictionary
        fullDict =  dict.wordList; //put the full 5-letter word dictionary into arraylist
        fullCont = cont.countryList; //put all countries into country arraylist
        gameWon = false; //initializes the gameWon to false because the word has not been guessed
        guess = "";  //initializes the guess to empty
        theWord = new Word(); //instantiates the word
        check = new Checker(); //instantiates check
        numGuesses = 7;
    }
    //Plays the game Wordle with a random 5-letter word from the top 3000 most common words
    public void playWordleSinglePlayer() throws IOException {
        check.setPositives();
        gameWon = false;
        String result; //the result of the guess
        Scanner input = new Scanner(System.in);
        theWord.randomWord();
        Keyboard keys = new Keyboard(); //Keys that aren't red
        //plays 6 turns of Wordle, each turn lets the player guess once
        for (int i = 1; i < numGuesses; i++) {
            System.out.print("Guess #" + i + ": "); //shows which guess the player is on
            guess = input.nextLine(); //gets the players guess
            while (!fullDict.contains(guess)) { //loops until the guess is a real word in the full dictionary
                System.out.print("That word doesn't work! Please enter a real lowercase 5-letter word: ");
                guess = input.nextLine();
            }
            result = Arrays.toString(Checker.getColorArray(guess, theWord.getWord(), keys)); //check the guess and create String
            System.out.println(result); //print the result of the guess
            if (result.equalsIgnoreCase("[g, g, g, g, g]")) { //if the player wins, tell them and end the loop
                gameWon = true;
                System.out.println("You win!");
                return;
            }
            System.out.print("Letters not excluded: ");
            for (String s : keys.getLettersPoss()) {
                System.out.print(s);
            }
            System.out.println();
            System.out.print("Letters you know are in the word: ");
            for (String s : Checker.getPositives()) {
                System.out.print(s);
            }
            System.out.println();
        }
        if (!gameWon) { //if the player lost, tell them the word
            System.out.println("You lose. The word was " + theWord.getWord());
        }
    }

    //Plays the game Wordle where one player enters the word,
    // then lines are printed to obstruct the word from player 2's vision, and player 2 tries to guess the word
    public void playWordleMultiPlayer() throws IOException {
        check.setPositives();
        gameWon = false;
        String result; //the result of the guess
        Scanner input = new Scanner(System.in);
        Keyboard keys = new Keyboard();//Keys that aren't red
        System.out.print("Please enter a real lowercase 5-letter word: "); //prompts player 1 to enter a word
        theWord.setWord(input.nextLine());
        while (!fullDict.contains(theWord.getWord())) { //loops until player 1 enters a real word
            System.out.print("That word doesn't work! Please enter a real lowercase 5-letter word: ");
            theWord.setWord(input.nextLine());
        }
        for (int i = 0; i < 20; i++) //obstructs the word from player 2's view
            System.out.println("/////////////////////////////////////////");
        for (int i = 1; i < numGuesses; i++) {
            System.out.print("Guess #" + i + ": "); //shows which guess the player is on
            guess = input.nextLine(); //gets the players guess
            while (!fullDict.contains(guess)) { //loops until the guess is a real word in the full dictionary
                System.out.print("That word doesn't work! Please enter a real lowercase 5-letter word: ");
                guess = input.nextLine();
            }
            result = Arrays.toString(Checker.getColorArray(guess, theWord.getWord(), keys)); //check the guess and create String
            System.out.println(result); //print the result of the guess
            System.out.print("Letters not excluded: ");
            for (String s : keys.getLettersPoss()) {
                System.out.print(s);
            }
            System.out.println();
            System.out.print("Letters you know are in the word: ");
            for (String s : Checker.getPositives()) {
                System.out.print(s);
            }
            System.out.println();
            if (result.equalsIgnoreCase("[g, g, g, g, g]")) { //if the player wins, tell them and end the loop
                gameWon = true;
                System.out.println("You win!");
                return;
            }
        }
        if (!gameWon) { //if the player lost, tell them the word
            System.out.println("You lose. The word was " + theWord.getWord());
        }
    }
    public void playWorldleSinglePlayer() throws IOException {
        gameWon = false;
        Scanner input = new Scanner(System.in);
        countryGuess = null;
        theCountry = Country.randomCountry();
        for(int i = 1; i < numGuesses; i++) {
            while (countryGuess == null){
                System.out.print("Guess #" + i + ": ");
                guess = input.nextLine();
                for (Country country : fullCont) {
                    if (country.getName().equalsIgnoreCase("\"" + guess + "\"") || country.getCode().equalsIgnoreCase("\"" + guess + "\"")) {
                        countryGuess = country;
                    }
                }
            }
            if (((int)(Checker.getDistance(theCountry, countryGuess)) == 0)) {
                gameWon = true;
                System.out.println("You win!");
                return;
            }
            System.out.println("Distance from the country " + (int)(Checker.getDistance(theCountry, countryGuess))
                    + "km, direction: " + Checker.getDirection(theCountry, countryGuess) + " Proximity rating: " +
                    Checker.getProximity(theCountry, countryGuess) + "%");
            countryGuess = null;
        }
        System.out.println("You lose! The country was " + theCountry.getName());
    }

    public void playWorldleMultiplayer(){
        gameWon = false;
        Scanner input = new Scanner(System.in);
        countryGuess = null;
        theCountry = null;
        while (theCountry == null) {
            System.out.print("Please enter the name of a country, or country code: ");
            guess = input.nextLine();
            for (Country country : fullCont) {
                if (country.getName().equalsIgnoreCase("\"" + guess + "\"") || country.getCode().equalsIgnoreCase("\"" + guess + "\"")) {
                    theCountry = country;
                }
            }
        }

        for (int i = 0; i < 20; i++) //obstructs the word from player 2's view
            System.out.println("/////////////////////////////////////////");

        for(int i = 1; i < numGuesses; i++) {
            while (countryGuess == null){
                System.out.print("Guess #" + i + ": ");
                guess = input.nextLine();
                for (Country country : fullCont) {
                    if (country.getName().equalsIgnoreCase("\"" + guess + "\"") || country.getCode().equalsIgnoreCase("\"" + guess + "\"")) {
                        countryGuess = country;
                    }
                }
            }
            if (((int)(Checker.getDistance(theCountry, countryGuess)) == 0)) {
                gameWon = true;
                System.out.println("You win!");
                return;
                //"Canada"
                //"Mexico"
            }
            System.out.println("Distance from the country " + (int)(Checker.getDistance(theCountry, countryGuess))
                    + "km, direction: " + Checker.getDirection(theCountry, countryGuess) + " Proximity rating: " +
                    Checker.getProximity(theCountry, countryGuess) + "%");
            countryGuess = null;
        }
        System.out.println("You lose! The country was " + theCountry.getName());
    }
}
