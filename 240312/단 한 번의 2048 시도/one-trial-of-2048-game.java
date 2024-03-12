import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] grid = new int[4][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        char dir = br.readLine().charAt(0);
        int dirNum = getDirNum(dir);

        if(dirNum == 0) {
            for (int i = 0; i < 4; i++) {
                simulate(i,0,dirNum);
            }
        }
        else if(dirNum == 1) {
            //D
            for (int i = 0; i < 4; i++) {
                simulate(3,i,dirNum);
            }
        }

        else if(dirNum == 2) {
            //U
            for (int i = 0; i < 4; i++) {
                simulate(0,i,dirNum);
            }
        }
        else{
            for (int i = 0; i < 4; i++) {
                simulate(i,3,dirNum);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void simulate(int r,int c,int dir) {
        int[] drs = {0, -1, 1, 0};
        int[] dcs = {1, 0, 0, -1};
        int startR = r;
        int startC = c;

        int[] temps = new int[4];
        int tempIdx = 0;

        for (int i = 0; i < 4; i++) {
            int nR = startR + (drs[dir] * i);
            int nC = startC + (dcs[dir] * i);

            if(grid[nR][nC] != 0) {
                temps[tempIdx++] = grid[nR][nC];
            }
        }

        for (int i = 0; i < 4; i++) {
            int nR = startR + (drs[dir] * i);
            int nC = startC + (dcs[dir] * i);

            grid[nR][nC] = temps[i];
        }

        temps = new int[4];
        tempIdx = 0;

        for (int i = 0; i < 3; i++) {
            int nR = r + drs[dir];
            int nC = c + dcs[dir];

            int num1 = grid[nR][nC];
            int num2 = grid[r][c];

            if(checkSame(num1,num2)) {
                //같은 경우
                grid[r][c] = num1 * 2;
                grid[nR][nC] = 0;
            }

            r = nR;
            c = nC;
        }

        // 합쳐진 것이 temp에 있다면 옮기기
        for (int i = 0; i < 4; i++) {
            int nR = startR + (drs[dir] * i);
            int nC = startC + (dcs[dir] * i);

            if(grid[nR][nC] != 0) {
                temps[tempIdx++] = grid[nR][nC];
            }
        }

        for (int i = 0; i < 4; i++) {
            int nR = startR + (drs[dir] * i);
            int nC = startC + (dcs[dir] * i);

            grid[nR][nC] = temps[i];
        }

    }

    public static boolean checkSame(int num1, int num2) {
        return num1 == num2;
    }

    public static int getDirNum(char cmd) {
        int dirNum = -1;

        switch (cmd) {
            case 'L':
                dirNum = 0;
                break;
            case 'D':
                dirNum = 1;
                break;
            case 'U':
                dirNum = 2;
                break;
            case 'R':
                dirNum = 3;
                break;
        }

        return dirNum;
    }
}