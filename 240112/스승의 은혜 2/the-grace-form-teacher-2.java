import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] prices = new int[n];

        for(int i = 0; i <n; i++) {
            prices[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(prices);


        int ans = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            int curBudget = b;
            int cnt = 0;

            for(int j = 0; j < n; j++) {
                if(i == j) {
                    curBudget -= prices[i]/2;
                }
                else {
                    curBudget -= prices[j];
                }

                if(curBudget >= 0){

                    cnt++;
                }
            }

            ans = Math.max(ans,cnt);
        }

        System.out.print(ans);
    }
}