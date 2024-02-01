import java.io.*;

public class Main {
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        long[] dp = new long[1001];

        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;
        dp[3] = 22;

        for (int i = 4; i <= n; i++) {
            long calcValue = 0;
            for (int j = i; j > 0; j--) {
                if(i - j == 2){
                    calcValue = (calcValue +(dp[j] * 3)) % 1_000_000_007;
                    continue;
                }
                calcValue = (calcValue % 1_000_000_007 + (dp[j] * 2) % 1_000_000_007) % 1_000_000_007;
                
            }
            dp[i] = (calcValue % 1_000_000_007 + 2 % 1_000_000_007) % 1_000_000_007;
        }

        System.out.println(dp[n]);
    }
}