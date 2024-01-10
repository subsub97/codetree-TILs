import java.util.*;
import java.io.*;


public class Main {
    public static final int MAX_SIZE = 40000;

    public static void main(String[] args) throws IOException{


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());


        int answer = Integer.MAX_VALUE;
        int width = 0;


        int[] xArray = new int[n];
        int[] yArray = new int[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new  StringTokenizer(br.readLine());

            xArray[i] = Integer.parseInt(st.nextToken());
            yArray[i] = Integer.parseInt(st.nextToken());

        }

        for(int i = 0; i < n; i++) {
            int xMin = Integer.MAX_VALUE;
            int yMin = Integer.MAX_VALUE;
            int xMax = Integer.MIN_VALUE;
            int yMax = Integer.MIN_VALUE;

            for(int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                int x = xArray[j];
                int y = yArray[j];

                xMin = Math.min(xMin, x);
                xMax = Math.max(xMax, x);
                yMin = Math.min(yMin, y);
                yMax = Math.max(yMax, y);

            }
            width = (xMax - xMin) * (yMax - yMin);

            answer = Math.min(answer,width);

        }

        System.out.print(answer);
    }
}