import java.util.*;
import java.io.*;

public class Main {
    public static final int OFFSET = 1_000_000;
    public static final int MAX_SIZE = 2_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] xStarts = new int[n+1];
        int[] xEnds = new int[n+1];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int tempa = Integer.parseInt(st.nextToken()) + OFFSET;
            int tempb = Integer.parseInt(st.nextToken()) + OFFSET;

            xStarts[i] = Math.min(tempb,tempa);
            xEnds[i] = Math.max(tempa,tempb);

        }

        int ans = 0;
        boolean[] overlapLine = new boolean[n+1];

        for(int i = 1; i < n+1; i++) {

                for(int j = 0; j < n + 1; j++ )  {
                    if(xStarts[i] <= xStarts[j] && xEnds[i] > xEnds[j]) {
                        //겹치는 경우
                        overlapLine[i] = true;
                        overlapLine[j] = true;
                    }
                }

        }
        for(int i = 1; i <= n; i++) {
            if(!overlapLine[i]){
                ans++;
            }
        }
        System.out.print(ans);

    }
}