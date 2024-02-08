import java.io.*;
import java.util.*;

public class Main {
    public static boolean[] vistied;
    public static int[][] grid;
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        vistied = new boolean[n];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            grid[x][y] = 1;
            grid[y][x] = 1; // 연결되었음을 의미
        }

        int ans = 0;
        dfs(1);

        for(int i = 0; i < n; i++) {
            if(vistied[i]) {
                ans++;
            }
        }
        System.out.print(ans - 1);
    }

    public static void dfs(int vertex) {
        vistied[vertex] = true;

        for(int i = 0; i < n; i++)  {
            if(grid[vertex][i] == 1 && !vistied[i]) {
                //현재 정점과 연결되어 있고 그 정점이 방문되지 않은 곳이라면 방문
                dfs(i);
            }
        }
    }
}