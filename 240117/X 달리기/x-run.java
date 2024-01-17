import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int bestTime = Integer.MAX_VALUE;


        for (int i = 1; i < n/2; i++) {
            // 최고 속력을 어디까지로 할지 정함
            int elapseTime = 0;
            int curLocation = 0;
            int speed = 0;
            int[] distance = new int[n];
            while(curLocation < n) {
                elapseTime++;

                if(speed < i && distance[i] == 0){
                    speed++;
                    distance[speed] = distance[speed-1] + speed;
                }

                curLocation += speed;

                if(n - curLocation - distance[speed] < 0) speed--;

            }

            if(curLocation == n && (speed == 0 || speed == 1)) {
                bestTime = Math.min(bestTime, elapseTime);
            }
        }
        System.out.println(bestTime);
    }
}