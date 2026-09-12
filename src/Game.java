import java.util.Scanner;

public class Game {

    private GameStatistics statistics = new GameStatistics();
    private ConsoleUI ui = new ConsoleUI();

    public void startGame(){

        ui.start();
        String name = ui.askName();
        ui.greetPlayer(name);

        int count = 0;

        do{

            count++;
            ui.showRound(count);

            Deck deck = new Deck();
            deck.shuffle();

            Player player = new HumanPlayer(name);
            Player dealer = new Dealer();

            drawFirstTwoCards(player,dealer,deck);
            if(checkBlackjack(player) && !checkBlackjack(dealer)){
                ui.showBlackjackResult("BLACKJACK! You won!");
                statistics.addWin();
                if(!endRound(name)){
                    return;
                }

            }else if(checkBlackjack(player) && checkBlackjack(dealer)){
                ui.showBlackjackResult("You both have BLACKJACK! Draw!");
                statistics.addDraw();
                if(!endRound(name)){
                    return;
                }

            }else if(!checkBlackjack(player) && checkBlackjack(dealer)){
                ui.showBlackjackResult("Dealer has BLACKJACK! You lost!");
                statistics.addLoss();
                if(!endRound(name)){
                    return;
                }

            } else {
                boolean playerBust = playerTurn(player,deck);
                if(playerBust){
                    if(!endRound(name)){
                        return;
                    }
                }else {
                    boolean dealerBust = dealerTurn(dealer, deck);
                    if(dealerBust){
                        ui.dealerBust(dealer.calculateScore());
                        if(!endRound(name)){
                            return;
                        }
                    }else {
                        calculateWinner(player, dealer);
                        if(!endRound(name)){
                            return;
                        }
                    }
                }
            }
        }while (true);

    }


    //HELPER METHODS


    private void calculateWinner(Player player, Player dealer){

        String flag;
        int playerScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();
        if(playerScore > dealerScore){
            flag = "Player";
            ui.showWinner(flag, playerScore, dealerScore);
            statistics.addWin();

        }else if(playerScore < dealerScore){
            flag = "Dealer";
            ui.showWinner(flag, playerScore, dealerScore);
            statistics.addLoss();

        }else {
            flag = "Draw";
            ui.showWinner(flag, playerScore, dealerScore);
            statistics.addDraw();
        }
    }


    private void drawFirstTwoCards(Player player, Player dealer, Deck deck){

        for(int i = 0; i < 2; i++) {

            Card playerCard = deck.drawCard();
            ui.showPlayerCard(playerCard.toString());
            player.addCard(playerCard);


            Card dealerCard = deck.drawCard();

            dealer.addCard(dealerCard);
        }

        ui.showDealerFirstCard(dealer.getCard(0).toString());
    }


    private boolean playerTurn(Player player, Deck deck){

        while(player.calculateScore() < 21){

            ui.showScore(player.calculateScore());
            String answer;

            do{
                answer = ui.askHitOrStand();

                if (!(answer.equals("hit") || answer.equals("stand"))) {
                    ui.errorInputAskCard();
                }
            }while(!(answer.equals("hit") || answer.equals("stand")));



            if(answer.equals("hit")){
                Card playerCard = deck.drawCard();
                ui.showPlayerCard(playerCard.toString());
                player.addCard(playerCard);
                if(player.calculateScore() > 21){
                    ui.showPlayerBust(player.calculateScore());
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

        ui.showDealerSecondCard(dealer.getCard(1).toString());

        while(dealer.calculateScore() < 17){
            Card dealerCard = deck.drawCard();
            dealer.addCard(dealerCard);
            ui.showDealerDraws(dealerCard.toString());
            if(dealer.calculateScore() > 21){
                statistics.addWin();
                return true;
            }
        }
        return false;
    }


    private boolean askPlayer(String name){


        String answer;
        do{

            answer = ui.askPlayer();
            if(!(answer.equals("yes") || answer.equals("no"))){
                ui.errorInputAskGame();
            }

        }while (!(answer.equals("yes") || answer.equals("no")));

        if(answer.equals("yes")){
            return true;
        }else{
            ui.showGoodbye(name);
            return false;
        }
    }

    private boolean checkBlackjack(Player player){

        return player.calculateScore() == 21 && player.getHandSize() == 2;
    }

    private boolean endRound(String name){
        ui.showStatistics(statistics.toString());
        return askPlayer(name);
    }
}
