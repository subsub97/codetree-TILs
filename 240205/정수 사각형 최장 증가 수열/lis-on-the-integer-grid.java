import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static int[][] grid;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());

        grid = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = 1;
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans,checkCnt(i,j));
            }
        }

        System.out.println(ans);

    }

    public static int checkCnt(int x, int y) {
        int[] dxs = {1, -1, 0, 0};
        int[] dys = {0, 0, 1, -1};
        int dir = 0;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dxs[dir];
            int nextY = y + dys[dir];

            if(inRange(nextY,nextX)) {
                if(grid[y][x] > grid[nextY][nextX]) {
                    dp[y][x] = dp[nextY][nextX] + 1;
                }
            }
            dir++;
        }

        return dp[y][x];
    }

    public static boolean inRange(int row,int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }
}