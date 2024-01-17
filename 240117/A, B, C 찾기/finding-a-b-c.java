import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[7];

        for (int i = 0; i < 7; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int a = numbers[0];
        int b = numbers[1];
        int c = numbers[6] - (a + b);

        System.out.println(a+ " " + b +" "+ c);
    }
}