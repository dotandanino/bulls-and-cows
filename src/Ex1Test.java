//my I.D. 328144951
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Ex1Test {
    public static double checkAvg(int numberOfDigitsToCheck){// function that help calculating the avg
        double avg=0.0;
        BP_Server game= new BP_Server();
        for(int i=0;i<100 ;i++){
            game.startGame(328144951, numberOfDigitsToCheck);  // Starting a game
            Ex1.autoEx1Game(game);
            avg=avg+Ex1.guessCounter;
        }
        avg=avg/100.0;
        return avg;
    }
    private static int[] toArray(int a, int size){// help me with the filter test
        int[] c = new int[size];
        for(int j=0;j<c.length;j+=1) {
            c[j] = a%10;
            a=a/10;
        }
        return c;
    }
    public static void printArr(int[] arr){// i want to print the true arrays
        System.out.print("{");
        for (int i=0; i<arr.length;i++){
            System.out.print(arr[i]+",");
        }
        System.out.print("} ");
    }
    @Test
    void testPrintArr(){
        int[] guess2={4,3};
        printArr(guess2);
        System.out.println("");//i want space line
        int[] guess3={1,7,8};
        printArr(guess3);
        int[] guess4={2,5,6,5};
        printArr(guess4);
        int[] guess5={4,3,2,7,1};
        printArr(guess5);
        int[] guess6={6,7,2,4,9,1};
        printArr(guess6);
    }
    @Test
    void testFilter(){
        boolean[] arr=new boolean[100];
        for (int i=0;i<arr.length;i++){
            arr[i]=true;
        }
        int[] guess={3,0};
        int[] res={1,0};
        Ex1.filter(arr,guess,res);
        for (int i=0;i< arr.length;i++){
            if(arr[i])
                printArr(toArray(i,2));
        }
        System.out.println("");// i want space before the next print
        for (int i=0;i<arr.length;i++){
            arr[i]=true;
        }
        int[] guess1={4,7,1};
        res[0]=0;
        res[1]=1;
        Ex1.filter(arr,guess1,res);
        for (int i=0;i< arr.length;i++){
            if(arr[i])
                printArr(toArray(i,3));
        }
    }
    @Test
    void testNextGuess(){
        boolean[] arr=new boolean[100];// everything is false at the start
        arr[8]=true;
        int[] flag={8,0};
        assertArrayEquals(Ex1.nextGuess(arr,2,0),flag);
        assertArrayEquals(Ex1.nextGuess(arr,2,0),flag);
    }
    @Test
    void testIsValidGuess(){
        int [] guess={0,0,0,1};
        int [] compare= {0,9,8,2};
        int[] res={1,0};
        boolean flag=Ex1.isValidGuess(guess,compare,res);
        assertTrue(flag, "you have error 1");
        int []guess2={0,0,0,1};
        int[] compare2={1,2,3,4};
        flag=Ex1.isValidGuess(guess2,compare2,res);
        assertFalse(flag, "you have error 2");
        int []guess3={0,0,0,1};
        int[] compare3={2,0,3,4};
        flag=Ex1.isValidGuess(guess3,compare3,res);
        assertTrue(flag, "you have error 3");
        int []guess4={0,0,0,1};
        int[] compare4={0,2,0,4};
        flag=Ex1.isValidGuess(guess4,compare4,res);
        assertFalse(flag, "you have error 4");
        int []guess5={0,0,0,1};
        int[] compare6={1,2,1,4};
        res[0]=0;
        res[1]=2;
        flag=Ex1.isValidGuess(guess4,compare4,res);
        assertFalse(flag, "you have error 5");
    }
    @Test
    void checkAvgTwo(){
        double avg=checkAvg(2);
        assertTrue(avg<6,"the average for 2 digit is higher than 7.5");
    }
    @Test
    void checkAvgThree(){
        double avg=checkAvg(3);
        assertTrue(avg<6.5,"the average for 3 digit is higher than 8.5");
    }
    @Test
    void checkAvgFour(){
        double avg=checkAvg(4);
        assertTrue(avg<6.5,"the average for 4 digit is higher than 9");
    }
    @Test
    void checkAvgFive(){
        double avg=checkAvg(5);
        assertTrue(avg<7,"the average for 5 digit is higher than 10");
    }
    @Test
    void checkAvgSix(){
        double avg=checkAvg(6 );
        System.out.println(avg);
        assertTrue(avg<8,"the average for 5 digit is higher than 10");
    }
    @Test
    void checkAllAvg(){
        double avg=0.0;
        for (int i=2;i<7;i++) {
           avg+=checkAvg(i);
        }
        avg=avg/5.0;
        System.out.println(avg);
        assertTrue(avg<6.8);
    }
}
