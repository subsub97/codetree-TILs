import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int ans = 0;

        for(int i = a; i <= b; i++) {
            String num = Integer.toString(i);
            int cnt = num.length() /2;
            boolean isPalindrome = true;

            for(int j = 0; j < cnt; j++) {
                if(num.charAt(j) != num.charAt(num.length()-j-1)) {
                    isPalindrome = false;
                    break;
                }
            }

            if(isPalindrome) {
                ans++;
            }
        }

        System.out.print(ans);
    }
}