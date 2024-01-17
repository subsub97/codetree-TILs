import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int[] line = new int[101];
    public static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());
        }

        boolean allOverlap = false;
        // 모든 선분 그리기
        for (int i = 0; i < n; i++) {
            for (int j = start[i]; j <= end[i]; j++) {
                line[j] += 1;
                if(line[j] == n) {
                    allOverlap = true;
                }
            }
        }

        if(allOverlap) {
            System.out.println("Yes");
        } else{
            System.out.println("No");

        }
    }
}