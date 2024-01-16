import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 101;

        for (int i = 2; i <= 100 ; i+=2) {
            for (int j = 2; j <= 100 ; j+=2) {
                int cnt1 = 0;
                int cnt2 = 0;
                int cnt3 = 0;
                int cnt4 = 0;

                for (int k = 0; k < n; k++) {
                    // 1사분면 점의 개수 확인
                    if(y[k] > i && x[k] >j) {
                        cnt1++;
                    }
                    else if(y[k] > i && x[k] < j) {
                        cnt2++;
                    } else if (y[k] < i && x[k] < j) {
                        cnt3++;
                    }
                    else{
                        cnt4++;
                    }
                }
                int curMax = Math.max(Math.max(cnt1, cnt2), Math.max(cnt3, cnt4));
                ans = Math.min(curMax,ans);
            }
        }
        System.out.println(ans);
    }
}