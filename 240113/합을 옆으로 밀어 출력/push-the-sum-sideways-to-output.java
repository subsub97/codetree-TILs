import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int sum = 0;
        String ans = "";

        for(int i = 0; i < n; i++) {
            
            String number = br.readLine();

            sum += Integer.parseInt(number);
        }
        ans = Integer.toString(sum);
        ans = ans.substring(1) + ans.substring(0,1);
        System.out.print(ans);
    }
}