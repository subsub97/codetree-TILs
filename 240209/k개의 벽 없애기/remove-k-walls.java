import java.io.*;
import java.util.*;

public class Main {
    public static int n,k,startR,startC,endR,endC,ans;
    public static int[][] grid;
    public static boolean[][] visited;
    public static ArrayList<Pair> blockList = new ArrayList<>();
    public static ArrayList<Pair> selected = new ArrayList<>();
    public static Queue<Pair> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;

        grid = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 1) {
                    blockList.add(new Pair(i,j,0));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        endR = Integer.parseInt(st.nextToken()) - 1;
        endC = Integer.parseInt(st.nextToken()) - 1;

        choose(0,0);

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else{
            System.out.println(ans);
        }

    }
    public static void choose(int idx, int cnt) {
        if(cnt == k) {
            //시뮬
            simulate();
            return;
        }

        for (int i = idx; i < blockList.size(); i++) {
            selected.add(blockList.get(i));
            choose(idx+1,cnt+1);
            selected.remove(selected.size() - 1);
        }
    }
    public static void simulate() {
        visited = new boolean[n][n];
        for(Pair p : selected) {
            grid[p.row][p.col] = 0;
        }
        q.add(new Pair(startR, startC,0));
        bfs();
        for(Pair p : selected) {
            grid[p.row][p.col] = 1;
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


        while (!q.isEmpty()) {
            Pair p = q.poll();

            int curR = p.row;
            int curC = p.col;
            if(curR == endR && curC == endC){
                ans = Math.min(ans,p.cnt);
                while(!q.isEmpty()) {
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
        Pair(int r, int c ,int cn) {
            row = r;
            col = c;
            cnt = cn;
        }
    }

}