import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int n,result;

    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        result = 0;
        int[][] grid = new int[n][n];
        int[][] tempGrid = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited = new boolean[n];
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        tempGrid[k][l] = grid[k][l];
                    }
                }
                result = Math.max(result, simulate(i, j, tempGrid));
            }
        }

        System.out.println(result);

    }

    public static int simulate(int r, int c, int[][] curGrid) {
        int[][] explodedGrid = explode(r,c,curGrid);
        int[][] dropGrid = drop(explodedGrid);
        return countPair(dropGrid);

    }

    public static int countPair(int[][] curGrid) {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(curGrid[i][j] == 0) continue;
                if( inRange(i,j+1) && curGrid[i][j] == curGrid[i][j+1]) cnt++;
                if(inRange(i+1,j) && curGrid[i][j] == curGrid[i+1][j]) cnt++;
            }
        }
        return cnt;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static int[][] explode(int r, int c,int[][] curGrid) {
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, -1, 1};
        int power = curGrid[r][c] - 1;

        curGrid[r][c] = 0;
        visited[c] = true;

        for(int i = 0; i < 4; i++) {
            for (int j = 1; j <= power ; j++) {
                int nR = r + (drs[i] * j);
                int nC = c + (dcs[i] * j);

                if(inRange(nR,nC)) {
                    curGrid[nR][nC] = 0;
                    visited[nC] = true;
                }
            }
        }

        return curGrid;
    }

    public static int[][] drop(int[][] curGrid) {



        for (int  i = 0; i < n; i++) {
            if(visited[i]) {
                // drop이 가능한 경우
                int tempIdx = 0;
                int[] temp = new int[n];
                for (int j = n-1; j > -1; j--) {
                    if(curGrid[j][i] != 0) {
                        temp[tempIdx++] = curGrid[j][i];
                    }
                }

                tempIdx = 0;

                for (int j = n-1; j > -1 ; j--) {
                    curGrid[j][i] = temp[tempIdx++];
                }
            }
        }
        return curGrid;
    }

}