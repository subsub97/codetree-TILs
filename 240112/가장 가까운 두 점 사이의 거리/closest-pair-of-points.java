import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] xs = new int[1000];
        int[] ys = new int[1000];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            xs[i] = Integer.parseInt(st.nextToken());
            ys[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                int distance = (xs[i] - xs[j]) * (xs[i] - xs[j]) 
                            + (ys[i] - ys[j]) *  (ys[i] - ys[j]);

                ans = Math.min(ans, distance);
            }
        }

        System.out.print(ans);
    }
}