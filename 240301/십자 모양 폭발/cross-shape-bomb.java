import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine().trim());
        grid = new int[n][n];


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;

        explode(r,c);

        dropNumber();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.append(grid[i][j] + " ");
            }
            bw.append("\n");
        }

        bw.close();
    }

    public static void explode(int r, int c) {
        int power = grid[r][c];

        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, -1, 1};

        grid[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= power-1; j++) {
                int nR = r + (drs[i] * j);
                int nC = c + (dcs[i] * j);

                if(inRange(nR,nC)) {
                    //폭탄 범위이내인 경우
                    grid[nR][nC] = 0;
                }
            }
        }
    }

    public static void dropNumber() {
        //n개의 열을 하나씩 보면서 드랍하기

        for (int i = 0; i < n; i++) {
            int[] temp = new int[n];
            int tempIdx = 0;

            //각 열을 순회하면서 temp에 터진 곳을 제외하고 옮겨 담기
            for (int j = n-1; j >= 0; j--) {
                if(grid[j][i] != 0) {
                    temp[tempIdx++] = grid[j][i];
                }
            }
            // 빈 공간을 메꿀수있게 떨어트려 매꾸기
            int idx = 0;

            for (int j = n-1; j >= 0; j--) {
                grid[j][i] = temp[idx++];
            }

        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c <n;
    }

}