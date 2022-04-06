public class Card{
    
    private char suit;
    private int rank;

    // Initializes a card instance
    public Card(char suit, int rank){
        // your code here
        this.suit = suit;
        this.rank = rank;
    }

    // Accessor for suit
    public char getSuit(){
        // your code here;
        return this.suit;
    }
    
    // Accessor for rank
    public int getRank(){
        // your code here;
        return this.rank;
    }

    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString(){
        // your code here
    }
}