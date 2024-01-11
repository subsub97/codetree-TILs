import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        StringTokenizer heightTokens = new StringTokenizer(br.readLine());
        int[] heights = new int[n];

        for(int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(heightTokens.nextToken());
        }

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i <= n-t; i++)  {
            int curCost = 0;

            for(int j = i; j < t + i; j++) {
                curCost += Math.abs(heights[j] - h);
            }
            
            ans = Math.min(curCost,ans);
        }

        System.out.print(ans);
    }
}