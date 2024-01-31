import java.util.*;
import java.io.*;

public class Main {
    public static int n;
    public static int m;
    public static int cnt;
    public static int[][] grid;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];

        for(int i =0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int maxCnt = 0;
        int preMaxCnt = 0;
        int ans = 0;
        for(int k = 0; k < n + 10; k++) { //현재 마름모 크기
            int cost = k * k + (k+1) * (k+1); //현재 마름모 격자의 비용
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    cnt = 0;
                    visited = new boolean[n][n];
                    dfs(i,j,0,k);
                    maxCnt = Math.max(cnt,maxCnt);
                }
            }
            if(cost > maxCnt * m) {
                // 손해가 발생하는 경우
                continue;

            }
            preMaxCnt = maxCnt;
        }
        System.out.println(preMaxCnt);

    }

    public static void dfs(int row, int col, int currentDepth,int maxDepth) {
        if(currentDepth == maxDepth+1) {
            return;
        }
        visited[row][col] = true;

        if(grid[row][col] == 1) cnt++;

        int[] drs = {1,0,-1,0};
        int[] dcs = {0,1,0,-1};

        for(int i = 0; i < 4; i++) {
            //현재 위치에서 상하좌우로 움직이기.
            int nextRow = row + drs[i];
            int nextCol = col + dcs[i];
            if(canMine(nextRow,nextCol)) {
                dfs(nextRow,nextCol,currentDepth+1,maxDepth);
            }
        }
    }

    public static boolean inRange(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    public static boolean canMine(int row, int col) {
        if(!inRange(row,col)) return false;
        if(visited[row][col]) return false;
        return true;
    }
}