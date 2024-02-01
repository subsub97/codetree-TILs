import java.util.*;
import java.io.*;

public class Main {
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[][] grid = new int[n][n];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        for (int i = 0; i < n; i++) {
            for (int j = n-1; j >= 0 ; j--) {
                if(i == 0 && j ==n-1){
                    dp[i][j] = grid[i][j];
                    continue;
                }
                //n에서 부터 시작하기에 우측 상단부터 시작
                if(i==0 && j != n-1) {
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                    continue;
                }
                if(j != n-1) {
                    dp[i][j] = Math.min(dp[i][j + 1], dp[i - 1][j]) + grid[i][j];
                }
                else{
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
            }
        }

        System.out.println(dp[n-1][0]);

    }
}