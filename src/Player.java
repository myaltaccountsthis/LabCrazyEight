import java.util.ArrayList;
import java.util.Scanner;

public class Player{
    
    private ArrayList<Card> hand; // the player's hand
    private Scanner input;

    public Player(){
        // your code here
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        // your code here
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    public Card playsTurn(Deck deck){
        // your code here
    }

    
    // Accessor for the players hand
    public ArrayList<Card> getHand(){
        // your code here
    }

    // Returns a printable string representing the player's hand
    public String handToString(){
        // your code here
    }

    // you will likely wish to have several more helper methods to simplify
    // and shorten the methods above.

} // end
