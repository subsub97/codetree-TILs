import java.io.*;
import java.util.*;

public class Main {
    public static final int[][] arr = new int[10][20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < k; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        int ans = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <=n; j++) {
                // i번 개발자가 j번 개발자보다 항상 높은 순위인지 여부를 판단.

                if(i == j) {
                    continue;
                }

                boolean correct = true;

                // k번의 모든 경기에 대해서 두 개발자의 위치를 찾고,
                // 하나라도 i번 개발자가 더 뒤에 있으면 false

                for(int x = 0; x < k; x++) {
                    int indexI = 0, indexJ = 0;

                    for(int y = 0; y < n; y++) {
                        if(arr[x][y] == i)
                            indexI = y;
                        if(arr[x][y] == j)
                            indexJ = y;
                    }

                    if(indexI > indexJ)
                        correct = false;
                }

                if(correct)
                    ans++;
            }
    }
            System.out.print(ans);
        
    }
}