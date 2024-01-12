import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int budget = Integer.parseInt(st.nextToken());
        
        int[] prices = new int[n];
        int[] fees = new int[n];


        for(int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());

            prices[i] = Integer.parseInt(st1.nextToken());
            fees[i] = Integer.parseInt(st1.nextToken());
        }
        
        int ans = 0;

        for(int i = 0; i < n; i++) {
            int curSum = budget;
            int cnt = 0;
            for(int j = 0; j < n; j++) {  
                if(i == j) {
                    if(curSum >= (prices[j]/2 + fees[j])){
                        curSum -= prices[j] / 2;
                        curSum -= fees[j];
                        cnt++;
                    }
                }
                else{
                    if(curSum >= prices[j] + fees[j]){
                        curSum -= (prices[j] + fees[j]);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans,cnt);
        }

        System.out.print(ans);
    }
}