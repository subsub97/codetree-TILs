import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        String cmd = br.readLine();

        for(int i = 0; i < cmd.length(); i++) {
            if(cmd.charAt(i) == 'L') {
                String temp = word.substring(0,1);
                word = word.substring(1) + temp;
            }
            else{
             word = word.substring(word.length()-1,word.length()) + word.substring(0,word.length()-1);
            }
        }
        System.out.println(word);
    }
}