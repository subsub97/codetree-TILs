import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
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

        Iterator<Integer> itr1 = q1.iterator();
        Iterator<Integer> itr2 = q2.iterator();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(itr1.hasNext()){
            bw.append(itr1.next() +" ");
        }

        bw.append("\n");
        while(itr2.hasNext()){
            bw.append(itr2.next() +" ");
        }

        bw.close();
    }
}