import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();

        int n = Integer.parseInt(br.readLine().trim());

        for(int i =0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("push")) {
                int k = Integer.parseInt(st.nextToken());
                q.add(k);
            }
            else if(cmd.equals("front")) {
                if(!q.isEmpty())
                    System.out.println(q.peek());
            }
            else if(cmd.equals("size")) {
                System.out.println(q.size());
            }
            else if(cmd.equals("pop")) {
                System.out.println(q.poll());
            }
            else if(cmd.equals("empty")) {
                if(q.isEmpty()){
                    System.out.println("1");
                }
                else{
                    System.out.println("0");
                }
            }
        }
    }
}