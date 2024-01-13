import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;

        String a = br.readLine();
        String b = br.readLine();

        boolean check = true;

        for(int i = 0; i < a.length(); i++) {
            if(a.equals(b)){
                check = false;
                break;
            }
            a = a.substring(a.length()-1) + a.substring(0,a.length()-1);
            sum++;
        }

        if(!check)
            System.out.print(sum);
        else{
            System.out.print(-1);
        }
    }
}