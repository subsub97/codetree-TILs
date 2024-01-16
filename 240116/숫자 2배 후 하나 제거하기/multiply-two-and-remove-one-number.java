import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[n];


        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            numbers[i] *= 2;
            for (int j = 0; j < n; j++) {
                int[] remainingNum = new int[n-1];
                int cnt = 0;

                for (int k = 0; k < n; k++) {
                    if(k != j) {
                        remainingNum[cnt++] = numbers[k];
                    }
                }
                int sumDiff = 0;

                for (int k = 0; k < n-2; k++) {
                    sumDiff += Math.abs(remainingNum[k] - remainingNum[k+1]);
                }

                ans = Math.min(ans,sumDiff);
            }
            numbers[i] /= 2;
        }

        System.out.println(ans);
    }
}