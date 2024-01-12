import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int budget = Integer.parseInt(st.nextToken());
        
        int[][] prices = new int[n][2];
        


        for(int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());

            prices[i][0] = Integer.parseInt(st1.nextToken());
            prices[i][1] = Integer.parseInt(st1.nextToken());
        }

        Arrays.sort(prices, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0],b[0]);
            }
        });
        
        int ans = 0;

        for(int i = 0; i < n; i++) {
            int curSum = budget;
            int cnt = 0;
            for(int j = 0; j < n; j++) {  
                if(i == j) {
                    if(curSum >= ((prices[j][0]/2) + prices[j][1])){
                        curSum -= prices[j][0] / 2;
                        curSum -= prices[j][1];
                        cnt++;
                    }
                }
                else{
                    if(curSum >= prices[j][0] + prices[j][1]){
                        curSum -= (prices[j][0] + prices[j][1]);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans,cnt);
        }


        Arrays.sort(prices, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1],b[1]);
            }
        });

                for(int i = 0; i < n; i++) {
            int curSum = budget;
            int cnt = 0;
            for(int j = 0; j < n; j++) {  
                if(i == j) {
                    if(curSum >= ((prices[j][0]/2) + prices[j][1])){
                        curSum -= prices[j][0] / 2;
                        curSum -= prices[j][1];
                        cnt++;
                    }
                }
                else{
                    if(curSum >= prices[j][0] + prices[j][1]){
                        curSum -= (prices[j][0] + prices[j][1]);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans,cnt);
        }

        System.out.print(ans);
    }
}