import java.util.Scanner;

public class Game {



    public void startGame(){

        Deck deck = new Deck();
        deck.shuffle();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        Player player = new HumanPlayer(name);
        Player dealer = new Dealer();

        for(int i = 0; i < 2; i++) {

            Card playerCard = deck.drawCard();
            player.addCard(playerCard);

            Card dealerCard = deck.drawCard();
            dealer.addCard(dealerCard);
        }

        boolean playerBust = false;

        while(player.calculateScore() < 21){

            System.out.println("Hit or Stand? ");
            String answer = sc.nextLine();
            answer = answer.toLowerCase();

            if(answer.equals("hit")){
                player.addCard(deck.drawCard());
                if(player.calculateScore() > 21){
                    System.out.println("BUST! You lost!");
                    playerBust = true;
                    break;
                }
            }else {
                break;
            }
        }

        if(playerBust){
            System.out.println("Do you wanr to play again? YES or NO?");
            String answer = sc.nextLine();
            answer = answer.toLowerCase();
            if(answer.equals("yes")){
                // method to start a new game
            }else{
                System.out.println("Thank you for participating!");
                System.out.println("GOODBYE!");
                return;
            }
        }

        while(dealer.calculateScore() < 17){
            dealer.addCard(deck.drawCard());
            if(dealer.calculateScore() > 21){
                System.out.println("Dealer BUST! You won!");
                break;
            }
        }



    }
}
