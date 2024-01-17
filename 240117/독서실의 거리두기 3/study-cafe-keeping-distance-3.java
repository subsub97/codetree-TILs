import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[] seatArr = br.readLine().toCharArray();

        int[] seatDistance = new int[n];
        int idx = 0;
        boolean check = false;
        int preLocation = 0;
        int maxDistance = 0 ;

        for (int i = 0; i < n; i++) {

            if(seatArr[i] == '1'){
                if(!check) {
                    check = true;
                }
                else{
                    seatDistance[idx++] = i - preLocation;
                    maxDistance = Math.max(i-preLocation, maxDistance);
                    preLocation = i;

                }
            }
        }
        System.out.println(maxDistance/2);
    }
}