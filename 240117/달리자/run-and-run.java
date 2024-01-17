import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int[] first = new int[n];
        int[] second = new int[n];

        for (int i = 0; i < n; i++) {
            first[i] = Integer.parseInt(st1.nextToken());
            second[i] = Integer.parseInt(st2.nextToken());
        }

        int cost = 0;

        for (int i = n-1; i > 0 ; i--) {
            if(second[i] > first[i]) {
                cost += second[i] - first[i];
                first[i - 1] -= second[i] - first[i];
            }
        }

        System.out.println(cost);
    }
}