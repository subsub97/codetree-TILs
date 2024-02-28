import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int minLen = 100;

        for (int i = 0; i <= s.length(); i++) {
            minLen = Math.min(runLenEncoding(s),minLen);
            s = shiftS(s);
        }

        System.out.println(minLen);

    }

    public static String shiftS(String s) {
        s = s.substring(1) + s.substring(0, 1);
        return s;
    }

    public static int runLenEncoding(String s) {
        int idx = 0;
        String encodingString = "";

        while(idx < s.length()) {
            int curLen = 0;
            curLen += sameLen(idx, s);
            encodingString += s.substring(idx,idx+1) + Integer.toString(curLen);
            idx += curLen;
        }

        return encodingString.length();
    }

    public static int sameLen(int idx,String s) {
        char c = s.charAt(idx);
        int cnt = 1;
        for (int i = idx +1; i < s.length(); i++) {
            if(c != s.charAt(i)){
                // 서로 다른 경우
                break;
            }
            cnt++;
        }
        return cnt;
    }
}