import java.io.*;
import java.util.*;

public class Main {
    public static int n,m;
    public static int[][] grid;
    public static boolean[][] visited;
    public static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0,0);
        System.out.print(ans);

    }

    public static boolean inRange(int r,int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    public static boolean canGo(int r, int c) {
        if(!inRange(r,c)) return false;
        if(grid[r][c] == 0 || visited[r][c] ) return false;
        return true;
    }
    
    public static void bfs(int r, int c) {
        Spot s = new Spot(r,c);
        Queue<Spot> q = new LinkedList<>();
        q.add(s);

        while(!q.isEmpty()) {
            // 비어있지 않은 경우 진행
            Spot curS = q.poll();
        
            int curR = curS.row;
            int curC = curS.col;

            if(visited[curR][curC]) continue;

            if(curR == n-1 && curC == m-1) {
                //목적지에 도달한 경우
                ans = 1;
                break;
            }

            visited[curR][curC] = true;

            int[] drs = {1,-1,0,0};
            int[] dcs = {0,0,1,-1};
            int dir = 0;

            for(int i = 0; i<4; i++) {
                int nR = curR + drs[dir];
                int nC = curC + dcs[dir];
                dir++;

                if(canGo(nR,nC)) {
                    Spot nextSpot = new Spot(nR,nC);
                    q.add(nextSpot);
                }
            }
        }
        
    }

    public static class Spot {
        int row;
        int col;

        Spot(int r, int c) {
            row = r;
            col = c;
        }
    }
}