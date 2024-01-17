import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] x = new int[4];

        String correct = "interesting";
        String notCorrect = "nonintersecting";
        for (int i = 0; i < 4; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        if((x[0] <= x[2] && x[2] <= x[1]) || (x[0] <= x[3] && x[3] <= x[2])){
            System.out.println(correct);
        }
        else {
            System.out.println(notCorrect);
        }

    }
}