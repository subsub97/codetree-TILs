import java.util.Stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        Stack<Integer> s = new Stack<>();

        for(int i =0 ; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            String cmd = st.nextToken();

            if(cmd.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                s.push(num);
            }
            else if(cmd.equals("pop")) {
                System.out.println(s.pop());
            }
            else if(cmd.equals("size")) {
                System.out.println(s.size());
            }
            else if(cmd.equals("empty")) {
                if(s.isEmpty()) {
                    System.out.println("1");
                }
                else{
                    System.out.println("0");
                }
            }
            else
                System.out.println(s.peek());
        }
    }
}