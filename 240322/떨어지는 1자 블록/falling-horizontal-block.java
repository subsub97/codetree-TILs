import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int n, m, k;
    public static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dropBlock();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }


    }

    public static void dropBlock() {
        int row = 1;
        int col = k - 1;
        boolean canPlay = true;

        while(row < n) {
            for(int i = col; i < col + m; i++) {
                // 벽이 있는 경우 멈춘다.
                if(isBlock(row, col)) {
                    canPlay = false;
                    break;
                }
            }
            row++;
            if(!canPlay) break;
            
        }
        
        for (int i = col; i < col + m; i++) {
            grid[row-1][i] = 1;
        }
    }

    public static boolean isBlock(int r, int c) {
        if(grid[r][c] == 1) return true;
        return false;
    }
}