package com.company;
import java.io.IOException;
import java.util.Scanner;
//the interface for the user to play the game
public class GameMenu {
    Game game = new Game();
    //creates a new game
    public GameMenu() throws IOException {
        int check = -1;
        String wordInput; //the input from the user (string so it doesn't crash if an int)
        boolean isInt; //if the input is an int
        Scanner input = new Scanner(System.in);
        while (check != 0) {
            isInt = false;
            wordInput = " ";
            while (!isInt){
                //check what the user wants to do
                System.out.print("type 1 for single player, 2 for multiplayer, 3 for Worldle, 4 for Worldle Multiplayer" +
                        " 0 to quit: ");
                wordInput = input.nextLine(); //gets the user input
                Scanner intCheck = new Scanner(wordInput); //
                isInt = intCheck.hasNextInt();
            }
            check = Integer.parseInt(wordInput);
            if (check == 1) { //plays a single player game as defined in Game
                game.playWordleSinglePlayer();
                check = -1;

            } else if (check == 2) {
                game.playWordleMultiPlayer(); //plays a multiplayer game as defined in Game
                check = -1;
            } else if (check == 3) {
                game.playWorldleSinglePlayer(); //plays a single player game as defined in Game
                check = -1;
            } else if (check == 4) {
                game.playWorldleMultiplayer(); //plays a multiplayer game as defined in Game
                check = -1;
            } else if (check == 0) {
                System.out.println("Thanks for playing!"); //exits the program
            } else {
                System.out.println("please enter a valid number"); //alerts the user that the number was invalid
            }
        }
    }
}
