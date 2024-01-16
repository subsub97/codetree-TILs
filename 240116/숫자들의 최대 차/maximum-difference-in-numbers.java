import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(numbers);

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = i+1; j < n; j++) {
                if(numbers[j] - numbers[i] <= k) {
                    cnt++;
                }
            }

            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }
}