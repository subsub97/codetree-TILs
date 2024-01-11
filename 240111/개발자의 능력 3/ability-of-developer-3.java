import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[6];

        int totalSum = 0;

        for(int i = 0; i < 6; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            totalSum += numbers[i];
        }

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < 4; i++) {
            for(int j = i + 1; j < 5; j++) {
                for(int k = j + 1; k < 6; k++) {
                    int temp = totalSum;
                    temp -= (numbers[i] + numbers[j] + numbers[k]);
                    ans = Math.min(ans,Math.abs(temp - (numbers[i] + numbers[j] + numbers[k])));
                }
            }
        }
        System.out.print(ans);
    }
}