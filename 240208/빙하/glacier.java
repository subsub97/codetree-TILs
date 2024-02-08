import java.io.*;
import java.util.*;

public class Main {
    public static int n, m;
    public static int[][] grid;
    public static boolean[][] visited;
    public static boolean[][] meltVisited;
    public static Queue<Pair> q = new LinkedList<>();
    public static Queue<Pair> meltQ = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        visited = new boolean[n][m];
        meltVisited = new boolean[n][m];
        int iceBurgCnt = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 1) iceBurgCnt++;
            }
        }

        int elapsedTime = 0;
        int finalSize = iceBurgCnt;

        visited[0][0] = true;
        q.add(new Pair(0, 0));

        while (iceBurgCnt > 0) {
            elapsedTime++;
            finalSize = iceBurgCnt;
            visited = new boolean[n][m];
            bfs();
            if(meltQ.size() > 0) {
                Pair temp = meltQ.peek();
                q.add(new Pair(temp.row, temp.col));
            }
            iceBurgCnt -= meltQ.size();
            meltIceburg();
        }

        System.out.println(elapsedTime + " " + finalSize);
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    public static boolean canMove(int r, int c) {
        if(!inRange(r,c)) return false;
        if(visited[r][c] || grid[r][c] == 1) return false; //방문했거나 물이 아니라면 이동 불가
        return true;
    }

    public static boolean canMelt(int r, int c) {
        if(!inRange(r,c)) return false;
        // 녹일수 있는지 판단
        if (grid[r][c] != 1 || meltVisited[r][c]) {
            return false;
        }
        return true;
    }

    public static void meltIceburg() {
        while (!meltQ.isEmpty()) {
            Pair p = meltQ.poll();
            grid[p.row][p.col] = 0;
        }
    }

    public static void bfs() {
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, -1, 1};

        while(!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nR = p.row + drs[i];
                int nC = p.col + dcs[i];

                if(canMove(nR,nC)) {
                    //물이라 이동가능한 경우
                    q.add(new Pair(nR, nC));
                    visited[nR][nC] = true;
                }
                if(canMelt(nR,nC)) {
                    //녹일수 있는 경우
                    meltQ.add(new Pair(nR, nC));
                    meltVisited[nR][nC] = true;
                }
            }
        }

    }

    public static class Pair {
        int row, col;

        Pair(int r, int c) {
            row = r;
            col = c;
        }
    }
}