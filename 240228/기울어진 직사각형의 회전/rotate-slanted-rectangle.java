import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int n,row,col,m1,m2,m3,m4,dir;
    public static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //dir 0 인경우 반시계 1인 경우 시계 방향
        n = Integer.parseInt(br.readLine().trim());
        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken()) - 1;
        col = Integer.parseInt(st.nextToken()) - 1;
        m1 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());
        m3 = Integer.parseInt(st.nextToken());
        m4 = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        if(dir == 0)
            moveCounterClockDir(row,col);
        else{
            moveClockDir(row,col);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.append(grid[i][j] + " ");
            }
            bw.append("\n");
        }
        bw.close();
    }

    public static void moveClockDir(int r, int c) {
        int temp = grid[r][c];
        int curR = r;
        int curC = c;

        for (int i = 1; i <= m1; i++) {
            grid[curR][curC] = grid[--curR][++curC];
        }

        for (int i = 1; i <= m2; i++) {
            grid[curR][curC] = grid[--curR][--curC];
        }

        for (int i = 1; i <= m3; i++) {
            grid[curR][curC] = grid[++curR][--curC];
        }

        for (int i = 1; i < m4; i++) {
            grid[curR][curC] = grid[++curR][++curC];
        }
        grid[curR][curC] = temp;
    }

    public static void moveCounterClockDir(int r, int c) {
        int temp = grid[r][c];
        int curR = r;
        int curC = c;

        for (int i = 1; i <= m4; i++) {
            grid[curR][curC] = grid[--curR][--curC];
        }

        for (int i = 1; i <= m3; i++) {
            grid[curR][curC] = grid[--curR][++curC];
        }

        for (int i = 1; i <= m2; i++) {
            grid[curR][curC] = grid[++curR][++curC];
        }

        for (int i = 1; i < m1; i++) {
            grid[curR][curC] = grid[++curR][--curC];
        }
        grid[curR][curC] = temp;
    }
}