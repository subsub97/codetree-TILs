import java.util.*;
import java.io.*;

public class Main {
    public static final int MAX_SIZE = 101;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] basketLocation = new int[MAX_SIZE];
        
        for(int i = 0; i < N; i++) {
            StringTokenizer candyInfoToken = new StringTokenizer(br.readLine());
            int candyAmount = Integer.parseInt(candyInfoToken.nextToken());
            int location = Integer.parseInt(candyInfoToken.nextToken());

            basketLocation[location] += candyAmount;
        }

        int ans = Integer.MIN_VALUE;

        for(int i = 0; i <= MAX_SIZE; i++) {
            int curSum = 0;
            for(int j = i - K; j <= i + K; j++) {
                if(j >= 0 && j < MAX_SIZE)
                    curSum += basketLocation[j];
            }
            ans = Math.max(ans, curSum);
        }
        System.out.print(ans);
    }
}