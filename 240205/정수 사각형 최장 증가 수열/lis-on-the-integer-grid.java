import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static int n;
    public static int[][] grid;
    public static int[][] dp;
    public static Cell[] cells;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());

        grid = new int[n][n];
        dp = new int[n][n];
        cells = new Cell[n*n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                cells[idx] = new Cell(num, j, i);
                grid[i][j] = num;
                idx++;
                dp[i][j] = 1;
            }
        }

        Arrays.sort(cells);

        int ans = 0;

        for (Cell cell : cells) {
            ans = Math.max(ans,checkCnt(cell.x, cell.y));
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
                    dp[y][x] = Math.max(dp[y][x],dp[nextY][nextX] + 1);
                }
            }
            dir++;
        }

        return dp[y][x];
    }

    public static boolean inRange(int row,int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    public static class Cell implements Comparable<Cell> {
        int num,x,y;

        Cell(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Cell c) {
            return this.num - c.num;
        }
    }
}