import java.util.ArrayList;
import java.util.Scanner;

public class Player{
    
    private ArrayList<Card> hand; // the player's hand
    private Scanner input;

    public Player(){
        // your code here
        hand = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        hand.add(c);
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    public Card playsTurn(Deck deck){
        // print player's hand
        System.out.println("It is now your turn, your cards are " + hand);
        // TODO modify this thing to include index

        int nextPlayIndex = -1;

        // TODO prob gonna check drawing cards in game


        System.out.print("Enter index of card");
        // TODO use scanner and get input
        // TODO i will probably check if rank and suit are valid
        // TODO also remember to ask for new suit if card played is 8
    }

    
    // Accessor for the players hand
    public ArrayList<Card> getHand(){
       return hand;
    }

    // Returns a printable string representing the player's hand
    public String handToString(){
        String str = hand.toString();
        return "" + str;
    }

    // you will likely wish to have several more helper methods to simplify
    // and shorten the methods above.

} // end
