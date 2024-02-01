import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[][] grid;
    public static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        grid = new int[n][n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int currSum = 0;
        int ans = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n-1; j++) {
                for (int k = 1; k < n; k++) {
                    if(isStartSpot(i,j)) {
                        currSum = sumSquare(i,j,k);
                        ans = Math.max(ans, currSum);
                    }
                }
                }
            }
        System.out.print(ans);
    }

    public static boolean isStartSpot(int row, int col) {
        //기울어진 직사각형 틀을 만들기위한 시작점으로 가능한 스팟인지 확인
        return row > 1 && row < n && col > 0 && col < n-1;
    }

    public static int sumSquare(int row, int col,int limit) {
        // 기울어진 사각형 모양의 숫자들을 합산하는 함수
        int[] drs = {0,-1,-1,1,1};
        int[] dcs = {0,1,-1,-1,1};
        int dirIdx = 0;
        int sumNumber = 0;

        int currRow = row;
        int currCol = col;

        while(dirIdx != 4 || (row != currRow || col != currCol)) {
            dirIdx = selectDir(dirIdx,currRow,currCol,row,col,limit);
            currRow = currRow + drs[dirIdx];
            currCol = currCol + dcs[dirIdx];

            sumNumber += grid[currRow][currCol];
        }


        return sumNumber;
    }

    public static int selectDir(int dir, int row, int col,int startRow,int startCol,int limit) {
        if(dir == 0) {
            return 1;
        }
        if(dir == 1) {
            if(row == 1 || col == n-1 || limit == startRow - row) return 2;
        }
        if(dir == 2) {
            if(row == 0 || col == 1 || col + startCol == startRow - row)
                return 3;
        }
        if(dir == 3) {
            if(row == startRow -1 || col == 0 || startRow - row == startCol - col)
                return 4;
        }
        return dir;
    }
}
/*
우측 대각으로 올라가는건 row 1 까지고 col이 < n인 경우 가능
좌측 대각으로 올라가는건 row 0 까지고 col >=0 경우 가능
좌측 하단으로 내려오는건 출발한 row-1 까지가능
 */