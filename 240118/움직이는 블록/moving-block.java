import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] blocks = new int[n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            blocks[i] = Integer.parseInt(br.readLine().trim());
            sum += blocks[i];
        }

        int average = sum / n;
        int upBlock = 0;
        int downBlock = 0;
        int cost = 0;
        int gap = 0;

        for (int i = 0; i < n; i++) {
            if(blocks[i] > average) {
                //평균보다 높아서 줄여야 하는 경우
                gap = blocks[i] - average;
                if(upBlock >= gap) {
                    upBlock -= gap;
                } else {
                    gap -= upBlock;
                    upBlock = 0;
                    cost += gap;
                    downBlock += gap;
                }
            }
            else{
                gap = average - blocks[i];
                if(downBlock >= gap) {
                    downBlock -= gap;
                } else{
                    gap -= downBlock;
                    downBlock = 0;
                    cost += gap;
                    upBlock += gap;
                }
            }
        }

        System.out.println(cost);


    }

}