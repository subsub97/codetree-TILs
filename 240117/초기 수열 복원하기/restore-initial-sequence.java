import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static int[] numbers = new int[1001];
    public static int[] original;
    public static int[] answer;
    public static boolean find = false;
    public static boolean[] used = new boolean[1001];

    public static void findInitNumbers(int len) {
        if(len == n) {
            for (int i = 0; i < n; i++) {
                System.out.print(answer[i] +" ");
            }
            find = true;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if(!used[i]) {
                // 사용중이지 않다면
                if(i + answer[len - 1] == original[len -1]) {
                    answer[len] = i;
                    used[i] = true;
                    findInitNumbers(len +1);
                    if (find) return;
                    used[i] = false;
                    answer[len] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());

        original = new int[n];
        answer = new int[n];

        for (int i = 0; i < n-1; i++) {
            original[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            answer[0] = i;
            used[i] = true;
            findInitNumbers(1);
            if (find)
                break;
            answer[0] = 0;
            used[i] = false;
        }
    }
}