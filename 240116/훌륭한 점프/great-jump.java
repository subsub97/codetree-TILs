import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];

        StringTokenizer st1 = new StringTokenizer(br.readLine());

        int maxNumber = 0;
        int ans =0;
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st1.nextToken());
            maxNumber = Math.max(numbers[i], maxNumber);
        }
        for (int i = maxNumber; i > 0; i--) {
            int cnt = 0;
            int[] stampLeg = new int[n];
            for (int j = 0; j < n; j++) {
                if(numbers[j] <= i) {
                    // 밟을 수 있는 다리
                    stampLeg[cnt++] = j;
                }
            }
            boolean canGo = false;

            for (int j = 1; j < cnt; j++) {
                int diff = Math.abs(stampLeg[j] - stampLeg[j - 1]);

                if(diff > k){
                    canGo = false;
                    break;
                }
                canGo =true;
            }

            if(canGo) ans = i;


        }
        System.out.println(ans);
    }
}