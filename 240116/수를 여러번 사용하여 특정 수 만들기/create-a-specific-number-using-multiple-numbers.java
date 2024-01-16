import java.io.*;
import java.util.*;

public class Main {
    public static int sum = 0;
    public static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int aCnt = c / a;
        int bCnt = c / b;
        int ans = 0;

        for (int i = 0; i <= a ; i++) {
            for (int j = 0; j <= b; j++) {
                int curSum = (a * i) + (b * j);
                if(curSum < c)
                    ans = Math.max(ans,curSum);
            }
        }

        System.out.println(ans);
    }


}