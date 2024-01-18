import java.io.*;

public class Main {
    public static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        String seatInfo = br.readLine();

        for (int i = 0; i < n; i++) {
            // 좌석 지정
            if(seatInfo.charAt(i) == '1') {
                continue;
            }
            else{
                String temp = seatInfo.substring(0,i) + "1" + seatInfo.substring(i+1);
                findSeat(temp);
            }
        }
        System.out.println(ans);
    }

    public static void findSeat(String seatInfo) {
        int preDiff = 1001;

        for (int i = 0; i < seatInfo.length(); i++) {
            if(seatInfo.charAt(i) == '1') {
                int diff = 1;
                for (int j = i+1; j < seatInfo.length(); j++) {
                    if(seatInfo.charAt(j) == '1') {
                        preDiff = Math.min(diff,preDiff);
                        break;
                    }
                    diff++;
                }
            }
        }
        ans = Math.max(ans,preDiff);
    }
}