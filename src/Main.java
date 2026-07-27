public class Main {

// Blackjack game logic

//Classes: Card, HumanPlayer, Deck, Dealer, Game, GameStatistics??
//Abstract Classes : Player
//Logic of the game: We have a dealer and 1 player.We play basic blackjack without split(who has more or 21 wins). The classes Player and dealer represents the dealer and the player.
//The class card represents every card of the deck(type,value, color). The class deck represents the deck of cards for blackjack(52 cards), must have shuffle, should be representet as a stack
//Class Game is where the whole logic of the game will be implemented.The logic is that it will take input from the comand line if the players want to hit or stay.
//After every game there will retunr if the player won or lost and a question if he wants to play again.Also the dealer needs to hit a card if he has under 17 points

    public static void main(String[] args) {

        Card card1 = new Card(Suit.Diamonds, Rank.Ace);

        Deck deck = new Deck();
        System.out.println(deck.remainingCards());
        while(!deck.isEmpty()){
            System.out.println(deck.drawCard());
        }

        Deck deck1 = new Deck();
        System.out.println("--------------------------------------");
        deck1.shuffle();
        System.out.println(deck1.remainingCards());
        while(!deck1.isEmpty()){
            System.out.println(deck1.drawCard());
        }

    }
}