import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        Deque<Integer> q1 = new LinkedList<>();
        Deque<Integer> q2 = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            q1.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            q2.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < t; i++) {
            q2.addFirst(q1.pollLast());
            q1.addFirst(q2.pollLast());
        }

        for(Integer num:q1) {
            System.out.print(num +" ");
        }

        System.out.println();
        for(Integer num:q2) {
            System.out.print(num +" ");
        }
    }
}