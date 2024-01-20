import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> v = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] word = br.readLine().toCharArray();

        for (int i = 0; i < word.length; i++) {
            if(i == 1 || i == word.length - 2) {
                word[i] = 'a';
            }
        }

        System.out.println(String.valueOf(word));


    }
}