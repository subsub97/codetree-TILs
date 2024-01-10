import java.util.*;
import java.io.*;

public class Main {
    public static final int MAX_SIZE = 200000; 
    public static int n;
    public static int currentIndex = 100000;
    public static char[] tileArray = new char[MAX_SIZE];
    public static int[] answerArray = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int distance = Integer.parseInt(st.nextToken());
            int direction = st.nextToken().charAt(0);
            
            while(distance-- > 0) {
                if(direction == 'R') {
                    tileArray[currentIndex] = 'R';
                    if(distance > 0) {
                      currentIndex++;
                    }
                }
                else{
                    tileArray[currentIndex] = 'L';
                    if(distance > 0) {
                    currentIndex--;
                    }
                }
            }
        }
        for(int i = 0; i < MAX_SIZE; i++) {
            if(tileArray[i] =='L') {
                answerArray[0] += 1;
            }
            else if(tileArray[i] == 'R'){
                answerArray[1] += 1;
            }
        }

        for(int i = 0; i < 2; i++) {
            System.out.print(answerArray[i] + " ");
        }
        
    }
}