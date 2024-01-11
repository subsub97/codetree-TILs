import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =   new StringTokenizer(br.readLine());

        int[] developers = new int[6];
        
        for(int i = 0; i < 6; i++) {
            developers[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                for(int k = 0; k < 6; k++) {
                    for(int l = 0; l < 6; l++) {
                        for(int n = 0; n < 6; n++) {
                            for(int m = 0; m < 6; m++) {
                                if(i != j && i != k && i != l && i != n && i != m && j != k && j != l 
                                 && j != n && j != m && k != l && k != n && k != m && l != n && l != m
                                    && n != m) {
                                        int teamA = developers[i] + developers[j];
                                        int teamB = developers[k] + developers[l];
                                        int teamC = developers[n] + developers[m];

                                        int max = Math.max(Math.max(teamA,teamB),teamC);
                                        int min = Math.min(Math.min(teamA,teamB),teamC);

                                        ans = Math.min(ans,max-min);
                                    }
                            }
                        }
                    }
                }
            }
        }
        System.out.print(ans);
    }
}