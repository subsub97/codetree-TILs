import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int n,m;
    public static int[][] grid;
    public static boolean[] exploded;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            int col = Integer.parseInt(br.readLine().trim()) -1;
            //M번의 열이 선택되고 폭탄이 터진다.
            exploded = new boolean[n];
            simulate(col);

        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void simulate(int col) {
        int row = findStartRow(col);
        if(row == -1) return;

        explode(row, col);
        drop();
    }

    public static int findStartRow(int c) {
        int r = -1;

        for(int i = 0; i  < n; i++) {
            if(grid[i][c] != 0) {
                r = i;
                return r;
            }
        }
        return r;
    }

    public static boolean inRange(int r, int c) {
        return r >=0 && r < n && c >= 0 && c < n;
    }

    public static void explode(int r, int c) {
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, -1, 1};

        int bombPower = grid[r][c] - 1;
        grid[r][c] = 0;
        exploded[c] = true;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= bombPower; j++) {
                int nR = r + (drs[i] * j);
                int nC = c + (dcs[i] * j);

                if(inRange(nR,nC)){
                    //폭발
                    exploded[nC] = true;
                    grid[nR][nC] = 0;
                }
            }
        }
    }

    public static void drop() {
        //중력의 영향을 받아야 하는 경우라면 적용하기
        for(int i = 0 ; i < n; i++) {
            if(exploded[i]) {
                //폭발한 숫자가 있기에 중력 작용시키기
                int[] temp = new int[n];
                int tempIdx = 0;

                for (int j = n-1; j >= 0; j--) {
                    if(grid[j][i] != 0) {
                        temp[tempIdx++] = grid[j][i];
                    }
                }
                int idx = 0;

                for (int j = n-1; j >= 0; j--) {
                    grid[j][i] = temp[idx++];
                }
            }
        }
    }

    /*
    1. Col이 주어지면 유효한 Row 찾기
    2. 십자로 터트리기
        터트리면서 exploded로 터졌는지 확인하고 터진 열이라면 중력 적용하기
    3. 중력으로 떨구기
     */
}