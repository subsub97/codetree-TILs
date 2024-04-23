import java.util.*;

public class Main {
    static class User{

        String userId;
        int lv;

        User(String userId, int lv){
            this.userId = userId;
            this.lv = lv;
        }
        User(){

        }


    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User user1 = new User("codetree",10);
        User user2 = new User();
        user2.userId = sc.next();
        user2.lv = sc.nextInt();

        System.out.println("user " + user1.userId + " lv " +user1.lv);
        System.out.println("user " + user2.userId + " lv " +user2.lv);
    }
}