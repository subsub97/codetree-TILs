import java.io.*;
import java.util.*;

class Edge {
    int x,y,z;

    public Edge(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
};

class Node {
    int index, dist;

    public Node(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
};

class Element implements Comparable<Element> {
    int dist, index;

    public Element(int dist, int index) {
        this.dist = dist;
        this.index = index;
    }

    @Override
    public int compareTo(Element e) {
        return this.dist - e.dist; //dist 기준 오름차순 정렬
    }
}
public class Main {
    public static int n,m,k;
    public static ArrayList<Node>[] graph = new ArrayList[20001];
    public static PriorityQueue<Element> pq = new PriorityQueue<>();

    public static int[] dist = new int[20001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine().trim());

        Edge[] edges = new Edge[m+1];

        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i<= m ; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(x,y,w);
            graph[x].add(new Node(y,w));
            graph[y].add(new Node(x,w));
        }

        for(int i = 1; i <= n; i++) {
            dist[i] = (int)1e9;
        }

        dist[k] = 0;

        pq.add(new Element(0,k));

        while(!pq.isEmpty())  {
            int minDist = pq.peek().dist;
            int minIndex = pq.peek().index;
            pq.poll();

            // 같은 정점의 원소가 여러번 들어가는 문제가 발생할 수 있기에 minDist가 최신 dist[minIndex]값과 다르면 패스
            if(minDist != dist[minIndex])
                continue;

            for(int j = 0; j < graph[minIndex].size(); j++) {
                int targetIndex = graph[minIndex].get(j).index;
                int targetDist = graph[minIndex].get(j).dist;

                //현재 위치에서 연결된 간선으로 가는 것이 더 작다면 갱신
                int newDist = dist[minIndex] + targetDist;
                if(dist[targetIndex] > newDist) {
                    // 값 갱신하고 우선순위 큐에 정보 넣기
                    dist[targetIndex] = newDist;
                    pq.add(new Element(newDist, targetIndex));
                }
            }
        }



        for(int i = 1; i <=n; i++) {
            if(dist[i] == (int)1e9)
                bw.write(-1 + "\n");
            else
                bw.write(dist[i] + "\n");
        }

        bw.close();
    }
}