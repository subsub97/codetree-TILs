import java.util.*; 
import java.io.*;

public class Main {
    public static final int MAX_LENGTH = 101;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        
        int[] starts = new int[n];
        int[] ends = new int[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            starts[i] = Integer.parseInt(st.nextToken());
            ends[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        
        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                for(int k = j + 1; k < n; k++) {
                    int[] map = new int[MAX_LENGTH];
                    boolean overlap = false;
                    for(int l = 0; l < n; l++) {
                        if(i != l && j != l && k != l) {
                            for(int z = starts[l]; z <= ends[l]; z++) {
                                if(map[z] == 1) {
                                    overlap = true;
                                    break;
                                }
                                map[z] = 1;
                            }
                        }
                    }
                    if(!overlap) {
                        
                        ans++;
                        }
                }
            }
        }
        

        System.out.print(ans);
    }
}