import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[] up = new int[n];
        int[] down = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            up[i] = Integer.parseInt(st.nextToken());
            down[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 1; i <= 10000; i++) {
            boolean success = true;
            int x = i;
            for (int j = 0; j < n; j++) {
                x *= 2;
                if(!(up[j] <= x && x <= down[j])) {
                    success = false;
                }
            }

            if(success) {
                System.out.println(i);
                break;
            }
        }
    }
}