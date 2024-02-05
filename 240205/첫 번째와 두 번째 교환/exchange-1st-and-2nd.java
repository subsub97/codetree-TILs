import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        StringBuffer sb = new StringBuffer();

        char first = word.charAt(0);
        char second = word.charAt(1);

        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == first) {
                sb.append(second);
            } else if (word.charAt(i) == second) {
                sb.append(first);
            }
            else{
                sb.append(word.charAt(i));
            }
        }

        System.out.println(sb.toString());
    }
}