package com.company;

import java.io.IOException;
//a word that can be randomly generated, get, or set
public class Word {
    private String word;
    public Word () {
        word = "";
    }

    //generates a random 5-letter word from the top 3000 words
    public void randomWord() throws IOException {
        int randNum;
        Dictionary dict = new Dictionary();
        dict.Fill3000();
        randNum = (int) (Math.random() * dict.wordList.size() + 1);
        word = dict.wordList.get(randNum);
    }

    //returns the string word
    public String getWord() {
        return word;
    }

    //sets the string to the parameter
    public void setWord(String w) {
        word = w;
    }
}
