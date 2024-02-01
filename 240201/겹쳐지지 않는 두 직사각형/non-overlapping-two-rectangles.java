import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int m;
    public static int[][] grid;
    public static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < m; l++) {
                        ans = Math.max(findAnotherRectSum(i,j,k,l),
                                                          ans);
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static int findAnotherRectSum(int sr, int sc, int er, int ec) {
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < m; l++) {
                        if(!overlapRect(sr,sc,er,ec,i,j,k,l)) {
                            // 겹치지 않는 경우에 서로의 직사각형의 합을 계산한다.
                            maxValue = Math.max(maxValue,
                                    sumRect(sr, sc, er, ec) +
                                    sumRect(i, j, k, l));
                        }
                    }
                }
            }
        }
        return maxValue;
    }

    public static boolean overlapRect(int sr1, int sc1, int er1, int ec1,
                                      int sr2, int sc2, int er2, int ec2) {
        board = new int[n][m];
        draw(sr1,sc1,er1,ec1);
        draw(sr2,sc2,er2,ec2);

        for (int i = sr1; i <= er1; i++) {
            for (int j = sc2; j <= ec2; j++) {
                if(board[i][j] >= 2){
                    return true;
                }
            }
        }
        return false;
    }

    public static void draw(int sr, int sc, int er, int ec) {
        for (int i = sr; i <= er; i++) {
            for (int j = sc; j <= ec; j++) {
                board[i][j] += 1;
            }
        }
    }

    public static int sumRect(int sr, int sc, int er, int ec) {
        int sumValue = 0;

        for (int i = sr; i <= er; i++) {
            for (int j = sc; j <= ec; j++) {
                sumValue += grid[i][j];
            }
        }
        return sumValue;
    }
}