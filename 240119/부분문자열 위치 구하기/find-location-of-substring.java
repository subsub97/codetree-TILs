import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        String partitionWord = br.readLine();

        int ans = -1;

        for (int i = 0; i < st.length(); i++) {
            if(i + partitionWord.length() <= st.length()){
                if(st.substring(i,i+partitionWord.length()).equals(partitionWord)) {
                ans = i;
                break;
            }
            else{
                break;
            }
            }

        }

        System.out.println(ans);
    }
}