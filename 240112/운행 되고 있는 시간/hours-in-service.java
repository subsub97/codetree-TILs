import java.io.*;
import java.util.*;

public class Main {
    public static final int MAX_SCHEDULE = 1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[][] workers = new int[n][2];
        

        for(int i = 0; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            workers[i][0] = Integer.parseInt(st.nextToken());
            workers[i][1] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            int cnt = 0;
            int[] schedule = new int[MAX_SCHEDULE];
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }
                for(int k  = workers[j][0]; k < workers[j][1]; k++) {
                    if(schedule[k] == 0) {
                        schedule[k] = 1;
                        cnt++;                        
                    }
                }
            }
            ans = Math.max(ans,cnt);
        }
        System.out.print(ans);
    }
}