import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        int maxValue = Integer.MIN_VALUE;

        maxValue = Math.max(numbers[n - 1] * numbers[n - 2] * numbers[n - 3],maxValue);
        maxValue = Math.max(numbers[0] * numbers[1] * numbers[n - 1], maxValue);

        System.out.println(maxValue);
    }
    
}