import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        threeN(n);
        System.out.println(cnt);
    }

    public static void threeN(int num) {
        if(num == 1){
            return;
        }

        if(num % 2 == 0) {
            threeN(num / 2 );
        }
        if(num % 2 != 0){
            threeN(num * 3 + 1);
        }

        cnt++;
    }
}