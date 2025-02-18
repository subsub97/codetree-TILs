import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[][] grid;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        dp = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1.  i + 1,j + 1 이상 값으로만 이동 가능 
        dp[0][0] = 1;
        int preScore = grid[0][0];
        int preR = 0;
        int preC = 0;
        int ans = 0 ;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(i == 0 && j != 0 || i > 0 && j == 0) continue;

                for(int ii = i + 1; ii < N; ii++) {
                    for(int jj = j+1; jj < M; jj++) {
                        if(grid[i][j] < grid[ii][jj]) {
                            //뛸 수 있는 경우
                            dp[ii][jj] = Math.max(dp[ii][jj], dp[i][j] + 1);
                            ans = Math.max(ans, dp[ii][jj]);
                        }
                    }
                }
            }
        }

        System.out.print(ans);


        
    }
}