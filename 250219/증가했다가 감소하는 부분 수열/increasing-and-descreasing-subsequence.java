import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static int[] dp;
    static int[] reverseDp;
    static int[] seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        seq = new int[N];

        dp = new int[N];
        reverseDp = new int[N];

        for(int i = 0 ; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());    
        }

        // 정방향으로 한번
        // 역방향으로 한번
        // LIS 해서 구해?
        // 1 2 2 3 4 2 1
        // 1 3 2 3 3 2 1  
        
        dp[0] = 1;
        reverseDp[N-1] = 1;
        for(int i = 1; i < N; i++) {
            dp[i] = 1;
            
            for(int j = i; j >= 0; j--) {
                if(seq[i] > seq[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for(int i = N-2; i >= 0; i--) {
            reverseDp[i] = 1;
        
            for(int j = i + 1; j < N; j++) {
                if(seq[i] > seq[j]) {
                    reverseDp[i] = Math.max(reverseDp[i], reverseDp[j] + 1);
                }
            }
        }

        int maxLen = 0;

        for(int i =0; i < N; i++) {
            int cur = dp[i] + reverseDp[i];

            maxLen = Math.max(cur, maxLen);
        }

        System.out.print(maxLen - 1);
    }
}