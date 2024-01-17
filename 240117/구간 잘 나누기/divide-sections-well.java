import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];
        int maxNumber = 0;
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st1.nextToken());
            maxNumber = Math.max(numbers[i], maxNumber);
        }


        for (int i = maxNumber; i <= MAX_N; i++) {
            //답을 i라고 가정하고 문제 풀이
            int[] segement = new int[m];
            int index = 0;
            boolean isPartition = true;
            for (int j = 0; j < n; j++) {
                if(segement[index] + numbers[j] > i ){
                    if(index >= m){
                        isPartition = false;
                        break;
                    }
                    index++;
                    
                }
                if(index >= m){
                    isPartition =false;
                    break;
                }
                segement[index] += numbers[j];
            }
            if (isPartition) {
                System.out.println(i);
                break;
            }
        }
    }
}