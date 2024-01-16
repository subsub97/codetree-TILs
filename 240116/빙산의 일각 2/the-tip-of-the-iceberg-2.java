import java.io.*;

public class Main {
    public static final int MAXHEIGHT = 1001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[] iceBurg = new int[n];

        for(int i = 0; i < n; i++) {
            iceBurg[i] = Integer.parseInt(br.readLine().trim());
        }

        int ans = 0;

        for(int i = 1; i < MAXHEIGHT; i++) {
            int cnt = 0;
            boolean[] visited = new boolean[n];
            for(int j = 0; j < n; j++) {
                if(!visited[j]) {
                    // 현재 빙산이 해수면 보다 높은 경우
                    for(int k = j; k < n; k++) {
                        if(iceBurg[k] > i) {
                            visited[k] = true;
                        }
                        else{
                            break;
                        }
                    }
                    if(iceBurg[j] > i)
                        cnt++;
                }
            }

            ans = Math.max(cnt,ans);
        }
        System.out.print(ans);
    }


}