import java.util.*;
import java.io.*;

public class Main {
    static int N,ans;
    static int[][] grid; 
    static boolean[] rows;
    static boolean[] cols;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        grid = new int[N][N];
        rows= new boolean[N];
        cols= new boolean[N];
        ans = 0;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }   
        }
        
        back(0);
        System.out.print(ans);
        
    }

    public static void back(int depth) {
        if(depth == N) {
            int curMax = 100000;

            for(int num : list) {
                curMax  = Math.min(curMax, num);
            }

            ans  = Math.max(curMax, ans);
            return;
        }

        for(int i =0; i < N; i++) {
            if(cols[i]) continue;
            cols[i] = true;
            list.add(grid[depth][i]);
            back(depth + 1);
            list.remove(list.size()-1);
            cols[i] = false;
        }
    }
}