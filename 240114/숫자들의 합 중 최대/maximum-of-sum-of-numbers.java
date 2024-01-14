import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int ans = 0;

        for(int i = a; i <= b; i++) {
            String stringNumber = Integer.toString(i);
            int curSum = 0;
            
            for(int j = 0; j < stringNumber.length(); j++) {
                curSum += Integer.parseInt(stringNumber.substring(j,j+1));
                
                ans = Math.max(ans,curSum);
            }   
        }

        System.out.print(ans);
    }
}