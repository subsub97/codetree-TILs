import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int N=sc.nextInt();
        int [] first=new int[3];
        int [] second=new int[3];
        for (int j=0; j<3; j++){
            first[j]=sc.nextInt();
        }
        for (int j=0; j<3; j++){
            second[j]=sc.nextInt();
        }

        if (N<=5){
            System.out.println(N*N*N);
            return;
        }

        int[][] first_can = new int[3][5];
        int[][] second_can = new int[3][5];

        for (int i=0; i<3; i++){
            for (int k=-2; k<3; k++) {
                if (first[i] + k <= 0) {
                    first_can[i][k + 2] = N + first[i] + k;
                }
                else{
                    first_can[i][k+2]=first[i]+k;
                    }
                if(second[i]+k<=0){
                    second_can[i][k+2]=N+first[i]+k;
                }
                else{
                    second_can[i][k+2]=second[i]+k;
                }
            }
        }
        int cnt=0;
        int sum=1;
        for(int i=0; i<3; i++){
            for (int j=0; j<5; j++){
                for (int k=0; k<5; k++){
                    if(first_can[i][j]==second_can[i][k]){
                        cnt+=1;
                    }
                }
            }
            if(cnt!=0) {
                sum *= cnt;
            }
            cnt=0;
        }
        System.out.println(250-sum);

    }
}