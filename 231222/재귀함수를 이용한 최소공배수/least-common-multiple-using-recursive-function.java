import java.util.*;
import java.io.*;

public class Main {
    
    static int answer = 0;
    static int[] numberArray;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());        
        numberArray = new int[n];

        for(int i = 0; i < n; i++) {
            numberArray[i] = Integer.parseInt(st.nextToken());
        }

        answer = findLCM(n-1);
        System.out.print(answer);
        
    }
    
    public static int findLCM(int index) {
        if(index == 0) {
            return numberArray[index];
        }
        int a = findLCM(index - 1);

        return (a * numberArray[index]) / gcd(a,numberArray[index]);
    }

    public static int gcd(int a, int b) {
        int r;
        while(b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}