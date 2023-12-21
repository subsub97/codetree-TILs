import java.util.*;
import java.io.*;

public class Main {
    static final int ARRAY_SIZE = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numberArray = new int[ARRAY_SIZE];
        int multi = 1;

        for(int i = 0; i < ARRAY_SIZE; i++) {
            int n = Integer.parseInt(st.nextToken());
            multi *= n;
        }
        int answer = sumEachNumber(multi);
        System.out.print(answer);
    
    }

    public static int sumEachNumber(int n) {
         if(n < 10) {
            return n;
         }

         return sumEachNumber(n/10) + n % 10;
    }
}