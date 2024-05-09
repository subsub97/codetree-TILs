import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int result = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            int x = 0;

            if(cmd.equals("add")) {
                x = Integer.parseInt(st.nextToken());
                result = (1 << x) | result;
            } else if (cmd.equals("delete")) {
                x = Integer.parseInt(st.nextToken());
                result =  (result | (1 << x)) ^ 2 << x;
            } else if (cmd.equals("print")) {
                x = Integer.parseInt(st.nextToken());
                System.out.println((result >> x) & 1);
            } else if (cmd.equals("toggle")) {
                x = Integer.parseInt(st.nextToken());
                result = result ^ (1 << x);
            } else {
                result = 0;
            }
        }
    }
}