import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] town = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            town[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int idx = 0;
        while(idx < n) {
            if(town[idx] == 1) {
                ans++;
                idx += (2*m) +1;
            }
            else{
                idx++;
            }
        }

        System.out.println(ans);

    }

}