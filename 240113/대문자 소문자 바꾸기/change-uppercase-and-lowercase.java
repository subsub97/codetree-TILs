import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        for(int i = 0; i < word.length(); i++) {
            char alpa = word.charAt(i);

            if('A' <= alpa && alpa <= 'Z') {
                char temp = (char)((alpa - 'A') + 'a');
                word = word.substring(0,i) + temp + word.substring(i+1);
            }
            else{
                char temp = (char)((alpa - 'a') + 'A');
                word = word.substring(0,i) + temp + word.substring(i+1);
            }
        }
        System.out.print(word);
    }
}