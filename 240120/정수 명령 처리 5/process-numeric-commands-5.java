import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> v = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int k = 0;
        for(int i  = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd= st.nextToken();

            if(!(cmd.equals("size") || cmd.equals("pop_back"))){
                k = Integer.parseInt(st.nextToken());
            }

            if(cmd.equals("push_back")) {
                v.add(k);
            } else if(cmd.equals("get")) {
                System.out.println(v.get(k-1));
            } else if(cmd.equals("size")) {
                System.out.println(v.size());
            } else if(cmd.equals("pop_back")) {
                v.remove(v.size() - 1);
            }
        }
    }
}