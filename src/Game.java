import java.util.Scanner;

public class Game {

    private GameStatistics statistics = new GameStatistics();
    private Scanner sc = new Scanner(System.in);

    public void startGame(){

        System.out.println("===================================");
        System.out.println("BLACKJACK");
        System.out.println("===================================");

        System.out.println();
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
                if(!endRound()){
                    return;
                }
            }else if(checkBlackjack(player) && checkBlackjack(dealer)){
                System.out.println("You both have BLACKJACK! Draw!");
                statistics.addDraw();
                if(!endRound()){
                    return;
                }
            }else if(!checkBlackjack(player) && checkBlackjack(dealer)){
                System.out.println("Dealer has BLACKJACK! You lost!");
                statistics.addLoss();
                if(!endRound()){
                    return;
                }
            } else {
                boolean playerBust = playerTurn(player,deck);
                if(playerBust){
                    if(!endRound()){
                        return;
                    }
                }else {
                    boolean dealerBust = dealerTurn(dealer, deck);
                    if(dealerBust){
                        System.out.println("Dealer bust! You won!");
                        System.out.println("Dealer score:" + dealer.calculateScore());
                        if(!endRound()){
                            return;
                        }
                    }else {
                        calculateWinner(player, dealer);
                        if(!endRound()){
                            return;
                        }
                    }
                }
            }



        }while (true);

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

        System.out.print("Your cards are: ");
        for(int i = 0; i < 2; i++) {

            Card playerCard = deck.drawCard();
            System.out.print(playerCard.toString() + "  ");
            player.addCard(playerCard);


            Card dealerCard = deck.drawCard();

            dealer.addCard(dealerCard);
        }
        System.out.println();
    }


    private boolean playerTurn(Player player, Deck deck){

        while(player.calculateScore() < 21){

            System.out.println("Your score is: " + player.calculateScore());
            String answer;

            do{
                System.out.println("Hit or Stand? ");
                answer = sc.nextLine().toLowerCase();

                if (!(answer.equals("hit") || answer.equals("stand"))) {
                    System.out.println("Invalid input! Please enter 'hit' or 'stand'.");
                }

            }while(!(answer.equals("hit") || answer.equals("stand")));



            if(answer.equals("hit")){
                Card playerCard = deck.drawCard();
                System.out.println(playerCard.toString());
                player.addCard(playerCard);
                if(player.calculateScore() > 21){
                    System.out.println("BUST! You lost!");
                    System.out.println("Your score is: " + player.calculateScore());
                    statistics.addLoss();
                    return true;
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


        String answer;
        do{
            System.out.println("Do you want to play again? YES or NO?");
            answer = sc.nextLine().toLowerCase();

            if(!(answer.equals("yes") || answer.equals("no"))){
                System.out.println("Invalid input! Please enter 'yes' or 'no'.");
            }

        }while (!(answer.equals("yes") || answer.equals("no")));

        if(answer.equals("yes")){
            return true;
        }else{
            System.out.println("Thank you for participating!");
            System.out.println("GOODBYE!");
            return false;
        }
    }

    private boolean checkBlackjack(Player player){

        return player.calculateScore() == 21;
    }

    private boolean endRound(){
        System.out.println(statistics.toString());
        return askPlayer();
    }
}
