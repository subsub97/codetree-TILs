import com.sun.security.jgss.GSSUtil;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[15];
        for (int i = 0; i < 15; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        System.out.println(numbers[0] + " "+numbers[1] + " " + numbers[2] +" "+ (numbers[14] - (numbers[0] + numbers[1] + numbers[2])) );

    }
}