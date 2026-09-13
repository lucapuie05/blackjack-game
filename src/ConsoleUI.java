import java.util.Scanner;

public class ConsoleUI {

    private Scanner sc = new Scanner(System.in);

    public void start(){
        System.out.println("===================================");
        System.out.println("            BLACKJACK");
        System.out.println("===================================");
        System.out.println();
    }

    public void greetPlayer(String name){
        System.out.println("Welcome to BLACKJACK " + name);
    }

    public String  askName(){
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        return name;
    }

    public void showRound(int count){
        System.out.println();
        System.out.println("===================================");
        System.out.println("             ROUND " + count);
        System.out.println("===================================");
        System.out.println();
    }

    public void showBlackjackResult(String message){
        System.out.println(message);
    }

    public void dealerBust(int score){
        System.out.println();
        System.out.println("===================================");
        System.out.println("      DEALER BUST! YOU WON!");
        System.out.println("===================================");
        System.out.println();
        System.out.println("Dealer score:" + score);
    }

    public void showWinner(String flag, int playerScore, int dealerScore){
        System.out.println();
        if(flag.equals("Player")){
            System.out.println("===================================");
            System.out.println("              YOU WON!");
            System.out.println("===================================");
            System.out.println();
            System.out.println("Your score:" + playerScore);
            System.out.println("Dealer score:" + dealerScore);
        }else if(flag.equals("Dealer")){
            System.out.println("===================================");
            System.out.println("      YOU LOST! DEALER WON!");
            System.out.println("===================================");
            System.out.println();
            System.out.println("Dealer score: " + dealerScore);
            System.out.println("Your score:" + playerScore);
        }else if(flag.equals("Draw")){
            System.out.println("===================================");
            System.out.println("              Draw!");
            System.out.println("===================================");
            System.out.println();
            System.out.println("Your score:" + playerScore);
            System.out.println("Dealer score:" + dealerScore);
        }
    }

    public String askHitOrStand(){
        System.out.println();
        System.out.println("Hit or Stand? ");
        String answer = sc.nextLine().toLowerCase();
        return answer;
    }

    public String askSplit(){
        System.out.println();
        System.out.println("Hit, Stand or Split?");
        String answer = sc.nextLine().toLowerCase();
        return answer;
    }

    public void errorInputAskCard(){
        System.out.println("Invalid input! Please enter 'hit' or 'stand'.");
    }

    public void errorInputAskGame(){
        System.out.println("Invalid input! Please enter 'yes' or 'no'.");
    }

    public void errorInputAskSplit(){ System.out.println("Invalid input! Please enter 'hit', 'stand' or 'split'."); }

    public String askPlayer(){
        System.out.println();
        System.out.println("Do you want to play again? YES or NO?");
        String answer = sc.nextLine().toLowerCase();
        return answer;
    }

    public void showGoodbye(String name){
        System.out.println("Thank you for participating " + name + "!");
        System.out.println("GOODBYE!");
    }

    public void showPlayerCard(String card){
        System.out.println("Your card is: " + card);
    }

    public void showDealerFirstCard(String card){
        System.out.println();
        System.out.println("Dealer first card is: " + card);
    }

    public void showDealerSecondCard(String card){
        System.out.println("Dealer second card is: " + card);
    }

    public void showDealerDraws(String card){
        System.out.println("Dealer draws: " + card);
    }

    public void showPlayerBust(int score){
        System.out.println();
        System.out.println("===================================");
        System.out.println("           BUST! YOU LOST!");
        System.out.println("===================================");
        System.out.println();
        System.out.println("Your score is: " + score);
    }

    public void showScore(int score){
        System.out.println("Your score is: " + score);
    }

    public void showStatistics(String stats){
        System.out.println(stats);
    }
}
