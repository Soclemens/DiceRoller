import java.util.ArrayList;
import java.util.Random;

public class diceRoll {

    private static int returnDiceRoll(int lower, int upper){
        Random rand = new Random();
        return rand.nextInt((upper - lower) + 1) + lower;
    }

    private static int dayRollAmount(){
        return returnDiceRoll(8,12);
    }

    private static int d20(){
        return returnDiceRoll(1,20);
    }
    public static void main(String[] args) {
        //numberTracker keeps track of end results
        int[] numberTracker = new int[20];
        for(int i = 0; i < 1000000; i++){ //this for loop represents number of days "i"
            //rollsThisDay simulates a random variation to time between resetting LuckyFeet and foretelling
            int rollsThisDay = dayRollAmount();
            int luckyReRolls = 3;
            int fortellingNum1 = d20();
            int fortellingNum2 = d20();
            for(int x = 0; x < rollsThisDay; x++){ //this for loop represents the rolls made on the day "x"
                //Start of new day
                int roll = d20();
                int cut = 10;
                //if you roll a 1 use halfling lucky
                if(roll == 1){
                    roll = d20();
                }
                //if less than desirable (cut) use a lucky re-roll
                if (luckyReRolls > 0 && roll < cut){
                    roll = d20();
                    luckyReRolls -= 1;
                }
                //if for telling number better than roll and less than desirable (cut) use foretelling number instead
                if (roll < cut && (roll < fortellingNum1 || roll < fortellingNum2)){

                    int betterFortell = Math.max(fortellingNum1, fortellingNum2);
                    roll = betterFortell;
                    if(fortellingNum1 == betterFortell){
                        fortellingNum1 = 0;
                    }else {
                        fortellingNum2 = 0;
                    }
                }
                numberTracker[roll-1] += 1;
            }
        }
        int totalRolls = 0;
        for(int i = 0; i < numberTracker.length; i++) {
            // accessing each element of array
            totalRolls += numberTracker[i];
            System.out.println(i+1 + ": " + numberTracker[i] + " ");
        }
        System.out.println(totalRolls);
        for(int i = 0; i < numberTracker.length; i++) {
            // accessing each element of array
            float percentage = ((float) numberTracker[i] / totalRolls) * 100;
            System.out.println(i+1 + ": " + percentage + "%");
        }
    }
}
