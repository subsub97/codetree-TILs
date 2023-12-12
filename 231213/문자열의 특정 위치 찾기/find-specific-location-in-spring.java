import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        String a = sc.next();
        int flag = 0 ;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == a.charAt(0)) {
                System.out.println((i));
                flag =1;
                break;
            }
        }

        if(flag == 0) {
            System.out.print("No");
        }
    }
}