import java.util.*;
import java.io.*;

public class Main {
    public static final int MAX_SIZE = 17;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        char[] line = new char[MAX_SIZE];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int location = Integer.parseInt(st.nextToken());
            char initial = st.nextToken().charAt(0);

            line[location] = initial;
        }

        int ans = 0;

        for(int i = 1; i < MAX_SIZE; i++) {
            int gCnt = 0;
            int hCnt = 0;
            int lastPoint = i;
            if(line[i] == 'G' || line[i] == 'H') {
                for(int j = i; j < MAX_SIZE; j++) {

                    if(line[j] == 'G') {
                        gCnt++;
                        lastPoint = j;
                    }
                    else if(line[j] == 'H') {
                        hCnt++;
                        lastPoint = j;
                    }

                    if(lastPoint == i){
                        ans = Math.max(ans,0);
                    }
                    else if(gCnt == hCnt || (gCnt > 0 && hCnt ==0) || (hCnt > 0 && gCnt ==0)) {
                        ans = Math.max(ans, lastPoint - i);
                    }
                }
            }
        }

        System.out.print(ans);
    }
}