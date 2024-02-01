import java.io.*;

public class Main {
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] dp = new int[1001];

        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;
        dp[3] = 22;

        for (int i = 4; i <= n; i++) {
            int calcValue = 0;
            for (int j = i; j > 0; j--) {
                if(i - j == 2){
                    calcValue += dp[j] * 3;
                    continue;
                }
                calcValue += dp[j] * 2;
            }
            dp[i] = (calcValue + 2) % 1_000_000_007;
        }

        System.out.println(dp[n]);
    }
}