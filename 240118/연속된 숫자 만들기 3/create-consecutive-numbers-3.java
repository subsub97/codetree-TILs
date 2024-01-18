import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if(a+1 == b && b + 1 == c){
            System.out.println(0);
        } else {
            int ans = Math.max( Math.abs(b-a), Math.abs(b-c)) -1;
            System.out.println(ans);
        }
    }
}