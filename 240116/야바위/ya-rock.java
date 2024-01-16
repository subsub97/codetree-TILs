import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[][] cmds = new int[n][3];

        for(int i =0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 3; j++) {
                cmds[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        
        for(int i = 1; i <= 3; i++) {
            //1번에 넣고 먼저 돌리기
            int cnt = 0;
            boolean[] smallStone = new boolean[4];
            smallStone[i] = true;

            for(int j = 0; j < n; j++) {
                int a = cmds[j][0];
                int b = cmds[j][1];
                int c = cmds[j][2];
                    
                boolean temp = smallStone[a];
                smallStone[a] = smallStone[b];
                smallStone[b] = temp;

                if(smallStone[c]){
                    cnt++;
                }
                
            }

            ans = Math.max(ans,cnt);
        }
        System.out.print(ans);
    }
}