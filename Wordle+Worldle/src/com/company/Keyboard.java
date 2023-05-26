package com.company;
import java.io.IOException;
import java.util.ArrayList;
public class Keyboard {
    private final ArrayList<String> lettersPossible;
    //creates a new Keyboard object with the full alphabet, instantiates arrays, adds full alphabet to lettersAllowed because
    //all letters can be used in the first guess
    public Keyboard() throws IOException {
        Dictionary alphabet = new Dictionary();
        alphabet.fillAlpha();
        lettersPossible = new ArrayList<>();
        lettersPossible.addAll(alphabet.wordList);
    }
    //make a letter not possible
    public void denyLetter (String let) {
        for (int i = 0; i < lettersPossible.size(); i++) {
            if (lettersPossible.get(i).equalsIgnoreCase(let)) {
                lettersPossible.remove(i);
                i--;
            }
        }
    }
    public ArrayList<String> getLettersPoss() {
        return lettersPossible;
    }

}
