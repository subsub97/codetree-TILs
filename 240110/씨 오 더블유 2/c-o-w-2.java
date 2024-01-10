import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int answer = 0;

        String word = br.readLine();

        for(int i = 0; i < word.length(); i++) {
            for(int j = i+1; j < word.length(); j++) {
                for(int k = j+1; k < word.length(); k++) {
                    if(word.charAt(i) == 'C' && word.charAt(j) == 'O' && word.charAt(k) == 'W') {
                        answer++;
                    }
                }
            }
        }

        System.out.print(answer);
    }
}