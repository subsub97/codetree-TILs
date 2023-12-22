import java.util.Scanner;
import java.util.Arrays;

// 학생들의 정보를 나타내는 클래스 선언
class Student implements Comparable<Student> {
    String name;
    int height, weight;

    public Student(String name, int height, int weight){
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Student student) { // 키를 기준 오름차순 정렬합니다.
        return this.height - student.height;
    }
};

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 변수 선언 및 입력:
        int n = sc.nextInt();
        Student[] students = new Student[n];
        for(int i = 0; i < n; i++) {
            String name = sc.next();
            int height = sc.nextInt();
            int weight = sc.nextInt();

            // Student 객체를 생성해 리스트에 추가합니다.
            students[i] = new Student(name, height, weight);
        }

        // custom comparator를 활용한 정렬
        Arrays.sort(students);

        // 결과를 출력합니다.
        for (int i = 0; i < n; i++){
            System.out.print(students[i].name + " ");
            System.out.print(students[i].height + " ");
            System.out.println(students[i].weight);
        }
    }
}