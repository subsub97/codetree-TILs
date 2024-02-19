import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Element implements Comparable<Element> {
        int dist, index;

        Element(int d, int i) {
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

        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        ArrayList<Element>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[s].add(new Element(w, e));
            graph[e].add(new Element(w, s));
        }

        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = (int) 1e7;
        }

        int[][] ansDist = new  int[n+1][3];

        dist[a] = 0;

        PriorityQueue<Element> pq = new PriorityQueue<>();
        pq.add(new Element(0, a));

        while(!pq.isEmpty()) {
            int minIndex = pq.peek().index;
            int minDist = pq.peek().dist;

            pq.poll();

            if (dist[minIndex] != minDist) {
                continue;
            }

            for (int i = 0; i < graph[minIndex].size(); i++) {
                int targetIndex = graph[minIndex].get(i).index;
                int targetDist = graph[minIndex].get(i).dist;
                int newDist = minDist + targetDist;

                if(dist[targetIndex] > newDist) {
                    dist[targetIndex] = newDist;
                    pq.add(new Element(newDist, targetIndex));
                }
            }
        }

        int curAns = 0;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if(i == a || i == b || i == c) continue;
            curAns = Math.max(curAns, dist[i]);
        }
        ans = curAns;

        for (int i = 1; i <= n; i++) {
            ansDist[i][0] = dist[i];
        }

        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = (int) 1e7;
        }

        dist[b] = 0;

        pq = new PriorityQueue<>();
        pq.add(new Element(0, b));

        while(!pq.isEmpty()) {
            int minIndex = pq.peek().index;
            int minDist = pq.peek().dist;

            pq.poll();

            if (dist[minIndex] != minDist) {
                continue;
            }

            for (int i = 0; i < graph[minIndex].size(); i++) {
                int targetIndex = graph[minIndex].get(i).index;
                int targetDist = graph[minIndex].get(i).dist;
                int newDist = minDist + targetDist;

                if(dist[targetIndex] > newDist) {
                    dist[targetIndex] = newDist;
                    pq.add(new Element(newDist, targetIndex));
                }
            }
        }

        curAns = 0;
        for (int i = 1; i <= n; i++) {
            if(i == a || i == b || i == c) continue;
            curAns = Math.max(curAns, dist[i]);
        }

        for (int i = 1; i <= n; i++) {
            ansDist[i][1] = dist[i];
        }

        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = (int) 1e7;
        }

        dist[c] = 0;

        pq = new PriorityQueue<>();
        pq.add(new Element(0, c));

        while(!pq.isEmpty()) {
            int minIndex = pq.peek().index;
            int minDist = pq.peek().dist;

            pq.poll();

            if (dist[minIndex] != minDist) {
                continue;
            }

            for (int i = 0; i < graph[minIndex].size(); i++) {
                int targetIndex = graph[minIndex].get(i).index;
                int targetDist = graph[minIndex].get(i).dist;
                int newDist = minDist + targetDist;

                if(dist[targetIndex] > newDist) {
                    dist[targetIndex] = newDist;
                    pq.add(new Element(newDist, targetIndex));
                }
            }
        }

        curAns = 0;
        for (int i = 1; i <= n; i++) {
            if(i == a || i == b || i == c) continue;
            curAns = Math.max(curAns, dist[i]);
        }

        for (int i = 1; i <= n; i++) {
            ansDist[i][2] = dist[i];
        }

        int bb = 0;
        for (int i = 1; i <= n; i++) {
            int aa = 10000000;
            for (int j = 0; j < 3; j++) {
                aa = Math.min(aa, ansDist[i][j]);
            }
            bb = Math.max(bb, aa);
        }


        System.out.println(bb);
    }
}