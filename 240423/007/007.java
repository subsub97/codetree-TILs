import java.util.*;

public class Main {

    static class Codetree{
        String secretCode, meetingPoint;
        int time;

       
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Codetree codetree = new Codetree();
        codetree.secretCode = sc.next();
        codetree.meetingPoint = sc.next();
        codetree.time = sc.nextInt();
        System.out.println("secret code : " + codetree.secretCode);
        System.out.println("meeting point : " + codetree.meetingPoint);
        System.out.println("time : " + codetree.time);
    }
}