import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int[] line = new int[101];
    public static final int MAX_N = 100;
    public static int maxX1;
    public static int minX2 = MAX_N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());

            maxX1 = Math.max(maxX1, start[i]);
            minX2 = Math.min(minX2, end[i]);
        }

        if(minX2 >= maxX1) {
            System.out.println("Yes");
        } else{
            System.out.println("No");

        }
    }
}