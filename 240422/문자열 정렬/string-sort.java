import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] alphabet = str.toCharArray();
        Arrays.sort(alphabet);
        String sortedStr = new String(alphabet);
        System.out.println(sortedStr);
    }

}