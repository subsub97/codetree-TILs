import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a= sc.next();
        String b =sc.next();
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        Arrays.sort(aArr);
        Arrays.sort(bArr);
        if(aArr.length!=bArr.length){
            System.out.println("No");
            return;
        } else{
            for(int i=0; i<aArr.length; i++){
                if(aArr[i]!=bArr[i]){
                    System.out.println("No");
            return;
                }
            }
        }
        System.out.println("Yes");

    }

}