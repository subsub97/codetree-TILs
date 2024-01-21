import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LinkedList<Character> l = new LinkedList<>();


        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String code = br.readLine();

        for(int i = 0; i < code.length();i++) {
            l.add(code.charAt(i));
        }
        ListIterator<Character> it = l.listIterator(l.size());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);
            if('L' == cmd) {
                if(it.hasPrevious()) {
                    it.previous();
                }
            }
            else if('R' == cmd) {
                if(it.hasNext()) {
                    it.next();
                }
            }
            else if('D' == cmd) {
                if(it.hasNext()){
                    it.next();
                    it.remove();
                }
            }
            else {
                char codeAlpa = st.nextToken().charAt(0);
                it.add(codeAlpa);
            }
        }

        it = l.listIterator();

        while(it.hasNext()) {
            System.out.print(it.next());
        }
    }
}