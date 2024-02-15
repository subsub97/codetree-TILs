import java.util.*;
import java.io.*;

public class Main {
    public static class Edge {
        int s,e,w;

        Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    public static class Node {
        int dist,index;

        Node(int d, int i) {
            dist = d;
            index = i;
        }
    }

    public static class Element implements Comparable<Element> {
        int dist, index;

        Element(int dist, int index) {
            this.dist = dist;
            this.index = index;
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

        Edge[] edges = new Edge[m];
        ArrayList<Node>[] graph = new ArrayList[n+1];

        for(int i = 0; i< n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) -1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(e,s,w);
            graph[e].add(new Node(w,s));
        }

        int[] dist = new int[n];

        for(int i = 0; i < n; i++) {
            dist[i] = (int)1e9;
        }

        dist[n-1] = 0;
        PriorityQueue<Element> pq = new PriorityQueue<>();

        pq.add(new Element(0,n-1));

        while(!pq.isEmpty()) {
            int minDist = pq.peek().dist;
            int minIndex = pq.peek().index;
            pq.poll();

            if(minDist != dist[minIndex]) continue;

            for(int j = 0; j < graph[minIndex].size(); j++) {
                int targetIndex = graph[minIndex].get(j).index;
                int targetDist = graph[minIndex].get(j).dist;

                int newDist = dist[minIndex] + targetDist;
                if(dist[targetIndex] > newDist) {
                    //갱신
                    dist[targetIndex] = newDist;
                    pq.add(new Element(newDist, targetIndex));
                }
            }
        }
        int max = 0;

        for(int i = 0; i < n; i++) {
            if(dist[i] == (int)1e9) continue;
            max = Math.max(max,dist[i]);
        }

        System.out.print(max);
    }
}