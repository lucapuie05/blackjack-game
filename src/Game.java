import java.util.Scanner;

public class Game {

    private GameStatistics statistics = new GameStatistics();
    private Scanner sc = new Scanner(System.in);

    public void startGame(){

        boolean answer =true;
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        do{

            Deck deck = new Deck();
            deck.shuffle();

            Player player = new HumanPlayer(name);
            Player dealer = new Dealer();

            drawFirstTwoCards(player,dealer,deck);
            if(checkBlackjack(player) && !checkBlackjack(dealer)){
                System.out.println("BLACKJACK! You won!");
                statistics.addWin();
                System.out.println(statistics.toString());
                answer = askPlayer();
                if(!answer){
                    return;
                }
            }else if(checkBlackjack(player) && checkBlackjack(dealer)){
                System.out.println("You both have BLACKJACK! Draw!");
                statistics.addDraw();
                System.out.println(statistics.toString());
                answer = askPlayer();
                if(!answer){
                    return;
                }
            }else if(!checkBlackjack(player) && checkBlackjack(dealer)){
                System.out.println("Dealer has BLACKJACK! You lost!");
                statistics.addLoss();
                System.out.println(statistics.toString());
                answer = askPlayer();
                if(!answer){
                    return;
                }
            } else {
                boolean playerBust = playerTurn(player,deck);
                if(playerBust){
                    System.out.println(statistics.toString());
                    answer = askPlayer();
                    if(!answer){
                        return;
                    }
                }else {
                    boolean dealerBust = dealerTurn(dealer, deck);
                    if(dealerBust){
                        System.out.println("Dealer bust! You won!");
                        System.out.println("Dealer score:" + dealer.calculateScore());
                        System.out.println(statistics.toString());
                        answer = askPlayer();
                        if(!answer){
                            return;
                        }
                    }else {
                        calculateWinner(player, dealer);
                        System.out.println(statistics.toString());
                        answer = askPlayer();
                        if(!answer){
                            return;
                        }
                    }
                }
            }



        }while (answer);

    }


    //HELPER METHODS

    private void calculateWinner(Player player, Player dealer){
        if(player.calculateScore() > dealer.calculateScore()){
            System.out.println("You won!");
            System.out.println("Your score:" + player.calculateScore());
            System.out.println("Dealer score:" + dealer.calculateScore());
            statistics.addWin();
        }else if(player.calculateScore() < dealer.calculateScore()){
            System.out.println("You lost! Dealer won!");
            System.out.println("Dealer score: " + dealer.calculateScore());
            System.out.println("Your score:" + player.calculateScore());
            statistics.addLoss();
        }else {
            System.out.println("Draw!");
            System.out.println("Your score:" + player.calculateScore());
            System.out.println("Dealer score:" + dealer.calculateScore());
            statistics.addDraw();
        }
    }


    private void drawFirstTwoCards(Player player, Player dealer, Deck deck){
        for(int i = 0; i < 2; i++) {

            Card playerCard = deck.drawCard();
            System.out.println(playerCard.toString());
            player.addCard(playerCard);


            Card dealerCard = deck.drawCard();
            System.out.println(dealerCard.toString());
            dealer.addCard(dealerCard);
        }
    }


    private boolean playerTurn(Player player, Deck deck){

        boolean playerBust = false;

        while(player.calculateScore() < 21){

            System.out.println("Your score is: " + player.calculateScore());
            System.out.println("Hit or Stand? ");
            String answer = sc.nextLine();
            answer = answer.toLowerCase();

            if(answer.equals("hit")){
                Card playerCard = deck.drawCard();
                System.out.println(playerCard.toString());
                player.addCard(playerCard);
                //player.addCard(deck.drawCard());
                if(player.calculateScore() > 21){
                    System.out.println("BUST! You lost!");
                    statistics.addLoss();
                    playerBust = true;
                    return playerBust;
                }
            }else {
                return false;
            }
        }
        return false;
    }


    private boolean dealerTurn(Player dealer, Deck deck){
        while(dealer.calculateScore() < 17){
            dealer.addCard(deck.drawCard());
            if(dealer.calculateScore() > 21){
                statistics.addWin();
                return true;
            }
        }
        return false;
    }


    private boolean askPlayer(){
        System.out.println("Do you want to play again? YES or NO?");
        String answer = sc.nextLine();
        answer = answer.toLowerCase();
        if(answer.equals("yes")){
            return true;
        }else{
            System.out.println("Thank you for participating!");
            System.out.println("GOODBYE!");
            return false;
        }
    }

    private boolean checkBlackjack(Player player){
        if(player.calculateScore() == 21){
            return true;
        }
        return false;
    }
}
