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
            int[] sectionNumbers = new int[m];

            for(int j = 0; j < m; j++) {
                curSum += A[i+j];
                sectionNumbers[j] = A[i+j];
            }
            
            if(bSum == curSum) {
                boolean check = false;
                for(int k = 0; k < m; k++) {
                    check = false;
                    for(int l = 0; l < m; l++) {
                        if(sectionNumbers[k] == B[l]) {
                            check = true;
                        }
                    }
                    if(!check) {
                        break;
                    }
                }
                if(check)
                    ans++;
            }
        }

        System.out.print(ans);

    }
}