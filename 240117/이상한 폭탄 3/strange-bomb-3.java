import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int maxNumber = 0;
        int[] bombs = new int[n];

        for (int i = 0; i < n; i++) {
            bombs[i] = Integer.parseInt(br.readLine().trim());
            maxNumber = Math.max(bombs[i], maxNumber);
        }

        int maxCnt = 0;
        int ans = 0;
        for (int i = 1; i <= maxNumber ; i++) {
            // i번 폭탄이 몇개 터지는지 확인
            boolean[] explosion = new boolean[n];
            int cnt = 1;
            for (int j = 0; j < n; j++) {
                if(bombs[j] == i) {
                    for (int l = j+1; l < k; l++) {
                        if(bombs[l] == i && !explosion[l]) {
                            explosion[l] = true;
                            cnt++;
                        }
                    }
                }
            }
            if(cnt > maxCnt) {
                maxCnt = cnt;
                ans = i;
            }
        }
        
        if(ans == 1) {
            System.out.print(0);
        }
        else
            System.out.println(ans);

    }
}