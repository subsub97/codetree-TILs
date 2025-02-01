import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        TreeSet<Integer> t = new TreeSet<>();

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if(cmd.equals("add")){
                int a = Integer.parseInt(st.nextToken());
                t.add(a);        
            }
            else if(cmd.equals("remove")) {
                int a = Integer.parseInt(st.nextToken());
                t.remove(a);
            }
            else if(cmd.equals("find")) {
                int a = Integer.parseInt(st.nextToken());
                sb.append(t.contains(a) + "\n");
            }
            else if(cmd.equals("lower_bound")) {
                int a = Integer.parseInt(st.nextToken());
                Integer b = t.ceiling(a);
                if(b == null) {
                    sb.append("None\n");
                }
                else{
                    sb.append(b + "\n");
                }
            }
            else if(cmd.equals("upper_bound")) {
                int a = Integer.parseInt(st.nextToken());
                Integer b = t.higher(a);
                if(b == null) {
                    sb.append("None\n");
                }
                else{
                    sb.append(b + "\n");
                }
            }
            else if(cmd.equals("largest")) {
                if(t.size() == 0) {
                    sb.append("None\n");
                }
                else{
                    sb.append(t.last() + "\n");
                }
                
            }
            else{

                if(t.size() == 0) {
                    sb.append("None\n");
                }
                else{
                    sb.append(t.first() + "\n");
                }
                
            }
        }
        System.out.print(sb.toString());
    }
}