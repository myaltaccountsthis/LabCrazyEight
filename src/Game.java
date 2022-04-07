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
        for (int i = 0; i < 7; i++) {
            p1.getHand().add(cards.deal());
        }
        compHand = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            compHand.add(cards.deal());
        }
    }

    public boolean validNextPlay(Card c) {
        return c.getRank() == 8 || c.getRank() == faceup.getRank() || (c.getSuit() == faceup.getSuit() && faceup.getRank() != 8 || c.getSuit() == currentSuit);
    }

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){
        System.out.println("Starting new game of Crazy Eights");
        faceup = cards.deal();
        System.out.println("First card is " + faceup);
        // loop should stop if player uses all cards, or deck is exhausted
        while (cards.canDeal()) {
            System.out.println("It is now your turn");
            // probably check if player has to draw here
            boolean canPlay = false;
            for (int i = 0; i < p1.getHand().size(); i++) {
                if (validNextPlay(p1.getHand().get(i))) {
                    canPlay = true;
                    break;
                }
            }
            // spam draw card if not canPlay
            if (!canPlay) {
                System.out.println("You have no available moves, drawing cards");
                int cardsDrawn = 0;
                do {
                    Card c = cards.deal();
                    if (c == null)
                        break;
                    p1.addCard(c);
                    canPlay = validNextPlay(c);
                    cardsDrawn++;
                } while (!canPlay);
                System.out.println("You drew " + cardsDrawn + " cards");
            }
            // ask player which card to choose
            Card c;
            while (true) {
                c = p1.playsTurn(cards);
                canPlay = validNextPlay(c);
                if (canPlay)
                    break;
                System.out.println("Your choice of " + c + " is invalid. Please try again");
            }
            if (c.getRank() == 8) {
                // prompt user for new suit
                System.out.print("Enter your desired suit: ");
                String nextSuit;
                while (true) {
                    nextSuit = input.next();
                    String suitName = Deck.getSuitDisplayName(nextSuit.charAt(0));
                    if (!suitName.equals("Invalid Suit")) {
                        currentSuit = nextSuit.charAt(0);
                        break;
                    }
                    System.out.print("Invalid suit. Enter your desired suit: ");
                }
                System.out.println("You changed the suit to " + Deck.getSuitDisplayName(currentSuit));
            }
            else {
                currentSuit = c.getSuit();
            }
            faceup = c;
            p1.getHand().remove(faceup);
            System.out.println("You played " + c);
            if (p1.getHand().size() == 0)
                break;

            // computer turn
            c = computerTurn();
            // check if deck is out of cards
            if (c == null) {
                break;
            }
            faceup = c;
            System.out.println("Computer played " + c + ", now has " + compHand.size() + " cards left");
            if (c.getRank() == 8) {
                char choice = Deck.suits[(int) (Math.random() * 4)];
                currentSuit = choice;
                System.out.println("Computer changed suit to " + Deck.getSuitDisplayName(choice));
            }
            else {
                currentSuit = c.getSuit();
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
                System.out.println("You Lose! HaHa, what a noob! You had " + p1Size + " cards left, while the computer had " + compSize);
            else
                System.out.println("Draw: Both players had " + p1Size + " cards left");
        }
        else {
            if (compSize == 0)
                System.out.println("You Lose! HaHa, what a noob! Computer ran out of cards, you had " + p1Size + " cards left");
            else
                System.out.println("Wow! You actually Won! Your an epic gamer now! ヾ(⌐■_■)ノ♪You ran out of cards, and the computer had " + compSize + " cards left");
        }

        System.out.print("Would you like to play another game? (⌐■_■) (y/n): ");
        String option = input.next();
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
            System.out.println("Computer drew " + cardsDrawn + " cards, but that doesn't mean the game is over...");
            // set the next play for computer to the most recent card drawn
            nextPlayIndex = compHand.size() - 1;
        }
        return compHand.remove(nextPlayIndex);
    }
    
   // you will likely wish to have several more helper methods to simplify
   // and shorten the methods above.


}