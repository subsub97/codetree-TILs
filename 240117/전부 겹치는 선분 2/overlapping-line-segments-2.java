import java.io.*;
import java.util.StringTokenizer;

public class Main {
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
            int[] line = new int[101];
            for (int j = 0; j < n; j++) {
                if(i == j) continue;
                for (int k = start[j]; k <= end[j]; k++) {
                    line[k] += 1;
                    if(line[k] == n-1) {
                        allOverlap = true;
                    }
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