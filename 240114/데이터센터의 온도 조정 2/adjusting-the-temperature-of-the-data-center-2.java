import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Ta보다 낮으면 C만큼의 작업량 수행

        // Ta이상 Tb이하면 G만큼의 수행

        // Tb보다 높다면 H만큼
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] workScore = new int[1000];

        for(int i = 0; i<n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());

            int ta = Integer.parseInt(st1.nextToken());
            int tb = Integer.parseInt(st1.nextToken());

            for(int j = 0; j < 1000; j++) {
                if(j < ta) {
                    workScore[j] += c;
                }
                else if( ta <= j && j <= tb) {
                    workScore[j] += g;
                }
                else{
                    workScore[j] += h;
                }
            }
        }

        int ans = 0;

        for(int i = 0; i < 1000; i++) {
            ans = Math.max(ans,workScore[i]);
        }

        System.out.print(ans);
    }
}