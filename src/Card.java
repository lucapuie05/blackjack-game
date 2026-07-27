public class Card {

    final private Suit suit;
    final private Rank rank;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit(){
        return this.suit;
    }

    public Rank getRank(){
        return this.rank;
    }

    public int getValue(){
        return this.rank.getValue();
    }

    @Override
    public String toString(){
        return this.rank+ " of " + this.suit;
    }
}
