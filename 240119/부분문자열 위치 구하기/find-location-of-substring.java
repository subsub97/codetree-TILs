import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        String partitionWord = br.readLine();

        int ans = -1;

        for (int i = 0; i < st.length()-partitionWord.length()-1; i++) {
            if(st.substring(i,i+partitionWord.length()).equals(partitionWord)) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }
}