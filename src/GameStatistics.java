public class GameStatistics {

    private int wins;
    private int draws;
    private int losses;


    public void addWin(){
        wins++;
    }

    public void addDraw(){
        draws++;
    }

    public void addLoss(){
        losses++;
    }

    @Override
    public String toString(){

        return "You have " + wins + (wins == 1 ? " win" : " wins") + ", "
                + draws + (draws == 1 ? " draw" : " draws") + " and "
                + losses + (losses == 1 ? " loss" : " losses");
    }

}
