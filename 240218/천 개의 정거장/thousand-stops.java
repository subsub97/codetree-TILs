import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_STATION = 1000;

    public static class Element implements Comparable<Element> {
        int index;
        int dist;
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
        //최소 비용과 최소 시간 구하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int startA = Integer.parseInt(st.nextToken());
        int endB = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        ArrayList<Element>[] graph = new ArrayList[MAX_STATION + 1];

        for (int i = 1; i <= MAX_STATION; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            //1번 버스부터 n번 버스까지 노선 추가
            st = new StringTokenizer(br.readLine());

            int fare = Integer.parseInt(st.nextToken());
            int stationCount = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] stationsInfo = new int[stationCount];

            for (int j = 0; j < stationCount; j++) {
                stationsInfo[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < stationCount-1; j++) {
                //버스 노선별 연결된 정거장 추가
                for (int k = j+1; k < stationCount; k++) {
                    graph[stationsInfo[j]].add(new Element(fare, stationsInfo[k]));
                }

            }
        }

        int[] dist = new int[MAX_STATION + 1];
        int[] path = new int[MAX_STATION + 1];

        for (int i = 0; i <= MAX_STATION ; i++) {
            dist[i] = 2_000_000_000;
        }
        dist[startA] = 0; //시작지점 비용 0으로 선언

        PriorityQueue<Element> pq = new PriorityQueue<>();
        pq.add(new Element(0, startA));

        while(!pq.isEmpty()) {
            int minIndex = pq.peek().index;
            int minDist = pq.peek().dist;
            pq.poll();

            if (minDist != dist[minIndex])
                continue;

            for (int i = 0; i < graph[minIndex].size(); i++) {
                //다익스트라 ㄱ
                int targetDist = graph[minIndex].get(i).dist;
                int targetIndex = graph[minIndex].get(i).index;
                int newDist = minDist + targetDist;
                if (dist[targetIndex] > newDist) {
                    dist[targetIndex] = newDist;
                    path[targetIndex] = minIndex;
                    pq.add(new Element(newDist, targetIndex));
                }
            }
        }
        // 도착점부터 시작점으로 역 순회하면서 최단 경로 찾기
        int x = endB;
        Stack<Integer> minPaths = new Stack<>();
        int minCnt = 1;

        if(dist[endB] == 2_000_000_000) {
            System.out.println("-1 -1");
        }

        while(x != startA){
            minPaths.push(x);
            minCnt++;
            x = path[x];
        }

        System.out.print(dist[endB] + " ");
        System.out.println(minCnt);


    }
}