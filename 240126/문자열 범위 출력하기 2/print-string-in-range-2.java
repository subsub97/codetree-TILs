import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int n = Integer.parseInt(br.readLine());
        int sl = s.length();
        //sl 은 13 n 은 11 -1 더하면 
        //13부터 1까지 출력
        for (int i = sl-2; i > sl - n -2; i--) {
            System.out.print(s.substring(i,i+1));
        }
    }
}