import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_DIR = 3;
    public static int n;
    public static int m;
    public static int[][] grid;
    public static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans,findMaxValue(i, j,1,0));
            }
        }

        System.out.println(ans);
    }

    public static int findMaxValue(int row, int col, int depth,int value) {
        visited[row][col] = true;
        if(depth == 3) {
            visited[row][col] = false;
            return value += grid[row][col];
        }

        int[] drs = {1,0,-1};
        int[] dcs = {0,1,0};
        int dir = 0;

        int maxValue = 0;

        for (int i = 0; i < MAX_DIR; i++) {
            int nextRow = row + drs[dir];
            int nextCol = col + dcs[dir];
            if(canGo(nextRow,nextCol)) {
                //갈 수 있는 곳인 경우
                maxValue = Math.max(findMaxValue(nextRow,nextCol, depth + 1,
                        value+grid[row][col]),maxValue);

            }
            dir++;
        }
        visited[row][col] = false;
        return maxValue;
    }

    public static boolean inRange(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    public static boolean canGo(int row, int col) {
        if(!inRange(row,col)) {
            return false;
        }
        if(visited[row][col]) {
            return false;
        }
        return true;
    }

}