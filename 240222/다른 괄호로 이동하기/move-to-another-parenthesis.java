import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static Queue<Pair> q = new LinkedList<>();
    public static char[][] grid;
    public static boolean[][] visited;
    public static ArrayList<Element>[] graph;

    public static int n,a,b;
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

    public static class Pair {
        int row,col;

        Pair(int r, int c) {
            row = r;
            col = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        grid = new char[n][n];


        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < n; j++) {
                grid[i][j] = s.charAt(j);
            }
        }

        graph = new ArrayList[n*n + 1];

        for (int i = 1; i <= n*n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            if(i == n-1) {
                for (int j = 0; j <n-1 ; j++) {
                    int v1 =  (i * n) + (j + 1);
                    int v2 = v1 + 1;
                    if(grid[i][j] == grid[i][j+1]) {
                        graph[v1].add(new Element(1, v2));
                        graph[v2].add(new Element(1, v1));
                    }
                    else{
                        graph[v1].add(new Element(2, v2));
                        graph[v2].add(new Element(2, v1));
                    }
                }
                continue;
            }
            for (int j = 0; j < n; j++) {
                int v1 =  (i * n) + (j + 1);
                int v2 = v1 + 1;
                int v3 = v1 + n;
                if(j == n-1) {
                    if(grid[i][j] == grid[i+1][j]) {
                        graph[v1].add(new Element(1, v3));
                        graph[v3].add(new Element(1, v1));
                    }
                    else{
                        graph[v1].add(new Element(2, v3));
                        graph[v3].add(new Element(2, v1));
                    }
                    continue;
                }

                if(grid[i][j] == grid[i][j+1]) {
                    graph[v1].add(new Element(1, v2));
                    graph[v2].add(new Element(1, v1));
                }
                else{
                    graph[v1].add(new Element(2, v2));
                    graph[v2].add(new Element(2, v1));
                }
                if(grid[i][j] == grid[i+1][j]) {
                    graph[v1].add(new Element(1, v3));
                    graph[v3].add(new Element(1, v1));
                }
                else{
                    graph[v1].add(new Element(2, v3));
                    graph[v3].add(new Element(2, v1));
                }
            }
        }

        long[] dist = new long[n * n + 1];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = (long)1e17;
        }

        dist[1] = 0;

        PriorityQueue<Element> pq = new PriorityQueue<>();

        pq.add(new Element(0, 1));

        while(!pq.isEmpty()) {
            int minIndex = pq.peek().index;
            int minDist = pq.peek().dist;

            pq.poll();

            if(dist[minIndex] != minDist) continue;

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
        long maxDist = 0;

        for (int i = 1; i < dist.length; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }

        System.out.println(maxDist);
    }



}