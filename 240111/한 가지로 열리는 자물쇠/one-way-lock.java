import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[3];

        for(int i = 0; i < 3; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        int ans = n * n * n;
        int cnt = 0;

        for(int i =1; i <= n; i++) {
            for(int j = 1; j <=n; j++) {
                for(int k = 1; k <= n; k++) {
                    boolean flag = true;

                    for(int l = 0; l < 3; l++) {
                        if(l == 0) {
                            if(Math.abs(numbers[l]- i) <= 2) flag = false;
                        }
                        else if(l == 1){
                            if(Math.abs(numbers[l]- j) <= 2) flag = false;
                        }
                        else if(l == 2) {
                            if(Math.abs(numbers[l]- k) <= 2) flag = false;
                        }
                    }
                    if(flag) cnt++;
                }
            }
        }

        System.out.print(ans - cnt);
        
    }
}