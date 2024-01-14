import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int startNumber = Integer.parseInt(st.nextToken());
        int endNumber = Integer.parseInt(st.nextToken());
        
        int ans = 0;

        for(int i = startNumber; i <= endNumber; i++) {
            if(interestNumber(Integer.toString(i))) {
                ans++;
            }
        }

        System.out.print(ans);
    }

    public static boolean interestNumber(String number) {
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < number.length(); i++) {
            String key = number.substring(i,i+1);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        if(map.size() == 2) {
            return true;
        }
        else{
            return false;
        }
        
        
    }
}