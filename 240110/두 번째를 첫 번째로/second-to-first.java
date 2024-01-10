import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        char[] convertWord = new char[word.length()];

        char firstChar = word.charAt(0);
        char secondChar = word.charAt(1);

        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == secondChar) {
                convertWord[i] = firstChar;
            }
            else{
                convertWord[i] = word.charAt(i);
            }
            
        }

        
        System.out.print(String.valueOf(convertWord));

    }
}