import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine().trim());

        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("push_front")) {
                int k = Integer.parseInt(st.nextToken());
                dq.addFirst(k);
            }
            else if(cmd.equals("push_back")) {
                int k = Integer.parseInt(st.nextToken());
                dq.addLast(k);
            }
            else if(cmd.equals("pop_front")) {
                if(!dq.isEmpty())
                    System.out.println(dq.pollFirst());
            }
            else if(cmd.equals("pop_back")) {
                if(!dq.isEmpty())
                    System.out.println(dq.pollLast());                

            }
            else if(cmd.equals("front")) {
                if(!dq.isEmpty())
                    System.out.println(dq.peekFirst());

            }
            else if(cmd.equals("back")) {
                if(!dq.isEmpty())
                    System.out.println(dq.peekLast());
            }
            else if(cmd.equals("empty")) {
                if(dq.isEmpty()) {
                    System.out.println("1");
                }
                else{
                    System.out.println("0");
                }
            }
            else{
                System.out.println(dq.size());
            }
        }
    }
}