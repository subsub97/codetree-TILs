import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class Pair {
        long dist,time;

        Pair(long d, long t) {
            dist = d;
            time = t;
        }

        public boolean canRenew(Pair p) {
            // 기존에 있던 값이 갱신되어야 하는 경우
            return this.dist > p.dist || (this.dist == p.dist && this.time > p.time);
        }
    }

    public static final int MAX_STATION = 1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int busCount = Integer.parseInt(st.nextToken());

        Pair[][] graph = new Pair[MAX_STATION + 1][MAX_STATION + 1];

        for (int i = 1; i <= MAX_STATION ; i++) {
            for (int j = 1; j <=  MAX_STATION; j++) {
                // 그래프는 연결된 간선 초기화
                graph[i][j] = new Pair((long)1e17,0);
            }
            graph[i][i] = new Pair(0,0);
        }

        for (int i = 1; i <=  busCount; i++) {
            st = new StringTokenizer(br.readLine());
            int fare = Integer.parseInt(st.nextToken());
            int staionCount = Integer.parseInt(st.nextToken());
            int[] stationInfos = new int[staionCount + 1];

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= staionCount; j++) {
                stationInfos[j] = Integer.parseInt(st.nextToken());
                }

            for (int j = 1; j < staionCount; j++) {
                for (int k = j+1; k <= staionCount; k++) {
                    if (graph[stationInfos[j]][stationInfos[k]].canRenew(new Pair(fare, k - j))) {
                        graph[stationInfos[j]][stationInfos[k]] = new Pair(fare, k - j);
                    }
                }
            }
        }

        Pair[] dist = new Pair[MAX_STATION + 1];

        for (int i = 1; i <= MAX_STATION; i++) {
            dist[i] = new Pair((long) 1e17, 0);
        }

        dist[start] = new Pair(0, 0);
        boolean[] visited = new boolean[MAX_STATION + 1];

        for (int i = 1; i <= MAX_STATION; i++) {
            int minIndex = -1;

            for (int j = 1; j <= MAX_STATION; j++) {
                if(visited[j]) continue;
                if(minIndex == -1 || dist[minIndex].canRenew(dist[j])) {
                    minIndex = j;
                }
            }

            visited[minIndex] = true;
            long minCost = dist[minIndex].dist;
            long minTime = dist[minIndex].time;
            for (int j = 1; j <= MAX_STATION ; j++) {
                long cost = graph[minIndex][j].dist;
                long time = graph[minIndex][j].time;
                Pair newP = new Pair(minCost + cost, minTime + time);
                if (dist[j].canRenew(newP)) {
                    dist[j] = newP;
                }
            }
        }

        if(dist[end].dist == (long)1e17)
            dist[end] = new Pair(-1, -1);

        long minCost = dist[end].dist;
        long minTime = dist[end].time;

        System.out.print(minCost + " " + minTime);
    }
}