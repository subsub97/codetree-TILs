import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[]  numArray = new int[n];
        int maxSum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i <n; i++) {
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            for(int j = i+2; j < n; j++) {
                if(numArray[i] + numArray[j] > maxSum) {
                    maxSum = numArray[i] + numArray[j];
                }
            }
        }

        System.out.print(maxSum);
    }
}