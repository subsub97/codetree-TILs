import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> stringKeyMap = new HashMap<>();
        HashMap<Integer, String> intKeyMap =  new HashMap<>();

        for(int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            stringKeyMap.put(s, i + 1);
            intKeyMap.put(i+1, s);
        }

        for(int i = 0; i < m; i++) {
            String s = br.readLine().trim();
            if(stringKeyMap.containsKey(s)) {
                System.out.println(stringKeyMap.get(s));
            }
            else{
                System.out.println(intKeyMap.get(Integer.parseInt(s)));
            }
        }
    }
}