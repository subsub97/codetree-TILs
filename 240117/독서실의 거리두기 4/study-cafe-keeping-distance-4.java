import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static char[] charArray = new char[100];
    public static int n;
    public static int getMinDist() {
        int minDist = n;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(charArray[i] == '1' && charArray[j] == '1'){
                    minDist = Math.min(minDist, j - i);
                }
            }
        }
        return minDist;


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());
        charArray = br.readLine().toCharArray();

        int ans = 0;

        for (int i = 0; i < n; i++) {
            // 첫번째 학생 학생 자리 배정
            if(charArray[i] == '1') continue;
            charArray[i] = '1';
            for (int j = i + 1; j < n; j++) {
                // 두번째 학생 자리 배정
                if(charArray[j] == '1') continue;
                charArray[j] = '1';
                ans = Math.max(ans,getMinDist());
                charArray[j] = '0';
            }
            charArray[i] = '0';
        }

        System.out.println(ans);

    }
}