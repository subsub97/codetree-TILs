import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int minNumber = 10000;
        int maxNumber = 0;

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st1.nextToken());
            minNumber = Math.min(numbers[i], minNumber);
            maxNumber = Math.max(numbers[i], maxNumber);
        }
        int minCost = 1000000;

        for (int i = minNumber; i <= maxNumber-k ; i++) {
            int min = i;
            int max = i + k;
            int cost = 0;
            for (int j = 0; j < n; j++) {
                if(min <= numbers[j] && numbers[j] <= max) {
                    continue;
                } else if (min > numbers[j]) {
                    cost += min - numbers[j];
                }
                else{
                    cost += numbers[j] - max;
                }
            }

            minCost = Math.min(cost, minCost);
        }

        System.out.println(minCost);
    }
}