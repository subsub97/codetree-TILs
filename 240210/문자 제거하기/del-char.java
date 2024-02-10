import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        while(word.length() > 1) {
            int k = Integer.parseInt(br.readLine().trim());
            if(k > word.length()) {
                word = word.substring(0,word.length()-1);
            }
            else
                word = word.substring(0,k) + word.substring(k+1);
                System.out.println(word);
        }

        
        
    }
}