import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[][] grid = new int[n][n];
        int maxCoin = 0;
        
        for(int i = 0; i <n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n - 2; j++) {
                int a = grid[i][j] + grid[i][j + 1] + grid[i][j + 2];
                for(int k = 0 ; k <n; k++) {
                    if(i == k){
                        for(int l = j+3; l < n-2; l++) {
                            maxCoin = Math.max(maxCoin, a + grid[k][l] + grid[k][l + 1] + grid[k][l + 2]);
                        }
                    }
                    else{
                        for(int l = 0; l < n-2;l++) {
                            maxCoin = Math.max(maxCoin, a + grid[k][l] + grid[k][l + 1] + grid[k][l + 2]);
                        }
                    }

                }
                maxCoin = Math.max(maxCoin,a);
            }
        }
        System.out.print(maxCoin);
    }
}