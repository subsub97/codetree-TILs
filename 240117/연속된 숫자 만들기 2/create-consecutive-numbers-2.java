import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if(b-a == 1 && c-b == 1) {
            System.out.println(0);
        }
        if(Math.abs(b - a) == 2 || Math.abs(c-b) == 2){
            System.out.println(1);
        }
        else{
            System.out.println(2);
        }
    }
}