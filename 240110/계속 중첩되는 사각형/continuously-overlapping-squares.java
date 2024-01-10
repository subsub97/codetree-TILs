import java.util.*;
import java.io.*;

public class Main {

    public static int n;
    public static int answer;
    public static final int OFFSET = 100;
    public static int[][] grid = new int[200][200];
    public static int x1;
    public static int x2;
    public static int y1;
    public static int y2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            x1 = Integer.parseInt(st.nextToken()) + OFFSET; 
            y1 = Integer.parseInt(st.nextToken()) + OFFSET;
            x2 = Integer.parseInt(st.nextToken()) + OFFSET;
            y2 = Integer.parseInt(st.nextToken()) + OFFSET;

            if(i % 2 == 0) {
                //파란색 직사각형 그리기
                for(int x = x1; x < x2; x++) {
                    for(int y = y1; y < y2; y++) {
                        grid[x][y] = 2;
                    }
                }
            }
            else{
                // 빨간색 직사각형 그리기
                for(int x = x1; x < x2; x++) {
                    for(int y = y1; y < y2; y++) {
                        grid[x][y] = 1;
                    }
                }                
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                if(grid[i][j] == 2) {
                    answer += 1;
                }
            }
        }

        System.out.print(answer);
    }
}