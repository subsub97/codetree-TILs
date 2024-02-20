import java.util.*;
import java.io.*;

public class Main {
    public static final int OFFSET = 1000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] grid = new int[2001][2001];

        for(int i = 0 ; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + OFFSET;
            int y1 = Integer.parseInt(st.nextToken()) + OFFSET;
            int x2 = Integer.parseInt(st.nextToken()) + OFFSET;
            int y2 = Integer.parseInt(st.nextToken()) + OFFSET;

            if(i == 2) {
                for(int j = y1; j < y2; j++ ) {
                    for(int k = x1; k < x2; k++) {
                        grid[j][k] = 0;
                    }
                }
            }
            else{
                for(int j = y1; j < y2; j++ ) {
                    for(int k = x1; k < x2; k++) {
                        grid[j][k] = 1;
                }
            }
        }

        }

        int cnt = 0;

        for(int j = 0; j <= 2000 ; j++ ) {
            for(int k = 0; k <= 2000; k++) {
                if(grid[j][k] == 1) {
                    cnt++;
                }
        }
    }
    System.out.print(cnt);
    }
}