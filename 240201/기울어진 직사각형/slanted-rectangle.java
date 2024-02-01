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

    public static boolean inRange(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    public static int sumSquare(int row, int col,int k) {
        // 기울어진 사각형 모양의 숫자들을 합산하는 함수
        int[] drs = {-1,-1,1,1};
        int[] dcs = {1,-1,-1,1};
        int dirIdx = 0;
        int sumNumber = 0;

        int currRow = row;
        int currCol = col;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < k; j++) {
                currRow = currRow + drs[dirIdx];
                currCol = currCol + dcs[dirIdx];
                if(!inRange(currRow,currCol)) {
                    return 0;
                }
                sumNumber += grid[currCol][currRow];
            }
            dirIdx = (dirIdx + 1) % 4;
        }

        return sumNumber;
    }
}