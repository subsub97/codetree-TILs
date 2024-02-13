import java.io.*;
import java.util.*;

public class Main {
    public static int n,k;

    public static int[][] grid;
    public static int[][] answer;
    public static boolean[][] visited;
    public static Queue<Orange> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        answer = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                answer[i][j] = -2;
                if(grid[i][j] == 0) {
                    answer[i][j] = -1;
                } else if (grid[i][j] == 2) {
                    //상한 귤
                    q.add(new Orange(i, j,0));
                    visited[i][j] = true;
                    answer[i][j] = 0;
                }
            }
        }

        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(answer[i][j] +" ");
            }
            System.out.println();
        }
    }

    public static void bfs() {
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            Orange orange = q.poll();
            answer[orange.row][orange.col] = orange.rottenTime;

            for (int i = 0; i < 4; i++) {
                int nR = orange.row + drs[i];
                int nC = orange.col + dcs[i];

                if(canGo(nR,nC)) {
                    q.add(new Orange(nR, nC, orange.rottenTime + 1));
                    visited[nR][nC] = true;
                }
            }

        }

    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static boolean canGo(int r, int c) {
        if(!inRange(r,c)) return false;
        if(visited[r][c] || grid[r][c] == 0) return false;
        return true;
    }

    public static class Orange {
        int row;
        int col;
        int rottenTime;

        Orange(int r, int c,int t) {
            row = r;
            col = c;
            rottenTime = t;
        }
    }

}