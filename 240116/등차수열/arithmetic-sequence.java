import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n];
        int maxNumber = 0;
        for(int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            maxNumber = Math.max(maxNumber,numbers[i]);
        }

        Arrays.sort(numbers);

        int minNumber = numbers[0] + 1;
        
        int ans = 0;
        
        for(int i = minNumber; i < maxNumber; i++) {
            int gap = 1;
            int cnt = 0;

            while(i + gap <= maxNumber) {
                boolean downCondition = false;
                boolean upCondition = false;

                for(int j = 0; j < n; j++) {
                    if(i - gap == numbers[j]) {
                        downCondition = true;
                    }
                    if(i + gap == numbers[j]) {
                        upCondition = true;
                    }

                    if(downCondition && upCondition) {
                        cnt++;
                        break;
                    }
                }

                gap++;
                ans = Math.max(ans,cnt);
            }
        }

        System.out.print(ans);
    }
}