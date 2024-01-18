import java.io.*;

import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[n*2];

        for (int i = 0; i < 2*n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int[] sum = new int[n];
        int idx = 0;
        int ans = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            sum[i] = numbers[i+n] - numbers[i];
            ans = Math.min(sum[i], ans);
        }

        System.out.println(ans);

    }
}