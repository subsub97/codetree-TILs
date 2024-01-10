import java.io.*;
import java.util.*;

public class Main {
    static int OFFSET = 100;
    static int maxOverlap = 0;

    public static void main(String[] args) throws IOException {
        int n;
        int[] line = new int[201];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        


        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for(int j = start; j < end; j++) {
                line[j + OFFSET] += 1;
                if(line[j + OFFSET] > maxOverlap) {
                    maxOverlap = line[j + OFFSET];
                }
            }
        }
        System.out.print(maxOverlap);


    }
}