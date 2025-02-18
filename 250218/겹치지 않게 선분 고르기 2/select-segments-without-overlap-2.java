import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Pair> p = new ArrayList<>();
    static Pair[] selected;
    static boolean[] used;
    static int len;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            p.add(new Pair(a,b));
        }

        p.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.s == p2.s) {
                    return Integer.compare(p1.e, p2.e);
                }
                return Integer.compare(p1.s, p2.s);
            }
        });

        used = new boolean[1001];
        selected = new Pair[1001];

        len = 0;

        for(Pair p : p) {
            if(used[p.s]) continue;
            used[p.s] = true;
            selected[len++] = p;
        }

        //LIS를 어떻게 체크하지?
        // 앞에 있는 수직선의 끝보다 시작점이 큰 것만 골라야함
        dp = new int[len];
        Arrays.fill(dp, 1);
        int ans = 1;
        for(int i = 1; i < len; i++) {
            for(int j = i; j >= 0; j--) {
                if(selected[i].s > selected[j].e) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }

        System.out.println(ans);
    }

    private static class Pair {
        int s;
        int e;

        Pair(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}
