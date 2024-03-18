import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int n,m,k,start,end;
    public static boolean isBombed;
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

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < k; t++) {
            findSeqBombs();
            rotate();
            drop();
            findSeqBombs();
        }

        System.out.println(countEmptyNumber());
    }

    public static void findSeqBombs() {
            isBombed = false;
            while(true){
                doExplode();
                if(isBombed) {
                    drop();
                    isBombed = false;
                }
                else{
                    drop();
                    break;
                }
            }
    }

    public static void doExplode() {
        for (int j = 0; j < n; j++) {
            int curNum = grid[0][j];
            int curCnt = 1;
            int startIdx = 0;
            for (int i = 1; i < n; i++) {
                if(grid[i][j] == 0) continue;
                if(curNum == grid[i][j]) {
                    curCnt++;
                }
                else{
                    if(curCnt >= m) {
                        isBombed = true;
                        explode(startIdx,j);
                    }
                    curNum = grid[i][j];
                    curCnt = 1;
                    startIdx = i;
                }
            }

            if(curCnt >= m && curNum != 0) {
                isBombed = true;
                explode(startIdx,j);
            }
        }
    }


    public static void rotate() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = 0;

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = grid[n - j - 1][i];

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


    public static boolean canExplode(int c) {

        for (int i = 0; i < n; i++) {
            if(grid[i][c] == 0) continue;

            int curNum = grid[i][c];
            int cnt = 0;
            for (int j = i; j < i+m; j++) {
                if( !inRange(j,c) || curNum != grid[j][c]) break;
                cnt++;
            }

            if(cnt >= m) {
                start = i;
                return true;
            }
        }
        return false;
    }

    public static void explode(int start,int c) {
        int curNum = grid[start][c];
        int r = start;

        while(r < n) {
            int i = 0;
            if(curNum != grid[r][c]) break;
            grid[r][c] = 0;
            r++;
        }

        return;
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