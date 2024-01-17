import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[] seatArr = br.readLine().toCharArray();

        int[] seatDistance = new int[n];
        int idx = 0;
        boolean check = false;
        int preLocation = 0;


        for (int i = 0; i < n; i++) {

            if(seatArr[i] == '1'){
                if(!check) {
                    check = true;
                }
                else{
                    seatDistance[idx++] = i - preLocation;
                    preLocation = i;

                }
            }
        }
        int maxDistance = 0;
        int minDistance = 100000000;

        for (int i = 0; i < idx; i++) {
            maxDistance = Math.max(seatDistance[i], maxDistance);
            minDistance = Math.min(seatDistance[i], minDistance);
        }

        System.out.println(Math.min(minDistance,maxDistance/2));
    }
}