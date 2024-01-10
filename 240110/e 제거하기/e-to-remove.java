import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == 'e') {
                word = word.substring(0,i) + word.substring(i+1,word.length());
                break;
            }
        }

        System.out.print(word);
    }
}