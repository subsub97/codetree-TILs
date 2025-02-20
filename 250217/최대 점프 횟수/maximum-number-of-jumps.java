import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[n];
        dp = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;
        int maxJump = 0;
        for(int i = 0; i < n; i++) {
            int canJumpRange = arr[i];
            
            if(i != 0 && dp[i] == 0) continue;
            for(int j = i+1; j <= i+canJumpRange; j++) {
                if(j >= n || arr[i] == 0) break;
                
                dp[j] = dp[i] + 1;
            }
            maxJump = Math.max(maxJump,dp[i]);
        }

        // for(int i =0; i < n; i++) {
        //     System.out.print(dp[i] + " ");
        // }
        System.out.print(maxJump);

    }
}