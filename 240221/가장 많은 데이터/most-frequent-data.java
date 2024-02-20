import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        HashMap<String,Integer> map = new HashMap<>();
        int max = 0;
        for(int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            map.put(s,map.getOrDefault(s,0) + 1);
            
            max = Math.max(max,map.get(s));
        }

        System.out.print(max);

    }
}