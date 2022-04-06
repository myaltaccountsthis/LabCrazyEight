import java.util.Scanner;
import java.util.ArrayList;

public class Game{

    private char currentSuit; // need in case an 8 is played
    private Card faceup; 
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    
    // sets up the Game object for play
    public Game(){
        // your code here
        cards = new Deck();
        input = new Scanner(System.in);
        p1 = new Player();
        // TODO set up player hand
        compHand = new ArrayList<>();
        // TODO set up compHand
    }

    public boolean validNextPlay(Card c) {
        return c.getRank() == 8 || c.getRank() == faceup.getRank() || (c.getSuit() == faceup.getSuit() && faceup.getRank() != 8 || c.getSuit() == currentSuit);
    }

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){
        String option;

        // loop should stop if player uses all cards, or deck is exhausted
        while (cards.canDeal()) {
            // TODO do player turn
            // probably check if player has to draw here
            boolean canPlay = false;
            for (int i = 0; i < p1.getHand().size(); i++) {
                if (validNextPlay(p1.getHand().get(i))) {
                    canPlay = true;
                    break;
                }
            }
            // spam draw card if canPlay

            Card c = p1.playsTurn(cards);
            // p1.playsTurn() should return null if the deck is exhausted
            if (c == null) {
                break;
            }
            faceup = c;
            System.out.println("You played " + c);
            if (p1.getHand().size() == 0)
                break;

            c = computerTurn();
            // check if deck is out of cards
            if (c == null) {
                break;
            }
            faceup = c;
            System.out.println("Computer played " + c);
            if (c.getRank() == 8) {
                char choice = Deck.suits[(int) (Math.random() * 4)];
                currentSuit = choice;
                System.out.println("Computer changed suit to " + Deck.getSuitDisplayName(choice));
            }
            if (compHand.size() == 0)
                break;

        }

        // print winner, this block of code will print the correct message
        System.out.println("The game has ended, determining winner...");
        int p1Size = p1.getHand().size(), compSize = compHand.size();
        if (!cards.canDeal()) {
            if (p1Size < compSize)
                System.out.println("You Won! You had " + p1Size + " cards left, while the computer had " + compSize);
            else if (p1Size > compSize)
                System.out.println("You Lose! You had " + p1Size + " cards left, while the computer had " + compSize);
            else
                System.out.println("Draw: Both players had " + p1Size + " cards left");
        }
        else {
            if (compSize == 0)
                System.out.println("You Lose! Computer ran out of cards, you had " + p1Size + " cards left");
            else
                System.out.println("You Won! You ran out of cards, and the computer had " + compSize + " cards left");
        }

        System.out.print("Would you like to play another game? (y/n): ");
        option = input.next();
        return option.equalsIgnoreCase("y");
    }

    /* Naive computer player AI that does one of two actions:
        1) Plays the first card in their hand that is a valid play
        2) If no valid cards, draws until they can play

        You may choose to use a different approach if you wish but
        this one is fine and will earn maximum marks
     */
    private Card computerTurn() {
        int nextPlayIndex = -1;
        // your code here
        for (int i = 0; i < compHand.size(); i++) {
            if (validNextPlay(compHand.get(i))) {
                nextPlayIndex = i;
                break;
            }
        }
        if (nextPlayIndex == -1) {
            int cardsDrawn = 0;
            // keep drawing until computer gets a playable card
            Card c;
            do {
                c = cards.deal();
                if (c == null) {
                    return null;
                }
                compHand.add(c);
                cardsDrawn++;
            } while (!validNextPlay(c));
            System.out.println("Computer drew " + cardsDrawn + " cards");
            // set the next play for computer to the most recent card drawn
            nextPlayIndex = compHand.size() - 1;
        }
        return compHand.remove(nextPlayIndex);
    }
    
   // you will likely wish to have several more helper methods to simplify
   // and shorten the methods above.


}