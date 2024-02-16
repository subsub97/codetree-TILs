import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        String ans = st.nextToken();
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            String temp = br.readLine();
            if(temp.equals(ans)) {
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}