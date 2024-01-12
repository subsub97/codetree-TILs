import java.io.*;
import java.util.*;

public class Main {
    public static final int MAX_BOMB = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());        
        int k = Integer.parseInt(st.nextToken());

        int[] bombs = new int[n];

        for(int i = 0; i < n; i++) {
            bombs[i] = Integer.parseInt(br.readLine().trim());
        }
        
        int ans = -1;

        for(int i = 0; i < n - k; i++) {
            int curNumber = bombs[i];
            for(int j = i + 1; j <= k + i; j++) {
                if(bombs[j] == curNumber) {
                    ans = Math.max(ans,bombs[j]);
                }
            }
        }

        System.out.print(ans);
    }
}