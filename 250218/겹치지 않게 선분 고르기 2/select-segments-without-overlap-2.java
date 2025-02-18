import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int maxLen = 0;
    static ArrayList<Integer>[] lines = new ArrayList[1001];
    static int[] selected;
    static HashSet<Integer> set = new HashSet<>();
    static int answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            maxLen = Math.max(Math.max(a,maxLen),b);
            
            if(lines[a] == null) {
                lines[a] = new ArrayList<>();
                lines[a].add(b);
                set.add(a);
            }
            else {
                lines[a].add(b);
            }
        }

        selected = new int[set.size()];
        
        dfs(0,1);
        
        System.out.print(answer);

    }

    private static void dfs(int depth, int idx) {
        if(depth == set.size()) {
            //최장수열 체크
            answer = Math.max(lis(),answer);
            return;
        }

        for(int i = idx; i <= maxLen; i++) {
            if(lines[i] == null) continue;
            //있다면 최장수열 후보만들기
            for(int j = 0; j < lines[i].size(); j++) {
                selected[depth] = lines[i].get(j);
                dfs(depth + 1, i + 1);
                selected[depth] = 0;
            }
        }
    }
    
    private static int lis() {
    	int[] dp = new int[set.size()];
    	int ans = 1;
    	dp[0] = 1;
    	
    	for(int i = 1; i < set.size(); i++) {
    		for(int j = i; j >= 0; j--) {
    			if(j == i) {
    				dp[i] = 1;
    				continue;
    			}
    			if(selected[i] > selected[j]) {
    				dp[i] = Math.max(dp[i],dp[j] + 1);
    				ans = Math.max(ans, dp[i]);
    			}
    		}
    	}
    	
    	return ans;
    }
}