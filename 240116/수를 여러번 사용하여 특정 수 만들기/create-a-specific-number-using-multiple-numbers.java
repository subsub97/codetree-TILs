import java.io.*;
import java.util.*;

public class Main {
    public static int sum = 0;
    public static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[2];

        numbers[0] = Integer.parseInt(st.nextToken());
        numbers[1] = Integer.parseInt(st.nextToken());

        int c = Integer.parseInt(st.nextToken());

        findMaxSum(numbers,c);
        System.out.println(ans);
    }

    public static void findMaxSum(int[] numbers,int c) {
        if(sum > c) {
            return;
        }

        for (int i = 0; i < 2; i++) {
            sum += numbers[i];
            findMaxSum(numbers,c);
            sum -= numbers[i];
            ans = Math.max(ans,sum);
        }
    }
}