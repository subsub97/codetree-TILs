import java.io.*;
import java.util.*;

public class Main {
    public static int n, k, m, ans;
    public static Pair[] startIdxs;
    public static int[][] grid;
    public static boolean[][] visisted;

    public static ArrayList<Integer> removeStones = new ArrayList<>();
    public static ArrayList<Pair> stones = new ArrayList<>();
    public static Queue<Pair> q = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = 0;

        startIdxs = new Pair[k];
        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) {
                    //돌인 경우
                    stones.add(new Pair(i, j));
                }
            }
        }

        //시작 위치 입력
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            startIdxs[i] = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        choose(0,0);

        System.out.println(ans);
    }

    public static void choose(int idx, int depth) {
        if (depth == m) {
            //다 골라 졌을때 max 찾기
            getMaxArea();
            return;
        }

        for (int i = idx; i < stones.size(); i++) {
            removeStones.add(i);
            choose(i + 1, depth + 1);
            removeStones.remove(removeStones.size() - 1);
        }

    }

    public static void removeStone() {
        // 돌 치우기
        for (int i = 0; i < removeStones.size(); i++) {
            Pair p = stones.get(removeStones.get(i));
            grid[p.row][p.col] = 0;
        }
    }

    public static void restoreStoen() {
        // 돌 다시 채우기
        for (int i = 0; i < removeStones.size(); i++) {
            Pair p = stones.get(removeStones.get(i));
            grid[p.row][p.col] = 1;
        }
    }

    public static int bfs() {
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, -1, 1};

        int cnt = 1;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nR = p.row + drs[i];
                int nC = p.col + dcs[i];
                if (canGo(nR, nC)) {
                    //갈 수 있는 경우
                    visisted[nR][nC] = true;
                    cnt++;
                    q.add(new Pair(nR, nC));
                }
            }

        }
        return cnt;
    }

    public static void getMaxArea() {
        removeStone();
        for (int i = 0; i < startIdxs.length; i++) {
            visisted = new boolean[n][n];
            q.add(startIdxs[i]);
            visisted[startIdxs[i].row][startIdxs[i].col] = true;
            ans = Math.max(ans,bfs());
        }
        restoreStoen();
    }

    public static class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static boolean canGo(int r, int c) {
        if (!inRange(r, c)) {
            return false;
        }
        if (visisted[r][c] || grid[r][c] == 1) {
            return false;
        }
        return true;
    }

}