import java.util.*;
import java.io.*;

public class Main {

    public static final int OFFSET = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        
        int[] xs = new int[n];
        int[] ys = new int[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            xs[i] = Integer.parseInt(st.nextToken()) + OFFSET;
            ys[i] = Integer.parseInt(st.nextToken()) + OFFSET;
        }

        int ans = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n ; j++) {
                for(int k = j + 1; k < n; k++) {
                    int x1 = xs[i], x2 = xs[j], x3 = xs[k];
                    int y1 = ys[i], y2 = ys[j], y3 = ys[k];
                    
                    if((y1 == y2 || y1 == y3 || y2 == y3)
                        && (x1 == x2 || x1 == x3 || x2 == x3)) {
                            int xLen = 0;
                            int yLen = 0;

                            if(x1 == x2) {
                                yLen = Math.abs(y1 - y2);
                            }
                            else if(x1 == x3){
                                yLen = Math.abs(y1 - y3);
                            }
                            else if(x2 == x3) {
                                yLen = Math.abs(y2 - y3);
                            }

                            if(y1 == y2) {
                                xLen = Math.abs(x1 - x2);
                            }
                            else if(y1 == y3) {
                                xLen = Math.abs(x1 - x3);
                            }
                            else if(y2 == y3) {
                                xLen = Math.abs(x2 - x3);
                            }

                            ans = Math.max(ans,xLen * yLen);
                    }
                }
            }
        }
        System.out.print(ans);
    }
}