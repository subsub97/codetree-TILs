import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        
        s = s.substring(0,1) + s.substring(2,s.length() - 2) + s.substring(s.length()-1);

        System.out.print(s); 
    }
}