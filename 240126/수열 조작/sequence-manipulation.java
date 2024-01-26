import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine().trim());

       Deque<Integer> dq = new ArrayDeque<>();

       for(int i = 1; i <=n; i++) {
        dq.addLast(i);
       }

       while(dq.size() != 1) {
        dq.pollFirst();
        dq.addLast(dq.pollFirst());
       }

       System.out.print(dq.peekFirst());

    }
}