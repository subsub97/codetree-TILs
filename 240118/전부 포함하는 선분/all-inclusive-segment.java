import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int minX = 101;
            int maxX = 0;
            for (int j = 0; j < n; j++) {
                if(i == j) continue;
                minX = Math.min(minX, start[j]);
                maxX = Math.max(maxX, end[j]);
            }
            ans = Math.min(ans, maxX - minX);
        }
        System.out.println(ans);
    }
}