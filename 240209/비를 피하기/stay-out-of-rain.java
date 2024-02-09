import java.io.*;
import java.util.*;

public class Main {
    public static int n,h,m;
    public static int[][] grid;
    public static int[][] ans;
    public static boolean[][] visited;
    public static ArrayList<Pair> locations = new ArrayList<>();
    public static Queue<Pair> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        ans = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 2) {
                    locations.add(new Pair(i, j,0));
                }
            }
        }

        for(Pair p : locations) {
            visited = new boolean[n][n];
            visited[p.row][p.col] = true;
            q.add(p);
            bfs();
            if (ans[p.row][p.col] == 0) {
                ans[p.row][p.col] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }

    }
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
    public static boolean canGo(int r, int c) {
        if (!inRange(r, c)) {
            return false;
        }
        if(visited[r][c] || grid[r][c] == 1) return false;
        return true;
    }
    public static void bfs() {
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, 1, -1};


        Pair startP = q.peek();

        while (!q.isEmpty()) {
            Pair p = q.poll();

            int curR = p.row;
            int curC = p.col;


            if(grid[curR][curC] == 3) {
                //비를 피하는 곳이라면
                ans[startP.row][startP.col] = p.cnt;
                while (!q.isEmpty()){
                    q.poll();
                }
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nR = curR + drs[i];
                int nC = curC + dcs[i];
                if (canGo(nR, nC)) {
                    q.add(new Pair(nR, nC,p.cnt + 1));
                    visited[nR][nC] = true;
                }
                }
            }
        }



    public static class Pair {
        int row, col,cnt;
        Pair(int r, int c,int cn) {
            row = r;
            col = c;
            cnt = cn;
        }
    }

}