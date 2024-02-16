import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static class Element implements Comparable<Element> {
        int dist, index;

        Element(int d,int i) {
            dist = d;
            index = i;
        }

        @Override
        public int compareTo(Element e) {
            return this.dist - e.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        ArrayList<Element>[] graph = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[x].add(new Element(w,y));
        }

        int[] dist = new int[n+1];
        int[] path = new int[n+1];

        for (int i = 1; i <= n; i++) {
            dist[i] = (int) 1e9;
        }

        st = new StringTokenizer(br.readLine());
        int startV = Integer.parseInt(st.nextToken());
        int endV = Integer.parseInt(st.nextToken());

        dist[startV] = 0;

        PriorityQueue<Element> pq = new PriorityQueue<>();
        pq.add(new Element(0, startV));

        int minIndex;
        int minDist;

        int targetIndex;
        int targetDist;
        int newDist;
        while(!pq.isEmpty()) {
            minIndex = pq.peek().index;
            minDist = pq.peek().dist;
            pq.poll();

            if(minDist != dist[minIndex]) continue;

            for (int i = 0; i < graph[minIndex].size(); i++) {
                targetIndex = graph[minIndex].get(i).index;
                targetDist = graph[minIndex].get(i).dist;
                newDist = dist[minIndex] + targetDist;

                if(dist[targetIndex] >= newDist) {
                    dist[targetIndex] = newDist;
                    path[targetIndex] = minIndex;
                    pq.add(new Element(newDist, targetIndex));
                }
            }
        }
        int idx = endV;
        Stack<Integer> minPath = new Stack<>();

        minPath.add(idx);

        while(idx != startV) {
            idx = path[idx];
            minPath.add(idx);
        }
        int size = minPath.size();
        System.out.println(dist[endV]);
    
        for (int i = 0; i < size; i++) {
            System.out.print(minPath.pop() + " ");
        }

    }
}