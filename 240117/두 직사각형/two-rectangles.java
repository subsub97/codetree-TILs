import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        int[] square1 = new int[4];
        int[] square2 = new int[4];

        String correct = "overlapping";
        String notCorrect = "nonoverlapping";
        for (int i = 0; i < 4; i++) {
            square1[i] = Integer.parseInt(st.nextToken());
            square2[i] = Integer.parseInt(st1.nextToken());
        }
        if((square1[2] < square2[0]) || square2[2] < square1[0] || square1[3] < square2[1] || square2[3] < square1[1]){
            // 겹치지 않는 경우
            System.out.println(notCorrect);
        }
        else {
            System.out.println(correct);
        }

    }
}