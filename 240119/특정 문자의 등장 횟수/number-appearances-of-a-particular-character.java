import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        
        int eeCnt = 0;
        int ebCnt = 0;
        
        for (int i = 0; i < st.length()-1; i++) {
            if(st.substring(i,i+2).equals("ee")) eeCnt++;
            if(st.substring(i,i+2).equals("eb")) ebCnt++;
        }

        System.out.println(eeCnt + " " + ebCnt);
    }
}