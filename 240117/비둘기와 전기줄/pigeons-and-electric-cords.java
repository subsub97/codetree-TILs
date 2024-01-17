import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        boolean[] leftLine = new boolean[11];
        boolean[] rightLine = new boolean[11];

        int changeLine = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int pigeonNumber = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            if(idx == 1){
                if(leftLine[pigeonNumber]) {
                    changeLine++;
                    leftLine[pigeonNumber] = false;
                }
                rightLine[pigeonNumber] = true;
            }
            else{
                if(rightLine[pigeonNumber]) {
                    changeLine++;
                    rightLine[pigeonNumber] = false;
                }
                leftLine[pigeonNumber] = true;
            }
        }

        System.out.println(changeLine);
    }
}