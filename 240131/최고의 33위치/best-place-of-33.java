import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];

        for(int i =0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxCnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int cnt = 0;
                for (int k = i; k < 3+i; k++) {
                    for(int h = j; h < 3+j; h++) {
                        if(k < n && h < n) {
                            if(grid[k][h] == 1) cnt++;
                        }
                    }
                }
                maxCnt = Math.max(cnt,maxCnt);
            }
        }
        System.out.print(maxCnt);
    }
}