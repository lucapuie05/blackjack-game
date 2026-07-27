import java.util.*;

public class Deck {

    private Deque<Card> deck = new ArrayDeque<>();


    public Deck(){

        Suit[] suit = Suit.values();
        Rank[] rank = Rank.values();

        for(int i = 0; i < suit.length; i++){
            for(int j = 0; j < rank.length; j++){
                deck.push(new Card(suit[i], rank[j]));
            }
        }
    }


    public void shuffle(){

        List<Card> cardList = new ArrayList<>(deck);
        Collections.shuffle(cardList);
        deck.clear();
        for(int i = 0; i < cardList.size(); i++){
            deck.push(cardList.get(i));
        }
    }


    public boolean isEmpty(){
        return deck.isEmpty();
    }


    public Card drawCard(){
        return deck.removeFirst();
    }


    public int remainingCards(){
        return deck.size();
    }

}
