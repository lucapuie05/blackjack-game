import java.util.*;

abstract public class Player {

    private List<Card> hand = new ArrayList<>();

    public void addCard(Card card){
        hand.add(card);
    }

    public int calculateScore(){

        int score = 0;
        int count = 0;

        for(int i = 0; i < hand.size(); i++){
            Card currentCard = hand.get(i);

            if(!(currentCard.getRank().getValue() == 11)){
                score += currentCard.getValue();
            }else{
                score += 11;
                count++;
            }
        }

        while(score > 21 && count != 0){
            score -= 10;
            count--;
        }
        return score;
    }

    public int getHandSize(){
        return hand.size();
    }

    public Card getCard(int index){
        return hand.get(index);
    }
}
