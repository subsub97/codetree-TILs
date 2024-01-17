import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] square1 = new int[4];
        int[] square2 = new int[4];

        for (int i = 0; i < 4; i++) {
            square1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            square2[i] = Integer.parseInt(st.nextToken());
        }

        int minX = Math.min(square1[0], square2[0]);
        int maxX = Math.max(square1[2], square2[2]);
        int minY = Math.min(square1[1],square2[1]);
        int maxY = Math.max(square1[3],square2[3]);

        System.out.println((maxX - minX) * (maxY-minY));
    }
}