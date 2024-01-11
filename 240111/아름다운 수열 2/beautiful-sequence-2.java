import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        StringTokenizer stA = new StringTokenizer(br.readLine());
        StringTokenizer stB = new StringTokenizer(br.readLine());

        int[] A = new int[n];
        int[] B = new int[m];

        for(int i = 0; i < n; i++) {
            A[i] =  Integer.parseInt(stA.nextToken());
        }

        for(int i = 0; i < m; i++) {
            B[i] =  Integer.parseInt(stB.nextToken());
        }

        int bSum = 0;

        for(int i = 0; i < m; i++) {
            bSum += B[i];
        }
        int ans =0;
        for(int i = 0; i <= n - m; i++) {
            int curSum = 0;

            for(int j = 0; j < m; j++) {
                curSum += A[i+j];
            }
            
            if(bSum == curSum) {
                ans++;
            }
        }

        System.out.print(ans);

    }
}