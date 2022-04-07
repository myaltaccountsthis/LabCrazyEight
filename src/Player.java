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
        // this should repeat until any card inside of the hand is chosen
        // print player's hand
        System.out.println("Your cards are " + hand);

        int nextPlayIndex;
        while (true) {
            System.out.print("Enter the number of which card to choose: ");
            while (true) {
                nextPlayIndex = input.nextInt();
                if (nextPlayIndex >= 1 && nextPlayIndex <= hand.size())
                    break;
                System.out.print("Invalid number. Enter the number of which card to choose: ");
            }
            Card c = hand.get(nextPlayIndex - 1);
            System.out.print("You chose " + c + ". Confirm? (y/n): ");
            if (input.next().equalsIgnoreCase("y"))
                return c;
        }
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
