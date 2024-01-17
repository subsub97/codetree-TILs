import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int minCost = Integer.MAX_VALUE;

        minCost = Math.min(Math.abs(a - b), minCost);
        minCost = Math.min(Math.abs(a - x) + Math.abs(b-y), minCost);
        minCost = Math.min(Math.abs(a - y) + Math.abs(b-x), minCost);

        System.out.println(minCost);
    }
}