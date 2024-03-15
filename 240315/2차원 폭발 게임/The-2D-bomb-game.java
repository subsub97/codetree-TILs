import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int n,m,k;
    public static int[][] grid,nextGrid;
    public static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        nextGrid = new int[n][n];
        visited = new boolean[n][n];


        int result = 0;



        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < k; t++) {
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(visited[i][j]) continue;
                    if(canExplode(i,j)) {
                        explode(i,j);
                    }
                }
            }
            drop();
            rotate();
            drop();
        }

        System.out.println(countEmptyNumber());
    }

    // grid를 시계방향으로 90' 회전시킵니다.
    public static void rotate() {
        // nextGrid를 0으로 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = 0;

        // 90' 회전합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = grid[n - j - 1][i];

        // nextGrid를 grid에 옮겨줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = nextGrid[i][j];
    }

    public static int countEmptyNumber() {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] != 0 ) cnt++;
            }
        }

        return cnt;
    }


    public static boolean canExplode(int r, int c) {
        int curNum = grid[r][c];

        for(int i = r; i < r+m; i++) {
            if( !inRange(i,c) || curNum != grid[i][c]) return false;
        }
        return true;
    }

    public static void explode(int r, int c) {
        int curNum = grid[r][c];

        for (int i = r; i < n; i++) {
            if(curNum != grid[i][c]) break;
            visited[i][c] = true;
            grid[i][c] = 0;
        }

    }

    public static void drop() {
        for (int i = 0; i < n; i++) {
            int[] temp = new int[n];
            int idx = 0;
            for (int j = n-1; j > -1; j--) {
                if(grid[j][i] != 0) {
                    temp[idx++] = grid[j][i];
                }
            }

            idx = 0;
            for (int j = n-1; j > -1; j--) {
                grid[j][i] = temp[idx++];
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}