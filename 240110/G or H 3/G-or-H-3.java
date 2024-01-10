import java.util.*;
import java.io.*;

public class Main {
    public static final int MAX_SIZE = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int location;
        int ans = 0;
        char initial;

        char[] line = new char[MAX_SIZE];

        for(int i = 0 ; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());

            location = Integer.parseInt(st1.nextToken());
            initial = st1.nextToken().charAt(0);
            line[location] = initial;
        }

        for(int i = 0; i < MAX_SIZE - K; i++){
            int currentScore = 0;
        
            for(int j = i; j <= K+i; j++) {
                if(line[j] == 'G') {
                    currentScore += 1;
                }
                else if(line[j] == 'H') {
                    currentScore += 2;
                }
            }
            ans = Math.max(ans,currentScore);
        }
        System.out.print(ans);
    }
}