import java.util.*;
import java.io.*;

public class Main {
    static final int MAX_SIZE = 200000;

    public static void main(String[] args) throws IOException {
        
        int n;
        char[] line = new char[MAX_SIZE];
        int[] countArray = new int[MAX_SIZE];
        int currentIndex = 100000;
        int[] answerArray = new int[3];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int repeatCount = Integer.parseInt(st.nextToken());
            int count = 0;
            char direction = st.nextToken().charAt(0);

            if(direction == 'L') {
                while(count < repeatCount) {
                    line[currentIndex] = 'W';
                    countArray[currentIndex] += 1;
                    if(count + 1 != repeatCount) currentIndex--;
                    count++;
                }
            
            }
            else { // 'R'인 경우
                while(count < repeatCount){
                    line[currentIndex] = 'B';
                    countArray[currentIndex] += 1;
                    if(count + 1 != repeatCount) currentIndex++;
                    count++;
                }
            }
            
        }

        for(int i = 0; i < line.length; i++)  {
            char color = line[i];
            if(color == 'W' && countArray[i] < 4) {
                answerArray[0] += 1;
            }
            else if(color == 'B' && countArray[i] < 4) {
                answerArray[1] += 1;
            }
            else if (countArray[i] >= 4){
                answerArray[2] += 1;
            }
        }

        for(int i = 0; i < 3; i++) {
            System.out.print(answerArray[i] + " ");
        }
    }
}