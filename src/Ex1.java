import java.util.Random;
import java.util.Scanner;
import java.util.Random;
//my I.D. 328144951
/**
 * Introduction to Computer Scienc e, Ariel University, Ex1 (manual Example + a Template for your solution)
 * See: https://docs.google.com/document/d/1C1BZmi_Qv6oRrL4T5oN9N2bBMFOHPzSI/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 *
 * Ex1 Bulls & Cows - Automatic solution.
 * **** Add a general readme text here ****
 * Add your explanation here:
 *  I build alghoritm that check every time after I guess which guess is still avalaible
 * **** General Solution (algorithm) ****
     * Add your explanation here:
     * My algorithm start from random guess
     * after this I get number of bulls and cows
     * than I compare my guess to every other guess and
     * if he doesn't have the exactly number of bulls and
     * cows that I need I made this Index in the array false
     * after I finish doing this I send the next guess and doing this again
 * **** Results ****
 * Make sure to state the average required guesses
 * for 2,3,4,5,6 digit code:
 *
 * Average required guesses 2: 5.1
 * Average required guesses 3: 5.55
 * Average required guesses 4: 5.86
 * Average required guesses 5: 6.31
 * Average required guesses 6: 6.84
 * The avergae for all is: 5.932
 */
public class Ex1 {
    public static Random rnd=new Random();
    static int guessCounter =0;
    static int numOfDigits = 4;
    public static final String Title = "Ex1 demo: manual Bulls & Cows game";
    public static void main(String[] args) {
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 328144951;// Your ID should be written here// Number of digits [2,6]
        long startTime=System.currentTimeMillis();
        game.startGame(myID,numOfDigits);
        System.out.println(Title+" with code of "+numOfDigits+" digits");
        manualEx1Game(game);
    }
    /**
     * this function check the avarge guesses required for 100 guesses with specific number of digits
     * @param numberOfDigitsToCheck is the number of digits to check the average for
     * @return the average number og guesses
     */
    public static double checkAvg(int numberOfDigitsToCheck){
        double avg=0.0;
        BP_Server game= new BP_Server();
        for(int i=0;i<100 ;i++){
            game.startGame(328144951, numberOfDigitsToCheck);  // Starting a game
            autoEx1Game(game);
            avg=avg+ guessCounter;
        }
        avg=avg/100.0;
        return avg;
    }
    public static void manualEx1Game(BP_Server game) {
        Scanner sc = new Scanner(System.in);
        int ind=1;      // Index of the guess
        int numOfDigits = game.getNumOfDigits();
        double max = Math.pow(10,numOfDigits);
        while(game.isRunning()) {           // While the game is running (the code has not been found yet
            System.out.println(ind+") enter a guess: ");
            int g = sc.nextInt();
            if(g>=0 && g<max) {
                int[] guess = toArray(g, numOfDigits); // int to digit array
                int[] res = game.play(guess); // Playing a round and getting the B,C
                if (game.isRunning()) {     // While the game is running
                    System.out.println(ind + ") B: " + res[0] + ",  C: " + res[1]); // Prints the Bulls [0], and the Cows [1]
                    ind += 1;               // Increasing the index
                }
            }
            else {
                System.out.println("ERR: wrong input, try again");
            }
        }
        System.out.println(game.getStatus());
    }


    /**
     * Simple parsing function that gets an int and returns an array of digits
     * @param a - a natural number (as a guess)
     * @param size  - number of digits (to handle the 00 case).
     * @return an array of digits
     */
    private static int[] toArray(int a, int size){
        int[] c = new int[size];
        for(int j=0;j<c.length;j+=1) {
            c[j] = a%10;
            a=a/10;
        }
        return c;
    }
////////////////////////////////////////////////////
    /**
     * This function solves the Bulls & Cows game automatically.
     * You should implement
     * **** Add a specific explanation for each function ****
     * the autoEx1Game function it the function that auto solve the guess
     */
    public static void autoEx1Game(BP_Server game) {
        int N= game.getNumOfDigits();
        int size= (int) Math.pow(10,N);
        boolean[] arr = new boolean[size];
        for (int i=0;i<size;i++)
            arr[i]=true;
        int i=0;
        while(game.isRunning()){
            int [] guess=nextGuess(arr,N,i);
            int[] res=game.play(guess);
            filter(arr,guess,res);
            i++;
        }
        guessCounter =i;
    }
     /**
     * the function check if the guess  is valid
     * the function check this by the number of bulls and cows
     * for example:
     * if the guess is {0,3,5} and there is 1 bull, this function going in the array and if there is guess with
     * more or less than one bull with {0,3,5} the function make him false.
     * @param guess is the previous guess
     * @param compare is the guess that I want to check if he is valid
     * @param res the number of bulls and cows that should be
     * @return if the guess is valid
     */
    public static boolean isValidGuess(int guess[],int [] compare , int[] res){
        int bCounter=0;
        int counter=0;
        int b=res[0];
        int answer=b+res[1];
        for (int y=0;y< guess.length;y++){
            if(guess[y]==compare[y]){
                bCounter++;
                counter++;
                guess[y]=-1;
                compare[y]=10;
            }
        }
        if(b==bCounter){
            for (int x : guess) {
                for (int z = 0; z < guess.length; z++) {
                    if (x == compare[z]) {
                        counter++;
                        compare[z] = 10;
                        z=guess.length;
                    }
                }
            }
        }
        return (answer == counter) && (bCounter == b);
    }

    /**
     * this function going through the array and make sure there will be only valid guesses.
     * this function use the is valid guess function for this.
     * @param arr is the array that says foe every guess if he is valid or not;
     * @param guess is the last guess
     * @param res is the answer of the last game
     */
    public static void filter(boolean[] arr, int[] guess, int[] res) {
        int b = res[0];
        int c = res[1];
        for (int i = 0; i < arr.length; i++) {
            int[] flagArray = toArray(i, guess.length);
            int[] tempGuess = new int[guess.length];
            if(arr[i]){
                for (int j = 0; j < guess.length; j++) {
                    tempGuess[j] = guess[j];
                }
                boolean flag = isValidGuess(tempGuess, flagArray, res);
                if (!flag)
                    arr[i] = false;
            }
        }
    }

    /**
     * this function check what is the first true value in the array that I got in random or the first
     * true if i had number of guess higher than 4& higher than the guess length and after this
     * call for a function that will make this index array for the comparing
     * @param arr is the array that say for every guess is he valid
     * @param length the required guess length
     * @return the next valid guess
     */
    public static int[] nextGuess(boolean[] arr,int length,int i){
        if(i<length || i<5){
            int j=rnd.nextInt(arr.length-1);
            while(!arr[j]){
                j=rnd.nextInt(arr.length-1);
            }
            return toArray(j,length);
        }
        int j=0;
        while(!arr[j])
            j++;
        return toArray(j,length);
    }
}
