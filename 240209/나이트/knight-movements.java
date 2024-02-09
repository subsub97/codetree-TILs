import java.io.*;
import java.util.*;

public class Main {
    public static int n,r2,c2,ans;
    public static int[][] grid;
    public static boolean[][] visited;
    public static Queue<Pair> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1,c1;
        n = Integer.parseInt(st.nextToken());
        ans = -1;

        st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken()) -1;
        c1 = Integer.parseInt(st.nextToken()) -1;
        r2 = Integer.parseInt(st.nextToken()) -1;
        c2 = Integer.parseInt(st.nextToken()) -1;

        visited = new boolean[n][n];

        q.add(new Pair(r1, c1, 0));
        bfs();
        if(r1 == r2 && c1 == c2) {
            System.out.println(0);    
        }
        else{
            System.out.println(ans);
        }
    }
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static boolean canGo(int r, int c) {
        if (!inRange(r, c)) {
            return false;
        }
        if(visited[r][c]) return false;
        return true;
    }

    public static void bfs() {
        int[] drs = {2,1,2,-1,-1,-2,-2,1};
        int[] dcs = {1,-2,-1,-2,2,-1,1,2};

        while (!q.isEmpty()) {
            Pair p = q.poll();

            int curR = p.row;
            int curC = p.col;

            for (int i = 0; i < 8; i++) {
                int nR = curR + drs[i];
                int nC = curC + dcs[i];
                if(canGo(nR,nC)){
                    if(nR == r2 && nC == c2) {
                        ans = p.distance + 1;
                        while(!q.isEmpty()) {
                            q.poll();
                        }
                        break;
                    }
                    visited[nR][nC] = true;
                    q.add(new Pair(nR, nC, p.distance + 1));
                }
            }
        }

    }

    public static class Pair {
        int row, col,distance;
        Pair(int r, int c,int d) {
            row = r;
            col = c;
            distance = d;
        }
    }

}