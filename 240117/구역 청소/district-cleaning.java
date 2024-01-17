import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        int[] a = new int[2];
        int[] b = new int[2];

        for (int i = 0; i < 2; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st1.nextToken());
        }

        boolean[] cleaned = new boolean[101];

        for (int i = a[0]; i < a[1]; i++) {
            cleaned[i] = true;
        }

        for (int i = b[0]; i < b[1]; i++) {
            cleaned[i] = true;
        }
        int ans = 0;
        for (int i = 0; i <= 100; i++) {
            if (cleaned[i]) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}