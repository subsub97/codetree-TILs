import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] developers = new int[5];
        int ans = Integer.MAX_VALUE;
        int scoreSum = 0;
        for(int i = 0; i < 5; i++) {
            developers[i] = Integer.parseInt(st.nextToken());
            scoreSum += developers[i];
        }
        
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    for(int l = 0; l < 5; l++){
                        if(i != j && j != k && i != k && i != l 
                        && j != l && k !=l) {
                            int teamA = developers[i] + developers[j];
                            int teamB = developers[k] + developers[l];
                            int solo =  scoreSum - (teamA + teamB);

                            if(teamA != teamB && teamA != solo && teamB != solo){
                            int max = Math.max(Math.max(teamA,teamB),solo);
                            int min = Math.min(Math.min(teamA,teamB),solo);
                            
                            ans = Math.min(ans,max - min);
                            }
                        }
                    }                     
                }
            }
        }

        System.out.print(ans);
    }
}