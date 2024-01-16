import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] height = new int[n];

        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(br.readLine().trim());
        }

        int minCost = Integer.MAX_VALUE;

        for (int i = 0; i <= 100 - 17; i++) {
            int min = i;
            int max = i + 17;
            int cost = 0;
            for (int j = 0; j < n; j++) {
                if(min <= height[j] && height[j] <= max) {
                    continue;
                }
                else if(min > height[j]) {
                    cost += (min - height[j]) * (min - height[j]);
                }
                else {
                    cost += (height[j] - max) * (height[j] - max);
                }
            }

            minCost = Math.min(minCost, cost);
        }

        System.out.println(minCost);
    }
}