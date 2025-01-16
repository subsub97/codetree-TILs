import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int x = 0;

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();
            
            if(cmd.equals("add")){
              int value = Integer.parseInt(st.nextToken());

                x = x | (1 << value);  
            }
            else if(cmd.equals("delete")) {
                int value = Integer.parseInt(st.nextToken());
                if( ((x >> value) & 1) == 1) {
                    x = x ^ (1 << value);
                }
            }
            else if(cmd.equals("print")){
                int value = Integer.parseInt(st.nextToken());
                System.out.println((x >> value) & 1);
            }
            else if(cmd.equals("toggle")) {
                int value = Integer.parseInt(st.nextToken());
                x = x ^ (1 << value);
            }
            else{
                //clear
                x = 0;
            }
        }
    }

    // 1 1 1 1 1
    // 1 1 0 1 1
}