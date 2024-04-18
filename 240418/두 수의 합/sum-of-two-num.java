import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            Integer number = Integer.parseInt(st.nextToken());
            map.put(i,number);    
        }

        int answer = 0;

        for(Integer key1 : map.keySet()) {
            for(Integer key2 : map.keySet()) {
                if(key1 == key2) continue;
                if((map.get(key1) + map.get(key2)) == m) answer++;
            }
        }

        System.out.print(answer/2);
    }
}