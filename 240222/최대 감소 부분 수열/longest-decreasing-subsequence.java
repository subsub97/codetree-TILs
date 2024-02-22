import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            //이전에 나온 수보다 내가 작으면 거기 Dp + 1
            int maxOrder = 0;
            for(int j = i-1; j >= 0; j--) {
                if(numbers[i] < numbers[j]) {
                    // 현재 선택된 수 보다 J번째 수가 큰 경우
                    maxOrder = Math.max(dp[j], maxOrder);
                }
            }
            dp[i] = maxOrder + 1;
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}