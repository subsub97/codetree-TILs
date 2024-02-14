import java.io.*;
import java.util.*; 
class Node implements Comparable<Node>{
    int vertex, dist; 

    public Node(int vertex, int dist){
        this.vertex = vertex;
        this.dist = dist; 
    }

    @Override
    public int compareTo(Node o){
        return this.dist - o.dist; 
    }
    @Override
    public String toString(){
        return this.vertex+":"+this.dist; 
    }

}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens; 

    static int[] dist; 
    static ArrayList<Node>[] graph; 

    static int n; 
    static int m;     
    static int k; 


    static final int MAX = (int)1e9; 

    static void init() throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        n = Integer.valueOf(tokens.nextToken());
        m = Integer.valueOf(tokens.nextToken());

        k = Integer.valueOf(buffer.readLine()); 


        graph = new ArrayList[n+1];
        dist = new int[n+1]; 

        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>(); 
        }

        Arrays.fill(dist, MAX); 

        for(int i=0; i<m; i++){
            tokens = new StringTokenizer(buffer.readLine()); 
            int node1 = Integer.valueOf(tokens.nextToken());
            int node2 = Integer.valueOf(tokens.nextToken());
            int w = Integer.valueOf(tokens.nextToken());

            graph[node1].add(new Node(node2, w));
            graph[node2].add(new Node(node1, w));
        }
    }

    public static void main(String[] args)throws IOException{
        init(); 

        PriorityQueue<Node> pq = new PriorityQueue<>(); 

        pq.add(new Node(k, 0));
        dist[k] = 0;


        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.vertex]!=cur.dist) continue; 
            for(Node target : graph[cur.vertex]){
                int newDist = dist[cur.vertex] + target.dist;

                if(dist[target.vertex]>newDist){
                    dist[target.vertex] = newDist; 
                    pq.add(new Node(target.vertex, newDist));
                }
            }
        }

        StringBuilder sb = new StringBuilder(); 

        for(int end=1; end<=n; end++){
 

            if(dist[end]==MAX){
                sb.append(-1).append("\n");
            }else{
                sb.append(dist[end]).append("\n");
            }
        }

        System.out.println(sb); 


    }
}