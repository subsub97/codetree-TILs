import java.util.*;
public class Main {

    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        
        String strs = sc.nextLine();
        String[] str = strs.split(" ");

        int [] iarr = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
        System.out.println(maxNum(n,iarr));
        
    }

    public static int maxNum(int n, int[] iarr){
        
        if(n<=1){
            return max;
        }
        else {
            max = max<iarr[n-1]? iarr[n-1]: max ;
            return maxNum(n-2,iarr);
        }
       
    }
}