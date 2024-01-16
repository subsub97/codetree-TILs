import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int[] myArray =  new int[15];
    public static int[] pairSums =  new int[15];
    public static boolean isEqualArray() {
        Arrays.sort(myArray);


        for(int i = 0; i < 15; i++)
            if(pairSums[i] != myArray[i])
                return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        for (int i = 0; i < 15; i++) {
            pairSums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(pairSums);
        int a = pairSums[0];
        int b = pairSums[1];



        for (int i = b; i <= 40 ; i++) {
            for (int j = i; j <= 40 ; j++) {
                myArray[0] = a;
                myArray[1] = b;
                myArray[2] = i;
                myArray[3] = j;
                myArray[4] = a + b;
                myArray[5] = b + myArray[2];
                myArray[6] = myArray[2] + myArray[3];
                myArray[7] = myArray[3] + a;
                myArray[8] = a + myArray[2];
                myArray[9] = b + myArray[3];
                myArray[10] = a + b + myArray[2];
                myArray[11] = a + b + myArray[3];
                myArray[12] = a + myArray[2] + myArray[3];
                myArray[13] = b + myArray[2] + myArray[3];
                myArray[14] = a + b + myArray[2] + myArray[3];
                if(isEqualArray()) {
                    System.out.println(a +" " + b +" " + i +" " + j);
                }
            }
        }

    }
}