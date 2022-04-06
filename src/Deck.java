import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck{

    private Card[] deck; // contains the cards to play with
    private int top; // controls the "top" of the deck to deal from

    public static final char[] suits = {'h', 'd', 'c', 's'};
    private static final String[] suitNames = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] rankNames = {"Ace", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Jack", "Queen", "King"};

    public static String getSuitDisplayName(char suit) {
        for (int i = 0; i < suits.length; i++) {
            if (suit == suits[i]) {
                return suitNames[i];
            }
        }
        return "Invalid Suit";
    }

    public static String getRankDisplayName(int rank) {
        // return minus one because rank starts at 1 while array starts at 0
        return rankNames[rank - 1];
    }

    // constructs a default Deck
    public Deck(){
        // your code here
        deck = new Card[52];
        for (int i = 0; i < 4; i++) {
            for (int r = 1; r <= 13; r++) {
                deck[i * 13 + r - 1] = new Card(suits[i], r);
            }
        }
        shuffle();
    }

    // Deals the top card off the deck
    public Card deal(){
        // your code here
        if (canDeal())
            return deck[top++];
        return null;
    }


    // returns true provided there is a card left in the deck to deal
    public boolean canDeal(){
        // your code here
        return top < deck.length;
    }

    // Shuffles the deck
    public void shuffle(){
        // your code here
        List<Card> list = Arrays.asList(deck);
        Collections.shuffle(list);
        list.toArray(this.deck);
    }

    // Returns a string representation of the whole deck
    public String toString(){
       // your code here
    }

    // you may wish to have more helper methods to simplify
    // and shorten the methods above.
}