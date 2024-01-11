import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] developers = new int[5];
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < 5; i++) {
            developers[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    if(i != j && j != k && i != k) {
                        int diff = Math.abs((developers[i] + developers[j]) - developers[k]);
                        ans = Math.min(ans,diff);
                    }
                }
            }
        }

        System.out.print(ans);
    }
}