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
        
        for(int k = 0; k <= n; k++) { //현재 마름모 크기
            int cost = k * k + (k+1) * (k+1); //현재 마름모 격자의 비용
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    cnt = 0;
                    
                    for(int r = 0; r < n; r++) {
                        for(int c = 0; c < n; c++) {
                            if(grid[i][j] == 1 && Math.abs(i - r) + Math.abs(j -c) <= k){
                                //마름모 내부라면
                                cnt++;
                            }
                        }
                    }
                if(cost <= m * cnt) maxCnt = Math.max(cnt,maxCnt);
                }
            }
        }
        System.out.println(maxCnt);

    }
}