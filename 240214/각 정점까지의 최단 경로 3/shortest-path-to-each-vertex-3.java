import java.io.*;
import java.util.*;

public class Main {
    public static Edge[] edges;
    public static int n, m;
    public static int[] dist;
    public static boolean[] visited;
    public static int[][] graph;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n];
        visited = new boolean[n];
        graph = new int[n][n];
        edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()) -1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
            graph[edges[i].x][edges[i].y] = edges[i].w; // 그래프 생성
        }

        for(int i = 0; i < n; i++) {
            dist[i] = (int)1e9;
            if(i == 0) dist[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            //각 정점을 방문해야하기에 정점 개수 만큼
            int minIndex = -1;
            for (int j = 0; j < n; j++) {
                if(visited[j]) continue;

                if(minIndex == -1 || dist[minIndex] > dist[j]){
                    minIndex = j;
                }
            }

            visited[minIndex] = true;

            for (int j = 0; j < n; j++) {
                if(graph[minIndex][j] == 0) continue;
                dist[j] = Math.min(dist[j], dist[minIndex] + graph[minIndex][j]);
            }
        }

        for (int i = 1; i < n; i++) {
            System.out.println(dist[i]);
        }
    }

    public static class Edge {
        int x, y, w;

        Edge(int x,int y , int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}