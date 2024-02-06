import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
    public static int n;
    public static int m;
    public static int q;
    public static int[][] grid;
    public static int[][] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        temp = new int[n][m];

        int r1, r2, c1, c2;
        for (int i = 0; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken()) - 1;
            c1 = Integer.parseInt(st.nextToken()) - 1;
            r2 = Integer.parseInt(st.nextToken()) - 1;
            c2 = Integer.parseInt(st.nextToken()) - 1;

            blow(r1,c1,r2,c2);

            for (int j = r1; j <= r2; j++) {
                for (int k = c1; k <= c2; k++) {
                    grid[j][k] = temp[j][k];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }



    }

    public static void blow(int r1, int c1, int r2, int c2) {
        int[] temps = new int[2];
        int idx = 0;

        for (int i = c2; i > c1; i--) {
            //r1행인 경우
            if(i == c2){
                temps[idx++] = grid[r1][c2];
            }
            grid[r1][i] = grid[r1][i - 1];
        }

        for (int i = r2; i > r1; i--) {
            //c2열인 경우
            if(i == r2) {
                temps[idx] = grid[r2][c2];
                idx = (idx + 1) % 2;
            }
            grid[i][c2] = grid[i - 1][c2];
            if(i == r1 + 1) {
                grid[i][c2] = temps[idx];
            }
            if(r1+1 == r2) {
                grid[i][c2] = temps[idx];
            }
        }

        for (int i = c1; i < c2 ; i++) {
            //r2행인 경우
            if(i == c1) {
                temps[idx] = grid[r2][c1];
                idx = (idx + 1) % 2;
            }
            grid[r2][i] = grid[r2][i + 1];
            if(i == c2 - 1) {
                grid[r2][i] = temps[idx];
            }

        }

        for (int i = r1; i < r2 ; i++) {
            //c1열인 경우
            if(i == r1) {
                temps[idx] = grid[r1+1][c1];
                idx = (idx + 1) % 2;
                grid[i][c1] = grid[i+1][c1];
            }
            grid[i][c1] = grid[i + 1][c1];
            if(i == r1 + 1) {
                grid[i][c1] = temps[idx];
            }
            if(r1 + 1 == r2) {
                grid[r1][c1] = temps[idx];
            }
        }



        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                sumBorder(i,j);
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return  r >= 0 && r < n && c >= 0 && c < m;
    }

    public static void sumBorder(int r, int c) {
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, 1, -1};
        int dir = 0;
        int sum = 0;
        sum += grid[r][c];
        int cnt = 1;

        for (int i = 0; i < 4; i++) {
            int nextR = r + drs[dir];
            int nextC = c + dcs[dir++];

            if(inRange(nextR,nextC)) {
                sum += grid[nextR][nextC];
                cnt++;
            }
        }

        temp[r][c] = sum / cnt;
    }
}