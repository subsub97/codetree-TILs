import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static final int MAX_SIZE = 10;
    public static char[][] grid = new char[MAX_SIZE][MAX_SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int maxNumber = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    maxNumber = Math.max(maxNumber, numbers[i] * numbers[j] * numbers[k]);
                }
            }
        }

        System.out.println(maxNumber);
    }


}