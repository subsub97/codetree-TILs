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
            for (int j = 0; j < n; j++) {
                if(i == 0 && j ==0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }
                if(i == 0){
                    dp[i][j] = Math.min(dp[i][j - 1], grid[i][j]);
                    continue;
                }
                if(j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], grid[i][j]);
                    continue;
                }
                else{
                    dp[i][j] = Math.min(Math.max(dp[i - 1][j], dp[i][j - 1]), grid[i][j]);
                }
            }
        }

        System.out.println(dp[n-1][n-1]);

    }
}