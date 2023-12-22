import java.util.*;
import java.io.*;

    class Student implements Comparable<Student> {
        public String name;
        public int weight;
        public int height;

        Student(String name, int height, int weight) {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Student[] students = new Student[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            students[i] = new Student(name,height,weight);
        }

        //Arrays.sort(students, (a,b) -> a.height - b.height);
        Arrays.sort(students);

        for(Student s : students) {
            System.out.println(s.name + " " + s.height + " " + s.weight);
        }
    }
}