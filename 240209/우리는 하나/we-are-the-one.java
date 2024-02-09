import java.io.*;
import java.util.*;

public class Main {
    public static int n,k,u, d, ans;
    public static int[][] grid;
    public static boolean[][] visited;

    public static ArrayList<Pair> countries = new ArrayList<>();
    public static ArrayList<Pair> selected = new ArrayList<>();
    public static Queue<Pair> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        ans = 0;

        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                countries.add(new Pair(i, j));
            }
        }

        choose(0,0);
        System.out.println(ans);


    }
    public static void choose(int idx, int selectCount) {
        if(selectCount == k) {
            //bfs 시뮬 실행
            ans = Math.max(ans, simulate());
            return;
        }

        for (int i = idx; i < countries.size(); i++) {
            selected.add(countries.get(i));
            choose(idx + 1, selectCount + 1);
            selected.remove(selected.size() - 1);
        }
    }

    public static int simulate() {
        visited = new boolean[n][n];
        int sum = 0;

        for(Pair pair : selected) {
            q.add(pair);
            visited[pair.row][pair.col] = true;
            sum++;
            sum += bfs();
        }

        return sum;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static boolean checkGap(int r, int c, int nR, int nC) {
        if(Math.abs(grid[r][c] - grid[nR][nC]) >= u && Math.abs(grid[r][c] - grid[nR][nC]) <= d)
            return true;
        return false;
    }

    public static int bfs() {
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, -1, 1};

        int cnt = 0;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            int curR = p.row;
            int curC = p.col;

            for (int i = 0; i < 4; i++) {
                int nR = curR + drs[i];
                int nC = curC + dcs[i];
                if (inRange(nR, nC) && !visited[nR][nC]) {
                    if(checkGap(curR,curC,nR,nC)) {
                        cnt++;
                        q.add(new Pair(nR, nC));
                        visited[nR][nC] = true;
                    }
                }
            }
        }

        return cnt;
    }

    public static class Pair {
        int row, col;
        Pair(int r, int c) {
            row = r;
            col = c;
        }
    }

}